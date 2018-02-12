package repository.duty;

import model.duty.Duty;
import model.duty.DutyType;
import repository.common.CommonRepository;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 3:42 PM
 */
public interface DutyRepository extends CommonRepository<Duty> {

    Duty createDuty(final Long employeeId, final DutyType type);

    List<Duty> getDutiesOfEmployee(final Long employeeId);

    Duty getLastInsertedDuty();
}
