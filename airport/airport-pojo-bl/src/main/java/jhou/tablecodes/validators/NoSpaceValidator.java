package jhou.tablecodes.validators;

import static ua.com.fielden.platform.error.Result.failure;
import static ua.com.fielden.platform.error.Result.successful;

import java.lang.annotation.Annotation;
import java.util.Set;

import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.validation.IBeforeChangeEventHandler;
import ua.com.fielden.platform.error.Result;

public class NoSpaceValidator implements IBeforeChangeEventHandler<String> {

    @Override
    public Result handle(final MetaProperty<String> property, final String newValue, final Set<Annotation> mutatorAnnotations) {
        if (newValue.contains(" ")) {
            return failure("No spaces allowed.");
        }
        return successful("Ok.");
    }

}
