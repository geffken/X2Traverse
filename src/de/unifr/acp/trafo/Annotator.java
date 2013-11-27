package de.unifr.acp.trafo;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Annotator {
    
    /**
     * @param args
     * @throws NotFoundException 
     * @throws CannotCompileException 
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws NotFoundException, ClassNotFoundException, IOException, CannotCompileException {
        try {
            String className = args[0];
            String outputDir = (args.length >= 2) ? args[1] : "bin";
            //ClassPool defaultPool = ClassPool.getDefault();
            //CtClass target = defaultPool.get(className);
            TransClass.defaultAnnotateAndFlushHierarchy(className, outputDir);
        } catch (ArrayIndexOutOfBoundsException e) {
            StringBuilder usage = new StringBuilder();
            usage.append("Usage: Annotator class [output directory]\n");
            usage.append(" (to annotate the class hierarchy rootet at a class)\n");
            usage.append("where options include:\n");
            usage.append(" \n");
            System.out.println(usage);
        }

    }



}
