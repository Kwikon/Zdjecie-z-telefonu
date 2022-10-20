package com.example.zdjcieztelefonu;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
//
public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    Button buttonTurnLeft90;
    Button buttonTurnRight90;
    float x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Mikołaj Zabiegałowski 4pr T5");
        imageView = findViewById(R.id.photoCamera);
        button = findViewById(R.id.photoButton);
        buttonTurnLeft90 = findViewById(R.id.buttonTurnLeft90);
        buttonTurnRight90 = findViewById(R.id.buttonTurnRight90);


        //Request for camera access
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            },100);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

        //Button rotate
        buttonTurnLeft90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = x - 90;
                imageView.setRotation(x);
            }
        });
        buttonTurnRight90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = x + 90;
                imageView.setRotation(x);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

        }
    }
}