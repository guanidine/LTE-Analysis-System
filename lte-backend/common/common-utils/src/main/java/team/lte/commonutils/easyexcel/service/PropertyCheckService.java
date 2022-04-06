package team.lte.commonutils.easyexcel.service;

import com.alibaba.excel.annotation.ExcelProperty;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * 属性校验。校验数据是否符合实体类中@ExcelProperty注释所定义的规则。
 */
public class PropertyCheckService {

    private PropertyCheckService() {}

    private static final class ValidatorHolder {
        private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static <T> String check(T object) throws NoSuchFieldException {
        StringBuilder result = new StringBuilder();
        Set<ConstraintViolation<T>> set = ValidatorHolder.VALIDATOR.validate(object, Default.class);
        if (set != null && !set.isEmpty()) {
            for (ConstraintViolation<T> cv : set) {
                Field declaredField = object.getClass().getDeclaredField(cv.getPropertyPath().toString());
                ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                result.append(annotation.value()[0]).append(cv.getMessage()).append(";");
            }
        }
        return result.toString();
    }

}
