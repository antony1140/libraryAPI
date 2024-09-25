package com.antony1140.LibWebApp.Book;

import com.antony1140.LibWebApp.DataBase.Database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao {
    public Book getById(int id) throws SQLException {
        Book book = null;
        Connection connection = Database.getConnection();
        String sql = "SELECT * FROM books WHERE book_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            book = new Book();
            book.setId(resultSet.getInt("book_id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setIsbn(resultSet.getString("isbn"));
        }
        connection.close();
        resultSet.close();
        return book;

    }

    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection connection = Database.getConnection();
        String sql = "SELECT * FROM books";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("book_id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setIsbn(resultSet.getString("isbn"));
            books.add(book);
        }
        connection.close();
        resultSet.close();
        return books;
    }

    public int insert(Book book) throws SQLException{
        Connection connection = Database.getConnection();
        String sql = "Insert into books (title, author, isbn) values(?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getIsbn());
        int result = ps.executeUpdate();
        sql = "select LAST_INSERT_ID()";
        PreparedStatement ps2 = connection.prepareStatement(sql);
        ResultSet rs = ps2.executeQuery();
        int id = 0;
        if(rs.next()){
            id = rs.getInt("LAST_INSERT_ID()");
        }
        else{
            System.out.println("something went wrong");
        }

        connection.close();
        ps.close();
        return id;
    }

    public int delete(int id) throws SQLException{
       Connection connection = Database.getConnection();
       String sql = "DELETE FROM books where book_id = ?";
       PreparedStatement ps = connection.prepareStatement(sql);
       ps.setInt(1, id);
       int result = ps.executeUpdate();
       ps.close();
       connection.close();
       return result;
    }

    public int update(Book book) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ? WHERE book_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getIsbn());
        ps.setInt(4, book.getId());
        int result = ps.executeUpdate();

        connection.close();
        return result;

    }
}
