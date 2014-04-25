// Image529 project
// 
// Takes four files describing puzzle pieces and attempts to determine
// how they fit together.
//
// Written by Madeleine Mony

public class sort
{   
    static final int UNKNOWN = 0;
    static final int STRAIGHT = 1;
    static final int CONCAVE = 2;
    static final int CONVEX = 3;
    
    public static void main(String[] argv)
    {
        System.out.println("Reading in images...");
        
        GreyImage[] piece = new GreyImage[4];
        piece[0] = new GreyImage("out1fix.pgm");
        piece[1] = new GreyImage("out2fix.pgm");
        piece[2] = new GreyImage("out3fix.pgm");
        piece[3] = new GreyImage("out4fix.pgm");

	System.out.println("Starting processing...");
        
        int[][] lengths = new int[4][4];        
        Coord[][] corners = new Coord[4][4];
        Coord[][][] edges = separateEdges(piece, lengths, corners);
        float[][] distances = distancesOfCorners(corners); 
        int[][] concavity = determineConcavity(lengths, corners, edges, distances, piece);
        int[][] holeSize = new int[4][4];
        float[][][] cornerToMin = new float[4][4][2];
        float[][] minHoleSize = determineMinHoleSize(edges, concavity, holeSize, cornerToMin);
        
        for(int i=0 ; i<4 ; i++)
        {
            for(int e=0 ; e<4 ; e++)
            {
                //if(concavity[i][e]==UNKNOWN)
                //    System.out.println("Piece " +i +" edge " +e +" is unknown");
                //if(concavity[i][e]==STRAIGHT)
                //    System.out.println("Piece " +i +" edge " +e +" is STRAIGHT");
                //if(concavity[i][e]==CONCAVE)
                //    System.out.println("Piece " +i +" edge " +e +" is CONCAVE");
                //if(concavity[i][e]==CONVEX)
                //    System.out.println("Piece " +i +" edge " +e +" is CONVEX");
                //System.out.println("Piece " +i +" edge " +e +" has SHORT length " +distances[i][e]);
                //System.out.println("Piece " +i +" edge " +e +" has length " +lengths[i][e]);
                //System.out.println("Piece " +i +" corner " +e +" is at " 
                //        +corners[i][e].x +"," +corners[i][e].y);
                //System.out.println("Piece " +i +" edge " +e +" has min dist " +minHoleSize[i][e]);
                //System.out.println("Piece " +i +" edge " +e +" has holeSize " +holeSize[i][e]);
                //System.out.println("Piece " +i +" edge " +e +" has cornerToMin0 = " +cornerToMin[i][e][0] 
                //                        +" cornerToMin1 = " + cornerToMin[i][e][1]);
            }
        }
        
        // Now I have to do all of the matching...        
        
        boolean[][] isPossible = possibleCombos(concavity);
        
        // Match using lengths and distances
        
        boolean[][] couldBe = useLengths(isPossible, lengths);
        int counter = 0;
        for(int j=0 ; j<16 ; j++)
        {
            for(int k=0 ; k<16 ; k++)
            {
                if(couldBe[j][k] == true)
                    counter++;   
            }
        }           
        System.out.println("There are " +counter +" possible matches left, after length match");
        
        useDistances(couldBe, distances);
        counter = 0;
        for(int j=0 ; j<16 ; j++)
        {
            for(int q=0 ; q<16 ; q++)
            {
                if(couldBe[j][q] == true)
                    counter++;   
            }  
        }         
        System.out.println("There are " +counter +" possible matches left, after corner distance match");
        
        useMinHoleSize(couldBe, minHoleSize);
        counter = 0;
        for(int j=0 ; j<16 ; j++)
        {
            for(int q=0 ; q<16 ; q++)
            {
                if(couldBe[j][q])
                    counter++;   
            }   
        }        
        System.out.println("There are " +counter +" possible matches left, after minHoleSize match");
        
        useHoleSize(couldBe, holeSize);
        counter = 0;
        for(int j=0 ; j<16 ; j++)
        {
            for(int q=0 ; q<16 ; q++)
            {
                if(couldBe[j][q])
                    counter++;   
            }   
        }        
        System.out.println("There are " +counter +" possible matches left, after holeSize match");
        
        useCornerToMin(couldBe, cornerToMin);
        counter = 0;
        for(int j=0 ; j<16 ; j++)
        {
            for(int q=0 ; q<16 ; q++)
            {
                if(couldBe[j][q])
                    counter++;   
            }   
        }        
        System.out.println("There are " +counter +" possible matches left, after cornerToMin match");
        
        System.out.println("");
        for(int j=0 ; j<16 ; j++)
       {
            for(int q=0 ; q<16 ; q++)
            {
                if(couldBe[j][q])
                    System.out.println("Edge " +j +" and " +q);
            }
        }
        
//        int numPoss = getNumPossiblePuzzle(couldBe, counter);
    }
 
