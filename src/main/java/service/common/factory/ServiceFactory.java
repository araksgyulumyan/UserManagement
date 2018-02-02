package service.common.factory;

import service.admin.AdminServiceImpl;
import service.common.exception.ServiceRuntimeException;
import service.common.model.ServiceType;
import service.duty.DutyServiceImpl;
import service.employee.EmployeeServiceImpl;

/**
 * Created by araksgyulumyan
 * Date - 2/2/18
 * Time - 5:15 PM
 */
public class ServiceFactory {

    //use getShape method to get object of type shape
    public Service getService(ServiceType serviceType) {
        switch (serviceType) {
            case ADMIN:
                return new AdminServiceImpl();
            case EMPLOYEE:
                return new EmployeeServiceImpl();
            case DUTY:
                return new DutyServiceImpl();
            default:
                throw new ServiceRuntimeException("No such service exists - " + serviceType.name());
        }
    }
}
