package model.duty;

import model.common.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:20 PM
 */
public class Duty extends AbstractModel {

    // Properties
    private DutyType type;

    private Long employeeId;

    // Getters and setters
    public DutyType getType() {
        return type;
    }

    public void setType(DutyType type) {
        this.type = type;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(final Long employeeId) {
        this.employeeId = employeeId;
    }

    // Methods overrides
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("type", type)
                .append("employeeId", employeeId)
                .toString();
    }
}
