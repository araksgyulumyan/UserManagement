package repository.employee;

import model.employee.Employee;
import repository.common.exception.DataSourceException;
import repository.common.model.DataSourceConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 3:42 PM
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String FIND_ALL_QUERY = "SELECT * FROM EMPLOYEES";
    private static final String INSERT_INTO_QUERY = "INSERT INTO EMPLOYEES (name) VALUES ('%s')";

    @Override
    public List<Employee> findAll() {
        final List<Employee> employees = new ArrayList<>();
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
            employees.addAll(convertToEmployee(resultSet));
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        return employees;
    }

    @Override
    public void createEmployee(String name) {

        // Create sql query
        String sqlQuery = String.format(INSERT_INTO_QUERY, name);
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
            throw new DataSourceException("Something went wrong during employee creation process");
        }
    }

    @Override
    public Employee updateEmployeeName(String name) {
        return null;
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    // Utility methods
    private List<Employee> convertToEmployee(final ResultSet resultSet) {
        final ArrayList<Employee> arrayList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setName(resultSet.getString("name"));
                arrayList.add(employee);
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
