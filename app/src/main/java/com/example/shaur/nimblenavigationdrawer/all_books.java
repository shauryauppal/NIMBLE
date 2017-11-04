package com.example.shaur.nimblenavigationdrawer;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class all_books extends Fragment {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference bookRef = rootRef.child("BookAds");


    RecyclerView recyclerView;
    BookAdapter adapter;

    List<Books_getter_function> bookList;

    String b_name,b_price,b_desc,negotiate,contact,email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_books, container, false);





        bookList = new ArrayList<>();
        final BookAdapter adapter = new BookAdapter(getActivity(),bookList);

        recyclerView = view.findViewById(R.id.recyclerView_books);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(adapter);

        bookRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value =dataSnapshot.getValue().toString();

                try {
                    JSONObject object = new JSONObject(value);

                    b_name = object.getString("BookName");
                    b_price = object.getString("BookPrice");
                    negotiate = object.getString("Negotiable");
                    b_desc = object.getString("BookDescription");
                    contact = object.getString("PersonContactNumber");
                    email = object.getString("PersonEmail");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(negotiate.equals("true"))
                {
                    b_price+=" (negotiable)";
                }
                if(email.equals("NULL"))
                {
                    email="nimble@gmail.com";
                }
                bookList.add(new Books_getter_function(b_name, b_price,email,b_desc,contact,R.drawable.book_blue_icon));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        return view;
    }
}
