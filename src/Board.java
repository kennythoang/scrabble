import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
	
import javax.swing.*;
	
	/**
	 * Board
	 * 
	 * This class handles all logic and gameplay (as well as drawing) for Scrabble game. 
	 */
	@SuppressWarnings("serial")
	public class Board extends JPanel {
		
		private BoardSpace[][] board = new BoardSpace[15][15];
		private Player playerOne;
		private Player playerTwo;
		private ArrayList<Tile> tileBag;
		private ArrayList<Tile> movedTiles;
		private Dictionary dict;
		private Tile selectedTile = null;
		private TreeMap<Integer, ArrayList<String>> highScores;
	
		// size of board space
		final private int spaceSize = 40;
		
		// if it is the first turn of the game
		private boolean firstTurn = true;
	
	
	    public boolean playerOneTurn = true; 
	    private JLabel status; // Current status text, i.e. "Running..."
	
	    // Game constants
	    public static final int BOARD_WIDTH = 600;
	    public static final int BOARD_HEIGHT = 800;
	
	
	    public Board(JLabel status) {
//	    	highScores = new TreeMap<Integer, ArrayList<String>>();
//	    	BufferedReader reader;
//	    	try {
//	    		reader = new BufferedReader(new FileReader("files/HighScores.txt"));
//	    		String line = reader.readLine();
//	    		while (line != null) {
//	    			line = line.trim();
//	    			int colonIndex = line.indexOf(":");
//	    			int highScore = Integer.parseInt(line.substring(0, colonIndex));
//	    			String name = line.substring(colonIndex + 1);
//	    	    	if (!highScores.containsKey(highScore)) {
//	    	    		ArrayList<String> names = new ArrayList<String>();
//	    	    		names.add(name);
//	    	    		highScores.put(highScore, names);
//	    	    	}
//	    	    	else {
//	    	    		ArrayList<String> names = highScores.get(highScore);
//	    	    		names.add(name);
//	    	    		highScores.put(highScore, names);
//	    	    	}
//	    	    	line = reader.readLine();
//	    		}
//	    		reader.close();
//	    	}	catch (IOException e) {
//	    		// do nothing
//	    	}
	    	
	    
	        // creates border around the court area, JComponent method
	        setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        playerOne = new Player("Player One");
	        playerTwo = new Player("Player Two");
	        tileBag = new ArrayList<Tile>(100);
	        movedTiles = new ArrayList<Tile>();
	        try {
	        	dict = Dictionary.make("files/twl3.txt");
	        } catch (IOException ex) {
	        	// do nothing
	        }
	        refillBag();
	        fillRack(playerOne);
	        fillRack(playerTwo);
	        resetBoard();
	
	        this.status = status;
	    }
	    
	    public void fillRack(Player p) {
	    	while ((tileBag.size() > 0) && (p.getRackSize() < 7)) {
	    		int randomIndex = (int) (Math.random() * tileBag.size());
	    		p.addTile(tileBag.get(randomIndex));
	    		tileBag.remove(randomIndex);
	    	}
	    }
	    
	    public void refillBag() {
	    	int index = 0;
	    	tileBag = new ArrayList<Tile>(100);
	    	for (int i = 0; i < 9; i++) {
	    		tileBag.add(index, new Tile("A", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("E", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("I", 1, -1, -1));
	    		index++;
	    	}
	    	for (int i = 0; i < 3; i++) {
	    		tileBag.add(index, new Tile("E", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("G", 2, -1, -1));
	    		index++;
	    	}
	    	for (int i = 0; i < 8; i++) {
	    		tileBag.add(index, new Tile("O", 1, -1, -1));
	    		index++;
	    	}
	    	for (int i = 0; i < 2; i++) {
	    		tileBag.add(index, new Tile("B", 3, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("C", 3, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("F", 4, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("H", 4, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("M", 3, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("P", 3, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("V", 4, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("W", 4, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("Y", 4, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("?", 0, -1, -1));
	    		index++;
	    	}
	    	for (int i = 0; i < 4; i++) {
	    		tileBag.add(index, new Tile("L", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("S", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("U", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("D", 2, -1, -1));
	    		index++;
	    	}
	    	for (int i = 0; i < 6; i++) {
	    		tileBag.add(index, new Tile("R", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("T", 1, -1, -1));
	    		index++;
	    		tileBag.add(index, new Tile("N", 1, -1, -1));
	    		index++;
	    	}
			tileBag.add(index, new Tile("Z", 10, -1, -1));
			index++;
			tileBag.add(index, new Tile("K", 5, -1, -1));
			index++;
			tileBag.add(index, new Tile("J", 8, -1, -1));
			index++;
			tileBag.add(index, new Tile("X", 8, -1, -1));
			index++;
			tileBag.add(index, new Tile("Q", 10, -1, -1));
			index++;
	    }
	
	    /**
	     * (Re-)set the game to its initial state.
	     */
	    public void reset() {
	        playerOneTurn = true;
	        boolean invalidPlayerOneName = true;
	        boolean invalidPlayerTwoName = true;
	        String playerOneName = "";
	        String playerTwoName = "";
	        while (invalidPlayerOneName) {
		        playerOneName = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Enter your name. Only actually letters please, no spaces!",
	                    "What's your name player one?", JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    "Name");
		        invalidPlayerOneName = !isValidName(playerOneName);
	        }
	        while (invalidPlayerTwoName) {
		        playerTwoName = (String)JOptionPane.showInputDialog(
	                    null,
	                    "Enter your name. Only actually letters please, no spaces!",
	                    "What's your name player two?", JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    "Name");
		        invalidPlayerTwoName = !isValidName(playerTwoName);
	        }
	        
	        playerOne = new Player(playerOneName);
	        playerTwo = new Player(playerTwoName);
	        fillRack(playerOne);
	        fillRack(playerTwo);
	        status.setText(playerOne.getName() + "\'s Turn");
	        refillBag();
	        resetBoard();
	        movedTiles.clear();
	        firstTurn = true;
	        
	        repaint();
	
	        // Make sure that this component has the keyboard focus
	        requestFocusInWindow();
	    }
	    
	    // helper for if input name is valid
	    public boolean isValidName(String name) {
	    	if (name == null) {
	    		return false;
	    	}
	        char[] chars = name.toCharArray();

	        for (char c : chars) {
	            if(!Character.isLetter(c)) {
	                return false;
	            }
	        }

	        return true;
	    }
	    
	    public void makeMove() {
	    	// if direction of word played is vertical or not
	    	boolean vertical = false;
	    	if (movedTiles.size() == 0) {
	    		int input = JOptionPane.showConfirmDialog(null, 
	    				    "Are you sure you want to pass your turn?", "Passing your turn?", 
	    				JOptionPane.YES_NO_OPTION);
	    		if (input == 0) {
	    			newTurn();
	    		}
	    		return;
	    	}
	//    	// technically if only one tile is played, there is no direction 
	//    	else if (movedTiles.size() == 1) {
	//    		vertical = true;
	//    	}
	    	else {
	    		ArrayList<Integer> xPositions = new ArrayList<Integer>();
	    		ArrayList<Integer> yPositions = new ArrayList<Integer>();

	    		for (Tile tile : movedTiles) {
	    			xPositions.add(tile.getX());
	    			yPositions.add(tile.getY());
	    		}
	
	    		int minX = Collections.min(xPositions);
	    		int maxX = Collections.max(xPositions);
	    		int maxY = Collections.max(yPositions);
	    		int minY = Collections.min(yPositions);
	    		Set<String> wordsPlayed = new TreeSet<String>();
	    		int score = 0;

	    		
	    		if (firstTurn && (!xPositions.contains(7 * 40) || !yPositions.contains(7 * 40))) {
	    			JOptionPane.showMessageDialog(null,
	    				    ("First play needs to have a tile touching center of board"),
	    				    "Invalid play",
	    				    JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		if (firstTurn && movedTiles.size() == 1) {
	    			JOptionPane.showMessageDialog(null,
	    				    ("Words need to be longer than one letter"),
	    				    "Invalid play",
	    				    JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		if (Collections.frequency(xPositions, xPositions.get(0)) == xPositions.size()) {
	    			vertical = true;
	    		}
	    		else if (Collections.frequency(yPositions, yPositions.get(0)) == 
	    				yPositions.size()) {
	    			vertical = false;
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(null,
	    				    ("Placed tiles need to be horizontal OR vertical as each other"),
	    				    "Invalid play",
	    				    JOptionPane.ERROR_MESSAGE);	    			
	    			return;
	    		}


	    	    // score move
	    	    
	    	    // special edge case where only one tile played
	    	    if (movedTiles.size() == 1) {
	    			int x = xPositions.get(0);
	    			int y = yPositions.get(0);
	    			
	    			int leftBound = x;
	    			int rightBound = x;
	    			boolean moreLeft = true;
	    			boolean moreRight = true;
	    			
    				int upperBound = y;
    				int lowerBound = y;
    				boolean moreDown = true;
    				boolean moreUp = true;
	    			  				
    				
	    			// find left bound
	   				while (moreLeft) {
	   					if (leftBound <= 0) {
	   						leftBound = 0;
	   						moreLeft = false;
	   					}
	   					else if (board[y / 40][leftBound / 40].getTile() != null) {
	    					leftBound -= 40;
	    				}
	    				else {
	    					leftBound += 40;
	   						moreLeft = false;
	   					}
	   				}
	   				// find right bound
    				while (moreRight) {
    					if (rightBound >= 15 * 40) {
    						rightBound = 14 * 40;
    						moreRight = false;
    					}
    					else if (board[y / 40][rightBound / 40].getTile() != null) {
	    					rightBound += 40;
	    				}
	    				else {
	    					rightBound -= 40;
	   						moreRight = false;
	   					}
	   				}
    				while (moreUp) {
    					if (upperBound <= 0) {
    						upperBound = 0;
    						moreUp = false;
    					}
    					else if (board[upperBound / 40][x / 40].getTile() != null) {
    						upperBound -= 40;
    					}
    					else {
    						upperBound += 40;
    						moreUp = false;
    					}
    				}
    				// find lower bound
    				while (moreDown) {
    					if (lowerBound >= 15 * 40) {
    						lowerBound = 14 * 40;
    						moreDown = false;
    					}
    					else if (board[lowerBound / 40][x / 40].getTile() != null) {
    						lowerBound += 40;
    					}
    					else {
    						lowerBound -= 40;
    						moreDown = false;
    					}
    				}
	    			if (rightBound != leftBound) {
	    				int wordScore = 0;
	    				int wordMultiplier = 1;
	    				String currWord = "";
	    				for (int j = leftBound / 40; j <= rightBound / 40; j++) {
	    					Space curr = board[y / 40][j].getSpace();
	    					if (curr == Space.DW) {
	    						wordMultiplier *= 2;
	    					}
	    					if (curr == Space.TW) {
	    						wordMultiplier *= 3;
	   						}
	   						int tileMultiplier = 1;
	   						if (curr == Space.DL) {
	   							tileMultiplier = 2;
	    					}
	    					if (curr == Space.TL) {
	    						tileMultiplier = 3;
	    					}
	    					wordScore += board[y / 40][j].getTile().getValue() * tileMultiplier;
	    					currWord += board[y / 40][j].getTile().getLetter();
	    				}
	    				score += wordScore * wordMultiplier;
	    				wordsPlayed.add(currWord);
	   				}
	    			if (upperBound != lowerBound) {
	    				int wordScore = 0;
	    				int wordMultiplier = 1;
	    				String currWord = "";
	    				for (int j = upperBound / 40; j <= lowerBound / 40; j++) {
	    					Space curr = board[j][x / 40].getSpace();
	    					if (curr == Space.DW) {
	    						wordMultiplier *= 2;
	    					}
	    					if (curr == Space.TW) {
	    						wordMultiplier *= 3;
	   						}
	   						int tileMultiplier = 1;
	   						if (curr == Space.DL) {
	   							tileMultiplier = 2;
	    					}
	    					if (curr == Space.TL) {
	    						tileMultiplier = 3;
	    					}
	    					wordScore += board[j][x / 40].getTile().getValue() * tileMultiplier;
	    					currWord += board[j][x / 40].getTile().getLetter();
	    				}
	    				score += wordScore * wordMultiplier;
	    				wordsPlayed.add(currWord);
	    			}
	    	    }
	    	    
	    	    
	    		//for vertical words
	    	    else if (vertical) { 
	    			int x = xPositions.get(0);
	    			int y = yPositions.get(0);

	    			boolean moreUp = true;
	    			boolean moreDown = true;
	    			int upperBound = y;
	    			int lowerBound = y;
	    			
	    			// find upper bound (higher tile vertically)
	    			while (moreUp) {
	    				if (upperBound < 0) {
	    					upperBound = 0;
	    					moreUp = false;
	    				}
    					else if (upperBound == 0) {
    						if (board[upperBound / 40][x / 40].getTile() == null) {
    							upperBound += 40;
    						}
    						moreUp = false;
    					}
	    				else if (board[upperBound / 40][x / 40].getTile() != null) {
	    					upperBound -= 40;
	    				}
	    				else {
	    					upperBound += 40;
	    					moreUp = false;
	    				}
	    			}
	    			
	    			// find lower bound
	    			while (moreDown) {
	    				if (lowerBound / 40 > 14) {
	    					lowerBound = 14 * 40;
	    					moreDown = false;
	    				}
    					else if (lowerBound / 40 == 14) {
    						if (board[lowerBound / 40][x / 40].getTile() == null) {
    							lowerBound -= 40;
    						}
    						moreDown = false;
    					}
	    				else if (board[lowerBound / 40][x / 40].getTile() != null) {
	    					lowerBound += 40;
	    				}
	    				else {
	    					lowerBound -= 40;
	    					moreDown = false;
	    				}
	    			}
	    			
	    			
	    			
	    			// calculate score for each orthogonal word created, if any
	    			for (int i : yPositions) {
	    				i = i / 40;
	    				int leftBound = x;
	    				int rightBound = x;
	    				boolean moreLeft = true;
	    				boolean moreRight = true;
	    				
	    				// find left bound
	    				while (moreLeft) {
	    					if (leftBound < 0) {
	    						leftBound = 0;
	    						moreLeft = false;
	    					}
	    					else if (leftBound == 0) {
	    						if (board[i][leftBound / 40].getTile() == null) {
	    							leftBound += 40;
	    						}
	    						moreLeft = false;
	    					}
	    					else if (board[i][leftBound / 40].getTile() != null) {
	    						leftBound -= 40;
	    					}
	    					else {
	    						leftBound += 40;
	    						moreLeft = false;
	    					}
	    				}
	    				// find right bound
	    				while (moreRight) {
	    					if (rightBound > 14 * 40) {
	    						rightBound = 14 * 40;
	    						moreRight = false;
	    					}
	    					else if (rightBound == 14 * 40) {
	    						if (board[i][rightBound / 40].getTile() == null) {
	    							rightBound -= 40;
	    						}
	    						moreRight = false;
	    					}
	    					else if (board[i][(int) rightBound / 40].getTile() != null) {
	    						rightBound += 40;
	    					}
	    					else {
	    						rightBound -= 40;
	    						moreRight = false;
	    					}
	    				}
	    				if (rightBound != leftBound) {
	    					int wordScore = 0;
	    					int wordMultiplier = 1;
	    					String currWord = "";
	    					for (int j = (int) leftBound / 40; j <= (int) rightBound / 40; j++) {
	    						Space curr = board[i][j].getSpace();
	    						if (curr == Space.DW) {
	    							wordMultiplier *= 2;
	    						}
	    						if (curr == Space.TW) {
	    							wordMultiplier *= 3;
	    						}
	    						int tileMultiplier = 1;
	    						if (curr == Space.DL) {
	    							tileMultiplier = 2;
	    						}
	    						if (curr == Space.TL) {
	    							tileMultiplier = 3;
	    						}
	    						wordScore += board[i][j].getTile().getValue() * tileMultiplier;
	    						currWord += board[i][j].getTile().getLetter();
	    					}
	    					score += wordScore * wordMultiplier;
	    					wordsPlayed.add(currWord);
	    				}
	    			}
	    			// calculate score for vertical word created
	    			int wordScore = 0;
	    			int wordMultiplier = 1;
	    			String currWord = "";
	    			for (int i = upperBound / 40; i <= lowerBound / 40; i++) {
						Space curr = board[i][x / 40].getSpace();
						if (curr == Space.DW) {
							wordMultiplier *= 2;
						}
						if (curr == Space.TW) {
							wordMultiplier *= 3;
						}
						int tileMultiplier = 1;
						if (curr == Space.DL) {
							tileMultiplier = 2;
						}
						if (curr == Space.TL) {
							tileMultiplier = 3;
						}
						wordScore += board[i][x / 40].getTile().getValue() * tileMultiplier;
						currWord += board[i][x / 40].getTile().getLetter();
	    			}
	    			score += wordScore * wordMultiplier;
	    			wordsPlayed.add(currWord);
	    		}
	    		// for horizontal words
	    		else {
	    			int y = yPositions.get(0);
	    			boolean moreRight = true;
	    			boolean moreLeft = true;
	    			int leftBound = xPositions.get(0);
	    			int rightBound = xPositions.get(0);
	    			// find left bound
	   				while (moreLeft) {
	   					if (leftBound < 0) {
	   						leftBound = 0;
	   						moreLeft = false;
	   					}
    					else if (leftBound == 0) {
    						if (board[y / 40][leftBound / 40].getTile() == null) {
    							leftBound += 40;
    						}
    						moreLeft = false;
    					}
	   					else if (board[y / 40][leftBound / 40].getTile() != null) {
	    					leftBound -= 40;
	    				}
	    				else {
	    					leftBound += 40;
	   						moreLeft = false;
	   					}
	   				}
	   				// find right bound
    				while (moreRight) {
    					if (rightBound > 14 * 40) {
    						rightBound = 14 * 40;
    						moreRight = false;
    					}
    					else if (rightBound == 14 * 40) {
    						if (board[y / 40][rightBound / 40].getTile() == null) {
    							rightBound -= 40;
    						}
    						moreRight = false;
    					}
    					else if (board[y / 40][rightBound / 40].getTile() != null) {
	    					rightBound += 40;
	    				}
	    				else {
	    					rightBound -= 40;
	   						moreRight = false;
	   					}
	   				}
	    			
	    			// calculate score for each orthogonal word created, if any
	    			for (int i : xPositions) {
	    				i = i / 40;
	    				int upperBound = y;
	    				int lowerBound = y;
	    				boolean moreDown = true;
	    				boolean moreUp = true;

	    				// find upper bound
	    				while (moreUp) {
	    					if (upperBound < 0) {
	    						upperBound = 0;
	    						moreUp = false;
	    					}
	    					else if (upperBound == 0) {
	    						if (board[upperBound / 40][i].getTile() == null) {
	    							upperBound += 40;
	    						}
	    						moreUp = false;
	    					}
	    					else if (board[upperBound / 40][i].getTile() != null) {
	    						upperBound -= 40;
	    					}
	    					else {
	    						upperBound += 40;
	    						moreUp = false;
	    					}
	    				}
	    				// find lower bound
	    				while (moreDown) {
	    					if (lowerBound > 14 * 40) {
	    						lowerBound = 14 * 40;
	    						moreDown = false;
	    					}
	    					else if (lowerBound == 14 * 40) {
	    						if (board[lowerBound / 40][i].getTile() == null) {
	    							lowerBound -= 40;
	    						}
	    						moreDown = false;
	    					}
	    					else if (board[lowerBound / 40][i].getTile() != null) {
	    						lowerBound += 40;
	    					}
	    					else {
	    						lowerBound -= 40;
	    						moreDown = false;
	    					}
	    				}
	    				if (lowerBound != upperBound) {
	    					int wordScore = 0;
	    					int wordMultiplier = 1;
	    					String currWord = "";
	    					for (int j = upperBound / 40; j <= lowerBound / 40; j++) {
	    						Space curr = board[j][i].getSpace();
	    						if (curr == Space.DW) {
	    							wordMultiplier *= 2;
	    						}
	    						if (curr == Space.TW) {
	    							wordMultiplier *= 3;
	    						}
	    						int tileMultiplier = 1;
	    						if (curr == Space.DL) {
	    							tileMultiplier = 2;
	    						}
	    						if (curr == Space.TL) {
	    							tileMultiplier = 3;
	    						}
	    						wordScore += board[j][i].getTile().getValue() * tileMultiplier;
	    						currWord += board[j][i].getTile().getLetter();
	    					}
	    					score += wordScore * wordMultiplier;
	    					wordsPlayed.add(currWord);
	    				}
	    			}
	    			// calculate score for horizontal word created
	    			int wordScore = 0;
	    			int wordMultiplier = 1;
	    			String currWord = "";
	    			for (int i = leftBound / 40; i <= rightBound / 40; i++) {
						Space curr = board[y / 40][i].getSpace();
						if (curr == Space.DW) {
							wordMultiplier *= 2;
						}
						if (curr == Space.TW) {
							wordMultiplier *= 3;
						}
						int tileMultiplier = 1;
						if (curr == Space.DL) {
							tileMultiplier = 2;
						}
						if (curr == Space.TL) {
							tileMultiplier = 3;
						}
						wordScore += board[y / 40][i].getTile().getValue() * tileMultiplier;
						currWord += board[y / 40][i].getTile().getLetter();
	    			}
	    			score += wordScore * wordMultiplier;
	    			wordsPlayed.add(currWord);
	    		}	
				// String for all invalid words user plays in their turn (if any)
	    		String invalidWords = "";
	    		
	    		// bool for if all words are valid
	    		boolean validPlay = true;
	    		
	    		// loop through all words played to see if any are invalid
	    		for (String word : wordsPlayed) {
	    			if (!dict.isWord(word)) {
	    				invalidWords += (word + '\n');
	    				validPlay = false;
	    			}
	    		}
	    		if (validPlay) {
	    			// "bingo" bonus of 50 pts if all seven tiles are played in same turn
	    			int bingoBonus = 0;
	    			if (movedTiles.size() == 7) {
	    				bingoBonus = 50;
	    			}
		    		int input = JOptionPane.showConfirmDialog(null, 
	    				    "Are you sure you want to play for " + 
	    				    String.valueOf(score + bingoBonus) + " points?", "Confirm play", 
	    				JOptionPane.YES_NO_OPTION);
		    		if (input == 1) {
		    			return;
		    		}
	    			firstTurn = false;
					if (playerOneTurn) {
						playerOne.setScore(playerOne.getScore() + score + bingoBonus);
						fillRack(playerOne);
					}
					else {
						playerTwo.setScore(playerTwo.getScore() + score + bingoBonus);
						fillRack(playerTwo);
					}
	    			int y = yPositions.get(0);
	    			int x = xPositions.get(0);
	    			if (vertical) {
	    				for (int i = minY / 40; i <= maxY / 40; i++) {
	    					board[i][x / 40].setSpace(Space.N);
	    				}
	    			}
	    			else {
	    				for (int i = minX / 40; i <= maxX / 40; i++) {
	    					board[y / 40][i].setSpace(Space.N);
	    				}
	    			}
	    			if (getCurrPlayer().getRackSize() == 0) {
	    				endGame();
	    				return;
	    			}
					newTurn();
					return;
	    		}
	    		else {
	    			// dialog box alerting invalid words were played and what they are
	    			JOptionPane.showMessageDialog(null,
	    				    ("You've played invalid words. Try again: " + '\n' + invalidWords),
	    				    "Invalid play",
	    				    JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    	}
	    }
	    
	    public void placeTile(Tile t, double x, double y) {
	    	int col = (int) x / 40;
	    	int row = (int) (y - 25) / 40;
	    	if (board[row][col].getTile() != null) {
	    		return;
	    	}
	    	// if blank tile placed
	    	if (t.getLetter().equals("?")) {
	    		String blank = blankPlaced();
	    		if (blank.equals("")) {
	    			return;
	    		}
	    		t.setLetter(blank);;
	    	}
	    	t.deselect();
	    	t.setLocation((int) x, (int) y);
	    	
	    	board[row][col].setTile(t);
	    	movedTiles.add(t);
	    	getCurrPlayer().removeTile(t);
	    	selectedTile = null;
	    	repaint();
	    }
	    
	    public void endGame() {
	        System.out.println("GOT HERE");
	    	int bonusScore = 0;
	    	if (playerOneTurn) {
	    		for (Tile t : playerTwo.getRack()) {
	    			bonusScore += 2 * t.getValue();
	    		}
	    		playerOne.setScore(playerOne.getScore() + bonusScore);
	    	}
	    	else {
	    		for (Tile t : playerOne.getRack()) {
	    			bonusScore += 2 * t.getValue();
	    		}
	    		playerTwo.setScore(playerTwo.getScore() + bonusScore);
	    	}
	    	if (playerOne.getScore() > playerTwo.getScore()) {
	    		//one wins
				JOptionPane.showMessageDialog(null,
						playerOne.getName() + " wins " + String.valueOf(playerOne.getScore()) + 
						"-" + String.valueOf(playerTwo.getScore()) + "!");
	    	}
	    	else if (playerOne.getScore() < playerTwo.getScore()) {
	    		// two wins
				JOptionPane.showMessageDialog(null,
						playerTwo.getName() + " wins " + String.valueOf(playerTwo.getScore()) + 
						"-" + String.valueOf(playerOne.getScore()) + "!");
	    	}
	    	else {
	    		// tie
				JOptionPane.showMessageDialog(null,
						"It's a tie, " + String.valueOf(playerOne.getScore()) + "-" + 
				String.valueOf(playerOne.getScore()) + "! You don't see that everyday!");
	    	}
//	    	handleHighScores();
	    }
	    
	    
	    public void resetBoard() {
	    	for (int i = 0; i < 15; i ++) {
	    		for (int j = 0; j < 15; j++) {
	    			board[i][j] = new BoardSpace(spaceSize * j, spaceSize * i);
	    		}
	    	}
	    	//set TW's
	    	board[0][0].setSpace(Space.TW);
	    	board[7][0].setSpace(Space.TW);
	    	board[14][0].setSpace(Space.TW);
	    	board[0][7].setSpace(Space.TW);
	    	board[14][7].setSpace(Space.TW);
	    	board[0][14].setSpace(Space.TW);
	    	board[7][14].setSpace(Space.TW);
	    	board[14][14].setSpace(Space.TW);
	    	
	    	//set DW's
	    	board[1][1].setSpace(Space.DW);
	    	board[2][2].setSpace(Space.DW);
	    	board[3][3].setSpace(Space.DW);
	    	board[4][4].setSpace(Space.DW);
	    	board[13][1].setSpace(Space.DW);
	    	board[12][2].setSpace(Space.DW);
	    	board[11][3].setSpace(Space.DW);
	    	board[10][4].setSpace(Space.DW);
	    	board[1][13].setSpace(Space.DW);
	    	board[2][12].setSpace(Space.DW);
	    	board[3][11].setSpace(Space.DW);
	    	board[4][10].setSpace(Space.DW);
	    	board[13][13].setSpace(Space.DW);
	    	board[12][12].setSpace(Space.DW);
	    	board[11][11].setSpace(Space.DW);
	    	board[10][10].setSpace(Space.DW);
	    	board[7][7].setSpace(Space.DW);
	    	
	    	//set DL's
	    	board[3][0].setSpace(Space.DL);
	    	board[11][0].setSpace(Space.DL);
	    	board[6][2].setSpace(Space.DL);
	    	board[8][2].setSpace(Space.DL);
	    	board[0][3].setSpace(Space.DL);
	    	board[7][3].setSpace(Space.DL);
	    	board[14][3].setSpace(Space.DL);
	    	board[2][6].setSpace(Space.DL);
	    	board[6][6].setSpace(Space.DL);
	    	board[8][6].setSpace(Space.DL);
	    	board[12][6].setSpace(Space.DL);
	    	board[3][7].setSpace(Space.DL);
	    	board[11][7].setSpace(Space.DL);
	    	board[2][8].setSpace(Space.DL);
	    	board[6][8].setSpace(Space.DL);
	    	board[8][8].setSpace(Space.DL);
	    	board[12][8].setSpace(Space.DL);
	    	board[0][11].setSpace(Space.DL);
	    	board[7][11].setSpace(Space.DL);
	    	board[14][11].setSpace(Space.DL);
	    	board[6][12].setSpace(Space.DL);
	    	board[8][12].setSpace(Space.DL);
	    	board[3][14].setSpace(Space.DL);
	    	board[11][14].setSpace(Space.DL);
	    	
	    	//set TL's
	    	board[1][5].setSpace(Space.TL);
	    	board[1][9].setSpace(Space.TL);
	    	board[5][1].setSpace(Space.TL);
	    	board[5][5].setSpace(Space.TL);
	    	board[5][9].setSpace(Space.TL);
	    	board[5][13].setSpace(Space.TL);
	    	board[9][1].setSpace(Space.TL);
	    	board[9][5].setSpace(Space.TL);
	    	board[9][9].setSpace(Space.TL);
	    	board[9][13].setSpace(Space.TL);
	    	board[13][5].setSpace(Space.TL);
	    	board[13][9].setSpace(Space.TL);
	    }
	    // switches whose turn it is, with popup displays
	    public void newTurn() {
	    	selectedTile = null;
	    	movedTiles.clear();
			playerOneTurn = !playerOneTurn;
			String name;
			if (playerOneTurn) {
				name = playerOne.getName();
				status.setText(name + "\'s Turn");
			} 
			else {
				name = playerTwo.getName();
				status.setText(name + "\'s Turn");
			}
			JOptionPane.showMessageDialog(null,
					"Are you ready " + name + "?");
			repaint();
	    }
	    
	    public Player getPlayerOne() {
	    	return playerOne;
	    }
	    
	    public Player getPlayerTwo() {
	    	return playerTwo;
	    }
	    
	    public Player getCurrPlayer() {
	    	if (playerOneTurn) {
	    		return playerOne;
	    	}
	    	else {
	    		return playerTwo;
	    	}
	    }
	    
	    //handles clicks
	    public void click(double x, double y) {
	    	// clicking rack tile
	    			
	    	if ((y >= 685 && y <= 735) && (x >= 120 && x <= 120 + 
	    			getCurrPlayer().getRackSize() * 50)) {
	    		// selected tile's index in rack
	    		int rackIndex = (int) ((x - 120) / 50);
	    		// first tile selection
	    		if (selectedTile == null) {
	    			selectedTile = getCurrPlayer().getRack().get(rackIndex);
	    			selectedTile.toggleSelect();
	    		}
	    		// select same tile
	    		else if (selectedTile == getCurrPlayer().getRack().get(rackIndex)){
	    			selectedTile.toggleSelect();
	    			selectedTile = null;
	    		}
	    		// select different tile
	    		else {
	    			selectedTile.toggleSelect();
	    			selectedTile = getCurrPlayer().getRack().get(rackIndex);
	    			selectedTile.toggleSelect();
	    		}
	    		repaint();
	    	}
	    	// clicking spot on board
	    	else if ((y >= 25 && y <= 25 + 40 * 15) && (x >= 0 && x <= 40 * 15)) {
	    		if (selectedTile == null) {
	    			return;
	    		}
	    		else {
	    			placeTile(selectedTile, x, y);
	    		}
	    	}
	    }
	    
	    public void recallTiles() {
	    	for (Tile t : movedTiles) {
		    	// if blank tile
		    	if (t.getValue() == 0) {
		    		t.setLetter("?");
		    	}
		    	int col = t.getX() / 40;
		    	int row = t.getY() / 40;

		    	board[row][col].clearTile();
		    	t.setSize(50);
		    	getCurrPlayer().addTile(t);
	    	}
	    	movedTiles.clear();
	    	if (selectedTile != null) {
	    		selectedTile.deselect();
	    		selectedTile = null;
	    	}
	    	repaint();
	    }
	    
	    public void shuffleRack() {
	    	getCurrPlayer().shuffleRack();
	    	repaint();
	    }
	    
	    public String blankPlaced() {
	    	Object[] possibilities = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
	    			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	    	String blank = (String)JOptionPane.showInputDialog(
	    	                    null,
	    	                    "What tile would you like your blank to be?",
	    	                    "Blank tile placed!",
	    	                    JOptionPane.PLAIN_MESSAGE,
	    	                    null,
	    	                    possibilities,
	    	                    "A");
	    	if (blank != null && blank.length() > 0) {
	    		return blank;
	    	}
	    	else {
	    		return "";
	    	}
	    }
	    
//	    public void handleHighScores() {
//	    	// add player one
//	    	if (!highScores.containsKey(playerOne.getScore())) {
//	    		ArrayList<String> names = new ArrayList<String>();
//	    		names.add(playerOne.getName());
//	    		highScores.put(playerOne.getScore(), names);
//	    	}
//	    	else {
//	    		ArrayList<String> names = highScores.get(playerOne.getScore());
//	    		names.add(playerOne.getName());
//	    		highScores.put(playerOne.getScore(), names);
//	    	}
//	    	// add player two
//	    	if (!highScores.containsKey(playerTwo.getScore())) {
//	    		ArrayList<String> names = new ArrayList<String>();
//	    		names.add(playerTwo.getName());
//	    		highScores.put(playerTwo.getScore(), names);
//	    	}
//	    	else {
//	    		ArrayList<String> names = highScores.get(playerTwo.getScore());
//	    		names.add(playerTwo.getName());
//	    		highScores.put(playerTwo.getScore(), names);
//	    	}
//	    	int[] scores = new int[3];
//	    	String[] names = new String[3];
//	    	int count = 0;
//	    	// get top three high scores
//	    	int currHigh = highScores.lastKey();
//	    	ArrayList<String> currNames = highScores.get(currHigh);
//	    	for (String s : currNames) {
//	    		if (count >= 3) {
//	    			break;
//	    		}
//	    		else {
//	    			scores[count] = currHigh;
//	    			names[count] = s;
//	    			count++;
//	    		}
//	    	}
//	    	if (count < 3 && highScores.size() >= 3 - count) {
//	    		currHigh = highScores.lowerKey(currHigh);
//		    	currNames = highScores.get(currHigh);
//		    	for (String s : currNames) {
//		    		if (count >= 3) {
//		    			break;
//		    		}
//		    		else {
//		    			scores[count] = currHigh;
//		    			names[count] = s;
//		    			count++;
//		    		}
//		    	}
//	    	}
//	    	if (count < 3 && highScores.size() >= 3 - count) {
//		    	currHigh = highScores.lowerKey(currHigh);
//		    	currNames = highScores.get(currHigh);
//		    	for (String s : currNames) {
//		    		if (count >= 3) {
//		    			break;
//		    		}
//		    		else {
//		    			scores[count] = currHigh;
//		    			names[count] = s;
//		    			count++;
//		    		}
//		    	}
//	    	}
//			JOptionPane.showMessageDialog(null,
//					"High scores: \n" + names[0] + ": "+ String.valueOf(scores[0]) + "\n" + 
//					names[1] + ": " + String.valueOf(scores[1]) + "\n" +
//				    names[2] + ": " + String.valueOf(scores[2]));
//			try {
//				FileWriter fw = new FileWriter("files/Highscores.txt", false);
//			 
//				boolean firstOutput = true;
//		    	for (int score : highScores.keySet()) {
//		    		for (String name : highScores.get(score)) {
//		    			String output;
//		    			if (firstOutput) {
//			    			output = String.valueOf(score) + ":" + name;
//			    			firstOutput = false;
//		    			}
//		    			else {
//		    				output = "\n" + String.valueOf(score) + ":" + name;
//		    			}
//		    			fw.write(output);
//		    		}
//		    	}
//				fw.close();
//			} catch (IOException e) {
//				//do nothing
//			}
//	    }
	    
	    public ArrayList<Tile> getTileBag() {
	    	return tileBag;
	    }
	
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (playerOneTurn) {
	        	playerOne.drawRack(g);
	        }
	        else {
	        	playerTwo.drawRack(g);
	        }
	        for (int row = 0; row < board.length; row++) {
	        	for (int col = 0; col < board.length; col++) {
	        		board[row][col].draw(g);
	        	}
	        }
//	        g.setColor(Color.WHITE);
//	        g.fillRect(615, 75, 300, 200);
//	        g.setFont(new Font("Century Gothic Bold", Font.BOLD, 23)); 
//	        g.drawString(String.valueOf(playerOne.getScore()), 615, 0);
//	        
//	        g.fillRect(615, 300, 300, 200);
//	        g.drawString(String.valueOf(playerOne.getScore()), 615, 300);
	
	//        square.draw(g);
	//        poison.draw(g);
	//        snitch.draw(g);
	    }
	
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	    }
	    
	    
	    
	    // function copied from makeMove but altered for junit purposes
	    
	    // test code 0 = testing a pass, returns -1
	    // return -2 if First play needs to have a tile touching center of board error
	    // return -3 if first move needs to be more than one letter error
	    // return -4 Placed tiles need to be horizontal OR vertical as each other
	    // return -5 if invalid words error
	    public int testMakeMove(int testCode) {
	    	// if direction of word played is vertical or not
	    	boolean vertical = false;
	    	if (testCode == 0) {
	    		testNewTurn();
	    		return -1;
	    	}
	//    	// technically if only one tile is played, there is no direction 
	//    	else if (movedTiles.size() == 1) {
	//    		vertical = true;
	//    	}
	    	else {
	    		ArrayList<Integer> xPositions = new ArrayList<Integer>();
	    		ArrayList<Integer> yPositions = new ArrayList<Integer>();

	    		for (Tile tile : movedTiles) {
	    			xPositions.add(tile.getX());
	    			yPositions.add(tile.getY());
	    		}
	
	    		int minX = Collections.min(xPositions);
	    		int maxX = Collections.max(xPositions);
	    		int maxY = Collections.max(yPositions);
	    		int minY = Collections.min(yPositions);
	    		Set<String> wordsPlayed = new TreeSet<String>();
	    		int score = 0;

	    		
	    		if (firstTurn && (!xPositions.contains(7 * 40) || !yPositions.contains(7 * 40))) {
	    			return -2;
	    		}
	    		
	    		if (firstTurn && movedTiles.size() == 1) {
	    			return -3;
	    		}
	    		
	    		if (Collections.frequency(xPositions, xPositions.get(0)) == xPositions.size()) {
	    			vertical = true;
	    		}
	    		else if (Collections.frequency(yPositions, yPositions.get(0)) == 
	    				yPositions.size()) {
	    			vertical = false;
	    		}
	    		else { 			
	    			return -4;
	    		}


	    	    // score move
	    	    
	    	    // special edge case where only one tile played
	    	    if (movedTiles.size() == 1) {
	    			int x = xPositions.get(0);
	    			int y = yPositions.get(0);
	    			
	    			int leftBound = x;
	    			int rightBound = x;
	    			boolean moreLeft = true;
	    			boolean moreRight = true;
	    			
    				int upperBound = y;
    				int lowerBound = y;
    				boolean moreDown = true;
    				boolean moreUp = true;
	    			  				
    				
	    			// find left bound
	   				while (moreLeft) {
	   					if (leftBound <= 0) {
	   						leftBound = 0;
	   						moreLeft = false;
	   					}
	   					else if (board[y / 40][leftBound / 40].getTile() != null) {
	    					leftBound -= 40;
	    				}
	    				else {
	    					leftBound += 40;
	   						moreLeft = false;
	   					}
	   				}
	   				// find right bound
    				while (moreRight) {
    					if (rightBound >= 15 * 40) {
    						rightBound = 14 * 40;
    						moreRight = false;
    					}
    					else if (board[y / 40][rightBound / 40].getTile() != null) {
	    					rightBound += 40;
	    				}
	    				else {
	    					rightBound -= 40;
	   						moreRight = false;
	   					}
	   				}
    				while (moreUp) {
    					if (upperBound <= 0) {
    						upperBound = 0;
    						moreUp = false;
    					}
    					else if (board[upperBound / 40][x / 40].getTile() != null) {
    						upperBound -= 40;
    					}
    					else {
    						upperBound += 40;
    						moreUp = false;
    					}
    				}
    				// find lower bound
    				while (moreDown) {
    					if (lowerBound >= 15 * 40) {
    						lowerBound = 14 * 40;
    						moreDown = false;
    					}
    					else if (board[lowerBound / 40][x / 40].getTile() != null) {
    						lowerBound += 40;
    					}
    					else {
    						lowerBound -= 40;
    						moreDown = false;
    					}
    				}
	    			if (rightBound != leftBound) {
	    				int wordScore = 0;
	    				int wordMultiplier = 1;
	    				String currWord = "";
	    				for (int j = leftBound / 40; j <= rightBound / 40; j++) {
	    					Space curr = board[y / 40][j].getSpace();
	    					if (curr == Space.DW) {
	    						wordMultiplier *= 2;
	    					}
	    					if (curr == Space.TW) {
	    						wordMultiplier *= 3;
	   						}
	   						int tileMultiplier = 1;
	   						if (curr == Space.DL) {
	   							tileMultiplier = 2;
	    					}
	    					if (curr == Space.TL) {
	    						tileMultiplier = 3;
	    					}
	    					wordScore += board[y / 40][j].getTile().getValue() * tileMultiplier;
	    					currWord += board[y / 40][j].getTile().getLetter();
	    				}
	    				score += wordScore * wordMultiplier;
	    				wordsPlayed.add(currWord);
	   				}
	    			if (upperBound != lowerBound) {
	    				int wordScore = 0;
	    				int wordMultiplier = 1;
	    				String currWord = "";
	    				for (int j = upperBound / 40; j <= lowerBound / 40; j++) {
	    					Space curr = board[j][x / 40].getSpace();
	    					if (curr == Space.DW) {
	    						wordMultiplier *= 2;
	    					}
	    					if (curr == Space.TW) {
	    						wordMultiplier *= 3;
	   						}
	   						int tileMultiplier = 1;
	   						if (curr == Space.DL) {
	   							tileMultiplier = 2;
	    					}
	    					if (curr == Space.TL) {
	    						tileMultiplier = 3;
	    					}
	    					wordScore += board[j][x / 40].getTile().getValue() * tileMultiplier;
	    					currWord += board[j][x / 40].getTile().getLetter();
	    				}
	    				score += wordScore * wordMultiplier;
	    				wordsPlayed.add(currWord);
	    			}
	    	    }
	    	    
	    	    
	    		//for vertical words
	    	    else if (vertical) { 
	    			int x = xPositions.get(0);
	    			int y = yPositions.get(0);

	    			boolean moreUp = true;
	    			boolean moreDown = true;
	    			int upperBound = y;
	    			int lowerBound = y;
	    			
	    			// find upper bound (higher tile vertically)
	    			while (moreUp) {
	    				if (upperBound < 0) {
	    					upperBound = 0;
	    					moreUp = false;
	    				}
    					else if (upperBound == 0) {
    						if (board[upperBound / 40][x / 40].getTile() == null) {
    							upperBound += 40;
    						}
    						moreUp = false;
    					}
	    				else if (board[upperBound / 40][x / 40].getTile() != null) {
	    					upperBound -= 40;
	    				}
	    				else {
	    					upperBound += 40;
	    					moreUp = false;
	    				}
	    			}
	    			
	    			// find lower bound
	    			while (moreDown) {
	    				if (lowerBound / 40 > 14) {
	    					lowerBound = 14 * 40;
	    					moreDown = false;
	    				}
    					else if (lowerBound / 40 == 14) {
    						if (board[lowerBound / 40][x / 40].getTile() == null) {
    							lowerBound -= 40;
    						}
    						moreDown = false;
    					}
	    				else if (board[lowerBound / 40][x / 40].getTile() != null) {
	    					lowerBound += 40;
	    				}
	    				else {
	    					lowerBound -= 40;
	    					moreDown = false;
	    				}
	    			}
	    			
	    			
	    			
	    			// calculate score for each orthogonal word created, if any
	    			for (int i : yPositions) {
	    				i = i / 40;
	    				int leftBound = x;
	    				int rightBound = x;
	    				boolean moreLeft = true;
	    				boolean moreRight = true;
	    				
	    				// find left bound
	    				while (moreLeft) {
	    					if (leftBound < 0) {
	    						leftBound = 0;
	    						moreLeft = false;
	    					}
	    					else if (leftBound == 0) {
	    						if (board[i][leftBound / 40].getTile() == null) {
	    							leftBound += 40;
	    						}
	    						moreLeft = false;
	    					}
	    					else if (board[i][leftBound / 40].getTile() != null) {
	    						leftBound -= 40;
	    					}
	    					else {
	    						leftBound += 40;
	    						moreLeft = false;
	    					}
	    				}
	    				// find right bound
	    				while (moreRight) {
	    					if (rightBound > 14 * 40) {
	    						rightBound = 14 * 40;
	    						moreRight = false;
	    					}
	    					else if (rightBound == 14 * 40) {
	    						if (board[i][rightBound / 40].getTile() == null) {
	    							rightBound -= 40;
	    						}
	    						moreRight = false;
	    					}
	    					else if (board[i][(int) rightBound / 40].getTile() != null) {
	    						rightBound += 40;
	    					}
	    					else {
	    						rightBound -= 40;
	    						moreRight = false;
	    					}
	    				}
	    				if (rightBound != leftBound) {
	    					int wordScore = 0;
	    					int wordMultiplier = 1;
	    					String currWord = "";
	    					for (int j = (int) leftBound / 40; j <= (int) rightBound / 40; j++) {
	    						Space curr = board[i][j].getSpace();
	    						if (curr == Space.DW) {
	    							wordMultiplier *= 2;
	    						}
	    						if (curr == Space.TW) {
	    							wordMultiplier *= 3;
	    						}
	    						int tileMultiplier = 1;
	    						if (curr == Space.DL) {
	    							tileMultiplier = 2;
	    						}
	    						if (curr == Space.TL) {
	    							tileMultiplier = 3;
	    						}
	    						wordScore += board[i][j].getTile().getValue() * tileMultiplier;
	    						currWord += board[i][j].getTile().getLetter();
	    					}
	    					score += wordScore * wordMultiplier;
	    					wordsPlayed.add(currWord);
	    				}
	    			}
	    			// calculate score for vertical word created
	    			int wordScore = 0;
	    			int wordMultiplier = 1;
	    			String currWord = "";
	    			for (int i = upperBound / 40; i <= lowerBound / 40; i++) {
						Space curr = board[i][x / 40].getSpace();
						if (curr == Space.DW) {
							wordMultiplier *= 2;
						}
						if (curr == Space.TW) {
							wordMultiplier *= 3;
						}
						int tileMultiplier = 1;
						if (curr == Space.DL) {
							tileMultiplier = 2;
						}
						if (curr == Space.TL) {
							tileMultiplier = 3;
						}
						wordScore += board[i][x / 40].getTile().getValue() * tileMultiplier;
						currWord += board[i][x / 40].getTile().getLetter();
	    			}
	    			score += wordScore * wordMultiplier;
	    			wordsPlayed.add(currWord);
	    		}
	    		// for horizontal words
	    		else {
	    			int y = yPositions.get(0);
	    			boolean moreRight = true;
	    			boolean moreLeft = true;
	    			int leftBound = xPositions.get(0);
	    			int rightBound = xPositions.get(0);
	    			// find left bound
	   				while (moreLeft) {
	   					if (leftBound < 0) {
	   						leftBound = 0;
	   						moreLeft = false;
	   					}
    					else if (leftBound == 0) {
    						if (board[y / 40][leftBound / 40].getTile() == null) {
    							leftBound += 40;
    						}
    						moreLeft = false;
    					}
	   					else if (board[y / 40][leftBound / 40].getTile() != null) {
	    					leftBound -= 40;
	    				}
	    				else {
	    					leftBound += 40;
	   						moreLeft = false;
	   					}
	   				}
	   				// find right bound
    				while (moreRight) {
    					if (rightBound > 14 * 40) {
    						rightBound = 14 * 40;
    						moreRight = false;
    					}
    					else if (rightBound == 14 * 40) {
    						if (board[y / 40][rightBound / 40].getTile() == null) {
    							rightBound -= 40;
    						}
    						moreRight = false;
    					}
    					else if (board[y / 40][rightBound / 40].getTile() != null) {
	    					rightBound += 40;
	    				}
	    				else {
	    					rightBound -= 40;
	   						moreRight = false;
	   					}
	   				}
	    			
	    			// calculate score for each orthogonal word created, if any
	    			for (int i : xPositions) {
	    				i = i / 40;
	    				int upperBound = y;
	    				int lowerBound = y;
	    				boolean moreDown = true;
	    				boolean moreUp = true;

	    				// find upper bound
	    				while (moreUp) {
	    					if (upperBound < 0) {
	    						upperBound = 0;
	    						moreUp = false;
	    					}
	    					else if (upperBound == 0) {
	    						if (board[upperBound / 40][i].getTile() == null) {
	    							upperBound += 40;
	    						}
	    						moreUp = false;
	    					}
	    					else if (board[upperBound / 40][i].getTile() != null) {
	    						upperBound -= 40;
	    					}
	    					else {
	    						upperBound += 40;
	    						moreUp = false;
	    					}
	    				}
	    				// find lower bound
	    				while (moreDown) {
	    					if (lowerBound > 14 * 40) {
	    						lowerBound = 14 * 40;
	    						moreDown = false;
	    					}
	    					else if (lowerBound == 14 * 40) {
	    						if (board[lowerBound / 40][i].getTile() == null) {
	    							lowerBound -= 40;
	    						}
	    						moreDown = false;
	    					}
	    					else if (board[lowerBound / 40][i].getTile() != null) {
	    						lowerBound += 40;
	    					}
	    					else {
	    						lowerBound -= 40;
	    						moreDown = false;
	    					}
	    				}
	    				if (lowerBound != upperBound) {
	    					int wordScore = 0;
	    					int wordMultiplier = 1;
	    					String currWord = "";
	    					for (int j = upperBound / 40; j <= lowerBound / 40; j++) {
	    						Space curr = board[j][i].getSpace();
	    						if (curr == Space.DW) {
	    							wordMultiplier *= 2;
	    						}
	    						if (curr == Space.TW) {
	    							wordMultiplier *= 3;
	    						}
	    						int tileMultiplier = 1;
	    						if (curr == Space.DL) {
	    							tileMultiplier = 2;
	    						}
	    						if (curr == Space.TL) {
	    							tileMultiplier = 3;
	    						}
	    						wordScore += board[j][i].getTile().getValue() * tileMultiplier;
	    						currWord += board[j][i].getTile().getLetter();
	    					}
	    					score += wordScore * wordMultiplier;
	    					wordsPlayed.add(currWord);
	    				}
	    			}
	    			// calculate score for horizontal word created
	    			int wordScore = 0;
	    			int wordMultiplier = 1;
	    			String currWord = "";
	    			for (int i = leftBound / 40; i <= rightBound / 40; i++) {
						Space curr = board[y / 40][i].getSpace();
						if (curr == Space.DW) {
							wordMultiplier *= 2;
						}
						if (curr == Space.TW) {
							wordMultiplier *= 3;
						}
						int tileMultiplier = 1;
						if (curr == Space.DL) {
							tileMultiplier = 2;
						}
						if (curr == Space.TL) {
							tileMultiplier = 3;
						}
						wordScore += board[y / 40][i].getTile().getValue() * tileMultiplier;
						currWord += board[y / 40][i].getTile().getLetter();
	    			}
	    			score += wordScore * wordMultiplier;
	    			wordsPlayed.add(currWord);
	    		}	
				// String for all invalid words user plays in their turn (if any)
	    		String invalidWords = "";
	    		
	    		// bool for if all words are valid
	    		boolean validPlay = true;
	    		
	    		// loop through all words played to see if any are invalid
	    		for (String word : wordsPlayed) {
	    			if (!dict.isWord(word)) {
	    				invalidWords += (word + '\n');
	    				validPlay = false;
	    			}
	    		}
	    		if (validPlay) {
	    			// "bingo" bonus of 50 pts if all seven tiles are played in same turn
	    			int bingoBonus = 0;
	    			if (movedTiles.size() == 7) {
	    				bingoBonus = 50;
	    			}
	    			firstTurn = false;
					if (playerOneTurn) {
						playerOne.setScore(playerOne.getScore() + score + bingoBonus);
						fillRack(playerOne);
					}
					else {
						playerTwo.setScore(playerTwo.getScore() + score + bingoBonus);
						fillRack(playerTwo);
					}
	    			int y = yPositions.get(0);
	    			int x = xPositions.get(0);
	    			if (vertical) {
	    				for (int i = minY / 40; i <= maxY / 40; i++) {
	    					board[i][x / 40].setSpace(Space.N);
	    				}
	    			}
	    			else {
	    				for (int i = minX / 40; i <= maxX / 40; i++) {
	    					board[y / 40][i].setSpace(Space.N);
	    				}
	    			}
	    			if (getCurrPlayer().getRackSize() == 0) {
	    			    System.out.println("must be");
	    				endGame();
	    			}
					testNewTurn();
	    			return score + bingoBonus;

	    		}
	    		else {
	    			// dialog box alerting invalid words were played and what they are
	    			return -5;
	    		}
	    	}
	    }
	    // just like new turn just with no message dialog.
	    public void testNewTurn() {
	    	selectedTile = null;
	    	movedTiles.clear();
			playerOneTurn = !playerOneTurn;
			String name;
			if (playerOneTurn) {
				name = playerOne.getName();
				status.setText(name + "\'s Turn");
			} 
			else {
				name = playerTwo.getName();
				status.setText(name + "\'s Turn");
			}
			repaint();
	    }
	    
	    // for testing. Sets ups test racks for both players
	    public void testRacks() {
	    	playerOne.testRack();
	    	playerTwo.testRack();
	    }
	    
	}