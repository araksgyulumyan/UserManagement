import model.employee.Employee;
import service.admin.AdminService;
import service.admin.AdminServiceImpl;
import service.common.factory.ServiceFactory;
import service.common.model.ServiceType;
import service.employee.EmployeeService;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 8:07 PM
 */
public class UserManagementApplication {


    public static void main(String[] args) {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = (AdminService) serviceFactory.getService(ServiceType.ADMIN);
        System.out.println("asdasd");
    }

}
