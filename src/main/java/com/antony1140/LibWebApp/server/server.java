package com.antony1140.LibWebApp.server;

import java.io.File;
 
import jakarta.servlet.ServletException;

import org.apache.catalina.*;
//import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
 
public class server {
 
    public static void main(String[] args) throws LifecycleException{
        String contextPath = "";
        String webappDir = new File("src").getAbsolutePath();
                 
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);
         
        tomcat.addWebapp(contextPath, webappDir);
         
        tomcat.start();
        tomcat.getServer().await();    
    }
}
