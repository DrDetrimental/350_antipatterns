package maze;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class Frame {

	private JFrame frame;
	private static int mazeHeight;
	private static int mazeWidth;
	private static int[][] maze;
	private static JLabel[][] spaces;
	private static boolean loop = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		readMazeFile();
		
		getMazeInformation();
		
		Player player = new Player();
		
		createWindow();
		
		drawMaze();
		
		// Show finish space as "F"
		spaces[Maze.getEndCoords()[0]][Maze.getEndCoords()[1]].setText("F");
		
		// Game loop
		while (loop) {
			
			// Handle player movement
			for (int i = 0; i < player.checkSpaces().length; i++) {
				if (player.checkSpaces()[i] != 'x') {
					// Close current player spot
					maze[player.getPosition()[0]][player.getPosition()[1]] = 0;
					spaces[player.getPosition()[0]][player.getPosition()[1]].setText("x");
					
					player.handleMovement();
					break;
					
				} else {
					System.out.println("Player win!");
					loop = false;
				}
			}
			// Show new player position
			spaces[player.getPosition()[0]][player.getPosition()[1]].setText("O");
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void drawMaze() {
		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {
				JLabel lblNewLabel;
				if (maze[y][x] == 0) {
					lblNewLabel = new JLabel("X");
				} else {
					lblNewLabel = new JLabel("");
				}
				spaces[y][x] = lblNewLabel;
			}
		}
	}

	private static void createWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void readMazeFile() {
		try {
			Maze.readFile("maze.txt");
		} catch (Exception e) {
			System.out.println("Something went wrong reading the file, is the filename right?");
			e.printStackTrace();
		}
	}

	private static void getMazeInformation() {
		mazeHeight = Maze.getHeight();
		mazeWidth = Maze.getWidth();
		maze = Maze.getMaze();
		spaces = new JLabel[mazeHeight][mazeWidth];
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(10, 10, 0, 0)); // the maze height/width variables throw exceptions when going to the design tab; change them to arbitrary numbers to solve it
		
		System.out.println("Initializing window");
		
		// Creates the spaces[][] array, a frontend "mirror" of the backend maze[][] array. The text/titles of the JLabels in this array can be changed to update what is shown to the user.
		for (int y = 0; y < mazeHeight; y++) {
			for (int x = 0; x < mazeWidth; x++) {
				frame.getContentPane().add(spaces[y][x]);
			}
		}
	}
}
