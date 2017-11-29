package com.example.shaur.nimblenavigationdrawer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference UserRef = rootRef.child("User");
    DatabaseReference notref = rootRef.child("notification");

    boolean doubleBackToExitPressedOnce = false;
    Button year1,year2,year3,year4,fileserver;
    String Username;
    @Override
    protected void onStart() {
        super.onStart();
                /*
        This part of code prompts the user to connect to internet connection
         */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //get details on currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()){
            //Do-nothing
        }
        else{
            startActivity(new Intent(MainActivity.this,no_internet.class));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyFireBaseMessagingService message = new MyFireBaseMessagingService();
        notref.setValue(message.getMessage());

        final HashMap<String,String> userMap = new HashMap<String, String>();
        userMap.put("TokenID",SharedPreManager.getInstance(MainActivity.this).getToken());
        UserRef.child(mAuth.getCurrentUser().getUid()).setValue(userMap);

        Username="Anonymous";
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.chatfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading Chats,press again after few seconds", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(Username!=null) {
                    Intent intt = new Intent();
                    intt.setClass(MainActivity.this, ChatSupport.class);
                    intt.putExtra("Username", Username);
                    intt.putExtra("Uid", mAuth.getCurrentUser().getUid());
                    startActivity(intt);
                }

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        This part of code prompts the user to connect to internet connection
         */
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //get details on currently active default data network
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()){
            //Do-nothing
        }
        else{
            startActivity(new Intent(MainActivity.this,no_internet.class));
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }





        year1 = (Button) findViewById(R.id.year1);
        year2 = (Button) findViewById(R.id.year2);
        year3 = (Button) findViewById(R.id.year3);
        year4 = (Button) findViewById(R.id.year4);
        fileserver = (Button) findViewById(R.id.fileserver);

        /*
        TODO: ADD fileserver google drive api
         */
        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,year_main_fragment.class);
                intent.putExtra(Intent.EXTRA_TEXT,"0");
                startActivity(intent);
            }
        });
        year2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,year_main_fragment.class);
                intent.putExtra(Intent.EXTRA_TEXT,"1");
                startActivity(intent);
            }
        });

        year3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,year_main_fragment.class);
                intent.putExtra(Intent.EXTRA_TEXT,"2");
                startActivity(intent);
            }
        });

        year4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,year_main_fragment.class);
                intent.putExtra(Intent.EXTRA_TEXT,"3");
                startActivity(intent);
            }
        });

        fileserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Download.class));
            }
        });

    }


    /**
     * This part of code is for double back press to exit app
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                if (getFragmentManager().getBackStackEntryCount() ==0) {
                    finishAffinity();
                    System.exit(0);
                } else {
                    getFragmentManager().popBackStackImmediate();
                }
                return;
            }

            if (getFragmentManager().getBackStackEntryCount() ==0) {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                getFragmentManager().popBackStackImmediate();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }else if(id==R.id.nav_books){
            Intent intent = new Intent(MainActivity.this,BooksPost.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_recorder) {
            startActivity(new Intent(MainActivity.this,RecordActivity.class));

        } else if (id == R.id.nav_maps) {
           startActivity(new Intent(MainActivity.this,MapsActivity.class));
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(MainActivity.this ,Contact.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        }
        else if(id==R.id.nav_logout){
            Intent intent = new Intent(MainActivity.this ,signout.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_notifications){
            startActivity(new Intent(MainActivity.this,Recentupdates.class));
        }
        else if(id==R.id.nav_jubilate){
            startActivity(new Intent(MainActivity.this,Jubilate.class));
        }
        else if(id == R.id.nav_campusview){
            startActivity(new Intent(MainActivity.this,CampusView.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
