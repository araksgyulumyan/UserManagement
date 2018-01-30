package repository.admin;

import dataconnection.DataSourceConnection;
import model.admin.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 10:36 PM
 */
public class AdminRepositoryImpl implements AdminRepository {

    @Override
    public Admin findAll() {
        String sqlQuery = "SELECT * FROM ADMINS INTO ADMINS";
        ResultSet resultSet = DataSourceConnection.getInstance().executeQuery(sqlQuery);
        return convertToAdmin(resultSet);
    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Admin createAdmin(final String username) {

        final Admin admin = new Admin();

        if (username != null && !username.isEmpty()) {
            String sqlQuery = "INSERT INTO ADMINS (username) VALUES (" + username + ")";
            ResultSet resultSet = DataSourceConnection.getInstance().executeQuery(sqlQuery);

            try {
                if (resultSet.next() == Boolean.TRUE) {
                    do {
                        admin.setUsername(resultSet.getString("username"));
                    }
                    while (resultSet.next());

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return admin;
    }

    @Override
    public Admin updateAdminUsername(final String username) {
        return null;
    }

    @Override
    public Integer getAdminsCount() {
        String sqlQuery = "SELECT COUNT(*) FROM ADMINS";
        Admin admin = new Admin();
        ResultSet resultSet = DataSourceConnection.getInstance().executeQuery(sqlQuery);
        try {
            while (resultSet.next()) {
                admin.setAdminsCount(resultSet.getInt("count(*)"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin.getAdminsCount();
    }

    private Admin convertToAdmin(final ResultSet resultSet) {
        Admin admin = new Admin();

        try {
            while (resultSet.next()) {
                admin.setId(resultSet.getLong("id"));
                admin.setUsername(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }


}
