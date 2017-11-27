package com.example.shaur.nimblenavigationdrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class recorder extends Activity {

    private static final String TAG = "recorder";

    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private View mEmptyView;
    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            if (mAdapter.isEmpty()){
                mEmptyView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            } else {
                mEmptyView.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mEmptyView = findViewById(R.id.empty_view);
        mAdapter.registerAdapterDataObserver(mObserver);
        mAdapter.loadData();
        PlayServiceAgent.getInstance().init(this.getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayServiceAgent.getInstance().disconnect(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent i = new Intent(this, RecordActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                        |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i, com.example.shaur.nimblenavigationdrawer.Constants.ACTIVITY_REQUEST_CODE_FOR_RECORD);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == com.example.shaur.nimblenavigationdrawer.Constants.ACTIVITY_REQUEST_CODE_FOR_RECORD) {

            mAdapter.loadData();

            PlayServiceAgent.getInstance().setPlayingDir(com.example.shaur.nimblenavigationdrawer.Utils.getRecordDir(this));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
