import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Scrabble");
        frame.setLocation(300, 300);
        

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                double x = e.getX();
                double y = e.getY();
                board.click(x, y);
            }
        });

        // Reset button
        final JPanel control_panel = new JPanel();
        control_panel.setLayout(new GridLayout(6,1)); // This is the line that I have added.


        frame.add(control_panel, BorderLayout.EAST);
        
        final JLabel playerOneScore = new JLabel(board.getPlayerOne().getName() + ": " +
        		String.valueOf(board.getPlayerOne().getScore()));

        
        final JLabel playerTwoScore = new JLabel(board.getPlayerTwo().getName() + ": " +
        		String.valueOf(board.getPlayerTwo().getScore()));
        
        playerOneScore.setFont(new java.awt.Font("Georgia", Font.PLAIN, 22));
        playerOneScore.setOpaque(true);
        playerOneScore.setBackground(new Color(176, 224, 230));
        
        playerTwoScore.setFont(new java.awt.Font("Georgia", Font.PLAIN, 22));
        playerTwoScore.setOpaque(true);
        playerTwoScore.setBackground(new Color(220, 20, 60));
        
        control_panel.add(playerOneScore, BorderLayout.SOUTH);
        control_panel.add(playerTwoScore);
        
        
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			JOptionPane.showMessageDialog(null,
    					"This implementation of Scrabble is a two player word constructing affair."
    					+ " Players take turns player words with letters on their respective racks,"
    					+ "\n" + "until the bag of 100 tiles is empty and one of the two players "
    					+ "has" + " no tiles remaining on their rack. The player with the most "
    					+ "points wins." + "\n" + " Points are made by constructing words and "
    					+ "taking advantage of high " + "value tiles and bonus board spaces." +
    					" Here are the rules:" + "\n" + "-You may only have a maximum of seven"
    					+ " tiles on your rack at a time, and must draw to seven if there are"
    					+ " enough left in the bag" + "\n" + "-We are implementing a version of"
    					+ " void Scrabble, where only valid words can be played (the game will"
    					+ " stop you if you try to play a phony" + "\n" + "-Wild tiles, or those"
    					+ " with a question mark, can be any tile you want, but are worth 0 "
    					+ "points." + "\n" + "-Playing all seven tiles on your rack will result"
    					+ " in a bingo bonus of 50 additional points." + "\n" + "-You can only"
    					+ " play tiles on the same vertical OR horizontal line during your turn"
    					+ ", and all placed tiles must be adjacent to another tile already" + "\n"
    					+ "on"
    					+ " the board." + "\n" + "-The person who gets rid of all of their tiles"
    					+ " first, when the bag is empty, will end the game, with the bonus of"
    					+ " getting twice the total points" + "\n" 
    					+ "of all the tiles remaining on their"
    					+ " opponent's rack added to their score" + "\n" + "Again, the highest"
    					+ " score wins, and have fun!" + "\n" + "Full rules can be found here: "
    					+ "http://www.scrabblepages.com/scrabble/rules/");
            }
        });
        control_panel.add(instructions);
        
        
        
        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("New Game");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
        		playerOneScore.setText(board.getPlayerOne().getName() + ": " +
        		String.valueOf(board.getPlayerOne().getScore()));
        		playerTwoScore.setText(board.getPlayerTwo().getName() + ": " +
        		String.valueOf(board.getPlayerTwo().getScore()));
            }
        });
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);

        reset.setPreferredSize(new Dimension(60, 60));

        control_panel.add(reset);
        
        
        // Make move button
        final JButton makeMove = new JButton("Make Move");
        makeMove.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		board.makeMove();
        		playerOneScore.setText(board.getPlayerOne().getName() + ": " +
        		String.valueOf(board.getPlayerOne().getScore()));
        		playerTwoScore.setText(board.getPlayerTwo().getName() + ": " +
        		String.valueOf(board.getPlayerTwo().getScore()));
        	}
        });
        
        final JButton recallTiles = new JButton("Recall Tiles");
        recallTiles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		board.recallTiles();
        	}
        });
        
        
        control_panel.add(makeMove);

        control_panel.add(recallTiles);
        
        final JButton shuffle = new JButton("Shuffle Rack");
        shuffle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.shuffleRack();
            }
        });
        control_panel.add(shuffle);
        

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
//        board.endGame();
    }
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}