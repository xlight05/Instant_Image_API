package com.example.asus.fashionista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class isense_skin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isense_skin);
        getSupportActionBar().hide();
        final String body_type = getIntent().getStringExtra("body_type");

        ImageButton btn01 = (ImageButton)findViewById(R.id.imageButton4);
        ImageButton btn02 = (ImageButton)findViewById(R.id.imageButton5);
        ImageButton btn03 = (ImageButton)findViewById(R.id.imageButton6);
        ImageButton btn04 = (ImageButton)findViewById(R.id.imageButton7);
        ImageButton btn05 = (ImageButton)findViewById(R.id.imageButton8);

        btn01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),Test_Json.class);
                startActivity(goToNextActivity);
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),Test_Json.class);
                startActivity(goToNextActivity);
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),Test_Json.class);
                startActivity(goToNextActivity);
            }
        });
        btn04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),Test_Json.class);
                startActivity(goToNextActivity);
            }
        });
        btn05.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),Test_Json.class);
                startActivity(goToNextActivity);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goToNextActivity = new Intent(getApplicationContext(),Drawer.class);
        startActivity(goToNextActivity);
    }
}
