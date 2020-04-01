package minesweeper;

public class Square {
	private boolean hasBomb;
	private boolean marked;
	private char displayChar;
	
	Square(boolean hasBomb) {
		this.hasBomb = hasBomb;
		marked = false;
		displayChar = '.';	
	}
	
	@Override
	public String toString() {
		return ""+ displayChar;	
	}

	public boolean isBomb() {
		return hasBomb;
	}

	public void setBomb(boolean hasBomb) {
		this.hasBomb = hasBomb;
	}

	public boolean isMarked() {
		return marked;
	}

	public void mark(boolean isMarked) {
		this.marked = isMarked;
		
		if(isMarked) {
			displayChar = '*';
		} else {
			displayChar = '.';
		}
	}

	public char getDisplayChar() {
		return displayChar;
	}

	public void setDisplayChar(char displayChar) {
		this.displayChar = displayChar;
		marked = false;
	}
}
