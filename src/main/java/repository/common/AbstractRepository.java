package repository.common;

import model.common.AbstractModel;
import repository.common.exception.DataSourceException;

import java.util.Collection;

/**
 * Created by araksgyulumyan
 * Date - 2/11/18
 * Time - 1:28 PM
 */
public abstract class AbstractRepository {
    protected void assertCollectionIsNotEmpty(Collection<? extends AbstractModel> collection) {
        if (collection.isEmpty()) {
            throw new DataSourceException("No results are found");
        }
    }
}
