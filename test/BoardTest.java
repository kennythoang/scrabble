import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;


//AEINRST
// test code 0 = testing a pass, returns -1
// return -2 if First play needs to have a tile touching center of board error
// return -3 if first move needs to be more than one letter error
// return -4 Placed tiles need to be horizontal OR vertical as each other
// return -5 if invalid words error

public class BoardTest {
	Board board = new Board(new JLabel("Running..."));
    @Before
    public void setUp() {
    	board = new Board(new JLabel("Running..."));
        board.testRacks();
    }
	@Test
	public void testRackSize() {
		assertEquals(7, board.getPlayerOne().getRackSize());
		assertEquals(7, board.getPlayerTwo().getRackSize());
	}

	@Test
	public void testRefillBag() {
		assertEquals(100 - 7 - 7, board.getTileBag().size());
	}

	@Test
	public void testMakeMovePassingPlay() {
		board.testMakeMove(0);
		assertEquals(true, !board.playerOneTurn);
		assertEquals(0, board.getPlayerOne().getScore());
		board.testMakeMove(0);
		assertEquals(true, board.playerOneTurn);
		assertEquals(0, board.getPlayerTwo().getScore());
	}
	
	//playing "RAT"
	@Test
	public void testBasicPlay() {
		board.placeTile(board.getCurrPlayer().getRack().get(4), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 8 * 40, 7 * 40 + 25);
		assertEquals(6, board.testMakeMove(-1));
		assertEquals(6, board.getPlayerOne().getScore());
	}
	
	@Test
	public void testFirstPlayNotInCenter() {
		board.placeTile(board.getCurrPlayer().getRack().get(4), 8 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 9 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 10 * 40, 7 * 40 + 25);
		assertEquals(-2, board.testMakeMove(-1));
	}
	
	@Test
	public void testFirstNotMoreThanOneLetter() {
		board.placeTile(board.getCurrPlayer().getRack().get(4), 7 * 40, 7 * 40 + 25);
		assertEquals(-3, board.testMakeMove(-1));
	}
	
	@Test
	public void testTilesNotSameLine() {
		board.placeTile(board.getCurrPlayer().getRack().get(4), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 8 * 40, 8 * 40 + 25);		
		assertEquals(-4, board.testMakeMove(-1));
	}
	
	@Test
	public void testTilesNotAdjacent() {
		board.placeTile(board.getCurrPlayer().getRack().get(4), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 9 * 40, 8 * 40 + 25);		
		assertEquals(-4, board.testMakeMove(-1));
	}
	
	@Test
	public void testInvalidWord() {
		//Playing the word "TNA"
		board.placeTile(board.getCurrPlayer().getRack().get(6), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(3), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 8 * 40, 7 * 40 + 25);		
		assertEquals(-5, board.testMakeMove(-1));
	}
	
	@Test
	public void testSecondMoveOneTile() {
		//Playing the word "RAT"
		board.placeTile(board.getCurrPlayer().getRack().get(4), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 8 * 40, 7 * 40 + 25);
		assertEquals(6, board.testMakeMove(-1));
		
		//Playing word "AT"
		board.placeTile(board.getCurrPlayer().getRack().get(6), 7 * 40, 8 * 40 + 25);
		assertEquals(2, board.testMakeMove(-1));
		assertEquals(6, board.getPlayerOne().getScore());
		assertEquals(2, board.getPlayerTwo().getScore());
	}
	
	@Test
	public void testSecondMoveMultipleTiles() {
		//Playing the word "RAT"
		board.placeTile(board.getCurrPlayer().getRack().get(4), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 8 * 40, 7 * 40 + 25);
		assertEquals(6, board.testMakeMove(-1));
		
		//Playing word "RAT" below the prevoius rat to also form "AR" and "TA"
		board.placeTile(board.getCurrPlayer().getRack().get(4), 7 * 40, 8 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 8 * 40, 8 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 9 * 40, 8 * 40 + 25);		
		assertEquals(9, board.testMakeMove(-1));
		assertEquals(6, board.getPlayerOne().getScore());
		assertEquals(9, board.getPlayerTwo().getScore());
	}
	
	@Test
	public void testInvalidSecondMove() {
		//Playing the word "RAT"
		board.placeTile(board.getCurrPlayer().getRack().get(4), 6 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 7 * 40, 7 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 8 * 40, 7 * 40 + 25);
		assertEquals(6, board.testMakeMove(-1));
		
		//Playing word "RAT" but not adjacent
		board.placeTile(board.getCurrPlayer().getRack().get(4), 7 * 40, 10 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(0), 8 * 40, 10 * 40 + 25);
		board.placeTile(board.getCurrPlayer().getRack().get(4), 9 * 40, 0 * 40 + 25);		
		assertEquals(-4, board.testMakeMove(-1));
		assertEquals(6, board.getPlayerOne().getScore());
		assertEquals(0, board.getPlayerTwo().getScore());
	}

}
