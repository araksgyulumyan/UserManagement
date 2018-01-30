import service.admin.AdminService;
import service.admin.AdminServiceImpl;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 8:07 PM
 */
public class UserManagementApplication {

    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();



        adminService.getOrCreateAdmin();



    }

}
