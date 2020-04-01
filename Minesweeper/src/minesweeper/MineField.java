package minesweeper;

import java.util.HashMap;
import java.util.Random;

public class MineField {
	private HashMap<String, Square> mineField = new HashMap<String, Square>();
	
	private int fieldSize;
	private int bombChance;
	
	private static int bombScanRadius = 1;
	
	public MineField(int fieldSize, int bombChance) {
		this.fieldSize = fieldSize;
		this.bombChance = bombChance;
		
		generateSquares();
		print();
	}
	
	private void generateSquares() {
		Random rand = new Random();
		boolean mapContainsBomb = false;
		while (!mapContainsBomb) {			
			for (int yCoord = 1; yCoord <= fieldSize; yCoord++) {
				char xCoord = 'A';
				for (int j = 0;  j < fieldSize;  j++) {
					if(rand.nextInt(100) < bombChance) {
						mineField.put(""+ xCoord + yCoord, new Square(true));
						mapContainsBomb = true;
					} else {
						mineField.put(""+ xCoord + yCoord, new Square(false));
					}
					xCoord++;
				}
			}
		}
	}
	
	public void print() {
		privatePrint(false);
		if(Main.CHEAT) {
			privatePrint(true);
		}
	}
	
	private void privatePrint(boolean cheatPrint) {
		System.out.println();
		if (cheatPrint) {
			System.out.println("Cheat mode print");
		}
		// Loop per y row over each character
		for (int yCoord = fieldSize; yCoord > 0; yCoord--) {
			char xCoord = 'A';
			
			String output = "";
			// align numbers correctly and add space at the end
			if (yCoord >= 10) {
				output += yCoord;
			} else {
				output += " "+ yCoord;
			}
			output += " ";
			
			for (int j = 0;  j < fieldSize;  j++) {
				Square square = mineField.get(""+ xCoord + yCoord);
				if(cheatPrint) {
					if(square.isBomb()) {						
						output += '*';
					} else {
						output += '~';
					}
				} else {					
					output += square;
				}
				xCoord++;
			}			
			System.out.println(output);
		}
		char xCoord = 'A';
		String output = "   ";
		for (int i = 0; i < fieldSize; i++) {
			output += xCoord;
			xCoord++;
		}
		System.out.println(output);
	}
	
	public boolean chooseSquare(char xCoord, int yCoord) {
		Square chosenSquare = mineField.get(""+ xCoord + yCoord);
		boolean isBomb = chosenSquare.isBomb();
		
		// Generate number for surrounding bombs
		if(!isBomb) {
			int bombCount = 0;
			int xCoordInt = Main.getAlphabetIndexFromChar(xCoord);
			
			// Scan all surrounding squares for bombs
			for (int scannedXCoordInt = xCoordInt - bombScanRadius; scannedXCoordInt <= xCoordInt + bombScanRadius; scannedXCoordInt++) {
				for (int scannedYCoord = yCoord - bombScanRadius; scannedYCoord <= yCoord + bombScanRadius; scannedYCoord++) {
					Square square = mineField.get(""+ Main.getCharFromAlphabetIndex(scannedXCoordInt) + scannedYCoord);
					if(square != null && square.isBomb()) {
						bombCount++;
					}
				}
			}
			// https://stackoverflow.com/a/30654145
			chosenSquare.setDisplayChar((char) (bombCount + '0'));
		}
		return isBomb;
	}
	
	public void markSquare(char xCoord, int yCoord) {
		Square square = mineField.get(""+ xCoord + yCoord);
		square.mark(!square.isMarked());
	}
	
	// Checks if all bombs are marked and if no additional fields are marked
	public boolean allBombsMarked() {
		boolean allBombsAreMarked = true;
		// https://www.javacodeexamples.com/java-hashmap-foreach-for-loop-example/2329
		for (String key : mineField.keySet()) {
			Square square = mineField.get(key);
			
			if(square.isBomb() != square.isMarked()) {
				allBombsAreMarked = false;
				break;
			}
		}	
		return allBombsAreMarked;
	}
}
