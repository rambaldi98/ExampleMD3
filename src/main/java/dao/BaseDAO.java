package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    private static Connection connection;
    private static String jdbcURL = "jdbc:mysql://localhost:3306/product_new";
    private static String jdbcUserName = "root";
    private static String jdbcPassword = "123456";

    public BaseDAO() {
    }
    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                        jdbcURL,
                        jdbcUserName,
                        jdbcPassword
                );
                System.out.println("connect database is successful");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("connect database is false");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("connect database is false");
            }
        }
        return connection;
    }
}
