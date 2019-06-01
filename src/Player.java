import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
	private String name;
	private int score;
	private ArrayList<Tile> rack;
	
	Color tileColor = new Color(252, 231, 126);
	private Color color = tileColor;
	
	public Player(String name) {
		this.name = name;
		score = 0;
		rack = new ArrayList<Tile>();
	}
	
	public void addTile(Tile t) {
		t.setLocation(120 + t.getSize() * getRackSize(), 660);
		rack.add(t);
	}
	
	public void removeTile(Tile t) {
		rack.remove(t);
	}
	
	public boolean containsLetter(String letter) {
		for (int i = 0; i < rack.size(); i++) {
			if (rack.get(i).getLetter().equals(letter)) {
				return true;
			}
		}
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getName() {
		return name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getRackSize() {
		return rack.size();
	}
	
	public ArrayList<Tile> getRack() {
		return rack;
	}
	
	// for testing. Sets player rack to AEINRST
	public void testRack() {
		String[] array = new String[]{"A", "E", "I", "N", "R", "S", "T"};
		for (int i = 0; i < 7; i++) {
			rack.get(i).setLetter(array[i]);
			rack.get(i).setValue(1);
		}
	}
	
	public void shuffleRack() {
		Collections.shuffle(rack);
	}
	
    public void drawRack(Graphics g) {
        g.setColor(color);
        for (int i = 0; i < rack.size(); i++) {
        	Tile tile = rack.get(i);
        	tile.setLocation(120 + tile.getSize() * i, 660);
            tile.draw(g);
        }
    }
}