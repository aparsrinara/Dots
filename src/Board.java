import java.awt.Point;
import java.util.ArrayList;

public class Board {

    // Our representation of the board, where myBoard[0][0] represents 
    // the bottom left dot.
    private Dot[][] myBoard;
    // Total number of moves allowed for a single game session.
    private int totalScore = 0;
    private static int movesAllowed = 5;
    private int numSuccess = 0;
    private int numMovesLeft = movesAllowed;
    private ArrayList<Point> dotCoords = new ArrayList<Point>();

    public static final int MINSIZE = 4;
    public static final int MAXSIZE = 10;

    /**
     * Sets up the board's data and starts up the GUI. N is side length of the
     * board. (Number of dots per side) N will default to 0 if a non-integer is
     * entered as an argument. If there are no arguments, N will default to 10;
     */
    public static void main(String[] args) {
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            n = 0;
        } catch (IndexOutOfBoundsException e) {
            // This line is run if no command line arguments are given.
            // If you wish to modify this line to test, remember to change it back to 
            // n = 10;
            n = 10;
        }
        GUI.initGUI(n);
    }

    /**
     * When the New Game button is clicked, a new randomized board is constructed.
     * Sets up the board with input SIZE, such that the board's side length is SIZE.
     * Note: The Board is always a square, so SIZE is both the length and the width.
     * Generate a random board such that each entry in board is a random color. 
     * (represented by an int). Should print and error and System.exit if the size 
     * is not within the MINSIZE and MAXSIZE. This constructor will only be called 
     * once per game session. Initialize any variables if needed here.
     */
    public Board(int size) throws IllegalArgumentException {
    	if (size >= MINSIZE && size <= MAXSIZE) {
    		myBoard = new Dot[size][size];
    		for (int i = 0; i < myBoard.length; i++) {
    			for (int j = 0; j < myBoard.length; j++) {
    				myBoard[i][j] = new Dot();
    			}
    		}
    	}else {
    		throw new IllegalArgumentException("Incorrect size choice!");
    	}
    	
    }
    
    /**
     * This constructor takes in a 2D int array of colors and generates a preset board
     * with each dot matching the color of the corresponding entry in the int[][] 
     * argument. This constructor can be used for predetermined tests.
     * You may assume that the input is valid (between MINSIZE and MAXSIZE etc.) 
     * since this is for your own testing.
     */
    public Board(int[][] preset) {
    	myBoard = new Dot[preset.length][preset.length];
    	for (int i = 0; i < preset.length; i++) {
    		for (int j = 0; j < preset.length; j++) {
    			myBoard[i][j] = new Dot(preset[i][j]);
    		}
    	}
    }
    
    /**
     * Returns the array representation of the board. (Data is used by GUI).
     */
    public Dot[][] getBoard() {
    	if (myBoard == null) {
    		System.err.println("Trying to access empty board!");
    		System.exit(1);
    	}
    	return myBoard;
    }

    /**
     * Returns the number of moves allowed per game. This value should not
     * change during a game session.
     */
    public static int getMovesAllowed() {
    	return movesAllowed;
    }

    /**
     * Change the number of moves allowed per game. This method can be used for 
     * testing.
     */
    public static void setMovesAllowed(int n) {
    	  if (n > 0) {
          	movesAllowed = n;
          }
    }

    /** Returns the number of moves left. */
    public int getMovesLeft() {
    	numMovesLeft = movesAllowed - numSuccess;
    	return numMovesLeft;
    }

    /**
     * Return whether or not it is possible to make a Move. (ie, there exists
     * two adjacent dots of the same color.) If false, the GUI will report a
     * game over.
     */
    public boolean canMakeMove() {
    	for (int i = 0; i < myBoard.length; i++){
    		for (int j = 1; j < myBoard.length; j++){
    			if ((myBoard[i][j].getColor() == myBoard[i][j-1].getColor()) || (myBoard[j-1][i].getColor() == myBoard[j][i].getColor())) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

    /**
     * Returns if the board is in a state of game over. The game is over if there
     * are no possible moves left or if the player has used up the maximum
     * allowed moves.
     */
    public boolean isGameOver() {
    	if (numMovesLeft == 0 || !canMakeMove()) {
    		return true;
    	}
        return false;
    }

    
    /**
     * Returns whether or not you are allowed to select a dot at X, Y at the
     * moment. Remember, if the game is over, you cannot select any dots.
     */
    public boolean canSelect(int x, int y){
    	if (x < 0 || x >= myBoard.length || y < 0 || y >= myBoard.length){
    		return false;
    	}
    	if (isGameOver()){
    		return false;
    	}
    	if (findIndex(x, y) != -1){
    		return false;
    	}
    	if (numberSelected() == 0) {
			return true;
    	}
    	if (numberSelected() > 0) {
    		Point lastCoord = dotCoords.get(numberSelected()-1);
    		int xCoord = lastCoord.x;
    		int yCoord = lastCoord.y;
    		if ((xCoord+1 == x && yCoord == y) || (xCoord-1 == x && yCoord == y) ||
    			(yCoord+1 == y && xCoord == x) || (yCoord-1 == y && xCoord == x)) {
    			if (myBoard[x][y].getColor() == myBoard[lastCoord.x][lastCoord.y].getColor()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
     
    /**
     * Is called when a dot located at myBoard[X][Y] is selected on the GUI.
     */
    public void selectDot(int x, int y) {
    	dotCoords.add(new Point(x, y));
    }

    /**
     * Checks if you are allowed to deselect the chosen point.
     * Assumes at least one point has been selected already.
     * You can only deselect the most recent point you have selected.
     * (You can select 3 dots and deselect them in reverse order.)
     */
    public boolean canDeselect(int x, int y) {
    	if (x < 0 || x >= myBoard.length || y < 0 || y >= myBoard.length) {
    		return false;
    	}
    	if (numberSelected() == 1) {
    		return true;
    	}
    	if ((dotCoords.get(numberSelected()-1).x == x) && (dotCoords.get(numberSelected()-1).y == y)) {
    		return true;
    	}
    	return false;
    }
    	
    /**Is called when a dot located at myBoard[X][Y] is deselected on the GUI. */
    public void deselectDot(int x, int y) {
    	dotCoords.remove(numberSelected()-1);
    }

    /**Returns the number of currently selected dots */
    public int numberSelected() {
    	return dotCoords.size();
    }

    public static class CantRemoveException extends Exception {
    	public CantRemoveException() {
    		super();
    	}
    	
    	public CantRemoveException(String message) {
    		super(message);
    	}
    }
    
    /**
     * Is called when the "Remove" button is clicked. Puts all selected dots in
     * a "removed" state. If no dots should be removed, throw a CantRemoveException. 
     * You must also create your own Exception Class named CantRemoveException.
     * If selected dots form a closed shape, remove all dots on the board that have
     * the same color as the selected dots.
     */
    public void removeSelectedDots() throws CantRemoveException {
    	if (numberSelected() < 2) {
    		throw new CantRemoveException("Cannot remove selected dots!");
    	}
    	if (isClosedShape()) {
    		removeSameColor();
    	}else {
    		for (int i = 0; i < dotCoords.size(); i++) {
    			removeSingleDot (dotCoords.get(i).x, dotCoords.get(i).y);
    			i--;
    		}
    	}
    	numSuccess++;
    }
    
    public int findIndex (int x, int y) {
    	for (int i = 0; i < dotCoords.size(); i++) {
    		if (dotCoords.get(i).x == x && dotCoords.get(i).y == y) {
    			return i;
    		}
    	}
    	return -1;
    }
    /**
     * Puts the dot at X, Y in a removed state. Later all dots above a
     * removed dot will drop.
     */
    public void removeSingleDot(int x, int y) {
    	int index = findIndex (x, y);
    	if (index != -1) {
    		dotCoords.remove(index);
    	}  	
    	myBoard[x][y] = null;
    	totalScore++;
    }

    /**
     * Return whether or not the selected dots form a closed shape. Refer to
     * diagram for what a closed shape looks like.
     */
    public boolean isClosedShape() {
    	int numAdjacentDots = 0;
    	Point lastSelected = dotCoords.get(numberSelected()-1);
    	for (int i = 0; i < dotCoords.size(); i++) {
    		 if ((((lastSelected.x)+1 == dotCoords.get(i).x  || (lastSelected.x)-1 == dotCoords.get(i).x)
    				 && lastSelected.y == dotCoords.get(i).y) || ((lastSelected.y)+1 == dotCoords.get(i).y  || (lastSelected.y)-1 == dotCoords.get(i).y)
    				 && lastSelected.x == dotCoords.get(i).x) {
    			 numAdjacentDots++;
    		 }
    	}
    	if (numAdjacentDots >= 2){
    		return true;
    	}
    	return false;
    }

    /**
     * Removes all dots of the same color of the dots on the currently selected
     * dots. Assume it is confirmed that a closed shape has been formed from the
     * selected dots.
     */
    public void removeSameColor() {
    	int color = myBoard[dotCoords.get(0).x][dotCoords.get(0).y].getColor();
    	for (int i = 0; i < myBoard.length; i++) {
			for (int j = 0; j < myBoard.length; j++) {
				if (myBoard[i][j].getColor() == color) {
					removeSingleDot(i, j);
				}
			}
		}
    }

    /**
     * Once the dots are removed. Rearrange the board to simulate the dropping of
     * all of the dots above the removed dots. Refer to diagram in the spec for clarity.
     * After dropping the dots, there should exist some "bad" dots at the top. 
     * (These are the blank dots on the 4-stage diagram.)
     * fillRemovedDots will be called immediately after this by the GUI so that random 
     * dots replace these bad dots with new ones that have a randomly generated color.
     */
    
    public void dropRemainingDots() {
     	for (int i = 0; i < myBoard.length; i++) {
    		int k = 0;
    		int j = 0;
    		while (j < myBoard.length) {
    			if(myBoard[i][j] == null){
    				j++;
    			}
    			else{
    				myBoard[i][k] = myBoard[i][j];
    				j++;
    				k++;
    			}
    		}
    		while(k < myBoard.length) {
    			myBoard[i][k] = null;
    			k++;
    		}
    	}
    }
    /**
     * After removing all dots that were meant to be removed, replace any
     * removed dot with a new dot of a random color.
     */
    public void fillRemovedDots() {
    	for (int i = 0; i < myBoard.length; i++) {
    		for (int j = 0; j < myBoard.length; j++) {
    			if (myBoard[i][j] == null) {
    				myBoard[i][j] = new Dot();
    			}
    		}
    	}
    }

    /**
     * Return the current score, which is called by the GUI when it needs to
     * update the display of the score. Remember to update the score in your 
     * other methods.
     */
    public int getScore() {
    	return totalScore;
    }

    /* This is a private method we have created to add the points of our best square into
     * our bestSquare ArrayList.
     */
    private void addPointsToBestSquare(ArrayList<Point> list, int i, int j) {
    	list.add(0,new Point(i,j));
		list.add(1,new Point(i+1,j));
		list.add(2,new Point(i+1,j+1));
		list.add(3,new Point(i,j+1));
    }
    /**
     * Search the board for a sequence of 4 consecutive points which form a
     * square such that out of all possible 2x2 squares, selecting this one 
     * yields the most points.
     */
    public ArrayList<Point> findBestSquare() {
    	int[] colors = new int[6]; 
        for(int i = 0; i < myBoard.length; i++) {
        	
        	for(int j = 0; j<myBoard.length; j++) {
        		if(myBoard[i][j].getColor() == 1) {
        			colors[1] += 1;
        		}
        		else if(myBoard[i][j].getColor() == 2) {
        			colors[2] += 1;
        		}
        		else if(myBoard[i][j].getColor() == 3) {
        			colors[3] += 1;
        		}
        		else if(myBoard[i][j].getColor() == 4) {
        			colors[4]+= 1;
        		}else{
        			colors[5] += 1;
        		}
        	}
        }
        ArrayList<Point> bestSquare;
        bestSquare = new ArrayList<Point>();
        for(int i = 0; i < myBoard.length - 1; i ++) {
        	for(int j = 0; j < myBoard.length-1; j++) {
        		if(myBoard[i][j].getColor() == myBoard[i+1][j].getColor()
        				&& myBoard[i][j].getColor()== myBoard[i][j+1].getColor()
        				&& myBoard[i][j].getColor()== myBoard[i+1][j+1].getColor()
        				){
        			if((bestSquare.size() == 0)) {
        				addPointsToBestSquare(bestSquare, i, j);
	        			
        			}
        			else if(colors[myBoard[i][j].getColor()] > colors[myBoard[bestSquare.get(0).x][bestSquare.get(0).y].getColor()]) {
        				bestSquare = new ArrayList<Point>();
        				addPointsToBestSquare(bestSquare, i, j);
        			}
        		}
        	}
        }
        if (bestSquare.size() == 0) {
        	bestSquare = null;
        }
        return bestSquare;
    }

    /**Prints the the board any way you like for testing purposes. */
    public void printBoard() {
        // OPTIONAL
    }

    public void printBoard(String msg) {
    	System.out.println(msg);
    	printBoard();
    }
}