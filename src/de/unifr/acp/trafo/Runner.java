package de.unifr.acp.trafo;

import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.unifr.acp.runtime.TraversalTarget__;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Runner {
    
    private static ClassPool defaultPool = ClassPool.getDefault();
    
    /**
     * Start annotator that annotates class files with (default) contracts.
     * 
     * @param args
     * @throws CannotCompileException 
     * @throws NotFoundException 
     * @throws Throwable 
     * @throws  
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        Options options = new Options();

//        Option jarOption = new Option("jar", true,
//                "annotates all classes in the jar file");
//        options.addOption(jarOption);
        CommandLineParser parser = new org.apache.commons.cli.GnuParser();
        try {
            CommandLine cmd = parser.parse(options, args);
//            if (cmd.hasOption("jar")) {
//                
//            }
            String[] argList = cmd.getArgs();
            
            if (argList.length < 1) {
                throw new ParseException("Missing class name argument.");
            }
            String className = argList[0];
            String[] targetArgs = new String[argList.length -1]; 
                    
            System.arraycopy(argList, 1, targetArgs, 0, argList.length-1);
            
            javassist.Loader cl = new APCTransLoader(defaultPool, true);

            // run with custom class loader to instrument class at runtime
            cl.run(className, targetArgs);

            
        } catch (ParseException e1) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Runner", options , true);
            System.err.println( "Parsing failed.  Reason: " + e1.getMessage() );
        } catch (Throwable e) {
            System.err.println("Target application threw exception:");
            e.printStackTrace();
            System.err.println(e.getCause());
            System.err.println(e.getMessage());
        }
        


    }

}
