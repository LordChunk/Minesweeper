package minesweeper;

public class Game {
	private MineField mineField;
	
	public Game(int fieldSize, int bombChance) {
		mineField = new MineField(fieldSize, bombChance);
	}
	
	public GameState play(char xCoord, int yCoord, boolean isMarking) {
		GameState gameState = GameState.playing;
		if(isMarking) {
			// Check if all bombs are marked
			mineField.markSquare(xCoord, yCoord);
		} else {
			// Check if square was bomb
			if(mineField.chooseSquare(xCoord, yCoord)) {
				gameState = GameState.gameOver;
			}
		}
		
		if(mineField.allBombsMarked()) {
			gameState = GameState.won;
		}
		
		mineField.print();
		return gameState;
	}
}
