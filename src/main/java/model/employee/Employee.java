package model.employee;

import model.common.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .toString();
    }

}
