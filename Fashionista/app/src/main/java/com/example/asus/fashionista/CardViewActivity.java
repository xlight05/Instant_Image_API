package com.example.asus.fashionista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CardViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";


    int length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        getSupportActionBar().hide();
        String in ="";
        JSONArray jsonArray = null;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        if(getIntent()!=null) {
             in = getIntent().getStringExtra("json");
        }
        try {
           jsonArray = new JSONArray(in);
        }catch (Exception e){

        }
        Random r = new Random(100);
        int num = r.nextInt(2);
        int[] arr = {0,1,2,3,4,5,6};
        if(num == 0){
            arr[0] =3;
            arr[3] =0;
            arr[2] =6;
            arr[6] =2;
        }

        length = jsonArray.length();
        for(int i = 0 ; i < length; i++){
            Drawable drawable = null;
            JSONObject obj = null;
            String string;
            String string1;
            try {
                obj = jsonArray.getJSONObject(arr[i]);
                string = obj.getString("product_name");
                string1= obj.getString("product_price");

                DataObject dobj = new DataObject(string,string1);
                ((MyRecyclerViewAdapter) mAdapter).addItem(dobj, i);
            }catch (Exception e){

            }
        }
        /*
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
*/

// Code to Add an item with default animation
//((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);
// Code to remove an item with default animation
//((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                String display = "Card " + position + " clicked";
                Toast toast = Toast.makeText(getApplicationContext(),
                        display,
                        Toast.LENGTH_SHORT);

                toast.show();

            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
       ArrayList results = new ArrayList<DataObject>();
       for (int index = 0; index < length; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index, "Secondary " + index);
            results.add(index, obj);
        }
        return results;
   }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goToNextActivity = new Intent(getApplicationContext(),Drawer.class);
        startActivity(goToNextActivity);
    }
}

