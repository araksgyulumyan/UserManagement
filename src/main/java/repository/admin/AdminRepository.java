package repository.admin;

import model.admin.Admin;
import repository.common.CommonRepository;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 10:37 PM
 */
public interface AdminRepository extends CommonRepository<Admin> {

    void createAdmin(final String username);

    Integer getAdminsCount();

}
