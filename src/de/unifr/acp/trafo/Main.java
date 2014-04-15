package de.unifr.acp.trafo;

import java.io.IOException;
import java.io.InvalidClassException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Main {

    /**
     * Performs an offline transformation of the specified program.
     * @param args
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws NotFoundException, ClassNotFoundException, IOException, CannotCompileException {
        try {
            if (args.length < 1) {
                StringBuilder usage = new StringBuilder();
                usage.append("Usage: x2traverse class [output directory]\n");
                usage.append(" (to transform the class hierarchy rootet at a class)\n");
                usage.append("where options include:\n");
                usage.append(" \n");
                System.out.println(usage);
                System.exit(1);
            }
            String className = args[0];
            String outputDir = (args.length >= 2) ? args[1] : "bin";
            ClassPool defaultPool = ClassPool.getDefault();
            CtClass target = defaultPool.get(className);
            // TransClass.doTransform(target,
            // !target.getSuperclass().equals(objectClass));
        
            TransClass.transformAndFlushHierarchy(defaultPool, className, outputDir, true);

            // condition needed to avoid bug in writing unmodified class files
            // causing invalid class files
            // if (target.isModified()) {
            // target.writeFile(outputDir);
            // }


        } catch (InvalidClassException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            throw e;
        } catch (CannotCompileException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println(e.getReason());
            throw e;
        }

    }

}
