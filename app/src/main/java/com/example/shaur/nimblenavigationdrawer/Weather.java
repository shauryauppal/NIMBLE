package com.example.shaur.nimblenavigationdrawer;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather extends AppCompatActivity {

    EditText city;
    Button button;
    TextView result;
    ImageView weathercurrent;
    //http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=b250e144fe2ee939856b8afb84e83c6e

    String baseURL="http://api.openweathermap.org/data/2.5/weather?q=";
    String API="&appid=b250e144fe2ee939856b8afb84e83c6e";

    int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        city = (EditText) findViewById(R.id.city);
        button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.result);
        weathercurrent = (ImageView) findViewById(R.id.weathercurrent);

        final String []myImage={
                "https://scontent.fdel1-2.fna.fbcdn.net/v/t1.0-9/13119000_10154308228282518_3104131913688045709_n.png?oh=f3bdbc025dd7fde440446e0546cc9336&oe=5A991320"
                ,"http://www.srh.noaa.gov/jetstream/clouds/cloudwise/images/cu.jpg"
                ,"www.haze.gov.sg/App_Theme/images/widget/banner.jpg"
                ,"https://vignette.wikia.nocookie.net/gameknight999/images/f/f5/Mist.jpg/revision/latest/scale-to-width-down/640?cb=20170112011652"
                ,"http://www.srh.noaa.gov/jetstream/clouds/cloudwise/images/cu.jpg"
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(city.getText().toString()!=null || city.getText().toString()!="")
                {
                    String myURL=baseURL+city.getText().toString()+API;
                    Log.i("URL","url is"+myURL);


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,

                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    Log.i("JSON", "jsonobject" + jsonObject);
                                    String myWeather=null;
                                    try {
                                        String info = jsonObject.getString("weather");

                                        JSONArray ar = new JSONArray(info);

                                        for (int i = 0; i <ar.length();i++)
                                        {
                                            JSONObject parObj = ar.getJSONObject(i);
                                            Log.i("main","main:"+parObj.getString("main"));
                                            myWeather= parObj.getString("main");
                                            //result.setText(myWeather);
                                        }

                                        String temperature = jsonObject.getJSONObject("main").getString("temp");

                                        Double x = Double.parseDouble(temperature);
                                        x-=273.15;
                                        Log.i("temp","is "+x);
                                        String wresult= myWeather+" with "+x+ " Degree Celcius";
                                        result.setText(wresult);

//                                        if(myWeather=="Mist")
//                                        {
//                                            k=3;
//                                        }
//                                        else if(myWeather=="Sunny")
//                                        {
//                                            k=0;
//                                        }
//                                        else if(myWeather=="Rainy")
//                                        {
//                                            k=1;
//                                        }
//                                        else if(myWeather.equals("Haze") || myWeather=="Haze")
//                                        {
//                                            k=2;
//                                        }
//                                        else if(myWeather=="clouds")
//                                        {
//                                            k=4;
//                                        }
//                                        else k=-1;

                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("Error","VolleyError"+error);
                                }
                            }
                    );

                    MySingleton.getInstance(Weather.this).addToRequestQueue(jsonObjectRequest);



//
//                        ImageRequest imageRequest = new ImageRequest(myImage[k],
//                                new Response.Listener<Bitmap>() {
//                                    @Override
//                                    public void onResponse(Bitmap response) {
//                                        weathercurrent.setImageBitmap(response);
//                                    }
//                                }, 0, 0, null,
//                                new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        Log.i("ERROR", "Fetch went wrong");
//                                    }
//                                }
//                        );
//                        MySingleton.getInstance(Weather.this).addToRequestQueue(imageRequest);
                    }


                else
                {
                    Toast.makeText(Weather.this, "Please Enter a City", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}