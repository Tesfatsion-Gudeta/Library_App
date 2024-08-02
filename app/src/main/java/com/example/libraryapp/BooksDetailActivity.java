package com.example.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class BooksDetailActivity extends AppCompatActivity {

    private TextView bookName,authorName,numberOfPages,longDescription;
    private Button reading,wishList,read,favourite;
    private ImageView bookCover;

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

        views();
        Intent intent=getIntent();
        if(null!=intent){
            int bookId=intent.getIntExtra("bookId",-1);
            if(bookId !=-1){
                BooksModel bookDetail=Utils.getSingletonInstance().getBookById(bookId);
                if(null!=bookDetail){
                    setData(bookDetail);
                }

            }
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