package com.example.asus.fashionista;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class isense_female extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isense_female);

        getSupportActionBar().hide();
        final String gender = getIntent().getStringExtra("gender");
        ImageButton inverted_t = (ImageButton) findViewById(R.id.inverted_t);
        ImageButton triangle = (ImageButton) findViewById(R.id.triangle);
        ImageButton rectangle = (ImageButton) findViewById(R.id.rectangle);
        ImageButton rhomboid = (ImageButton) findViewById(R.id.rhomboid);
        ImageButton oval = (ImageButton) findViewById(R.id.oval);

        inverted_t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_skin.class);
                goToNextActivity.putExtra("gender",gender);
                goToNextActivity.putExtra("body_type","Inverted Triangle");
                startActivity(goToNextActivity);
            }
        });
        triangle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_skin.class);
                goToNextActivity.putExtra("gender",gender);
                goToNextActivity.putExtra("body_type","Triangle");
                startActivity(goToNextActivity);
            }
        });
        rectangle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_skin.class);
                goToNextActivity.putExtra("gender",gender);
                goToNextActivity.putExtra("body_type","Rectangle");
                startActivity(goToNextActivity);
            }
        });
        rhomboid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_skin.class);
                goToNextActivity.putExtra("gender",gender);
                goToNextActivity.putExtra("body_type","Rhomboid");
                startActivity(goToNextActivity);
            }
        });
        oval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(),isense_skin.class);
                goToNextActivity.putExtra("gender",gender);
                goToNextActivity.putExtra("body_type","Oval");
                startActivity(goToNextActivity);
            }
        });
    }
}
