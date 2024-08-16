package com.example.libraryapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<BooksModel> arrayList;
    private BooksModel booksModel;
    private LinearLayout booksBtn,readingBtn,tobeReadBtn,alreadyRead,favBtn,aboutBtn;
    private MaterialToolbar toolbar;

    DrawerLayout drawerLayout;

    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout=findViewById(R.id.main);
        recyclerView=findViewById(R.id.bookslist);
//        menu=findViewById(R.id.menuNav);
        booksBtn=findViewById(R.id.books);
        readingBtn=findViewById(R.id.reading);
        tobeReadBtn=findViewById(R.id.wantToRead);
        alreadyRead=findViewById(R.id.alreadyRead);
        favBtn=findViewById(R.id.fav);
        aboutBtn=findViewById(R.id.about);
        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation icon click
                openDrawer(drawerLayout);
            }
        });

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDrawer(drawerLayout);
//            }
//        });



            booksBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            });


            alreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectActivity(MainActivity.this,AlreadyReadBooksActivity.class);
                }
            });

            readingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectActivity(MainActivity.this,CurrentlyReadingActivity.class);

                }
            });
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectActivity(MainActivity.this,FavouritesActivity.class);

                }
            });
            tobeReadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectActivity(MainActivity.this,WishListActivity.class);

                }
            });
            aboutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("developed by tesfatsiongudeta7@gmail.com\n" +
                            "visit my website for more applications ");
                    builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent=new Intent(MainActivity.this,WebViewAcivity.class);
                            intent.putExtra("url","https://google.com");
                            startActivity(intent);


                        }
                    });
                    builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();


                }
            });
        recyclerAdapter=new RecyclerAdapter(this,"books");
        recyclerView=findViewById(R.id.bookslist);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.setBooks(Utils.getSingletonInstance(this).getAllBooks());


    }

    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity,Class secondActivity){
        Intent intent=new Intent(activity,secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}