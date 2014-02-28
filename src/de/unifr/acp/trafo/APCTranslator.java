package de.unifr.acp.trafo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import de.unifr.acp.runtime.Global;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;

public class APCTranslator implements Translator {
    private static String filterVisitRegex = Global.FILTER_VISIT_REGEX_DEFAULT;
    TransClass tc = null;
    javassist.Loader loader;

    public APCTranslator(javassist.Loader loader) {
        this.loader = loader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoad(ClassPool cp, String className)
            throws NotFoundException, CannotCompileException {

        CtClass target = cp.get(className);

        try {
            if (!className.matches(filterVisitRegex)) {
                tc.transformClass(target, true);
            }
        } catch (ClassNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
//        catch (CannotCompileException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public void start(ClassPool cp) throws NotFoundException,
            CannotCompileException {
        tc = new TransClass(cp, false);
    }

}
