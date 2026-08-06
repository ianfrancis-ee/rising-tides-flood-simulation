package tides;
import java.util.*;
/**
* This class contains methods that provide information about select terrains
* using 2D arrays. Uses floodfill to flood given maps and uses that
* information to understand the potential impacts.
* Instance Variables:
*  - a double array for all the heights for each cell
*  - a GridLocation array for the sources of water on empty terrain
*
* @author Original Creator Keith Scharz (NIFTY STANFORD)
* @author Vian Miranda (Rutgers University)
*
*
* @name  Ian Francis
* @date  12/13/24
*
*/
public class RisingTides {
   // Instance variables
   private double[][] terrain;     // an array for all the heights for each cell
   private GridLocation[] sources; // an array for the sources of water on empty terrain
   
   public RisingTides(Terrain terrain) {
       this.terrain = terrain.heights;
       this.sources = terrain.sources;
   }
  
   public double[] elevationExtrema() {
       /* WRITE YOUR CODE BELOW */
   	double min = terrain[0][0];
   	double max = terrain[0][0];
   	double [] extrema = new double[2];
   	for(int i = 0; i< terrain.length; i++ ) {
   		for(int j = 0; j< terrain[1].length; j++ ) {
       		if(terrain[i][j] > max) {
       			max = terrain[i][j];
       		}
       		if(terrain[i][j] < min) {
       			min = terrain[i][j];
       		}
       	}
   	}
   	extrema[0] = min;
   	extrema[1] = max;
       return extrema;
   }

      public boolean[][] floodedRegionsIn(double height) {
   	
       boolean[][] full = new boolean[terrain.length][terrain[0].length];
       ArrayList<GridLocation> check = new ArrayList<GridLocation>();

       for(int i = 0; i < sources.length; i++ ) {
    	   check.add(sources[i]);
    	   full[sources[i].row][sources[i].col] = true;
       	}
       
       while (check.size() != 0) { //adds each gridlocation to be checked in the arraylist
    	   GridLocation temp = check.get(0);
    	   check.remove(0);
    	   int row = temp.row;
       		int col = temp.col;
       	
       for(int i = 0; i< 4; i++) { //4 neighbors
    	   int row2 = row;
    	   int col2 = col;
    	   if (i==0) { 
    		   row2++;
    	   }
    	   if (i==1) {
    		   row2--;
    	   }
    	   if (i==2) {
    		   col2++;
    	   }
    	   if (i==3) {
    		   col2--;
    	   }
       if(row2 >= 0 && col2 >= 0 && row2 < terrain.length && col2 < terrain[0].length) { //boundaries
    	   if (!full[row2][col2] && terrain[row2][col2] <= height) { //levels
    		   full[row2][col2] = true;
    		   check.add(new GridLocation(row2, col2));
    	   }
       	 }   
       }   
     }
       return full;
   }
   
   public boolean isFlooded(double height, GridLocation cell) {
      
   	 boolean[][] flood = floodedRegionsIn(height);
        return flood[cell.row][cell.col];
   }
   
	
   public double heightAboveWater(double height, GridLocation cell) {
      
   	return terrain[cell.row][cell.col] - height;
   }

   public int totalVisibleLand(double height) {
       
   	boolean[][] flood = floodedRegionsIn(height);
   	int count = 0;
   	for(int i = 0 ; i < flood.length; i++) {
   		for(int j = 0 ; j < flood[0].length; j++) {
       		if (flood[i][j] == false){
       			count++;
       		}
       	}
   	}
       return count; // substitute this line. It is provided so that the code compiles.
   }
  
   public int landLost(double height, double newHeight) {
      
       
   	int start = totalVisibleLand(height);
   	int end = totalVisibleLand(newHeight);
       return start-end;
   }

   public int numOfIslands(double height) {//floods in 8 directions
	   boolean[][] full = floodedRegionsIn(height);
	   WeightedQuickUnionUF islands = count8(full);
	   int count = 0;
	   boolean[][] visited = new boolean[terrain.length][terrain[0].length];
	  
      for(int r = 0; r<terrain.length; r++) {
   	   for(int c = 0; c<terrain[0].length; c++) {
	    	   if(!full[r][c]) {
	    		   GridLocation parent = islands.find(new GridLocation(r,c));
	    		   if(!visited[parent.row][parent.col]) {
	    			   visited[parent.row][parent.col] = true;
	    			   count++;
	    		   }
	    	   }
	       }
      }
      return count;
  }

   private WeightedQuickUnionUF count8(boolean[][] full) {
	   WeightedQuickUnionUF uf = new WeightedQuickUnionUF(terrain.length,terrain[0].length);
	   int[][] directions = {
	            {-1, -1}, {-1, 0}, {-1, 1},
	            {0, -1},           {0, 1},
	            {1, -1}, {1, 0}, {1, 1}};
	   
	   for(int r=0; r<full.length; r++) {
		   for(int c=0; c<full[0].length; c++) {
			   if(!full[r][c]) {
				   for(int[] d : directions) {
					   int row2 = r + d[0];
					   int col2 = c + d[1];
					   if(row2 >=0 && row2 < full.length && col2 >=0 && col2 < full[0].length && !full[row2][col2]) {
						   uf.union(new GridLocation(r, c), new GridLocation(row2, col2));
					   }
				   }
			   }
		   }  
	   }
	   return uf;
   }
}
