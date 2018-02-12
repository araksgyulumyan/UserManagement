package repository.duty;

import model.duty.Duty;
import model.duty.DutyType;
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
public class DutyRepositoryImpl extends AbstractRepository implements DutyRepository {

    private static final String INSERT_INTO_QUERY = "INSERT INTO DUTIES (employee_id, type) VALUES (%d, '%s')";
    private static final String GET_DUTIES_OF_EMPLOYEE = "SELECT * FROM DUTIES WHERE employee_id=%d";
    private static final String GET_LAST_INSERTED_DUTY = "SELECT * FROM DUTIES ORDER BY id DESC LIMIT 1";
    private static final String REMOVE_BY_ID = "DELETE FROM DUTIES WHERE id=%d";

    @Override
    public Duty createDuty(Long employeeId, DutyType type) {
        // Create sql query
        String sqlQuery = String.format(INSERT_INTO_QUERY, employeeId, type.name());
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
            throw new DataSourceException("Something went wrong during duty creation process");
        }
        return getLastInsertedDuty();
    }

    @Override
    public List<Duty> getDutiesOfEmployee(Long employeeId) {

        // Create sql query
        String sqlQuery = String.format(GET_DUTIES_OF_EMPLOYEE, employeeId);

        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(sqlQuery, connection);
        final List<Duty> duties;
        try {
            // Execute
            final ResultSet resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            duties = convertToDuty(resultSet);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        return duties;
    }

    @Override
    public Duty getLastInsertedDuty() {
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(GET_LAST_INSERTED_DUTY, connection);
        final List<Duty> duties;
        try {
            // Execute
            final ResultSet resultSet = preparedStatement.executeQuery();
            // Convert result set into model
            duties = convertToDuty(resultSet);
        } catch (SQLException e) {
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
        this.assertCollectionIsNotEmpty(duties);
        return duties.get(0);
    }

    @Override
    public List<Duty> findAll() {
        return null;
    }

    @Override
    public Duty findById(Long id) {
        return null;
    }

    @Override
    public void removeById(Long id) {
        // Open connection
        final Connection connection = getConnection();
        // Get prepared statement
        final PreparedStatement preparedStatement = DataSourceConnection.
                getInstance().
                createPreparedStatement(String.format(REMOVE_BY_ID, id), connection);
        try {
            connection.setAutoCommit(Boolean.FALSE);
            // Execute
            preparedStatement.executeQuery();
            connection.commit();
        } catch (final SQLException e) {
            try {
                connection.rollback();
            } catch (final SQLException ex) {
                throw new DataSourceException(ex);
            }
            throw new DataSourceException(e);
        } finally {
            // Close resources
            closeExecutionResources(preparedStatement, connection);
        }
    }

    // Utility methods
    private List<Duty> convertToDuty(final ResultSet resultSet) {
        final ArrayList<Duty> dutiesList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Duty duty = new Duty();
                duty.setId(resultSet.getLong("id"));
                duty.setType(DutyType.valueOf(resultSet.getString("type")));
                duty.setEmployeeId(resultSet.getLong("employee_id"));
                dutiesList.add(duty);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
        return dutiesList;
    }

    private Connection getConnection() {
        return DataSourceConnection.getInstance().getConnection();
    }

    private void closeExecutionResources(PreparedStatement preparedStatement, Connection connection) {
        DataSourceConnection.getInstance().closeQueryExecution(preparedStatement);
        DataSourceConnection.getInstance().closeConnection(connection);
    }
}
