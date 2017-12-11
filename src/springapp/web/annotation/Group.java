package springapp.web.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupConstraintValidator.class)
@Documented
public @interface Group {

    String message() default "Aucun groupe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
