import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class BoardSpace {
	private Space space;
	private Tile tile;
	
	final private int px;
	final private int py;
	final private int size = 40;
	final private int fontSize = 12;
	
	public BoardSpace(int px, int py) {
		space = Space.N;
		tile = null;
		this.px = px;
		this.py = py;
	}
	
	public int getX() {
		return px;
	}
	
	public int getY() {
		return py;
	}
	
	public void setTile(Tile t) {
		t.setLocation(px, py);
		t.setSize(size);
		tile = t;
	}
	
	public void clearTile() {
		tile = null;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public void setSpace(Space s) {
		space = s;
	}
	
	public Space getSpace() {
		return space;
	}
	
	public void clearBoardSpace() {
		space = Space.N;
		tile = null;
	}
	
    public void draw(Graphics g) {
        if (getTile() != null) {
        	getTile().draw(g);
        }
        else {
        	Space s = getSpace();
            switch (s) 
            { 
            case DW: 
                g.setColor(new Color(255, 153, 153));
                g.fillRect(getX(), getY(), size, size);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Century Gothic Bold", Font.BOLD, fontSize)); 
                g.drawRect(getX(), getY(), size, size);
                g.drawString("DW", getX() + size / 2 - 9, getY() + size / 2 + 6);
              break; 
            case DL: 
                g.setColor(new Color(153, 204, 255));
                g.fillRect(getX(), getY(), size, size);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Century Gothic Bold", Font.BOLD, fontSize)); 
                g.drawRect(getX(), getY(), size, size);
                g.drawString("DL", getX() + size / 2 - 9, getY() + size / 2 + 6);
               break; 
            case TW: 
                g.setColor(new Color(255, 51, 51));
                g.fillRect(getX(), getY(), size, size);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Century Gothic Bold", Font.BOLD, fontSize)); 
                g.drawRect(getX(), getY(), size, size);
                g.drawString("TW", getX() + size / 2 - 9, getY() + size / 2 + 6);
                break;
            case TL: 
                g.setColor(new Color(0, 130, 255));
                g.fillRect(getX(), getY(), size, size);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Century Gothic Bold", Font.BOLD, fontSize)); 
                g.drawRect(getX(), getY(), size, size);
                g.drawString("TL", getX() + size / 2 - 9, getY() + size / 2 + 6);
               break;
            case N:
                g.setColor(new Color(255, 250, 205));
                g.fillRect(getX(), getY(), size, size);
                g.setColor(Color.BLACK);
                g.drawRect(getX(), getY(), size, size);
            	break;
            default: 
                g.setColor(new Color(255, 250, 205));
                g.fillRect(getX(), getY(), size, size);
                g.setColor(Color.BLACK);
                g.drawRect(getX(), getY(), size, size);                
                break; 
            }         }
    }
}