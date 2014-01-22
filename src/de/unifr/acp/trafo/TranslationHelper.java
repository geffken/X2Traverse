package de.unifr.acp.trafo;

import java.util.HashSet;
import java.util.Set;

import javassist.CtBehavior;
import javassist.CtClass;

public class TranslationHelper {
    
    public static Set<CtClass> filterClassesToTransform(Set<CtClass> visited, String filterTransformRegex) {
        HashSet<CtClass> toTransform = new HashSet<CtClass>();
        for (CtClass clazz : visited) {
            if (!clazz.getName().matches(filterTransformRegex)) {
                toTransform.add(clazz);
            }
        }
        return toTransform;
    }
    
    public static int parameterCountOf(CtBehavior methodOrCtor) {
        return methodOrCtor.getAvailableParameterAnnotations().length;
    }

}
