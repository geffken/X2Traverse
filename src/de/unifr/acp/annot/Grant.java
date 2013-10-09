package de.unifr.acp.annot;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Grant {
        String value() default "";
}