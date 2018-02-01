package repository.common.model;

import repository.common.exception.DataSourceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

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

    public PreparedStatement createPreparedStatement(final String query, final Connection connection) {
        final PreparedStatement preparedStatement;
        try {
            preparedStatement = getConnection().prepareStatement(query);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        if (Objects.isNull(preparedStatement)) {
            throw new DataSourceException("Prepared statement should not be null");
        }
        return preparedStatement;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            throw new DataSourceException(e);
        }
        if (connection == null) {
            throw new DataSourceException("Could not connect to database");
        }
        return connection;
    }

    public void closeConnection(final Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public void closeQueryExecution(final PreparedStatement preparedStatement) {
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }
}
