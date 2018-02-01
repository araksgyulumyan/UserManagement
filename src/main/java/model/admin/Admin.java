package model.admin;

import model.common.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 9:20 PM
 */
public class Admin extends AbstractModel {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("username", username)
                .toString();
    }
}
