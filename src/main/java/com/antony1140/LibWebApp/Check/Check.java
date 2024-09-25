package com.antony1140.LibWebApp.Check;

public class Check {
    private int checkId;
    private int userId;
    private int bookId;
    private String userName;
    private String bookTitle;
    private String bookAuthor;
    private String isbn;

    public Check(int checkId, int userId, int bookId, String userName, String bookTitle, String bookAuthor, String isbn) {
        this.checkId = checkId;
        this.userId = userId;
        this.bookId = bookId;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;

    }

    public Check(int userId, int bookId, String userName, String bookTitle, String bookAuthor, String isbn) {
        this.userId = userId;
        this.bookId = bookId;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkId=" + checkId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", userName='" + userName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
