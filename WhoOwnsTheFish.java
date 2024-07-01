/*
* Who Owns The Fish?
*/

import java.util.Arrays;

public class WhoOwnsTheFish {

  // Constants for each possible value
  final int NONE = -1;

  final int NATIONALITY = 0;
  final int COLOR = 1;
  final int DRINKS = 2;
  final int SMOKES = 3;
  final int PET = 4;

  final int NORWEGIAN = 0;
  final int DANE = 1;
  final int BRIT = 2;
  final int GERMAN = 3;
  final int SWEDE = 4;

  final int YELLOW = 0;
  final int BLUE = 1;
  final int RED = 2;
  final int GREEN = 3;
  final int WHITE = 4;

  final int WATER = 0;
  final int TEA = 1;
  final int MILK = 2;
  final int COFFEE = 3;
  final int BEER = 4;

  final int DUNHILLS = 0;
  final int BLENDS = 1;
  final int PALL_MALLS = 2;
  final int PRICES = 3;
  final int BLUEMASTER = 4;

  final int CATS = 0;
  final int HORSES = 1;
  final int BIRDS = 2;
  final int DOGS = 3;

  


  /*
  Check if assigning value to houses[h][a] is valid
  */
  public boolean valid (int[][] houses, int h, int a, int value){
	  
	  for (int i = 0; i < 5; i++) {
		  if (houses[h][i] == value || houses[i][a] == value) {
			  return false;
		  }
	  }
	  return true;
    
  }

  /*
  * Find the next open position in the house matrix
  */
  public int[] findNext (int[][] houses){
	  
    int open[] = new int[2];
    	
    for (int h = 0; h < 5; h++){
      for (int a = 0; a < 5; a++){
        if (houses[h][a] == NONE){
          open[0] = h;
          open[1] = a; 
          return open;
        }
      }
    }
    return null;
  }

  /*
  * Backtracking search
  */
  public void search(int[][] houses){

    int[] result = findNext(houses);
    if (result == null){
      System.out.println(houses);
      System.exit(0);
    }

    int h = result[0];    // Houses
    int a = result[1];    // Attributes

    // Test all possible assignments for house[h][a]
    for (int i = 0; i < 5; i++){
      if (valid(houses, h, a, i)){
        houses[h][a] = i;
        search(houses);
      }
    }

    // No solution on this path, backtrack
    houses[h][a] = NONE;
    
  }

  /*
  * Start the solution process
  */
  public void solve(){

    // Empty 5x5 solution grid
    int[][] houses = new int [5][5];

    for (int[] house : houses){
      Arrays.fill(house, NONE);  // Initialize to NONE
    }

    // Norwegian lives in the first house and in the yellow house. Also smokes Dunhills
    houses[0][NATIONALITY] = NORWEGIAN;
    houses[0][COLOR] = YELLOW;
    houses[0][SMOKES] = DUNHILLS;

    // Norwegian lives next to the blue house
    houses[1][COLOR] = BLUE;

    // Brit lives in the third house, drinks milk and the house is red
    houses[2][NATIONALITY] = BRIT;
    houses[2][DRINKS] = MILK;
    houses[2][COLOR] = RED;
    

    search(houses);
    printTheGrid(houses);
  }
  
  public void printTheGrid (int[][] houses) {
	  
	  for(int i = 0; i < 5; i++) {
		  for (int j = 0; j < 5; j++) {
			  System.out.println(houses[i][j]);
		  }
	  }
	  
  }

  public static void main(String[] args){
    
    WhoOwnsTheFish who = new WhoOwnsTheFish();
    who.solve();
    
  }
}
