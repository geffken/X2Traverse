package ashes.tools.unitweigher;

import java.io.*;
import java.text.*;

public class CounterClass
{
    public static long[] unitCounts;
    public static boolean isProfiling;
    
    public static void startProfiling()    
    {
    }
    
    public static void stopProfiling()
    {
        isProfiling = false;
        
        // Open file
            FileOutputStream streamOut = null;
            PrintWriter out = null;
            BufferedReader in = null;
            
            try {
                in = new BufferedReader(new FileReader("signatures.txt"));
                streamOut = new FileOutputStream("tagfile");
                out = new PrintWriter(streamOut);
            }
            catch (IOException e)
            {
                System.out.println("Cannot output tagfile");
            }
        
        // Write out profiling information
        {
            for (int i = 0; i < unitCounts.length; i++)
            {
                String s = null;
                try
                {
                    s = in.readLine().trim();
                }
                catch(IOException e) {}
                out.println(s + "/count.l: " + unitCounts[i]);
            }
        }
        
        // Close file
            try     
            {   
                out.flush();
                streamOut.close();
                in.close();
            }
            catch (IOException e)
            {
                System.out.println("Cannot output file tagfile");
            }

    }
}




