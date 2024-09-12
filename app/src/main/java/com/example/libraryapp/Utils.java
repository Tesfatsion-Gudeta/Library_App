package com.example.libraryapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private SharedPreferences sharedPreferences;
    private static final String BOOKS_KEY="books";
    private static final String WANT_TO_READ_KEY="wantToRead";
    private static final String FAVOURITE_KEY="favourite";
    private static final String READ_KEY="read";
    private static final String READING_KEY="reading";


    private static Utils singletonInstance;



    private Utils(Context context){

        sharedPreferences=context.getSharedPreferences("book_db",Context.MODE_PRIVATE);

        if(null ==getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();

        if(null ==getAlreadyRead()){
            editor.putString(READ_KEY,gson.toJson(new ArrayList<BooksModel>()));
            editor.commit();

        }
        if(null ==getWantToRead()){
            editor.putString(WANT_TO_READ_KEY,gson.toJson(new ArrayList<BooksModel>()));
            editor.commit();

        }
        if(null ==getReading()){
            editor.putString(READING_KEY,gson.toJson(new ArrayList<BooksModel>()));
            editor.commit();

        }
        if(null ==getFavourite()){
            editor.putString(FAVOURITE_KEY,gson.toJson(new ArrayList<BooksModel>()));
            editor.commit();

        }

    }

    private void initData() {


        ArrayList<BooksModel> books=new ArrayList<>();

        books.add(new BooksModel("Tuesdays with Morrie","mitch","slice of life","this is advices and perspectives from a person in his deathbed","https://imgs.search.brave.com/HAVbITs2epyhTtGiD1Aq0n94bQURNgYVnDI4JjlbjNw/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL0kv/NDFXS2lRRDJPckwu/anBn",1,200));
        books.add(new BooksModel("How To Win Friends & Influence People","Dale Carnegie","self-help book","Learn the six ways to make people like you, the twelve ways to win people to your way of thinking, and the nine ways to change people without arousing resentment.","https://imgs.search.brave.com/ZwNWOpWUrmQL95laFpYlODamY3mBDviDMk-ZeMnHAXk/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pbWFn/ZXMtbmEuc3NsLWlt/YWdlcy1hbWF6b24u/Y29tL2ltYWdlcy9J/LzcxOExPUThlY3lM/LmpwZw",2,288));


        Gson gson=new Gson();
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(BOOKS_KEY,gson.toJson(books));
        editor.commit();


    }


    public static Utils getSingletonInstance(Context context) {

        if(null!=singletonInstance){
            return singletonInstance;
        }
        else{
            singletonInstance=new Utils(context);
            return singletonInstance;
        }


    }

    public ArrayList<BooksModel> getAllBooks() {

        Gson gson=new Gson();

        Type type=new TypeToken<ArrayList<BooksModel>>(){}.getType();
        ArrayList<BooksModel> books=gson.fromJson(sharedPreferences.getString(BOOKS_KEY,null),type);

        return books;
    }

    public ArrayList<BooksModel> getAlreadyRead() {
        Gson gson=new Gson();

        Type type=new TypeToken<ArrayList<BooksModel>>(){}.getType();
        ArrayList<BooksModel> books=gson.fromJson(sharedPreferences.getString(READ_KEY,null),type);

        return books;
    }

    public  ArrayList<BooksModel> getWantToRead()
    {
        Gson gson=new Gson();

        Type type=new TypeToken<ArrayList<BooksModel>>(){}.getType();
        ArrayList<BooksModel> books=gson.fromJson(sharedPreferences.getString(WANT_TO_READ_KEY,null),type);

        return books;
    }

    public  ArrayList<BooksModel> getReading() {

        Gson gson=new Gson();

        Type type=new TypeToken<ArrayList<BooksModel>>(){}.getType();
        ArrayList<BooksModel> books =gson.fromJson(sharedPreferences.getString(READING_KEY,null),type);

        return books;
    }

    public  ArrayList<BooksModel> getFavourite() {

        Gson gson=new Gson();

        Type type=new TypeToken<ArrayList<BooksModel>>(){}.getType();
        ArrayList<BooksModel> books=gson.fromJson(sharedPreferences.getString(FAVOURITE_KEY,null),type);

        return books;
    }

    public BooksModel getBookById(int id){
        ArrayList<BooksModel>books=getAllBooks();
        if(null!=books){
            for(BooksModel b:books){
                if(b.getId()==id){
                    return b;
                }
            }
        }

    return null;
    }
    public boolean addToAlreadyRead(BooksModel booksModel){
       ArrayList<BooksModel> books=getAlreadyRead();
       if(null!=books){
           if(books.add(booksModel)){
               Gson gson=new Gson();
               SharedPreferences.Editor editor=sharedPreferences.edit();
               editor.remove(READ_KEY);
               editor.putString(READ_KEY,gson.toJson(books));
               editor.commit();
               return true;

           }

        }
       return false;
    }
    public boolean addToWishList(BooksModel booksModel)

    {
        ArrayList<BooksModel> books=getWantToRead();
        if(null!=books){
            if(books.add(booksModel)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(WANT_TO_READ_KEY);
                editor.putString(WANT_TO_READ_KEY,gson.toJson(books));
                editor.commit();
                return true;

            }

        }
        return false;
    }

    public boolean addToFavouriteList(BooksModel bookDetail) {

        ArrayList<BooksModel> books=getFavourite();
        if(null!=books){
            if(books.add(bookDetail)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(FAVOURITE_KEY);
                editor.putString(FAVOURITE_KEY,gson.toJson(books));
                editor.commit();
                return true;

            }

        }
        return false;
    }

    public boolean addToReadingList(BooksModel bookDetail) {

        ArrayList<BooksModel> books=getReading();
        if(null!=books){
            if(books.add(bookDetail)){
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(READING_KEY);
                editor.putString(READING_KEY,gson.toJson(books));
                editor.commit();
                return true;

            }

        }
        return false;
    }

    public boolean removeFromAlreadyRead(BooksModel book){

        ArrayList<BooksModel> books=getAlreadyRead();
        if(null!=books){
            if(null!=books){
                for(BooksModel b:books){
                    if(b.getId()==book.getId()){
                        if(books.remove(b)){
                            Gson gson=new Gson();
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.remove(READ_KEY);
                            editor.putString(READ_KEY,gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }

            }
        }
        return false;

    }
    public boolean removeFromFavourite(BooksModel book){

        ArrayList<BooksModel> books=getFavourite();
        if(null!=books){
            if(null!=books){
                for(BooksModel b:books){
                    if(b.getId()==book.getId()){
                        if(books.remove(b)){
                            Gson gson=new Gson();
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.remove(FAVOURITE_KEY);
                            editor.putString(FAVOURITE_KEY,gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    public boolean removeFromWishList(BooksModel book){

        ArrayList<BooksModel> books=getWantToRead();
        if(null!=books){
            if(null!=books){
                for(BooksModel b:books){
                    if(b.getId()==book.getId()){
                        if(books.remove(b)){
                            Gson gson=new Gson();
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.remove(WANT_TO_READ_KEY);
                            editor.putString(WANT_TO_READ_KEY,gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
    public boolean removeFromReading(BooksModel book){

        ArrayList<BooksModel> books=getReading();
        if(null!=books){
            if(null!=books){
                for(BooksModel b:books){
                    if(b.getId()==book.getId()){
                        if(books.remove(b)){
                            Gson gson=new Gson();
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.remove(READING_KEY);
                            editor.putString(READING_KEY,gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }
}


