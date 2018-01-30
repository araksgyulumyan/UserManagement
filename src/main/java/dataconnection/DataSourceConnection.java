package dataconnection;

import dataconnection.exception.DataSourceException;

import java.sql.*;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 4:42 PM
 */
public class DataSourceConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/user_management";
    private static final DataSourceConnection instance = new DataSourceConnection();


    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    //make the constructor private so that this class cannot be
    //instantiated
    private DataSourceConnection() {
    }

    //Get the only object available
    public static DataSourceConnection getInstance() {
        return instance;
    }

    public ResultSet executeQuery(String sqlQuery) {
        final ResultSet resultSet;
        try {
            // Open connection
            final Connection connection = connectToDatabase();
            // Prepare statement
            final PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            // Execute query
            resultSet = preparedStatement.executeQuery(sqlQuery);
            // Close statement
            closeQueryExecution(preparedStatement);
            // Close connection
            closeConnection(connection);
        } catch (final SQLException e) {
            throw new DataSourceException("Error occurred during query execution", e);
        }
        return resultSet;
    }

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(connection == null) {
            throw new RuntimeException("Could not connect to database");
        }
        return connection;
    }

    private void closeConnection(final Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeQueryExecution(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
