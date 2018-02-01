package repository.admin;

import repository.common.model.DataSourceConnection;
import repository.common.exception.DataSourceException;
import model.admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 10:36 PM
 */
public class AdminRepositoryImpl implements AdminRepository {

    private static final String FIND_ALL_QUERY = "SELECT * FROM ADMINS";
    private static final String INSERT_INTO_QUERY = "INSERT INTO ADMINS (username) VALUES ('%s')";
    private static final String SELECT_COUNT_QUERY = "SELECT COUNT(*) AS TOTAL FROM ADMINS";

    @Override
    public List<Admin> findAll() {
        final List<Admin> admins = new ArrayList<>();
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(FIND_ALL_QUERY, connection);
        try {
            final ResultSet resultSet;
            // Execute
            resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            admins.addAll(convertToAdmin(resultSet));
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        return admins;
    }

    @Override
    public Admin findById(Long id) {
        //todo
        return null;
    }

    @Override
    public void removeById(Long id) {
        //todo
    }

    @Override
    public void createAdmin(final String username) {
        // Create sql query
        String sqlQuery = String.format(INSERT_INTO_QUERY, username);
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(sqlQuery, connection);
        int result;
        try {
            result = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new DataSourceException(e);
        } finally {
            closeExecutionResources(preparedStatement, connection);
        }
        if (result == 0) {
            throw new DataSourceException("Something went wrong during admin creation process");
        }
    }

    @Override
    public Integer getAdminsCount() {
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(SELECT_COUNT_QUERY, connection);
        final ResultSet resultSet;
        Integer adminsCount = null;
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                adminsCount = resultSet.getInt("TOTAL");
                break;
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            closeExecutionResources(preparedStatement, connection);
        }
        return adminsCount;
    }

    // Utility methods
    private List<Admin> convertToAdmin(final ResultSet resultSet) {
        final ArrayList<Admin> arrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("id"));
                admin.setUsername(resultSet.getString("username"));
                arrayList.add(admin);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return arrayList;
    }

    private Connection getConnection() {
        return DataSourceConnection.getInstance().getConnection();
    }

    private void closeExecutionResources(PreparedStatement preparedStatement, Connection connection) {
        DataSourceConnection.getInstance().closeQueryExecution(preparedStatement);
        DataSourceConnection.getInstance().closeConnection(connection);
    }
}
