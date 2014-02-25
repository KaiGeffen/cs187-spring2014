package maze;


/**
 * A {@link MazeImplementation} is a concrete implementation of the {@link Maze} interface backed
 * by a 2D boolean array representing the walls in the maze.
 * @author jcollard
 *
 */
public class MazeImplementation implements Maze {

	private boolean[][] passables;
	private Location start, exit;

	/**
	 * <p>
	 * Given an array of {@link String}s, construct a maze such that:
	 * </p>
	 * <pre>
	 * 	# - represents a wall
	 * 	S - represents the Start
	 * 	E - represents the Exit
	 * 	  - represents a passable area (this is a space not a typo)
	 * </pre>
	 * <p>
	 * The maze constructed may have behavior if the following properties do not hold:
	 * <pre>
	 * 	- maze must be an array of size > 0.
	 * 	- The length of each String in maze must be > 0 and equal.
	 *  - the maze must have walls encapsulating it
	 *  - Each String can only contain the values #, S, E, and ' '. <- that is a space
	 *  - The maze must contain exactly 1 S
	 *  - The maze must contain exactly 1 E
	 * </pre> 
	 * Can anyone think of additional constraints? What else might cause problems?
	 * </p>
	 * @param maze
	 */
	public MazeImplementation(String[] maze) {
		int rows = maze.length;
		int cols = maze[0].length();
		passables = new boolean[rows][cols];
		initPassables(0, 0, maze);
	}

	// Precondition: all rows less than row have been initialized
	// all cols in the row before col have been initialized
	//
	/**
	 * Initializes the row and col of the maze and all values after it
	 */
	private void initPassables(int row, int col, String[] maze) {
		if (col == maze[0].length()) {
			initPassables(row + 1, 0, maze);
			return;
		} else if (row == maze.length) {
			return;
		}

		char room = maze[row].charAt(col);
		initRoom(room, row, col);

		initPassables(row, col + 1, maze);
	}

	/**
	 * Based on the room character, initializes the row and col of the maze.
	 */
	private void initRoom(char room, int row, int col) {
		switch (room) {
		case '#':
			setWall(row, col);
			break;
		case ' ':
			setPath(row, col);
			break;
		case 'S':
			setStart(row, col);
			break;
		case 'E':
			setExit(row, col);
			break;
		}
	}

	/**
	 * Sets the exit of the maze to be this row / col. This space
	 * is also passable.
	 */
	private void setExit(int row, int col) {
		passables[row][col] = true;
		exit = new Location(row, col);
	}

	/**
	 * Sets the start of the maze to be this row / col. This space
	 * is also passable.
	 */
	private void setStart(int row, int col) {
		passables[row][col] = true;
		start = new Location(row, col);
	}

	/**
	 * Sets the row / col of the maze to be passable.
	 */
	private void setPath(int row, int col) {
		passables[row][col] = true;
	}

	/**
	 * Sets the row / col of the maze to be impassable. 
	 */
	private void setWall(int row, int col) {
		passables[row][col] = false;
	}

	@Override
	public Location getStart() {
		return start;
	}

	/* (non-Javadoc)
	 * @see maze.Maze#getExit()
	 */
	@Override
	public Location getExit() {
		return exit;
	}

	/* (non-Javadoc)
	 * @see maze.Maze#isWall(maze.Location)
	 */
	@Override
	public boolean isWall(Location loc) {
		return !passables[loc.getRow()][loc.getCol()];
	}

	/**
	 * Returns a pretty String representation of our maze
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		buildMyMazeString(0, 0, builder);
		String mazeString = builder.toString();
		return mazeString;
	}

	/**
	 * Using the supplied builder, adds the row and col specified to the builder
	 * plus all rows below this row and all columns after this col in this row.
	 */
	private void buildMyMazeString(int row, int col, StringBuilder builder) {
		if (col == passables[0].length) {
			builder.append('\n');
			buildMyMazeString(row+1, 0, builder);
			return;
		} else if (row == passables.length) {
			return;
		}
		
		buildMyRoom(row, col, builder);
		
		buildMyMazeString(row, col+1, builder);
	}

	/**
	 * Using the specified builder, places a character at the row / col that
	 * represents the location
	 */
	private void buildMyRoom(int row, int col, StringBuilder builder) {
		if(!tryToBuildStart(row, col, builder) && !tryToBuildExit(row, col, builder))
			buildPath(row, col, builder);
	}

	/**
	 * If the location at row / col is passable place a ' ' character else 
	 * place a '#' character
	 */
	private void buildPath(int row, int col, StringBuilder builder) {
		char room = passables[row][col] ? ' ' : '#';
		builder.append(room);
	}

	/**
	 * If the row / col is an exit, place an 'E' and return true. Otherwise return false. 
	 */
	private boolean tryToBuildExit(int row, int col, StringBuilder builder) {
		if(exit.equals(new Location(row, col))){
			builder.append('E');
			return true;
		}
		return false;
		
	}

	/**
	 * If the row / col is the start, place an 'S' and return true. Otherwise return false.
	 */
	private boolean tryToBuildStart(int row, int col, StringBuilder builder) {
		if(start.equals(new Location(row, col))){
			builder.append('S');
			return true;
		}
		return false;
	}
	
	

}
