package com.company;



import java.io.*;
import java.sql.*;

public class MySQL {
   public static String url;
    static {
        url ="jdbc:mysql://localhost:3306/AdventureWorks?user=root&password=root";
    }
   public static void putPhoto (String name, String path) throws ClassNotFoundException {
       try(Connection con = DriverManager.getConnection(url)){
           File file = new File(path);
           int size = (int) file.length();
           BufferedInputStream fis=new BufferedInputStream(new FileInputStream(file));
           String sql = "Insert into dbo.pictures (name, photo) Values (?, ?)";
           PreparedStatement cmd = con.prepareStatement(sql);
           cmd.setString(1, name);
           cmd.setBinaryStream(2, fis, size);

       } catch (FileNotFoundException | SQLException e) {
           System.out.println("Файл не существует");

       }
   }}
