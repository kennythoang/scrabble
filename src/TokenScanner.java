
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.io.Reader;

/**
 * Provides a token Iterator for a given Reader.
 */
public class TokenScanner implements Iterator<String> {
	private Reader r;
	private int nextC = -1;
    
    /**
     * Creates a TokenScanner for the argued Reader.
     * <p>
     * As an Iterator, the TokenScanner should only read from the Reader as much as is necessary to
     * determine hasNext() and next(). The TokenScanner should NOT read the entire stream and compute
     * all of the tokens in advance.
     * <p>
     *
     * @param in The source Reader for character data
     * @throws IOException If there is an error in reading
     * @throws IllegalArgumentException If the argued Reader is null
     */
    public TokenScanner(java.io.Reader in) throws IOException {
        this.r = in;    
        nextC = r.read();
    }

    /**
     * Determines whether the argued character is a valid word character.
     * <p>
     * Valid word characters are letters (according to Character.isLetter).
     *
     * @param c The character to check
     * @return True if the character is a word character
     */
    public static boolean isWordCharacter(int c) {
        return Character.isLetter(c);
    }

    /**
     * Determines whether the argued String is a valid word
     * <p>
     * A valid word is any sequence of only letter (see Character.isLetter) and/or apostrophe
     * characters. Strings that are null or empty are not valid words.
     *
     * @param s The string to check
     * @return True if the String is a word
     */
    public static boolean isWord(String s) {
        if (s == null || s.equals("")) {
        	return false;
        }
        for (char c : s.toCharArray()) {
        	if (!isWordCharacter(c)) {
        		return false;
        	}
        }
        return true;
    }

    /**
     * Determines whether there is another token available.
     *
     * @return True if there is another token available
     */
    public boolean hasNext() {
        return (nextC != -1);
    }

    /**
     * Returns the next token, or throws a NoSuchElementException if none remain.
     *
     * @return The next token if one exists
     * @throws NoSuchElementException When the end of stream is reached
     */
    public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		String answer = "";
		answer = answer + (char)nextC;
		try {
			int c = r.read();
			while (isWordCharacter(c) == isWordCharacter(nextC) && c != -1) {
				answer = answer + (char)c;
				c = r.read();
			}
			nextC = c;
		} catch (IOException e) {
			throw new NoSuchElementException();
		}
		return answer;
	}

    /**
     * We don't support this functionality with TokenScanner, but since the method is required when
     * implementing Iterator, we just <code>throw new UnsupportedOperationException();</code>
     *
     * @throws UnsupportedOperationException Since we do not support this functionality
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
