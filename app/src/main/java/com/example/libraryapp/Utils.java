package com.example.libraryapp;

import java.util.ArrayList;

public class Utils {

    private static Utils singletonInstance;
    private static ArrayList<BooksModel> allBooks;
    private static ArrayList<BooksModel> alreadyRead;
    private static ArrayList<BooksModel> wantToRead;
    private static ArrayList<BooksModel> reading;
    private static ArrayList<BooksModel> favourite;


    private Utils(){
        if(null ==allBooks){
            allBooks=new ArrayList<>();
            initData();
        }
        if(null ==alreadyRead){
            alreadyRead=new ArrayList<>();

        }
        if(null ==wantToRead){
            wantToRead=new ArrayList<>();

        }
        if(null ==reading){
            reading=new ArrayList<>();

        }
        if(null ==favourite){
            favourite=new ArrayList<>();

        }

    }

    private void initData() {
        //TODO:add initial data

        allBooks.add(new BooksModel("Tuesdays with Morrie","mitch","slice of life","this is advices and perspectives from a person in his deathbed","https://imgs.search.brave.com/HAVbITs2epyhTtGiD1Aq0n94bQURNgYVnDI4JjlbjNw/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL0kv/NDFXS2lRRDJPckwu/anBn",1,200));
    }


    public static Utils getSingletonInstance() {

        if(null!=singletonInstance){
            return singletonInstance;
        }
        else{
            singletonInstance=new Utils();
            return singletonInstance;
        }


    }

    public static ArrayList<BooksModel> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<BooksModel> getAlreadyRead() {
        return alreadyRead;
    }

    public static ArrayList<BooksModel> getWantToRead() {
        return wantToRead;
    }

    public static ArrayList<BooksModel> getReading() {
        return reading;
    }

    public static ArrayList<BooksModel> getFavourite() {
        return favourite;
    }

    public BooksModel getBookById(int id){
        for(BooksModel b:allBooks){
            if(b.getId()==id){
                return b;
            }
        }
    return null;
    }
    public boolean addToAlreadyRead(BooksModel booksModel){
        return alreadyRead.add(booksModel);
    }
    public boolean addToWishList(BooksModel booksModel){
        return wantToRead.add(booksModel);
    }

    public boolean addToFavouriteList(BooksModel bookDetail) {
        return favourite.add(bookDetail);
    }

    public boolean addToReadingList(BooksModel bookDetail) {
        return reading.add(bookDetail);
    }
}


