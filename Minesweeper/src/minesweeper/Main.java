package minesweeper;

public class Main {
	public static final boolean CHEAT = true;	
	public static void main(String[] args) {
		MineSweeper mineSweeper = new MineSweeper();

		mineSweeper.start();
	}
	
	// https://stackoverflow.com/questions/10813154/how-do-i-convert-a-number-to-a-letter-in-java
	public static char getCharFromAlphabetIndex(int i) {
	    return i > 0 && i < 27 ? (char)(i + 64) : '?';
	}
	
	// https://stackoverflow.com/questions/2564541/how-to-map-character-to-numeric-position-in-java
	public static int getAlphabetIndexFromChar(char letter) {
		return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(letter) + 1;
	}
}
