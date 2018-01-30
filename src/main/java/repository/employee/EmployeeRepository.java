package repository.employee;

import model.employee.Employee;
import repository.common.CommonRepository;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 3:42 PM
 */
public interface EmployeeRepository extends CommonRepository<Employee> {

    Employee createEmployee(String name);

    Employee updateEmployeeName(String name);
}
