import model.duty.Duty;
import model.duty.DutyType;
import model.employee.Employee;
import service.common.factory.ServiceFactory;
import service.common.model.ServiceType;
import service.duty.DutyService;
import service.employee.EmployeeService;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 8:07 PM
 */
public class UserManagementApplication {

    private static final Integer EMPLOYEES_COUNT = 2;
    private static final Integer DUTIES_COUNT = 3;

    public static void main(String[] args) {
        ServiceFactory serviceFactory = new ServiceFactory();
//        AdminService adminService = (AdminService) serviceFactory.getService(ServiceType.ADMIN);

        EmployeeService employeeService = (EmployeeService) serviceFactory.getService(ServiceType.EMPLOYEE);
        DutyService dutyService = (DutyService) serviceFactory.getService(ServiceType.DUTY);


        for (int i = 0; i < 2; i++) {
            Employee employee = employeeService.createEmployee("employee" + i);
            System.out.println(employee.toString());
            for (int j = 0; j < 3; j++) {
                Duty duty = dutyService.createDuties(employee.getId(), DutyType.BARMAN);
                System.out.println(duty.toString());
            }
        }


        System.out.println("asdasd");
    }

}
