package model.employee;

import model.common.AbstractModel;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:20 PM
 */
public class Employee extends AbstractModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
