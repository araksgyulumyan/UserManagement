package repository.duty;

import model.duty.Duty;
import model.duty.DutyType;
import repository.common.CommonRepository;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 3:42 PM
 */
public interface DutyRepository extends CommonRepository<Duty> {

    Duty createDuty(final DutyType type, final Long employeeId);

    Duty updateDutyType(final DutyType type);

    Duty updateDutyEmployeeId(final Long employeeId);

}
