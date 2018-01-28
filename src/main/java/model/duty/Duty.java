package model.duty;

import model.common.AbstractModel;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:20 PM
 */
public class Duty extends AbstractModel {

    private DutyType type;
    private Long employee_id;

    public DutyType getType() {
        return type;
    }

    public void setType(DutyType type) {
        this.type = type;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }
}