 //==============================================================================
    static int getNumPossiblePuzzle(boolean[][] couldBe, int counter)
    {
        boolean[][] match = (boolean[][]) couldBe.clone();
        
        int numPoss = 0;
        for(int i=0 ; i<counter ; i++)
        {
            // reset match booleans
            for(int j=0 ; j<16 ; j++)
            {
                for(int k=0 ; k<16 ; k++)
                {
                    match[j][k] = couldBe[j][k];
                }
            }    
            int numEdges = 0;
            
            // start flipping
            for(int j=0 ; j<16 ; j++)
            {
                for(int k=0 ; k<16 ; k++)
                {
                    if(match[j][k])
                        match[j][k] = false;                        
                }
            }
        }      
        
        System.out.println("");
        System.out.println("There are " +numPoss +" possible ways of organizing the puzzle pieces");
        
        return numPoss;
    }
 //==============================================================================
    static void useCornerToMin(boolean[][] couldBe, float[][][] cornerToMin)
    {        
        System.out.println("Checking cornerToMin match");
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            for(int edge=0 ; edge<4 ; edge++)
            {
                for(int p=0 ; p<4 ; p++)
                {
                    for(int e=0 ; e<4 ; e++)
                    {
                        if(couldBe[piece*4 + edge][p*4 + e] == true)
                        {
                            if(cornerToMin[piece][edge][0] > (cornerToMin[p][e][1] + 5) || 
                                (cornerToMin[piece][edge][0] < (cornerToMin[p][e][1] - 5)))
                            {
                                couldBe[piece*4 + edge][p*4 + e] = false;
                                System.out.println("Change at " +(piece*4 + edge) +"," +(p*4 + e));
                            } 
                            if(cornerToMin[piece][edge][1] > (cornerToMin[p][e][0] + 5) || 
                                (cornerToMin[piece][edge][1] < (cornerToMin[p][e][0] - 5)))
                            {
                                couldBe[piece*4 + edge][p*4 + e] = false;
                                System.out.println("Change at " +(piece*4 + edge) +"," +(p*4 + e));
                            }   
                        }
                    }
                } 
            }       
        }    
    } // end of cornerToMin match  
            
  //==============================================================================
    static void useHoleSize(boolean[][] couldBe, int[][] holeSize)
    {        
        System.out.println("Checking holeSize match");
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            for(int edge=0 ; edge<4 ; edge++)
            {
                for(int p=0 ; p<4 ; p++)
                {
                    for(int e=0 ; e<4 ; e++)
                    {
                        if(couldBe[piece*4 + edge][p*4 + e] == true)
                        {
                            if(holeSize[piece][edge] > (holeSize[p][e] + 3) || 
                                (holeSize[piece][edge] < (holeSize[p][e] - 3)))
                            {
                                couldBe[piece*4 + edge][p*4 + e] = false;
                                //System.out.println("Change at " +(piece*4 + edge) +"," +(p*4 + e));
                            }   
                        }
                    }
                } 
            }       
        }    
    } // end of holeSize match  
    
 //==============================================================================
    static void useMinHoleSize(boolean[][] couldBe, float[][] minHoleSize)
    {        
        System.out.println("Checking minHoleSize match");
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            for(int edge=0 ; edge<4 ; edge++)
            {
                for(int p=0 ; p<4 ; p++)
                {
                    for(int e=0 ; e<4 ; e++)
                    {
                        if(couldBe[piece*4 + edge][p*4 + e] == true)
                        {
                            if(minHoleSize[piece][edge] > (minHoleSize[p][e] + 4) || 
                                (minHoleSize[piece][edge] < (minHoleSize[p][e] - 4)))
                            {
                                couldBe[piece*4 + edge][p*4 + e] = false;
                                //System.out.println("Change at " +(piece*4 + edge) +"," +(p*4 + e));
                            }   
                        }
                    }
                } 
            }       
        }    
    } // end of minHoleSize match
       
