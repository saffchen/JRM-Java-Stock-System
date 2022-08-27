package saffchen.util.validation;

/**
 * @author alex_jd on 8/10/22
 * @project JRM-Java-Stock-System
 */

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import saffchen.entities.HasId;
import saffchen.exception.IllegalRequestDataException;

@UtilityClass
@Slf4j
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, Long id) {
        if (bean.isNew()) {
            bean.setId(id);
            log.info("set new id={} for HasId", id);
        } else if (bean.id() != id) {
            log.info("bean id != id");
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, Long id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }
}
