import java.util.*;

public class Scrabble {
	private String[][] board;
	private Map<String, Integer> tileBag;
	private Map<String, Integer> tileValues;
	
	public Scrabble() {
		board = new String[15][15];
		tileBag = new TreeMap<String, Integer>();
		tileBag.put("A", 9);
		tileBag.put("B", 2);
		tileBag.put("C", 2);
		tileBag.put("D", 4);
		tileBag.put("E", 12);
		tileBag.put("F", 2);
		tileBag.put("G", 3);
		tileBag.put("H", 2);
		tileBag.put("I", 9);
		tileBag.put("J", 1);
		tileBag.put("K", 1);
		tileBag.put("L", 4);
		tileBag.put("M", 2);
		tileBag.put("N", 6);
		tileBag.put("O", 8);
		tileBag.put("P", 2);
		tileBag.put("Q", 1);
		tileBag.put("R", 6);
		tileBag.put("S", 4);
		tileBag.put("T", 6);
		tileBag.put("U", 4);
		tileBag.put("V", 2);
		tileBag.put("W", 2);
		tileBag.put("X", 1);
		tileBag.put("Y", 2);
		tileBag.put("Z", 1);
		
		tileValues = new TreeMap<String, Integer>();
		tileValues.put("A", 1);
		tileValues.put("B", 3);
		tileValues.put("C", 3);
		tileValues.put("D", 2);
		tileValues.put("E", 1);
		tileValues.put("F", 4);
		tileValues.put("G", 2);
		tileValues.put("H", 4);
		tileValues.put("I", 1);
		tileValues.put("J", 8);
		tileValues.put("K", 5);
		tileValues.put("L", 1);
		tileValues.put("M", 3);
		tileValues.put("N", 1);
		tileValues.put("O", 1);
		tileValues.put("P", 4);
		tileValues.put("Q", 10);
		tileValues.put("R", 1);
		tileValues.put("S", 1);
		tileValues.put("T", 1);
		tileValues.put("U", 1);
		tileValues.put("V", 4);
		tileValues.put("W", 4);
		tileValues.put("X", 8);
		tileValues.put("Y", 4);
		tileValues.put("Z", 10);	
	}
}