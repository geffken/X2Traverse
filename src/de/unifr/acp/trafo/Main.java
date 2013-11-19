package de.unifr.acp.trafo;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Main {

    /**
     * @param args
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws NotFoundException, ClassNotFoundException, IOException, CannotCompileException {
        String className = args[0];
        ClassPool defaultPool = ClassPool.getDefault();
        CtClass target = defaultPool.get(className);
//        TransClass.doTransform(target, !target.getSuperclass().equals(objectClass));
        TransClass.transformHierarchy(className);

        // condition needed to avoid bug in writing unmodified class files causing invalid class files
        if (target.isModified()) { 
            target.writeFile("bin");
        }

    }

}
