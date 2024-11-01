package com.example.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Objects;

public class BooksDetailActivity extends AppCompatActivity {

    private TextView bookName,authorName,numberOfPages,longDescription;
    private Button reading,wishList,read,favourite;
    private ImageView bookCover;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_books_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);



        views();
        Intent intent=getIntent();
        if(null!=intent){
            int bookId=intent.getIntExtra("bookId",-1);
            if(bookId !=-1){
                BooksModel bookDetail=Utils.getSingletonInstance(this).getBookById(bookId);
                if(null!=bookDetail){
                    setData(bookDetail);
                    handleAlreadyRead(bookDetail);
                    handleWishList(bookDetail);
                    handleFavouriteBooks(bookDetail);
                    handleCurrentlyReading(bookDetail);

                }

            }
        }

//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


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
    private void handleCurrentlyReading(final BooksModel bookDetail) {


        ArrayList<BooksModel> readingList=Utils.getSingletonInstance(this).getReading();
        boolean existInReadingList=false;
        for(BooksModel b:readingList) {
            if (b.getId() == bookDetail.getId()) {
                existInReadingList = true;

            }
        }
        if(existInReadingList){
            reading.setEnabled(false);

        }

        else{
            reading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Utils.getSingletonInstance(BooksDetailActivity.this).addToReadingList(bookDetail)){
                        Toast.makeText(BooksDetailActivity.this, "book added to reading list", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BooksDetailActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BooksDetailActivity.this, "something went wrong try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    private void handleWishList(final BooksModel bookDetail) {

        ArrayList<BooksModel> wishLists=Utils.getSingletonInstance(this).getWantToRead();
        boolean existInWishList=false;
        for(BooksModel b:wishLists) {
            if (b.getId() == bookDetail.getId()) {
                existInWishList = true;

            }
        }
        if(existInWishList){
            wishList.setEnabled(false);

        }

        else{
            wishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Utils.getSingletonInstance(BooksDetailActivity.this).addToWishList(bookDetail)){
                        Toast.makeText(BooksDetailActivity.this, "book added to wishlist", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BooksDetailActivity.this,WishListActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BooksDetailActivity.this, "something went wrong try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }



    }

    private void handleFavouriteBooks(final BooksModel bookDetail) {


        ArrayList<BooksModel> favouriteList=Utils.getSingletonInstance(this).getFavourite();
        boolean existInFavouriteList=false;
        for(BooksModel b:favouriteList) {
            if (b.getId() == bookDetail.getId()) {
                existInFavouriteList = true;

            }
        }
        if(existInFavouriteList){
            favourite.setEnabled(false);

        }

        else{
            favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Utils.getSingletonInstance(BooksDetailActivity.this).addToFavouriteList(bookDetail)){
                        Toast.makeText(BooksDetailActivity.this, "book added to favourites list", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BooksDetailActivity.this,FavouritesActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BooksDetailActivity.this, "something went wrong try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    private void handleAlreadyRead(final BooksModel bookDetail) {
        ArrayList<BooksModel> alreadyRead=Utils.getSingletonInstance(this).getAlreadyRead();
        boolean isAlreadyRead=false;
        for(BooksModel b:alreadyRead) {
            if (b.getId() == bookDetail.getId()) {
                isAlreadyRead = true;

            }
        }
            if(isAlreadyRead){
                read.setEnabled(false);

            }

            else{
                read.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Utils.getSingletonInstance(BooksDetailActivity.this).addToAlreadyRead(bookDetail)){
                            Toast.makeText(BooksDetailActivity.this, "book added to already read list", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(BooksDetailActivity.this,AlreadyReadBooksActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BooksDetailActivity.this, "something went wrong try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }



    }

    private void setData(BooksModel bookDetail) {
        bookName.setText(bookDetail.getBookName());
        authorName.setText(bookDetail.getAuthorName());
        numberOfPages.setText(String.valueOf(bookDetail.getNumberOfPages()));
        Glide.with(this)
                        .asBitmap()
                                .load(bookDetail.getImgUrl())
                                        .into(bookCover);
        longDescription.setText(bookDetail.getLongDesc());

    }

    private void views() {
        bookName=findViewById(R.id.booknamee);
        authorName=findViewById(R.id.authornamee);
        numberOfPages=findViewById(R.id.pagess);
        longDescription=findViewById(R.id.longdescc);
        reading=findViewById(R.id.addtoreading);
        wishList=findViewById(R.id.addtowishlist);
        read=findViewById(R.id.read);
        favourite=findViewById(R.id.favourite);
        bookCover=findViewById(R.id.bookimgg);


    }
}