package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectionFactory {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost/db_app?" +
    		"user=root&password=123456"; 
    
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private Connection connection;
    
    public Connection createConnection(){
        try {
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(JDBC_URL);
            }
            return connection;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
