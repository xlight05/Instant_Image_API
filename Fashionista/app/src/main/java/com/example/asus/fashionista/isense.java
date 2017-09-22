package com.example.asus.fashionista;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class isense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isense);
        getSupportActionBar().hide();
        ImageButton female = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton male = (ImageButton) findViewById(R.id.imageButton2);
        female.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_female.class);
                goToNextActivity.putExtra("gender","female");
                startActivity(goToNextActivity);
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_female.class);
                goToNextActivity.putExtra("gender","male");
                startActivity(goToNextActivity);
            }
        });

    }
}
