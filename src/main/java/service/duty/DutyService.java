package service.duty;

import model.duty.Duty;
import model.duty.DutyType;
import service.common.factory.Service;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:03 PM
 */
public interface DutyService extends Service {

    //todo return duty after creation
    //todo first arg should be id or primitive type, than objects - everywhere
    Duty createDuties(Long employeeId, DutyType dutyType);

    //todo rename
    List<Duty> getEmployeeDuties(Long employeeId);

    List<Duty> getAllDuties();

    void removeById(Long id);

    void removeAllByEmployee(Long employeeId);

}
