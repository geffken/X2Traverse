package de.unifr.acp.trafo;

import java.util.Set;

import javassist.CtClass;

public class Config {
    // resembles SOOT's argument classes
    Set<CtClass> argumentClasses;

    // resembles SOOT's application classes
    Set<CtClass> applicationClasses;

    // resembles SOOT's library classes
    Set<CtClass> libraryClasses;

    // boolean application mode
    boolean applicationModeEnabled;

    
}
