import java.io.*;

public class GreyImage
{
    public int width;
    public int height;
    public int[][] data;
    public int maxGrey;
    
    public GreyImage(String fileName)
    {
        BufferedReader in;
        
        try {
            in = new BufferedReader(new FileReader(fileName));
                            
            // Read in magic string P2
            {
                String s = in.readLine();
                
                if(!s.equals("P5"))
                    System.out.println("Not a PGM(raw) file.");
            }
            
            // Read in headers
            {
                String s = in.readLine();
                
                // Skip comments
                {
                    while(s.startsWith("#"))
                        s = in.readLine();
                }
                    
                int index = s.indexOf(' ');
    
                String widthString = s.substring(0, index);
                String restString = s.substring(index + 1);        
                
                width = new Integer(widthString).intValue();
                height = new Integer(restString).intValue();
                
                s = in.readLine();
                maxGrey = new Integer(s).intValue();
            }
            
            // Read in actual data
            {
                char rawdata[] = new char[height * width];
                   
                int readSoFar = 0;
                
                for(;;)
                {
                    int numRead = in.read(rawdata, readSoFar, rawdata.length);
                    
                    if(numRead == -1 || (numRead + readSoFar == rawdata.length))
                        break;
                        
                    readSoFar += numRead;
                }
                
                data = new int[height][width];
                
                // Fill up the data
                {
                    int i = 0;
                    
                    for(int y = 0; y < height; y++)
                        for(int x = 0; x < width; x++)
                            data[y][x] = rawdata[i++];
                }
            }   
            
        } catch(IOException e)
        {
            System.out.println("Could not open file: " + fileName);
            System.exit(0);
        }    
    }
        
    public void writeToFile(String fileName)
    {
        DataOutputStream out = null;
        
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            
            out = new DataOutputStream(file);
        } catch(IOException e)
        {
            System.out.println("Could not open file " + fileName + "to write");
            System.exit(1);
        }
        
        try {
            out.writeBytes("P5\n");
            out.writeBytes(new Integer(width).toString() + " " + new Integer(height).toString() + "\n");
            out.writeBytes(new Integer(maxGrey).toString() + "\n");
    
            byte[] rawData = new byte[width * height];
            int i = 0;
            
            for(int y = 0; y < height; y++)
                for(int x = 0; x < width; x++)
                    rawData[i++] = (byte) data[y][x];
                    
            out.write(rawData, 0, rawData.length);
        } catch(IOException e)
        {
            System.out.println("Error writing file.");
            System.exit(1);
        }

    }
        
    public GreyImage(int width, int height, int defaultValue, int maxGrey)
    {
        this.width = width;
        this.height = height;
        
        this.maxGrey = maxGrey;
        
        this.data = new int[height][width];
        
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                data[y][x] = defaultValue;
    }
    
    private GreyImage(int width, int height, int maxGrey)
    {
        this.width = width;
        this.height = height;
        
        this.maxGrey = maxGrey;
    }
    
    public Object clone()
    {
        GreyImage newImage = new GreyImage(width, height, maxGrey);
    
        newImage.data = (int[][]) data.clone();
        
        for(int i = 0; i < newImage.data.length; i++)
            newImage.data[i] = (int[]) data[i].clone();
                
        return newImage;
    }
}




