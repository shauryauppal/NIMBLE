package com.example.shaur.nimblenavigationdrawer;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.Random;

public class CampusView extends AppCompatActivity {

    ImageView image;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_view);
        image = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        final String [] myURL ={"http://www.jiit.ac.in/sites/default/files/1.jpg"
                ,"https://schoolbook.getmyuni.com/assets/images/rev_img/23570__4744/1476814711JIIT_Auditorium[1].jpg"
                ,"https://scontent.fdel1-2.fna.fbcdn.net/v/t1.0-9/12143329_1087937001231528_8395845898978489779_n.jpg?oh=2721748567e94796541e02b042d4adb8&oe=5A8E80BE"
                ,"https://scontent.fdel1-2.fna.fbcdn.net/v/t1.0-9/12143329_1087937001231528_8395845898978489779_n.jpg?oh=2721748567e94796541e02b042d4adb8&oe=5A8E80BE"
                ,"https://scontent.fdel1-2.fna.fbcdn.net/v/t1.0-9/12088427_1087937367898158_3768584265769631222_n.jpg?oh=4052d5978dc322039544d1374551558e&oe=5AD625D3"
                ,"http://2.bp.blogspot.com/_VuI3_-K7ACs/TCoyuGmpEMI/AAAAAAAAAHo/VYWqmzfrwCY/S748/800px-JIIT-inside.jpg"
                ,"https://scontent.fdel1-2.fna.fbcdn.net/v/t31.0-8/17546721_1873461246268812_8504364225856597288_o.jpg?oh=7b533cd4768dbb8361b8e2ba93e41c14&oe=5AA34429"
                ,"http://www.jiit.ac.in/sites/default/files/organization_0.jpg"
                ,"http://www.jiit.ac.in/sites/default/files/1_3.jpg"
                ,"http://www.jiit.ac.in/sites/default/files/5_0.jpg"
                ,"http://www.jiit.ac.in/jiit/ncssi-9/images/jaypee12.JPG"
                ,"http://www.jiit.ac.in/jiit/ncssi-9/images/dtu.gif"
                ,"https://images.static-collegedunia.com/public/college_data/images/campusimage/1440573662s1.jpg"
                ,"https://images.static-collegedunia.com/public/college_data/images/campusimage/1440573662s1.jpg"
                ,"https://images.static-collegedunia.com/public/college_data/images/campusimage/1440573941p4.jpg"
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random ran = new Random();
                int randomnumber = ran.nextInt(15);
                Log.i("Images number","is"+randomnumber);
                ImageRequest imageRequest = new ImageRequest(myURL[randomnumber],
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                image.setImageBitmap(response);
                            }
                        }, 0, 0, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("ERROR","Fetch went wrong");
                            }
                        }
                );
                MySingleton.getInstance(CampusView.this).addToRequestQueue(imageRequest);
            }
        });
    }
}
