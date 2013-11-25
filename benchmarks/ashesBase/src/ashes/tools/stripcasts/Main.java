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

package ashes.tools.stripcasts;
 
import soot.*;
import soot.jimple.*;
import soot.grimp.*;
import java.io.*;
import java.util.*;
import soot.util.*;

/**
   Cast stripper.  Removes the reference casts from the given application or classfile.  Note that this usually generates
   classfiles which still run, but which no longer verify. 
 */
public class Main
{    
    public static void main(String[] args) 
    {
        if(args.length == 0)
        {
            System.out.println("Syntax: java ashes.tools.stripcasts.Main [soot options]");
            System.exit(0);
        }            
        
        Scene.v().getPack("jtp").add(new Transform("jtp.stripper", Stripper.v()));
        soot.Main.main(args);
    }
}


class Stripper extends BodyTransformer
{
    private static Stripper instance = new Stripper();
    private Stripper() {}
    static String oldPath;
    
    public static Stripper v() { return instance; }
    
    protected void internalTransform(Body b, String phaseName, Map options)
    {        
        if (soot.Main.isVerbose) 
            System.out.println("[" + b.getMethod().getName() + "] Stripping casts...");

        JimpleBody body = (JimpleBody) b;
        
        Iterator stmtIt = body.getUnits().snapshotIterator();
        
        while(stmtIt.hasNext())
        {
            Stmt s = (Stmt) stmtIt.next();
            
            if(s instanceof AssignStmt)
            {
                Value rightOp = ((AssignStmt) s).getRightOp();
                
                if(rightOp instanceof CastExpr)
                {
                    CastExpr castExpr = (CastExpr) rightOp;
                    
                    if(castExpr.getCastType() instanceof RefType ||
                        castExpr.getCastType() instanceof ArrayType)
                    {
                        Stmt newStmt = Jimple.v().newAssignStmt(((AssignStmt) s).getLeftOp(), castExpr.getOp());
                        body.getUnits().swapWith(s, newStmt);
                    }
                }
            }
        }
    }
}


