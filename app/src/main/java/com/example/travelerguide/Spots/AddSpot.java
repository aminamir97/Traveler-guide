package com.example.travelerguide.Spots;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.travelerguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.*;

import java.io.IOException;
import java.util.UUID;

public class AddSpot extends AppCompatActivity {
    ImageView image;
    Uri filepath;
    int pickImgReuest = 71;
    StorageReference storageReference;
    FirebaseStorage storage;
    EditText spotname,spotcity;
    RatingBar ratingBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference ref;
    String urlDownload="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spot);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        image = findViewById(R.id.imagePut);

        ratingBar = findViewById(R.id.spotRate);
        spotname=findViewById(R.id.spotName);
        spotcity = findViewById(R.id.spotCity);


    }

    public void BrowseImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select photo"),pickImgReuest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == pickImgReuest && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                image.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

    public String uploadimage()
    {
         ref = storageReference.child("images/"+ UUID.randomUUID().toString());
        ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                         urlDownload= uri.toString();

                    }
                });
                Log.e("mineimg",urlDownload) ;

            }
        });







        return urlDownload;

    }

    public void uploadspot(View view) {
        String imageURl = uploadimage();
        SpotModel newspot = new SpotModel(
                spotcity.getText().toString() ,
                spotname.getText().toString() ,
                imageURl ,
                ratingBar.getRating()+"" ,
                "comments");

        databaseReference.push().setValue(newspot);


    }
}
