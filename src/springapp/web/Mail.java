package springapp.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MailConstraintValidator.class)
@Documented
public @interface Mail {

    String message() default "Adresse mail incorrecte";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}