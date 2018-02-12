package repository.employee;

import model.employee.Employee;
import repository.common.CommonRepository;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 3:42 PM
 */
public interface EmployeeRepository extends CommonRepository<Employee> {

    Employee createEmployee(final String name);

    Employee updateEmployeeName(final String name);

    Employee getEmployeeDuties (Long id);

}
