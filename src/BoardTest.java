import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class BoardTest {

	@Test
	public void testBoardConstructor() {
		Board test = new Board(4);
		assertEquals(test.getBoard().length, 4);
		
		try {
			Board test2 = new Board(2);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Board test3 = new Board(11);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void testMovesAllowed(){
			
		int[][] preset = new int[4][4];
		
		preset[0][0] = 2;
		preset[1][0] = 2;
		preset[2][0] = 5;
		preset[3][0] = 1;
		preset[0][1] = 4;
		preset[1][1] = 2;
		preset[2][1] = 5;
		preset[3][1] = 1;
		preset[0][2] = 2;
		preset[1][2] = 2;
		preset[2][2] = 1;
		preset[3][2] = 4;
		preset[0][3] = 2;
		preset[1][3] = 2;
		preset[2][3] = 3;
		preset[3][3] = 2;
		
		Board test = new Board(preset);
		
		test.setMovesAllowed(10);
		assertEquals(test.getMovesAllowed(), 10);
		
		test.setMovesAllowed(-5);
		assertEquals(test.getMovesAllowed(), 10);
		
		test.setMovesAllowed(0);
		assertEquals(test.getMovesAllowed(), 10);
		
		test.selectDot(0,0);
		test.selectDot(1, 0);
		try {
			test.removeSelectedDots();
		}
		catch(Board.CantRemoveException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(test.getMovesLeft(), 9);
	}
	
	@Test
	public void testCanSelect() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;

		Board test = new Board(preset);
		assertTrue(test.canSelect(0, 0));
		test.selectDot(0, 0);
		assertTrue(test.canSelect(1, 0));
		test.selectDot(1, 0);
		assertTrue(test.canSelect(2, 0));
		test.selectDot(2, 0);
		assertTrue(test.canSelect(3, 0));
		test.selectDot(3, 0);
		assertFalse(test.canSelect(0, 4));
		assertFalse(test.canSelect(1, 2));
		assertFalse(test.canSelect(3, 2));
	}
	
	@Test
	public void testSelectDot() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		test.selectDot(0, 1);
		test.selectDot(1, 1);
		assertTrue(test.numberSelected() == 2);
		
	}
	
	@Test 
	public void testCanDeselect() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		test.selectDot(0, 0);
		test.selectDot(1, 0);
		test.selectDot(2, 0);
		assertTrue(test.canDeselect(2, 0));
		assertFalse(test.canDeselect(0, 0));
	}
	
	@Test
	public void testDeselectDot() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		test.selectDot(0, 0);
		test.selectDot(1, 0);
		test.selectDot(2, 0);
		assertTrue(test.canDeselect(2, 0));
		test.deselectDot(2, 0);
		assertEquals(test.numberSelected(), 2);
	}
	
	@Test
	public void testRemoveSelectedDots() {
		int[][] preset = new int[4][4];
		
		preset[0][0] = 2;
		preset[1][0] = 4;
		preset[2][0] = 5;
		preset[3][0] = 1;
		preset[0][1] = 4;
		preset[1][1] = 2;
		preset[2][1] = 5;
		preset[3][1] = 1;
		preset[0][2] = 2;
		preset[1][2] = 2;
		preset[2][2] = 1;
		preset[3][2] = 4;
		preset[0][3] = 2;
		preset[1][3] = 2;
		preset[2][3] = 3;
		preset[3][3] = 2;
		
		Board test = new Board(preset);
		test.selectDot(2, 0);
		try {
			test.removeSelectedDots();
		}
		catch(Board.CantRemoveException e) {
			System.out.println(e.getMessage());
		}
		test.selectDot(2, 1);
		try {
			test.removeSelectedDots();
		}
		catch(Board.CantRemoveException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(test.numberSelected() == 0);
		assertNull(test.getBoard()[2][0]);
		assertNull(test.getBoard()[2][1]);
		
		Board test2 = new Board(preset);
		int numRedDots = 0;
		test2.selectDot(1, 1);
		test2.selectDot(1, 2);
		test2.selectDot(1, 3);
		test2.selectDot(0, 3);
		test2.selectDot(0, 2);
		assertTrue(test2.numberSelected() == 5);
		assertTrue(test2.isClosedShape());
		assertTrue(test2.getBoard()[1][1].getColor() == 2);
		for (int i = 0; i < test2.getBoard().length; i++) {
			for (int j = 0; j < test2.getBoard().length; j++)
			if (test2.getBoard()[i][j] != null && test2.getBoard()[i][j].isColor(2)) 
				numRedDots++;
		}
		assertTrue(numRedDots == 7);
		try {
			test2.removeSelectedDots();
		}
		catch(Board.CantRemoveException e) {
			System.out.println(e.getMessage());
		}	
		assertTrue(test2.numberSelected() == 0);
		numRedDots = 0;
		for (int i = 0; i < test2.getBoard().length; i++) {
			for (int j = 0; j < test2.getBoard().length; j++)
			if (test2.getBoard()[i][j] != null && test2.getBoard()[i][j].isColor(2)) 
				numRedDots++;
		}
		assertTrue(numRedDots == 0);
	}
	
	@Test
	public void testIsClosedShape() {
		int[][] preset = new int[4][4];
		preset[0][0] = 2;
		preset[1][0] = 4;
		preset[2][0] = 5;
		preset[3][0] = 1;
		preset[0][1] = 4;
		preset[1][1] = 2;
		preset[2][1] = 5;
		preset[3][1] = 1;
		preset[0][2] = 2;
		preset[1][2] = 2;
		preset[2][2] = 1;
		preset[3][2] = 4;
		preset[0][3] = 2;
		preset[1][3] = 2;
		preset[2][3] = 3;
		preset[3][3] = 2;
		
		Board test = new Board(preset);
		test.selectDot(0, 2);
		test.selectDot(1, 2);
		test.selectDot(1, 1);
		assertFalse(test.isClosedShape());
		Board test2 = new Board(preset);
		test2.selectDot(1, 1);
		test2.selectDot(1, 2);
		test2.selectDot(1, 3);
		test2.selectDot(0, 3);
		test2.selectDot(0, 2);
		assertTrue(test2.isClosedShape());
		
		test2.deselectDot(0, 2);
		test2.deselectDot(0, 3);
		test2.deselectDot(1, 3);
		test2.deselectDot(1, 2);
		test2.deselectDot(1, 1);
		// it should not be a closed if you choose the above same points in the same order
		test2.selectDot(0, 2);
		test2.selectDot(0, 3);
		test2.selectDot(1, 3);
		test2.selectDot(1, 2);
		test2.selectDot(1, 1);
		assertFalse(test2.isClosedShape());
		
		Board test3 = new Board(preset);
		test3.selectDot(0, 3);
		test3.selectDot(1, 3);
		test3.selectDot(1, 2);
		test3.selectDot(0, 2);
		assertTrue(test3.isClosedShape());
		
		int[][] preset2 = new int[4][4];
		preset2[0][0] = 1;
		preset2[1][0] = 2;
		preset2[2][0] = 2;
		preset2[3][0] = 2;
		preset2[0][1] = 1;
		preset2[1][1] = 5;
		preset2[2][1] = 2;
		preset2[3][1] = 2;
		preset2[0][2] = 3;
		preset2[1][2] = 1;
		preset2[2][2] = 3;
		preset2[3][2] = 1;
		preset2[0][3] = 1;
		preset2[1][3] = 4;
		preset2[2][3] = 3;
		preset2[3][3] = 5;
		
		Board test4 = new Board(preset2);
		test4.selectDot(1, 0);
		test4.selectDot(2, 0);
		test4.selectDot(3, 0);
		test4.selectDot(3, 1);
		test4.selectDot(2, 1);
		assertTrue(test4.isClosedShape());
		
	}
	
	@Test
	public void testGetScore() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		assertTrue(test.getScore() == 0);
		test.removeSingleDot(0, 0);
		test.removeSingleDot(1, 0);
		test.removeSingleDot(2, 0);
		test.removeSingleDot(3, 0);
		assertTrue(test.getScore() == 4);
	}
	
	public void testDropRemainingDots() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		test.removeSingleDot(0, 0);
		test.removeSingleDot(1, 0);
		test.removeSingleDot(2, 0);
		test.removeSingleDot(3, 0);
		test.dropRemainingDots();
		assertTrue(test.getBoard()[0][0].getColor() == 5);
		assertTrue(test.getBoard()[1][0].getColor() == 5);
		assertTrue(test.getBoard()[2][0].getColor() == 1);
		assertTrue(test.getBoard()[3][0].getColor() == 3);
		
	}
	
	@Test
	public void testBestSquare(){
		int[][] preset = new int[5][5];
		preset[0][0] = 2;
		preset[1][0] = 1;
		preset[2][0] = 2;
		preset[3][0] = 1;
		preset[4][0] = 1;
		preset[0][1] = 1;
		preset[1][1] = 2;
		preset[2][1] = 2;
		preset[3][1] = 1;
		preset[4][1] = 1;
		preset[0][2] = 2;
		preset[1][2] = 2;
		preset[2][2] = 1;
		preset[3][2] = 1;
		preset[4][2] = 3;
		preset[0][3] = 2;
		preset[1][3] = 2;
		preset[2][3] = 1;
		preset[3][3] = 4;
		preset[4][3] = 5;
		preset[0][4] = 1;
		preset[1][4] = 1;
		preset[2][4] = 1;
		preset[3][4] = 2;
		preset[4][4] = 2;
		
		Board test1 = new Board(preset);
		
		ArrayList<Point> sq = new ArrayList<Point>();
		sq.add(new Point(3,0));
		sq.add(new Point(4,0));
		sq.add(new Point(4,1));
		sq.add(new Point(3,1));
		assertEquals(test1.findBestSquare(), sq);
		
		int[][] preset2 = new int[5][5];
		preset2[0][0] = 1;
		preset2[1][0] = 2;
		preset2[2][0] = 3;
		preset2[3][0] = 4;
		preset2[4][0] = 5;
		preset2[0][1] = 1;
		preset2[1][1] = 3;
		preset2[2][1] = 4;
		preset2[3][1] = 3;
		preset2[4][1] = 5;
		preset2[0][2] = 1;
		preset2[1][2] = 2;
		preset2[2][2] = 3;
		preset2[3][2] = 4;
		preset2[4][2] = 5;
		preset2[0][3] = 2;
		preset2[1][3] = 1;
		preset2[2][3] = 5;
		preset2[3][3] = 3;
		preset2[4][3] = 2;
		preset2[0][4] = 1;
		preset2[1][4] = 4;
		preset2[2][4] = 2;
		preset2[3][4] = 1;
		preset2[4][4] = 3;
		
		Board test2 = new Board(preset2);
		assertEquals(test2.findBestSquare(), null);	
	}
	
	
	@Test
	public void testFillRemainingDots() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		test.removeSingleDot(0, 0);
		test.removeSingleDot(1, 0);
		test.removeSingleDot(2, 0);
		test.removeSingleDot(3, 0);
		test.dropRemainingDots();
		test.fillRemovedDots();
		assertNotNull(test.getBoard()[0][3]);
		assertNotNull(test.getBoard()[1][3]);
		assertNotNull(test.getBoard()[2][3]);
		assertNotNull(test.getBoard()[3][3]);
	}
	@Test
	public void testCanMakeMove() {
		int[][] preset = new int[4][4];
		int i = 0;
		while(i < preset.length){
			preset[i][0] = 2;
			i++;
		}
		preset[0][1] = 5;
		preset[1][1] = 5;
		preset[2][1] = 1;
		preset[3][1] = 3;
		preset[0][2] = 4;
		preset[1][2] = 1;
		preset[2][2] = 4;
		preset[3][2] = 1;
		preset[0][3] = 3;
		preset[1][3] = 2;
		preset[2][3] = 2;
		preset[3][3] = 3;
		
		Board test = new Board(preset);
		assertTrue(test.canMakeMove());
		int[][] preset2 = new int[4][4];
		preset2[0][0] = 1;
		preset2[1][0] = 2;
		preset2[2][0] = 3;
		preset2[3][0] = 4;
		preset2[0][1] = 4;
		preset2[1][1] = 3;
		preset2[2][1] = 2;
		preset2[3][1] = 1;
		preset2[0][2] = 1;
		preset2[1][2] = 2;
		preset2[2][2] = 3;
		preset2[3][2] = 4;
		preset2[0][3] = 4;
		preset2[1][3] = 3;
		preset2[2][3] = 2;
		preset2[3][3] = 1;
		
		Board test2 = new Board(preset2);
		assertFalse(test2.canMakeMove());
		
	}
	

	@Test
	public void testIsGameOver() {
		int[][] preset = new int[3][3];
		preset[0][0] = 1;
		preset[1][0] = 2;
		preset[2][0] = 3;
		preset[0][1] = 4;
		preset[1][1] = 5;
		preset[2][1] = 4;
		preset[0][2] = 3;
		preset[1][2] = 2;
		preset[2][2] = 1;
		
		int[][] preset2 = new int[4][4];
		int i = 0;
		while(i < preset2.length){
			preset2[i][0] = 2;
			i++;
		}
		preset2[0][1] = 5;
		preset2[1][1] = 5;
		preset2[2][1] = 1;
		preset2[3][1] = 3;
		preset2[0][2] = 4;
		preset2[1][2] = 1;
		preset2[2][2] = 4;
		preset2[3][2] = 1;
		preset2[0][3] = 3;
		preset2[1][3] = 2;
		preset2[2][3] = 2;
		preset2[3][3] = 3;
		
		Board test = new Board(preset);
		assertTrue(test.isGameOver());
		Board test2 = new Board(preset2);
		test.setMovesAllowed(0);
		assertFalse(test2.isGameOver());		
		
	}
}