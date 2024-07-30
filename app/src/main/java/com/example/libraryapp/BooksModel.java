package com.example.libraryapp;

public class BooksModel {
    private String bookName,authorName,shortDesc,longDesc,imgUrl;
    private int id,numberOfPages;
    private boolean isExpanded;

    public BooksModel(String bookName, String authorName, String shortDesc, String longDesc, String imgUrl, int id, int numberOfPages) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.imgUrl = imgUrl;
        this.id = id;
        this.numberOfPages = numberOfPages;
        this.isExpanded=false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "BooksModel{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", id=" + id +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
