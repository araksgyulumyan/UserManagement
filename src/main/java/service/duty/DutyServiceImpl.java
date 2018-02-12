package service.duty;

import model.duty.Duty;
import model.duty.DutyType;
import repository.duty.DutyRepository;
import repository.duty.DutyRepositoryImpl;
import service.common.exception.ServiceRuntimeException;

import java.util.List;
import java.util.Objects;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 4:38 PM
 */
public class DutyServiceImpl implements DutyService {

    private final DutyRepository dutyRepository;

    public DutyServiceImpl() {
        this.dutyRepository = new DutyRepositoryImpl();
    }

    @Override
    public Duty createDuties(Long employeeId, DutyType dutyType) {
        assertIdNotEmpty(employeeId);
        assertDutyTypeNotEmpty(dutyType);
        dutyRepository.createDuty(employeeId, dutyType);
        return getLastCreatedDuty();
    }

    @Override
    public List<Duty> getEmployeeDuties(Long employeeId) {
        assertIdNotEmpty(employeeId);
        return dutyRepository.getDutiesOfEmployee(employeeId);
    }

    @Override
    public List<Duty> getAllDuties() {
        return null;
    }


    @Override
    public void removeById(Long id) {
        assertIdNotEmpty(id);
        dutyRepository.removeById(id);
    }

    @Override
    public void removeAllByEmployee(Long employeeId) {
        assertIdNotEmpty(employeeId);
        List<Duty> employeeDuties = getEmployeeDuties(employeeId);
        for (Duty duty : employeeDuties) {
            removeById(duty.getId());
        }
    }

    private Duty getLastCreatedDuty() {
        return dutyRepository.getLastInsertedDuty();
    }

    // Utility methods
    private void assertIdNotEmpty(final Long id) {
        if (Objects.isNull(id)) {
            throw new ServiceRuntimeException("Duty id should not be empty");
        }
    }

    private void assertDutyTypeNotEmpty(final DutyType dutyType) {
        if (dutyType == null) {
            throw new ServiceRuntimeException("Duty type should not be empty");
        }
    }
}
