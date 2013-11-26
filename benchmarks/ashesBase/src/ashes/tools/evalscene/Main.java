/* Ashes - a J*va Compiler Infrastructure
 * Copyright (C) 1997-1999 Raja Vallee-Rai
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */

package ashes.tools.evalscene;

 
import soot.*;
import soot.jimple.*;
import soot.grimp.*;
import java.io.*;
import java.text.*;

import java.util.*;
import soot.util.*;

/**
   Scene evaluator application.  Calculates the number of classes, methods and fields in a given application.
   
 */
public class Main
{    
    public static void main(String[] args) 
    {
        if(args.length == 0)
        {
            System.out.println("Syntax: java ashes.tools.evalscene.Main --app mainClass [soot options]");
            System.exit(0);
        }            
        
        Scene.v().getPack("wjtp").add(new Transform("wjtp.profiler", Evaluator.v()));
        soot.Main.main(args);
    }
}


class Evaluator extends SceneTransformer
{
    private static Evaluator instance = new Evaluator();
    private Evaluator() {}
    static String oldPath;
    
    public static Evaluator v() { return instance; }
    
    protected void internalTransform(String phaseName, Map options)
    {        
        long classCount = 0;
        long stmtCount = 0;
        long methodCount = 0;
        
        // Pre-process each class, constructing the invokeToNumberMap
        {
            Iterator classIt = Scene.v().getApplicationClasses().iterator();
            
            while(classIt.hasNext())
            {
                SootClass sClass = (SootClass) classIt.next();                                       
                
                classCount++;
                
                Iterator methodIt = sClass.getMethods().iterator();
                
                while(methodIt.hasNext())
                {
                    SootMethod m = (SootMethod) methodIt.next();

                    methodCount++;
                    
                    if(!m.isConcrete())
                        continue;
            
                    JimpleBody body = (JimpleBody) m.retrieveActiveBody();
                    stmtCount += body.getUnits().size();
                }
            }
        }

        DecimalFormat format = new DecimalFormat("0.0");
        
        System.out.println("Classes: \t" + classCount);
        System.out.println("Methods: \t" + methodCount + " (" + format.format((double) methodCount / classCount) + " methods/class)" );
        System.out.println("Stmts:   \t" + stmtCount +  " (" + format.format((double) stmtCount / methodCount) + " units/methods)");
        System.exit(0);
    }
}









