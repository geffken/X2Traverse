package de.unifr.acp.trafo;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;

/**
 * This is a custom loader that extends Javassist's loader with a translation
 * phase and records alread loaded classes.
 * These 
 */
public class APCTransLoader extends Loader {
    
    // a set of already loaded classes (strongly referred as we assume classes
    // not to be garbage collected) 
    // only already loaded classes can be roots into the heap and need to be
    // traverse on contract installation
    // this is a vector as we need a synchronized set here but
    // no contains check (provided by findLoadedClass(String))
//    private Vector<Class<?>> classesAlreadyLoaded = new Vector<Class<?>>(); 

    
//    public Vector<Class<?>> getClassesAlreadyLoaded() {
//        return classesAlreadyLoaded;
//    }

    /*
     * Call MyApp.main().
     */
    public static void main(String[] args) throws Throwable {
        APCTransLoader s = new APCTransLoader(ClassPool.getDefault());
        Class c = s.loadClass("MyApp");
        c.getDeclaredMethod("main", new Class[] { String[].class }).invoke(
                null, new Object[] { args });
    }

    // public APCLoader() throws NotFoundException {
    // pool = new ClassPool();
    // //pool.insertClassPath("./class"); // MyApp.class must be there.
    // }

    public APCTransLoader(ClassPool cp) throws NotFoundException,
            CannotCompileException {
        super(cp);
        this.addTranslator(cp, new APCTranslator(this));
    }
    
    @Override
    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = super.loadClass(name);
        return clazz;
    }
    
    @Override
    public synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
        Class<?> clazz = super.loadClass(name, resolve);
        return clazz;
        } catch (Exception e) {
            throw e;
        }
    }

}
