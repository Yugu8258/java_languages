package com.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Student;
import com.example.util.JDBCUtils;

public class StudentService {
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public Student addStudent(Student student) {
        try {
            connection = JDBCUtils.getConnection();

            String sql = "INSERT INTO student (id, name, age, gender) VALUES (?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGender());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT id, name, age, gender FROM student;";

            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, resultSet, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return students;
    }

    public Student getStudentById(String id) {
        Student student = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "SELECT id, name, age, gender FROM student WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                student = new Student(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, resultSet, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student;
    }

    public Student updateStudentById(Student student) {
        try {
            connection = JDBCUtils.getConnection();

            String sql = "UPDATE student SET name = ?, age = ?, gender = ? WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student;
    }

    public boolean deleteStudentById(String id) {
        boolean isDeleted = false;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "DELETE FROM student WHERE id = ?;";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            isDeleted = rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isDeleted;
    }

}
