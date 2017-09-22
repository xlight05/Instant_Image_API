package com.example.asus.fashionista;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CAM_REQUEST = 1313;

    //final TextView textView = (TextView)findViewById(R.id.textView2) ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.camera);
        fab.setOnClickListener(new btnTakePhotoClicker());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //TextView textView = (TextView)findViewById(R.id.textView2) ;
        int id = item.getItemId();

        if (id == R.id.my_collection) {
            //textView.setText("My Collection");
            // Handle the camera action
        } else if (id == R.id.i_sense) {
            Intent goToNextActivity = new Intent(getApplicationContext(),isense.class);
            startActivity(goToNextActivity);
        } else if (id == R.id.my_feed) {

        } else if (id == R.id.search) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.about) {

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Bitmap bitmap = null;
        //String encodedString = null;
        if (requestCode == CAM_REQUEST) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //imgTakenPic.setImageBitmap(bitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedString = "data:image/png;base64,"+Base64.encodeToString(b, Base64.URL_SAFE | Base64.NO_WRAP);
            final EditText txt3 = (EditText)findViewById(R.id.editText3);
            txt3.setText(encodedString);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Image Sent",
                    Toast.LENGTH_SHORT);

            toast.show();

            RequestQueue queue = Volley.newRequestQueue(this);
            final String URL = "https://protected-sea-84907.herokuapp.com/captureImg";
// Post params to be sent to the server
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("imgString", encodedString);
            JSONObject obj = new JSONObject(params);

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,URL, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                VolleyLog.v("Response:%n %s", response.toString(4));
                                Intent goToNextActivity = new Intent(getApplicationContext(),CardViewActivity.class);
                                String result = response.toString();
                                //String result = response;
                                txt3.setText(result);
                                goToNextActivity.putExtra("json",result);
                                startActivity(goToNextActivity);


                            } catch (JSONException e) {
                                e.printStackTrace();
                                txt3.setText("error");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Intent goToNextActivity = new Intent(getApplicationContext(),Test_Json.class);
                    startActivity(goToNextActivity);
                }
            });
            int socketTimeout = 3000;//30 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            req.setRetryPolicy(policy);

// add the request object to the queue to be executed
            queue.add(req);
/*
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://httpbin.org/post";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            //Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            //Log.d("Error.Response", response);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", "Alif");

                    return params;
                }
            };
            queue.add(postRequest);
            */
            /*
            try {
                URLConnection connection = new URL("https://protected-sea-84907.herokuapp.com/captureImg").openConnection();
                Log.d("myTag", "This is my message");
                txt3.setText("this is a test");
                    // Http Method becomes POST
                connection.setDoOutput(true);
                JSONObject obj = new JSONObject();
                obj.put("imgString",encodedString);
                // Encode according to application/x-www-form-urlencoded specification
                String content =obj.toString() ;
                connection.setRequestProperty("Content-Type", "application/json");

                // Try this should be the length of you content.
                // it is not neccessary equal to 48.
                // content.getBytes().length is not neccessarily equal to content.length() if the String contains non ASCII characters.
                connection.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length));

                // Write body
                OutputStream output = connection.getOutputStream();
                output.write(content.getBytes());
                txt3.setText(output.toString());
                output.close();
            }catch (MalformedURLException e){
                txt3.setText("error");
            }catch (JSONException ee){
                txt3.setText("error1");
            }catch (IOException eee){
                txt3.setText("error3");
            }catch (Exception eeee){
                eeee.printStackTrace();
            }


            //EditText txt = (EditText)findViewById(R.id.editText4) ;
            //txt.setText(encodedString);
*/
        }

        //convert bitmap to base 64
        /*
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        encodedString = Base64.encodeToString(b, Base64.DEFAULT);
        EditText txt = (EditText)findViewById(R.id.editText4) ;
        txt.setText(encodedString);
        */


    }

    class btnTakePhotoClicker implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAM_REQUEST);
        }
    }
}
