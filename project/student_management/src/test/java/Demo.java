import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.example.util.JDBCUtils;

public class Demo {
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public PreparedStatement preparedStatement;

    @Test
    public void insertTest() {
        System.out.println("Insert test executed");

        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();

            String sql = "insert into info(name, age) values ('張三', 25);";

            int res = statement.executeUpdate(sql);
            if (res > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, statement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void updateTest() {
        System.out.println("Update test executed");

        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();

            String sql = "update info set age = 20 where name = 'Tom';";

            int res = statement.executeUpdate(sql);

            if (res > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, statement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deleteTest() {
        System.out.println("Delete test executed");

        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();

            String sql = "delete from info where name = 'Tom';";

            int res = statement.executeUpdate(sql);

            if (res > 0) {
                System.out.println("Delete successful");
            } else {
                System.out.println("Delete failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, statement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void selectTest() {
        System.out.println("Select test executed");
        // To be implemented
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from info;");

            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id") +
                        ", name: " + resultSet.getString("name") +
                        ", age: " + resultSet.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void insertTestWithPreparedStatement() {
        try {
            connection = JDBCUtils.getConnection();

            System.out.println("Please enter name and age:");

            String sql = "insert into info(name, age) values (?, ?)";
            String name = "李四";
            int age = 34;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            int res = preparedStatement.executeUpdate();

            if (res > 0) {
                System.out.println("Insert successful");
            } else {
                System.out.println("Insert failed");
            }
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
