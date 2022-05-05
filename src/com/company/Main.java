package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            MySQL.putPhoto("welldone.png", "C:\\test\\welldone.png");
        } catch (ClassNotFoundException e) {
            System.out.println("Файл не существует");

    }
}}
