package springapp.web;

import java.lang.annotation.Documented; 
import java.lang.annotation.Retention; 
import java.lang.annotation.Target; 
import javax.validation.Constraint; 
import javax.validation.Payload; 

import static java.lang.annotation.ElementType.ANNOTATION_TYPE; 
import static java.lang.annotation.ElementType.CONSTRUCTOR; 
import static java.lang.annotation.ElementType.FIELD; 
import static java.lang.annotation.ElementType.METHOD; 
import static java.lang.annotation.ElementType.PARAMETER; 
import static java.lang.annotation.RetentionPolicy.RUNTIME; 
 
/**
 * Validate that the string is a valid URL. 
 * 
 * @author Hardy Ferentschik 
 */ 
@Documented 
@Constraint(validatedBy = URLValidator.class) 
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER }) 
@Retention(RUNTIME) 
public @interface URL { 
 public abstract String protocol() default ""; 
 
 public abstract String host() default ""; 
 
 public abstract int port() default -1; 
 
 public abstract String message() default "{org.hibernate.validator.constraints.URL.message}"; 
 
 public abstract Class<?>[] groups() default { }; 
 
 public abstract Class<? extends Payload>[] payload() default { }; 
 
 /**
  * Defines several {@code @URL} annotations on the same element. 
  */ 
 @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER }) 
 @Retention(RUNTIME) 
 @Documented 
 public @interface List { 
  URL[] value(); 
 } 
}