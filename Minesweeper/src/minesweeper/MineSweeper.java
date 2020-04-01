package minesweeper;

public class MineSweeper {
	private ConsoleIO consoleIO = new ConsoleIO();
	private Game game;

	private final static int minFieldSize = 5;
	private final static int maxFieldSize = 20;

	private final static int minBombChance = 5;
	private final static int maxBombChance = 25;

	public void start() {
		boolean quit = false;
		while (!quit) {
			int fieldSize;
			int bombChance;
			
			System.out.println("Welcome to Minesweeper");

			// Request fieldSize from user
			System.out.println("Please fill in the size of your playfield. (" + minFieldSize + "-" + maxFieldSize + ")");
			int proposedFieldSize = consoleIO.readInputInt();
			while (proposedFieldSize < minFieldSize || proposedFieldSize > maxFieldSize) {
				System.out.println("Please enter a valid value");
				proposedFieldSize = consoleIO.readInputInt();
			}
			fieldSize = proposedFieldSize;

			// Request bombChance from user
			System.out.println("Now please fill in the chance that a field may contain a bomb. (" + minBombChance + "-"+ maxBombChance + ")");
			int proposedBombChance = consoleIO.readInputInt();
			while (proposedBombChance < minBombChance || proposedBombChance > maxBombChance) {
				System.out.println("Please enter a valid value.");
				proposedBombChance = consoleIO.readInputInt();
			}
			bombChance = proposedBombChance;

			// Initialise game
			game = new Game(fieldSize, bombChance);
			boolean gameOver = false;
			while (!gameOver) {
				System.out.println("Give the location you want to test or mark (*):");
				boolean invalidInput = true;
				char xCoord = '?';
				int yCoord = -1;
				boolean isMarking = false;
				while(invalidInput) {
					String input = consoleIO.readInput();
					
					// Input validation
					if(input.charAt(0) == '*') {
						input = input.substring(1);
						isMarking = true;
					}  else {
						isMarking = false;
					}
					
					// https://stackoverflow.com/questions/16787099/how-to-split-the-string-into-string-and-integer-in-java#16787236
					String[] splittedInput = input.split("(?<=\\D)(?=\\d)");
					// Check if input has a letter and a number
					if(splittedInput.length == 2) {			
						
						// Validate x coordinate
						xCoord =  Character.toUpperCase(splittedInput[0].charAt(0));						
						char lastValidChar = Main.getCharFromAlphabetIndex(fieldSize);
						if(Character.isLetter(xCoord) && xCoord <= lastValidChar && splittedInput[0].length() == 1) {
							
							// Validate y coordinate
							String yCoordString = splittedInput[1];
							yCoord = -1;
							try {								
								yCoord = Integer.parseInt(yCoordString);
							} catch (Exception e) {}
							
							if (yCoord > 0 && yCoord <= fieldSize) {
								invalidInput = false;
							}
						}
					}
					
					if(invalidInput) {
						System.out.println("Invalid input");
					}
				}
				GameState gameState = GameState.playing;
				// A check to see if one of the default values may not have been changed
				if(yCoord != -1 && xCoord != '?') {					
					gameState = game.play(xCoord, yCoord, isMarking);
				} else {
					System.out.println("ERROR: One of the default inputvalues was not changed but did pass input sanitation.");
				}
				
				switch (gameState) {
				case gameOver:
					gameOver = true;
					System.out.println("BOOM! You selected a bomb. Game over.");
					break;
				case won:
					gameOver = true;
					System.out.println("Congratulations, you marked all the bombs.");
				default:
					break;
				}
			}		
			
			System.out.println("The game is over, but this does not have to be the end. Do you want to play another game? (Yes/No)");
			boolean invalidInput = true;
			while (invalidInput) {
				String input = consoleIO.readInput().toLowerCase();
				if(input.equals("no")) {
					quit = true;
					invalidInput = false;
					System.out.println("Goodbye :(");					
				} else if(input.equals("yes")) {
					invalidInput = false;
					System.out.println("Okay, restarting game.");
				} else {
					System.out.println("Invalid input.");
				}
			}
		}
		
	}
}
