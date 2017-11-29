package com.example.shaur.nimblenavigationdrawer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Download extends AppCompatActivity {

    Button button;
    ImageView mImageView;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://nimble-1506358667425.appspot.com/").child("43730.png");
    File storagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mImageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button_download);

        storagePath = new File(Environment.getExternalStorageDirectory(), "nimble");

        if(!storagePath.exists()) {
            storagePath.mkdirs();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final File localFile;
                try {
                    localFile = File.createTempFile("images", "jpg");

                    //final File localFile = new File(storagePath,"jpg");
                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            mImageView.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    });
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
