package com.example.libraryapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Books extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<BooksModel> arrayList;
    private BooksModel booksModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerAdapter=new RecyclerAdapter(this);
        recyclerView=findViewById(R.id.bookslist);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList=new ArrayList<>();
        booksModel=new BooksModel("Tuesdays with Morrie","mitch","slice of life","this is advices and perspectives from a person in his deathbed","https://imgs.search.brave.com/HAVbITs2epyhTtGiD1Aq0n94bQURNgYVnDI4JjlbjNw/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL0kv/NDFXS2lRRDJPckwu/anBn",1,200);
        arrayList.add(booksModel);
        recyclerAdapter.setBooks(arrayList);


    }


}