//==============================================================================
    static void useDistances(boolean[][] couldBe, float[][] distances)
    {        
        System.out.println("Checking distance of corners match");
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            for(int edge=0 ; edge<4 ; edge++)
            {
                for(int p=0 ; p<4 ; p++)
                {
                    for(int e=0 ; e<4 ; e++)
                    {
                        if(couldBe[piece*4 + edge][p*4 + e] == true)
                        {
                            if(distances[piece][edge] > (distances[p][e] + 5) || 
                                (distances[piece][edge] < (distances[p][e] - 5)))
                            {
                                couldBe[piece*4 + edge][p*4 + e] = false;
                                //System.out.println("Change at " +(piece*4 + edge) +"," +(p*4 + e));
                            }   
                        }
                    }
                } 
            }       
        }    
    } // end of distance of corners match
    
    //==============================================================================
    static boolean[][] useLengths(boolean[][] isPossible, int[][] lengths)
    {
        boolean[][] couldBe = new boolean[16][16];
        
        for(int i=0 ; i<16 ; i++)
        {
            for(int j=0 ; j<16 ; j++)
            {
                couldBe[i][j] = isPossible[i][j];
            }
        }
        
        System.out.println("Checking total length match");
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            for(int edge=0 ; edge<4 ; edge++)
            {
                for(int p=0 ; p<4 ; p++)
                {
                    for(int e=0 ; e<4 ; e++)
                    {
                        if(couldBe[piece*4 + edge][p*4 + e] == true)
                        {
                            if(lengths[piece][edge] > (lengths[p][e] + 10) || 
                                (lengths[piece][edge] < (lengths[p][e] - 10)))
                            {
                                couldBe[piece*4 + edge][p*4 + e] = false;
                                //System.out.println("Change at " +(piece*4 + edge) +"," +(p*4 + e));
                            }   
                        }
                    }
                } 
            }       
        }
        
        return couldBe;
    }
    //==============================================================================
    static boolean[][] possibleCombos(int[][] concavity)
    {
        boolean[][] isPossible = new boolean[16][16];
        
        System.out.println("Checking concavity match");
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            //System.out.println("");
            for(int edge=0 ; edge<4 ; edge++)
            {
                //System.out.println("");
                // for this edge, go through all 16 other edges
                for(int p=0 ; p<4 ; p++)
                {
                    for(int e=0 ; e<4 ; e++)
                    {
                        if(p==piece)
                            isPossible[piece*4 + edge][p*4 + e] = false;
                        else if (concavity[piece][edge] == STRAIGHT || concavity[p][e] == STRAIGHT)
                            isPossible[piece*4 + edge][p*4 + e] = false;
                        else if (concavity[piece][edge] == concavity[p][e])
                            isPossible[piece*4 + edge][p*4 + e] = false;
                        else
                            isPossible[piece*4 + edge][p*4 + e] = true;
                            
                        //System.out.println("" +(piece*4 + edge) +" and " 
                        //    +(p*4 + e) +" are " +isPossible[piece*4 + edge][p*4 + e]);
                    }
                }    
            }
        } // end of going through arrays
        
        for(int j=0 ; j<16 ; j++)
        {
            for(int k=0 ; k<16 ; k++)
            {
                if(isPossible[j][k])
                    isPossible[k][j] = false;
            }
        }
        
        return isPossible;
    }
    //==============================================================================
    static int[][] determineConcavity(int[][] lengths, Coord[][] corners, Coord[][][] edges,
                                         float[][] distances, GreyImage[] pieces)
    {
        GreyImage[] clonePieces = (GreyImage[]) pieces.clone();
        for(int i=0 ; i<4 ; i++)
        {
            clonePieces[i] = (GreyImage) pieces[i].clone();
        }
    
        int[][] concavity = new int[4][4];
        
        for(int piece=0 ; piece<4 ; piece++)
        {
            for(int edge=0 ; edge<4 ; edge++)
            {
                Coord maxPix = new Coord(0,0);
                float maxDist = 0;
                
                if( ((int) distances[piece][edge]) < (lengths[piece][edge]+2) &&
                    ((int) distances[piece][edge]) > (lengths[piece][edge]-2) )
                {
                    concavity[piece][edge] = STRAIGHT;
                    //System.out.println("Edge " +edge +" is straight");
                }
                else
                {
                    //System.out.println("Edge " +edge +" is not straight");
                    int centerX = pieces[piece].height/2;
                    int centerY = pieces[piece].width/2;
                    
                    float slope = ((float) (corners[piece][(edge+1)%4].y - corners[piece][edge].y))/
                                    (corners[piece][(edge+1)%4].x - corners[piece][edge].x);
                    //System.out.println("Edge " +edge +" has slope " +slope);
                    
                    for(int pix=0 ; pix<edges[piece][edge].length ; pix++)
                    {
                        float dist = (float) 
                            Math.abs(slope*(edges[piece][edge][pix].x -corners[piece][edge].x) - 
                                (edges[piece][edge][pix].y - corners[piece][edge].y))
                                / (float) Math.sqrt(1 + slope*slope);
                        if(dist>maxDist)
                        {
                            maxDist = dist;
                            maxPix.x = edges[piece][edge][pix].x;
                            maxPix.y = edges[piece][edge][pix].y;
                        }
                    }
                    
                    //clonePieces[piece].data[maxPix.x][maxPix.y] = 150;
                    //clonePieces[piece].data[corners[piece][edge].x][corners[piece][edge].y] = 200;
                    //clonePieces[piece].writeToFile("check" +piece +edge +".pgm");
                    
                    // if top edge
                    if(corners[piece][edge].x < centerX && corners[piece][edge].y < centerY)
                    {
                        //System.out.println("Edge " +edge +" is on top");
                        if(maxPix.x > (corners[piece][edge].x + 5))
                            concavity[piece][edge] = CONCAVE;
                    } 
                    // if right edge
                    else if(corners[piece][edge].x < centerX && corners[piece][edge].y > centerY)
                    {
                        //System.out.println("Edge " +edge +" is on the right");
                        if(maxPix.y < (corners[piece][edge].y - 5))
                            concavity[piece][edge] = CONCAVE;
                    } 
                    // if bottom edge
                    else if(corners[piece][edge].x > centerX && corners[piece][edge].y > centerY)
                    {
                        //System.out.println("Edge " +edge +" is on the bottom");
                        if(maxPix.x < (corners[piece][edge].x -5))
                            concavity[piece][edge] = CONCAVE;
                    }
                    // if left edge
                    else if(corners[piece][edge].x > centerX && corners[piece][edge].y < centerY)
                    {
                        //System.out.println("Edge " +edge +" is on the left");
                        if(maxPix.y > (corners[piece][edge].y +5))
                            concavity[piece][edge] = CONCAVE;
                    }
                    else 
                        concavity[piece][edge] = CONVEX;
                }
                if(concavity[piece][edge] == UNKNOWN)
                    concavity[piece][edge] = CONVEX;
            } 
        }        
    
        return concavity;
    }
    
    //==============================================================================
    static float[][] determineMinHoleSize(Coord[][][] edges, int[][] concavity,
                                             int[][] holeSize, float[][][] cornerToMin)
    {
        float[][] minHoleSize = new float[4][4];
        
        for(int p=0 ; p<4 ; p++)
        {
            for(int e=0 ; e<4 ; e++)
            {
                if(concavity[p][e] != STRAIGHT)
                {
                    //System.out.println("Piece " +p +" edge " +e +" not straight so..."); 
                                       
                    boolean gettingSmaller = true;
                    float minDist = 30000;
                    holeSize[p][e] = 0;
                    float dist = 0;
                    int end = edges[p][e].length - 1;
                    int corn = end;
                    
                    for(int start=0 ; start<end && gettingSmaller ; start++, end--)
                    {
                        dist = (float) (edges[p][e][start].x - edges[p][e][end].x)
                                *(edges[p][e][start].x - edges[p][e][end].x)
                                + (edges[p][e][start].y - edges[p][e][end].y)
                                  *(edges[p][e][start].y - edges[p][e][end].y);
                        dist = (float) Math.sqrt(dist);
                        
                        if(dist<minDist)
                        {
                            minDist = dist;
                            holeSize[p][e] = end - start +1;
                            cornerToMin[p][e][0] = (float) (edges[p][e][start].x - edges[p][e][0].x)
                                *(edges[p][e][start].x - edges[p][e][0].x)
                                + (edges[p][e][start].y - edges[p][e][0].y)
                                  *(edges[p][e][start].y - edges[p][e][0].y);
                            cornerToMin[p][e][0] = (float) Math.sqrt(cornerToMin[p][e][0]);
                            
                            cornerToMin[p][e][1] = (float) (edges[p][e][end].x - edges[p][e][corn].x)
                                *(edges[p][e][end].x - edges[p][e][corn].x)
                                + (edges[p][e][end].y - edges[p][e][corn].y)
                                  *(edges[p][e][end].y - edges[p][e][corn].y);
                            cornerToMin[p][e][1] = (float) Math.sqrt(cornerToMin[p][e][1]);
                        }
                        else
                            gettingSmaller = false;
                    }
                    minHoleSize[p][e] = minDist;
                }
            }
        }
        
        return minHoleSize;
    } // end of finding min hole size
    
    //==============================================================================
    static float[][] distancesOfCorners(Coord[][] corn)
    {
        float[][] dist = new float[4][4];
        
        for(int p=0 ; p<4 ; p++)
        {
            dist[p][0] = (corn[p][1].x - corn[p][0].x)*(corn[p][1].x - corn[p][0].x)
                                + (corn[p][1].y - corn[p][0].y)*(corn[p][1].y - corn[p][0].y);
            dist[p][0] = (float) Math.sqrt(dist[p][0]);
            
            dist[p][1] = (corn[p][1].x - corn[p][2].x)*(corn[p][1].x - corn[p][2].x)
                                + (corn[p][1].y - corn[p][2].y)*(corn[p][1].y - corn[p][2].y);
            dist[p][1] = (float) Math.sqrt(dist[p][1]);
            
            dist[p][2] = (corn[p][2].x - corn[p][3].x)*(corn[p][2].x - corn[p][3].x)
                                + (corn[p][2].y - corn[p][3].y)*(corn[p][2].y - corn[p][3].y);
            dist[p][2] = (float) Math.sqrt(dist[p][2]);
            
            dist[p][3] = (corn[p][3].x - corn[p][0].x)*(corn[p][3].x - corn[p][0].x)
                                + (corn[p][3].y - corn[p][0].y)*(corn[p][3].y - corn[p][0].y);
            dist[p][3] = (float) Math.sqrt(dist[p][3]);
        }
    
        return dist;
    }
         
  //==============================================================================
    static Coord[][][] separateEdges(GreyImage[] piece, int[][] lengths, Coord[][] corners)
    {
        int w = 255;
        int b = 0;

        //create edges object, must be 3D array
        Coord[][][] edges = new Coord[4][4][];
        
        //edges[3][2] = new Coord[10];
        //edge[3][2][5] = new Coord(x, y);
                        
        for (int pieceNum=0 ; pieceNum<4 ; pieceNum++)
        {   
            //System.out.println("Separating edges of piece " +pieceNum);
            
            int numEdgePixels = 0; 
            for(int j=0 ; j<piece[pieceNum].height ; j++)
            {
                for(int k=0 ; k<piece[pieceNum].width ; k++)
                {
                    if(piece[pieceNum].data[j][k]==w)
                        numEdgePixels++;
                }
            }
            
            edges[pieceNum] = produceList(piece[pieceNum], numEdgePixels, edges[pieceNum], 
                                lengths[pieceNum], corners[pieceNum]);
            
        } // end of going through all four pieces

        for(int p=0 ; p<4 ; p++)
        {
            for(int e=0 ; e<4 ; e++)
            {
                GreyImage happy = new GreyImage(piece[p].width, piece[p].height, 0, 255);
                for(int i=0 ; i<edges[p][e].length ; i++)
                    happy.data[edges[p][e][i].x][edges[p][e][i].y] = 255;
                happy.writeToFile("edge" + p +"" + e +".pgm");
            }
        }        
        return edges;
         
    } // end of finding corner regions
    
    //==============================================================================
    static Coord[][] produceList(GreyImage picture, int numEdgePixels, Coord[][] edges, 
                                    int[] sizeOfEdge, Coord[] corners)
    {  
        GreyImage pict = (GreyImage) picture.clone();
          
        int w=255;
        int b=0;
        int pt=200;
        
        boolean gotStart = false;
        int r = 0;
        int c = 0;
        //System.out.println("Looking for start of edge...");
        for(int j=0 ; j<pict.height && !gotStart; j++)
        {
            for(int k=0 ; k<pict.width && !gotStart; k++)
            {
                if(pict.data[j][k] !=b && pict.data[j][k] != w)
                {
                    gotStart = true;
                    r = j;
                    c = k;
                    corners[0] = new Coord(r, c);
                } 
            }
        }
        
        //pict.writeToFile("ick.pgm");
        //System.out.println("wrote picture of piece I am processing in ick.pgm");
        
        boolean done = false;
        int edgeNum = 0;
        //int[] sizeOfEdge = new int[4];
        Coord[] list = new Coord[numEdgePixels];
                
        for(int i=0 ; i<numEdgePixels && !done ; i++)
        {
            //System.out.println("edgePixel " +i);
            //pict.writeToFile("ick.pgm");
            //System.out.println("wrote picture of piece I am processing in ick.pgm");
            if((pict.data[r][c] != w) && (i!=0))
            {
                //System.out.println("color " +pict.data[r][c]);
                edgeNum++;
                corners[edgeNum] = new Coord(r, c);
                //System.out.println("I am building up edge number " +edgeNum +" at pixel number" +i);
            }
                
            pict.data[r][c]=pt;
            list[i] = new Coord(r, c);
            sizeOfEdge[edgeNum]++;
                
            if(pict.data[r-1][c+1]!=b && pict.data[r-1][c+1]!=pt)
            {
                r= r-1;
                c= c+1;
            }
            else if(pict.data[r][c+1]!=b && pict.data[r][c+1]!=pt)
            {
                c= c+1;
            }
            else if(pict.data[r+1][c+1]!=b && pict.data[r+1][c+1]!=pt)
            {
                r= r+1;
                c= c+1;
            }
            else if(pict.data[r+1][c]!=b && pict.data[r+1][c]!=pt)
            {
                r= r+1;
            }
            else if(pict.data[r+1][c-1]!=b && pict.data[r+1][c-1]!=pt)
            {
                r= r+1;
                c= c-1;
            }
            else if(pict.data[r][c-1]!=b && pict.data[r][c-1]!=pt)
            {
                c= c-1;
            }
            else if(pict.data[r-1][c-1]!=b && pict.data[r-1][c-1]!=pt)
            {
                r= r-1;
                c= c-1;
            }
            else if(pict.data[r-1][c]!=b && pict.data[r-1][c]!=pt)
            {
                r= r-1;
            }
            else
            {
                done=true;
                //System.out.println("We seem to be done at edge " +edgeNum);
            }
            
        }
 
        
        //Shrink array
        int counter = 0;
        for(int i=0 ; i<list.length ; i++)
        {
            if(list[i].x == 0 && list[i].y == 0)
                counter++; 
        }              
//        System.out.println("We have to get ride of " +counter +" slots");
        Coord[] shortList = new Coord[numEdgePixels - counter];
        
        for (int i=0 ; i<shortList.length ; i++)
        {
            shortList[i] = new Coord(list[i].x, list[i].y);
        }    

        for(int i=0 ; i<4 ; i++)
        {
            edges[i] = new Coord[sizeOfEdge[i]];
            //System.out.println("Size of edge " +i +" is " +sizeOfEdge[i]);    
        }
            
        edgeNum = 0;        
        counter = 0;  
        int cumEdges = sizeOfEdge[0];
        //System.out.println("Size of shortList is " +shortList.length);   
        for (int i=0 ; i<shortList.length ; i++)
        {
            //System.out.println("i=" +i +" and sizeOfEdge[edgeNum]=" + sizeOfEdge[edgeNum]);  
            if(i>=cumEdges)
            {
                edgeNum++;
                counter = 0;
                cumEdges = cumEdges + sizeOfEdge[edgeNum];
            }
            //if(edgeNum!=0)
            //    System.out.println("Filling edges[" +edgeNum +"][" +counter +"] with point " +i);    
            edges[edgeNum][counter] = new Coord(shortList[i].x, shortList[i].y);
            counter++;
        }  
        
        return edges;
        
    } //end of producing pixel list of edges for one puzzle piece
}

//========================
class Coord
{
    int x;
    int y;
    
    Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}


