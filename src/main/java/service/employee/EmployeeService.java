package service.employee;

import model.employee.Employee;
import service.common.factory.Service;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:04 PM
 */
public interface EmployeeService extends Service {

    Employee createEmployee(final String name);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);
}
