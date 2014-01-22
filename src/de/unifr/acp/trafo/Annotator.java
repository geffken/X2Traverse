package de.unifr.acp.trafo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.unifr.acp.annot.Grant;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import static de.unifr.acp.trafo.TranslationHelper.filterClassesToTransform;
import static de.unifr.acp.trafo.TranslationHelper.parameterCountOf;

/**
 * Main class of the annotator that adds (default) APCs to classes.
 * 
 * @author geffken
 * 
 */
public class Annotator {

    static {
        try {
            logger = Logger.getLogger("de.unifr.acp.trafo.Annotator");
            fh = new FileHandler("mylog.txt");
            Annotator.logger.addHandler(Annotator.fh);
            Annotator.logger.setLevel(Level.ALL);
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Logger logger;
    private static FileHandler fh;
    public final String FILTER_TRANSFORM_REGEX_DEFAULT = "java\\..*";
    private String filterTransformRegex = FILTER_TRANSFORM_REGEX_DEFAULT;

    /**
     * Start annotator that annotates class files with (default) contracts.
     * 
     * @param args
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws NotFoundException,
            ClassNotFoundException, IOException, CannotCompileException {
        try {
            String className = args[0];
            String outputDir = (args.length >= 2) ? args[1] : "bin";
            Annotator.defaultAnnotateAndFlushHierarchy(className, outputDir);
        } catch (ArrayIndexOutOfBoundsException e) {
            StringBuilder usage = new StringBuilder();
            usage.append("Usage: Annotator class [output directory]\n");
            usage.append(" (to annotate the class hierarchy rooted at a class)\n");
            usage.append("where options include:\n");
            usage.append(" \n");
            System.out.println(usage);
        }

    }

    /**
     * Adds default annotations to all classes in the class hierarchy rooted at
     * the of the specified class name and writes the resulting classes to the
     * specified output directory.
     * 
     * @param className
     *            the root class
     * @param outputDir
     *            the output directory
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     * @throws ClassNotFoundException
     */
    public static void defaultAnnotateAndFlushHierarchy(String className,
            String outputDir) throws NotFoundException, IOException,
            CannotCompileException, ClassNotFoundException {
        final Set<CtClass> transformed = defaultAnnotateHierarchy(className);
        TransClass.flushClassesToDir(transformed, outputDir);
    }

    /**
     * Adds default annotations to all classes in the class hierarchy rooted at
     * the of the specified class name.
     * 
     * @param className
     *            the root class
     * @return the set of annotated classes
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     * @throws ClassNotFoundException
     */
    public static Set<CtClass> defaultAnnotateHierarchy(String className)
            throws NotFoundException, IOException, CannotCompileException,
            ClassNotFoundException {
        final ClassPool defaultPool = ClassPool.getDefault();
        final TransClass tc = new TransClass();
        final Set<CtClass> reachable = tc.computeReachableClasses(
                defaultPool.get(className), false);
        final Set<CtClass> transformed = new Annotator()
                .performDefaultAnnotatation(reachable);
        return transformed;
    }

    /**
     * Adds default annotations to each class in the specified set of classes.
     * 
     * @param classes
     *            the set of classes to annotate
     * @return the set of annotated classes (conservative approximation)
     * @throws NotFoundException
     * @throws IOException
     * @throws CannotCompileException
     * @throws ClassNotFoundException
     */
    protected Set<CtClass> performDefaultAnnotatation(Set<CtClass> classes)
            throws NotFoundException {
        Set<CtClass> toTransform = filterClassesToTransform(classes,
                this.filterTransformRegex);

        if (logger.isLoggable(Level.FINEST)) {
            StringBuilder sb = new StringBuilder();
            for (CtClass visitedClazz : toTransform) {
                sb.append(visitedClazz.getName() + "\n");
            }
            logger.finest("Classes to transform:\n" + sb.toString());
        }

        final HashSet<CtClass> transformed = new HashSet<CtClass>();
        for (CtClass cc : toTransform) {
            if (cc.isFrozen()) {
                continue;
            }

            // collect all methods and constructors
            // List<CtMethod> ownMethods =
            // Arrays.asList(cc.getDeclaredMethods());
            // List<CtConstructor> ctors = Arrays.asList(cc.getConstructors());
            // List<CtBehavior> methodsAndCtors = new ArrayList<CtBehavior>();
            // methodsAndCtors.addAll(ownMethods);
            // methodsAndCtors.addAll(ctors);

            ClassFile ccFile = cc.getClassFile();
            ConstPool constpool = ccFile.getConstPool();
            for (CtBehavior methodOrCtor : cc.getDeclaredBehaviors()) {
                logger.fine("Annotating method or ctor: "
                        + ((methodOrCtor != null) ? methodOrCtor.getLongName()
                                : methodOrCtor));
                // create and add the method-level annotation
                AnnotationsAttribute attr = new AnnotationsAttribute(constpool,
                        AnnotationsAttribute.visibleTag);
                Annotation annot = new Annotation(Grant.class.getName(),
                        constpool);
                annot.addMemberValue("value", new StringMemberValue("this.*",
                        constpool));
                attr.addAnnotation(annot);
                methodOrCtor.getMethodInfo().addAttribute(attr);

                // create and add the parameter-level annotation
                final CtClass[] parameterTypes = methodOrCtor
                        .getParameterTypes();
                Annotation parameterAnnotation = new Annotation(
                        Grant.class.getName(), constpool);
                StringMemberValue parameterMemberValue = new StringMemberValue(
                        "*", constpool);
                parameterAnnotation.addMemberValue("value",
                        parameterMemberValue);
                logger.fine("parameterAnnotation: " + parameterAnnotation);

                AttributeInfo paramAttributeInfo = methodOrCtor.getMethodInfo()
                        .getAttribute(ParameterAnnotationsAttribute.visibleTag); // or
                                                                                 // invisibleTag
                logger.finest("paramAttributeInfo: " + paramAttributeInfo);

                if (paramAttributeInfo != null) {
                    ParameterAnnotationsAttribute parameterAtrribute = ((ParameterAnnotationsAttribute) paramAttributeInfo);
                    Annotation[][] paramAnnots = parameterAtrribute
                            .getAnnotations();
                    for (int orderNum = 0; orderNum < paramAnnots.length; orderNum++) {
                        Annotation[] addAnno = paramAnnots[orderNum];
                        if (!parameterTypes[orderNum].isPrimitive()) {
                            Annotation[] newAnno = null;
                            if (addAnno.length == 0) {
                                newAnno = new Annotation[1];
                            } else {
                                newAnno = Arrays.copyOf(addAnno,
                                        addAnno.length + 1);
                            }
                            newAnno[addAnno.length] = parameterAnnotation;
                            paramAnnots[orderNum] = newAnno;
                        }
                    }
                    parameterAtrribute.setAnnotations(paramAnnots);
                } else {
                    ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(
                            constpool, ParameterAnnotationsAttribute.visibleTag);
                    Annotation[][] paramAnnots = new Annotation[parameterCountOf(methodOrCtor)][];
                    for (int orderNum = 0; orderNum < paramAnnots.length; orderNum++) {
                        Annotation[] annots = { parameterAnnotation };
                        if (parameterTypes[orderNum].isPrimitive()) {
                            annots = new Annotation[0];
                        }
                        paramAnnots[orderNum] = annots;
                    }
                    parameterAtrribute.setAnnotations(paramAnnots);
                    methodOrCtor.getMethodInfo().addAttribute(
                            parameterAtrribute);
                }
            }

        }

        // conservatively assume all classes have been transformed
        transformed.addAll(toTransform);
        return transformed;
    }

}
