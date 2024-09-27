package com.antony1140.LibWebApp.Controller;

import com.antony1140.LibWebApp.Book.*;
import com.antony1140.LibWebApp.User.User;
import com.antony1140.LibWebApp.User.UserDao;
import com.antony1140.LibWebApp.User.UserService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@WebServlet(name = "Controller", urlPatterns = {"/*", "/list", "/user/*", "/book/*", "/main"})
public class ControllerServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
String contextPath = req.getContextPath();
String servletPath = req.getServletPath();
String pathInfo = req.getPathInfo();
        //System.out.println("contextPath: " + contextPath);
        System.out.println("servletPath: " + servletPath);
        System.out.println("pathInfo: " + pathInfo);
        try{
            switch (servletPath) {
                case "/user":
                    if (pathInfo.equals("/register")) {
                     registerUser(req, resp);
                    }
                    if (pathInfo.equals("/login")) {
                        loginUser(req, resp);
                    }
                break;
                case "/book":
                    if (pathInfo.equals("/create")) {
                        System.out.println(servletPath + pathInfo + " reached");
                        createBook(req, resp);
                    }
                    if (pathInfo.equals("/update")) {
                        System.out.println(servletPath + pathInfo + " reached");
                    }
                break;
                case "/":

            }
        }catch(SQLException e){}


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String pathInfo = req.getPathInfo();
        //System.out.println("contextPath: " + contextPath);
        System.out.println("servletPath: " + servletPath);
        System.out.println("pathInfo: " + pathInfo);
        try {
            switch (servletPath) {
                case "/user":
                    if (pathInfo.equals("/list")) {
                        System.out.println(servletPath + pathInfo + " reached");
                        getAllUsers(req, resp);
                    }
                    if (pathInfo.equals("/get_by_id")){
                        System.out.println(servletPath + pathInfo + " reached");
                        getUserById(req, resp);
                    }
                        break;
                        case "/book":
                            if (pathInfo.equals("/list")) {
                                System.out.println(servletPath + pathInfo + " reached");
                                listAllBooks(req, resp);
                            }
                            if (pathInfo.equals("/get_by_Id")) {
                                System.out.println(servletPath + pathInfo + " reached");
                                String id = req.getParameter("id");
                                System.out.println("Requested id : " + id);
                                getBookById(req, resp);
                            }



                    }
            }catch(SQLException e){
            }

        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String pathInfo = req.getPathInfo();

        try{
          switch(servletPath){
              case "/user":
                  if(pathInfo.equals("/update")){
                      System.out.println(servletPath + pathInfo + " reached");
                      updateUser(req, resp);
                  }
                  break;
              case "/book":
                  if(pathInfo.equals("/update")){
                      System.out.println(servletPath + pathInfo + " reached");
                      updateBook(req, resp);
                  }


          }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String servletPath = req.getServletPath();
       String pathInfo = req.getPathInfo();

       try{

           switch(servletPath){
               case "/user":
                   if(pathInfo.equals("/delete")){
                       System.out.println(servletPath + pathInfo + " reached");
                       removeUser(req, resp);
                   }
                   break;
               case "/book":
                   if(pathInfo.equals("/delete")){
                        System.out.println(servletPath + pathInfo + " reached");
                        removeBook(req, resp);
                   }
                   break;
           }

       }catch(Exception e){}

    }

    //User Methods****************************************************

   private void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
       System.out.println("getting users");
       UserService userService = new UserService();

       String json = new Gson().toJson(userService.getUsers());
       resp.setContentType("application/json");
       resp.setCharacterEncoding("UTF-8");
       resp.getWriter().write(json);


   }

   private void getUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
       System.out.println("trying to get user by id");
        UserDao dao = new UserDao();
        int id = parseInt(req.getParameter("id"));
        String json = new Gson().toJson(dao.getById(id));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

   }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("trying to create user");
        User user = null;
        UserService userService = new UserService();
        UserDao dao = new UserDao();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String body = "";
        while ((body = br.readLine()) != null) {
            sb.append(body);
        }
        user = new Gson().fromJson(sb.toString(), User.class);
        int id = dao.insert(user);
        String json = new Gson().toJson(dao.getById(id));
        System.out.println("returned user: " + user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("trying to log in");
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        User user = userService.login(username, password);
//
//        if (user == null) {
//            req.setAttribute("error", "Invalid username or password. Please try again");
//            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
//        }else{
//            req.setAttribute("user", user);
//            req.setAttribute("title", "Welcome " + user.getFirstName() + " " + user.getLastName());
//            getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
//            System.out.println("login successful");
//        }

        String json = new Gson().toJson(user);
        System.out.println(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }

    //Foreign Key Constraint issue
    private void removeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
        System.out.println("trying to delete user");
        UserDao dao = new UserDao();
        String id = req.getParameter("id");
        System.out.println(id + ": to be deleted");
        int result = dao.deleteUser(parseInt(id));
        System.out.println("result: " + result);
        if(result > 0){
            System.out.println("User deleted");
        }else{
            System.out.println("Unsuccessful attempt");
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
        System.out.println("trying to update user");
        UserDao dao = new UserDao();
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String body = "";
        while((body = br.readLine()) != null){
            sb.append(body);
        }
        String id = req.getParameter("id");
        User user = new Gson().fromJson(sb.toString(), User.class);
        user.setId(parseInt(id));
        int result = dao.updateUser(user);
        if (result != 0){
            String json = new Gson().toJson(user);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
        else{
            resp.setContentType("plain/text");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("404 - There was an error with the request");
        }
    }

    //Book Methods*******************************************************************

    private void listAllBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("trying to list books");
        BookService bookService = new BookService();
        String json = new Gson().toJson(bookService.getBooks());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    private void getBookById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        BookService bookService = new BookService();
        System.out.println("trying to get book by id");
        int id = parseInt(req.getParameter("id"));
        String json = new Gson().toJson(bookService.getBook(id));
        System.out.println(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    private void createBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("trying to create book");
        BookService bookService = new BookService();
        BookDao bookDao = new BookDao();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(req.getReader());
        String body = "";
        while((body = br.readLine()) != null){
            sb.append(body);
        }
        Book book = new Gson().fromJson(sb.toString(), Book.class);
        System.out.println(book);
        int bookId = bookService.createBook(book);
        System.out.println("Book id is: " + bookId);
        String json = new Gson().toJson(bookService.getBook(bookId));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    //Foreign Key Constraint issue
    private void removeBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("Trying to delete book");
        BookDao dao = new BookDao();
        BookService bookService = new BookService();
        String id = req.getParameter("id");
        int result = bookService.deleteUser(parseInt(id));
        if(result > 0){
            resp.setContentType("text/plain");
            resp.getWriter().write(String.valueOf(result));
            System.out.println(result);
        }else{
            resp.setContentType("text/plain");
            resp.getWriter().write("Error: Id " + id +  " not found. " + String.valueOf(result) + " rows affected");
            System.out.println("Error: Id " + id +  " not found. " + String.valueOf(result) + " rows affected");
        }

    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("trying to update book");
        BookDao bookdao = new BookDao();
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String body = "";
        while((body = br.readLine()) != null){
            System.out.println(body);
            sb.append(body);
        }

        String id = req.getParameter("id");
        Book book = new Gson().fromJson(sb.toString(), Book.class);
        book.setId(parseInt(id));


        int result = bookdao.update(book);
        System.out.println(result);
        if(result > 0){
            System.out.println(book);
        }else{
            System.out.println("Error: book was not updated");
        }
    }


}


