package com.example.asus.fashionista;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import android.util.Base64;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;


public class CameraActivity extends AppCompatActivity {
    Button btnpic;
    ImageView imgTakenPic;
    private static final int CAM_REQUEST = 1313;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        btnpic = (Button) findViewById(R.id.button2);
        imgTakenPic = (ImageView) findViewById(R.id.imageView2);
        btnpic.setOnClickListener(new btnTakePhotoClicker());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Bitmap bitmap = null;
        //String encodedString = null;
        if (requestCode == CAM_REQUEST) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTakenPic.setImageBitmap(bitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedString = Base64.encodeToString(b, Base64.DEFAULT);
            EditText txt = (EditText)findViewById(R.id.editText4) ;
            txt.setText(encodedString);

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
