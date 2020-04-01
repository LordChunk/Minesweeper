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
			if(mineField.allBombsMarked()) {
				gameState = GameState.won;
			}
		} else {
			// Check if square was bomb
			if(mineField.chooseSquare(xCoord, yCoord)) {
				gameState = GameState.gameOver;
			}
		}
		
		mineField.print();
		return gameState;
	}
}
