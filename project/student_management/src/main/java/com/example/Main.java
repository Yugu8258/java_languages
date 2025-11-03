package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.example.util.JDBCUtils;

public class Main {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connection = JDBCUtils.getConnection();

            System.out.println("Please enter name and age:");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = scanner.nextInt();

            String sql = "insert into info(name, age) values (?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, statement, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
