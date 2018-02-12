package repository.employee;

import model.employee.Employee;
import repository.common.AbstractRepository;
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
public class EmployeeRepositoryImpl extends AbstractRepository implements EmployeeRepository {

    private static final String FIND_ALL_QUERY = "SELECT * FROM EMPLOYEES";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM EMPLOYEES WHERE id=%d";
    private static final String GET_LAST_INSERTED_EMPLOYEE = "SELECT * FROM EMPLOYEES ORDER BY id DESC LIMIT 1";
    private static final String INSERT_INTO_QUERY = "INSERT INTO EMPLOYEES (name) VALUES ('%s')";
    //// TODO: 2/11/18  use alias
    private static final String GET_EMPLOYEE_DUTIES = "SELECT * FROM EMPLOYEES LEFT JOIN DUTIES ON EMPLOYEES.id=DUTIES.employee_id WHERE EMPLOYEES.id=%d";

    @Override
    public List<Employee> findAll() {
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(FIND_ALL_QUERY, connection);
        final List<Employee> employees;
        try {
            // Execute
            final ResultSet resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            employees = convertToEmployee(resultSet);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        return employees;
    }

    @Override
    public Employee createEmployee(final String name) {
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
            connection.setAutoCommit(Boolean.FALSE);
            result = preparedStatement.executeUpdate();
            connection.commit();
        } catch (final SQLException e) {
            try {
                connection.rollback();
            } catch (final SQLException ex) {
                throw new DataSourceException(ex);
            }
            throw new DataSourceException(e);
        } finally {
            closeExecutionResources(preparedStatement, connection);
        }
        if (result == 0) {
            throw new DataSourceException("Something went wrong during employee creation process");
        }
        return getLastInsertedEmployee();
    }


    @Override
    public Employee updateEmployeeName(String name) {
        return null;
    }

    @Override
    public Employee getEmployeeDuties(Long id) {
        // Create sql query
        String sqlQuery = String.format(GET_EMPLOYEE_DUTIES, id);
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(sqlQuery, connection);
        final List<Employee> employees;
        try {
            // Execute
            final ResultSet resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            employees = convertToEmployee(resultSet);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        this.assertCollectionIsNotEmpty(employees);
        return employees.get(0);
    }

    @Override
    public Employee findById(Long id) {
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(FIND_BY_ID_QUERY, connection);
        final List<Employee> employees;
        try {
            // Execute
            final ResultSet resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            employees = convertToEmployee(resultSet);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        this.assertCollectionIsNotEmpty(employees);
        return employees.get(0);
    }


    //todo with transaction
    @Override
    public void removeById(Long id) {

    }

    private Employee getLastInsertedEmployee() {
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(GET_LAST_INSERTED_EMPLOYEE, connection);
        final List<Employee> employees;
        try {
            // Execute
            final ResultSet resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            employees = convertToEmployee(resultSet);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        this.assertCollectionIsNotEmpty(employees);
        return employees.get(0);
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
