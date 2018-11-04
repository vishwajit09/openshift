package com.vis.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.ws.rs.NameBinding;

@NameBinding      // its bind the anotation to filter and this will make its go through this 
@Retention(RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})

public @interface Secured {

}
