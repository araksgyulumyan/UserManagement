package service.employee;

import model.employee.Employee;
import org.apache.commons.lang3.StringUtils;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryImpl;
import service.common.exception.ServiceRuntimeException;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 3:40 PM
 */
public class EmployeeServiceImpl implements EmployeeService {

    // region Dependencies
    private final EmployeeRepository employeeRepository;
    // endregion

    // region Constructors
    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
    }
    // endregion

    // region Interface methods overrides
    @Override
    public Employee createEmployee(String name) {
        assertNameNotEmpty(name);
        return employeeRepository.createEmployee(name);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    //endregion

    // region Utility methods
    private void assertNameNotEmpty(final String name) {
        if (StringUtils.isEmpty(name)) {
            throw new ServiceRuntimeException("Employee name should not be empty");
        }
    }
    // endregion
}
