import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Tile {
	private int px;
	private int py;
	private String letter;
	private int value;
	private int size = 50;
	private boolean selected = false;
	
	Color tileColor = new Color(252, 231, 126);
	private Color color = tileColor;
	
	public Tile(String letter, int value, int px, int py) {
		this.letter = letter;
		this.value = value;
		this.px = px;
		this.py = py;
	}
	
	public void setLocation(int px, int py) {
		this.px = px;
		this.py = py;
	}
	
	public int getX() {
		return px;
	}
	
	public int getY() {
		return py;
	}
	
	public void setLetter(String letter) {
		this.letter = letter;
	}
	
	public String getLetter() {
		return letter;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void select() {
		selected = true;
	}
	
	public void deselect() {
		selected = false;
	}
	
	public void toggleSelect() {
		selected = !selected;
	}
	
    public void draw(Graphics g) {
    	if (selected) {
    		g.setColor(new Color(255, 255, 224));
    	}
    	else {
    		g.setColor(color);
    	}
        g.fillRect(getX(), getY(), size, size);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Century Gothic Bold", Font.BOLD, 23)); 
        g.drawRect(getX(), getY(), size, size);
        g.drawString(getLetter(), getX() + size / 2 - 8, getY() + size / 2 + 7);
        g.setFont(new Font("Arial", Font.BOLD, 12)); 
        // how much to shift number value of tile
        int valFactor = 0;
        if (getValue() == 10) {
        	valFactor = 16;
        }
        else {
        	valFactor = 10;
        }
        g.drawString(String.valueOf(getValue()), getX() + size - valFactor, getY() + size - 3);
    }
    
}