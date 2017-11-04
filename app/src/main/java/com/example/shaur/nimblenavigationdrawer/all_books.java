package com.example.shaur.nimblenavigationdrawer;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class all_books extends Fragment {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = db.getReference();
    DatabaseReference bookRef = rootRef.child("BooksAds");

    RecyclerView recyclerView;
    BookAdapter adapter;

    List<Books_getter_function> bookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_books, container, false);

        bookList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerView_books);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        bookList.add(new Books_getter_function("Ghatak", "200","shaurya@gmail.com","excellent","9899376787",R.drawable.book_blue_icon));
        bookList.add(new Books_getter_function("HK Das", "200","shaurya@gmail.com","excellent","9899376787",R.drawable.book_blue_icon));

        BookAdapter adapter = new BookAdapter(getActivity(),bookList);

        recyclerView.setAdapter(adapter);

        return view;
    }
}
