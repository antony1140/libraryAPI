package com.antony1140.LibWebApp.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService{
    public List<Book> getBooks() throws SQLException {
        BookDao dao = new BookDao();
        return dao.getAll();
    }

    public Book getBook(int id) throws SQLException {
        BookDao dao = new BookDao();
        return dao.getById(id);
    }

    public int createBook(Book book) throws SQLException{
        BookDao dao = new BookDao();
        return dao.insert(book);
    }

    public int deleteUser(int id) throws SQLException {
        BookDao dao = new BookDao();
        return dao.delete(id);
    }

}
