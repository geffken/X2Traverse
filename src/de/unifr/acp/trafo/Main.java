package de.unifr.acp.trafo;

import java.io.IOException;
import java.io.InvalidClassException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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
		Options options = new Options();

		Option logOption = new Option("log", false, "enables logging");
		options.addOption(logOption);

		CommandLineParser parser = new org.apache.commons.cli.GnuParser();
		try {
			CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption("log")) {
				TransClass.enableLogging();
			}

			String[] argList = cmd.getArgs();

			if (argList.length < 1) {
				throw new ParseException("Missing class name argument.");
			}

			String className = argList[0];
			String outputDir = (argList.length >= 2) ? argList[1]
					: "instrumented_classes";
            ClassPool defaultPool = ClassPool.getDefault();
        
            TransClass.transformAndFlushHierarchy(defaultPool, className, outputDir, true);

		} catch (ParseException e1) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Main class [output directory]", "to transform the class hierarchy rootet at a class", options, "",
					true);
			System.err.println("Parsing failed.  Reason: " + e1.getMessage());
		}
    }

}
