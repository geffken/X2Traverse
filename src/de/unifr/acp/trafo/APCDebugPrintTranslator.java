package de.unifr.acp.trafo;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.Translator;

public class APCDebugPrintTranslator implements Translator {
    @Override
    public void onLoad(ClassPool cp, String className) throws NotFoundException,
            CannotCompileException {
        System.out.println(className);
    }

    @Override
    public void start(ClassPool cp) throws NotFoundException,
            CannotCompileException {
    }

}
