package model.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:40 PM
 */
public abstract class AbstractModel {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
