package de.unifr.acp.runtime.annot;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
public @interface Grant {
        String value() default "";
}