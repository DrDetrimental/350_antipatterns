package maze;

import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author Jordan
 * Class for getting data for a "maze" from a file.
 * Relies on the source file being in the following format:
 * 
 * mazeHeight mazeWidth
 * startYCoordinate startXCoordinate (coordinates for where the player starts)
 * endYCoordinate endXCoordinate (coordinates for where the player must go to win)
 * Data for the maze spaces; should match the maze height and width readings. "0" = blocked, "1" = open.
 * 
 * See "maze.txt" for an example of what the file should look like.
 * 
 * NOTE: All x, y position data is formatted (y, x) to avoid confusion when moving to 2D array syntax which is "array[y][x]".
 * 
 */
public class Maze {
	
	private static int width;
	private static int height;
	private static int[] startCoords = {0, 0};
	private static int[] endCoords = {0, 0};
	private static int[][] maze;
	private static int y = 0;
	private static int x = 0;
	
	public static void readFile(String fileName) throws Exception {
		
		Scanner sc = new Scanner(new File (fileName));
		
		System.out.println("Getting data for the maze.");
		
		// Getting maze size data from the file and then instantiating the maze array from that size data
		height = sc.nextInt();
		width = sc.nextInt();
		maze = new int[height][width];
		System.out.println("Maze height is " + height + ", maze width is " + width);
		
		setStartAndEnd(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
		System.out.println("Starting coordinates are [" + startCoords[1] + ", " + startCoords[0] + "], end coordinates are [" + endCoords[1] + ", " + endCoords[0] + "].");
		
		// Read the data for the actual maze and set into our 2D maze array
		while(sc.hasNextInt()) {
			
			maze[y][x] = sc.nextInt();
			System.out.print(maze[y][x] + " ");
			x++;
			
			if (x == width) {
				System.out.println("");
				x = 0;
				y++;
			}
		}
	}

	private static void setStartAndEnd(int startY, int startX, int endY, int endX) {
		startCoords[0] = startY;
		startCoords[1] = startX;
		endCoords[0] = endY;
		endCoords[1] = endX;
	}
	
	// Getters and setters
	public static int[][] getMaze() {
		return maze;
	}
	
	public static int[] getStartCoords() {
		return startCoords;
	}
	
	public static int[] getEndCoords() {
		return endCoords;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static int getSpaceStatus(int y, int x) {
		return maze[y][x];
	}
}
