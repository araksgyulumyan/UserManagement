package repository.common;

import model.common.AbstractModel;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 1/28/18
 * Time - 10:49 PM
 */
public interface CommonRepository<T extends AbstractModel> {

    List<T> findAll();

    T findById(final Long id);

    void removeById(final Long id);
}
