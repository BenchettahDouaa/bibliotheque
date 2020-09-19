package com.pharmaservice.bibliotheque;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BookList extends AppCompatActivity {
    DBHelper mydb;

    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        lv = (ListView) findViewById(R.id.LV1);


        mydb = new DBHelper(this);

        ArrayList<Books> BookList =  mydb.ListerTousRDV();



        BooksListAdapter adapter = new BooksListAdapter(this, R.layout.re_adapter_view, BookList);
        lv.setAdapter(adapter);
    }
}
