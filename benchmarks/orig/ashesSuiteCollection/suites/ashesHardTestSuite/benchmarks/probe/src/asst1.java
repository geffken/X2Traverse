/** Name        : Jianlong Zuo
    Student ID. : 9732924
    Course      : cs621B
    Assignment  : No.1      */
/**This is my banchmark for question 2. */

import java.awt.*;
import java.io.*;
import java.lang.*;

public class asst1 {
     
     public static final int NUMBER = 5;
     public static final int BLOCKSIZE = 80*NUMBER + 1;
     public static byte[] block = new byte[BLOCKSIZE];
     public static final float RECORDS = 885;
         
  public static void main(String args[]) { 
     
        float alpha=0, pi=0, opi=0;
        int overflownum=0;
        String wholeRec, phone;
        int prob=0, total=0;

   try { 
        RandomAccessFile tbookd =
               new RandomAccessFile("tbook", "r");
                  
        System.out.println("The program is running NOW."); 
        for(int n=0; n<RECORDS; n++){
         tbookd.seek(n*81);
         wholeRec = tbookd.readLine();  
         phone = wholeRec.substring(73,80);
         System.out.print(".");
         prob=SearchAndCheck(phone);
         total += prob; 
         if (prob > 1)   overflownum++; }
       System.out.println("\ntotal number of probe = " + total);
       System.out.println("overflow= " + overflownum); 
        alpha = RECORDS/(179*5);
        pi = total/RECORDS;
        opi = 1 + overflownum/RECORDS;
       System.out.println("alpha= " + alpha);
       System.out.println("pi= " + pi);
       System.out.println("opi= " + opi);
       
       /* theoretical approximation to opi */
        float thpi = 0;
        float blocksize = 5;   
        for(int k=0; k<=5; k++)
            thpi += (5-k)*poisson(k, alpha*5);
        thpi = thpi - (1-alpha)*5;
        thpi = (thpi/alpha)/blocksize;
        thpi = 1 + thpi;
        System.out.println("theoretical approximation of opi= "
              + thpi);     
        

  } catch (IOException err)
  {
    System.err.println(err.toString());
  }  
     
 }


 public static int SearchAndCheck(String phonenum) throws IOException 
   {  int flag=0, flag2=0;
     int inputdata=0;
     long blocknum=0;
     long pos=0;
     int probnum = 0;
     String wholeBlock, phone;
  
     RandomAccessFile in = 
               new RandomAccessFile("hash", "r");
     	
     inputdata = Integer.parseInt(phonenum);
     blocknum = inputdata % 179;
     in.seek(blocknum * BLOCKSIZE);
     in.read(block);
     wholeBlock = byte2string(block);
   
     for(int i=0; i<NUMBER; i++) 
     { 
        phone = wholeBlock.substring((73+80*i), (80+80*i));      
        if (phone.compareTo(phonenum) == 0)
         { flag = 1;
           probnum = 1;      
           break;
         }
       else 
           continue;  
     }     


    if (flag == 0)
      {probnum = 1;
       for( long j=(blocknum-1); j>=0; j--)
        { in.seek(j*BLOCKSIZE);
          in.read(block);
          wholeBlock = byte2string(block);   
          
        for(int i=0; i<NUMBER; i++)
         { phone = wholeBlock.substring((73+80*i), (80+80*i));
         if (phone.compareTo(phonenum) == 0)
          { flag = 1;
            flag2 =1;
            probnum += 1;      
            break;
          }
        else 
             continue;  
        }
        if(flag2 == 1) break;
        probnum++; 
     }
  }       
          
         
   if(flag==0)
    {
      for( int m=178; m>blocknum; m--)
       { in.seek(m*BLOCKSIZE);
         in.read(block);
         wholeBlock = byte2string(block);
         
         for(int i=0; i<NUMBER; i++)
         {phone = wholeBlock.substring((73+80*i), (80+80*i));
         if (phone.compareTo(phonenum) == 0)
          { flag = 1;
            flag2 =1;
            probnum += 1;      
            break;
          }
        else 
             continue;  
        }
        if(flag2 == 1) break;
        probnum++; 
     }
  }       
    in.close();
     return  probnum;
}
    
  
 public static double poisson(int m, float n)
  {  double result=0; 
     result = Math.exp(-n)*Math.pow(n,(float)m)/factorial(m);
     return result;  }
     
 public static int factorial(int k)        
  {  if (k==0) return 1;
     else return (k*factorial(k-1)); }

 
 
 static String byte2string(byte[] b)
 { int i;
   String s="";
   for(i=0; i<b.length; i++)
     s = s + (char) b[i];
   return s;
 }

}
    
 



