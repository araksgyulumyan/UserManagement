package service.admin;

import model.admin.Admin;
import service.common.factory.Service;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:03 PM
 */
public interface AdminService extends Service {

    Admin getOrCreateAdmin(String username);
}
