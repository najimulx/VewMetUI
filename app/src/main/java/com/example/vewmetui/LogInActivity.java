package com.example.vewmetui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {


    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private EditText et_nickname;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    BottomSheetDialog bottomSheetDialog;
    public Bitmap profilePhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        bottomSheetDialog = new BottomSheetDialog(this);
        imageView = findViewById(R.id.iv_logIn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.setContentView(R.layout.image_selector_login);
                bottomSheetDialog.show();
                photoSelectAction();
            }
        });
        et_nickname = findViewById(R.id.et_logIn);

    }


    public void photoSelectAction(){
        ImageView cameraOpen = bottomSheetDialog.findViewById(R.id.image_selector_0);
        cameraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });




    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            profilePhoto = photo;
        }
    }


    public void finalLogInAction(View view){
        Bitmap photo = profilePhoto; // photo is your selected bitmap photo
        String nickName ; // nickName is the user given nickname
        if(!et_nickname.getText().toString().equals("")||!et_nickname.getText().toString().equals(null)){
            nickName = et_nickname.getText().toString();
        }else{Toast.makeText(LogInActivity.this,"Enter Nickname",Toast.LENGTH_SHORT).show();}


        // Here Specify What Happen after Join Button is Pressed...
        boolean LoggedIn = true;// change to false if log in fails





        if(LoggedIn){
            // If Logged In success full
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
