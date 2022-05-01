package maze;
import java.util.Stack;
/**
 * 
 * @author Jordan
 * Class for our "player" character.
 * Contains player position, movement methods and a method to check surrounding spaces.
 * 
 */
public class Player {
	
	// All our variables. Coordinate arrays are all in (y, x) format
	private  int[] playerCoords = {0, 0};
	private  int numOptions = 0;
	private  char[] openDirections = new char[4];
	private  Stack<Integer> S1 = new Stack<Integer>();
	private	 Stack<Integer> S2 = new Stack<Integer>();
	
	// Player constructor, defaults player coordinates to starting position of the maze
	public Player() {
		for (int i = 0; i < 2; i++) {
			playerCoords[i] = Maze.getStartCoords()[i];
		}
	}
	
	// Movement methods. Pretty self-explanatory
	public void moveNorth() {
		playerCoords[0] = playerCoords[0] - 1;
	}
	
	public void moveEast() {
		playerCoords[1] = playerCoords[1] + 1;
	}
	
	public void moveSouth() {
		playerCoords[0] = playerCoords[0] + 1;
	}
	
	public void moveWest() {
		playerCoords[1] = playerCoords[1] - 1;
	}
	
	// Method to check for the win condition and surrounding spaces' status. Returns a char array with all open directions and also increments numOptions
	public char[] checkSpaces() {
		
		for (int i = 0; i < openDirections.length; i++) {
			openDirections[i] = 'o';
		}
		
		numOptions = 0;
		
		// Check if player is on end space; if so return -1 for number of options
		if (playerCoords[0] == Maze.getEndCoords()[0] && playerCoords[1] == Maze.getEndCoords()[1]) {
			openDirections[0] = 'x';
		} else {
			// Check north
			if (Maze.getSpaceStatus(playerCoords[0] - 1, playerCoords [1]) == 1) {
				numOptions++;
				openDirections[0] = 'n';
			}
			// Check east
			if (Maze.getSpaceStatus(playerCoords[0], playerCoords [1] + 1) == 1) {
				numOptions++;
				openDirections[1] = 'e';
			}
			// Check south
			if (Maze.getSpaceStatus(playerCoords[0] + 1, playerCoords [1]) == 1) {
				numOptions++;
				openDirections[2] = 's';
			}
			// Check west
			if (Maze.getSpaceStatus(playerCoords[0], playerCoords [1] - 1) == 1) {
				numOptions++;
				openDirections[3] = 'w';
			}
			if (numOptions > 1 ) {
				System.out.println("Storing fork to stack");
				Stack(playerCoords[0], playerCoords [1]);
			}
			
			if(numOptions == 0) {
				setPosition(S1.pop(), S2.pop());
				
			}
			
		}
		return openDirections;
	}
	
	// Getters and setters
	public int getNumberOfOptions() {
		return numOptions;
	}
	
	public int[] getPosition() {
		return playerCoords;
	}
	
	public void setPosition(int y, int x) {
		playerCoords[0] = y;
		playerCoords[1] = x;
	}
	
	public void Stack(int p1, int p2) {
		S1.push(p1);
		S2.push(p2);
	}

	public void handleMovement() {
				
		if (checkSpaces()[0] == 'n') {
			System.out.println("Moving north");
			moveNorth();
		} else if (checkSpaces()[1] == 'e') {
			System.out.println("Moving east");
			moveEast();
		} else if (checkSpaces()[2] == 's') {
			System.out.println("Moving south");
			moveSouth();
		} else if (checkSpaces()[3] == 'w') {
			System.out.println("Moving west");
			moveWest();
		}
	}
}
