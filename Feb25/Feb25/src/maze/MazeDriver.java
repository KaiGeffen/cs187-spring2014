package maze;

// I cheated! 
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MazeDriver {

	/**
	 * Returns a {@link List} of {@link Location}s in an order that will lead
	 * from the starting {@link Location} of {@code m} to the exit
	 * {@link Location} of {@code m}. If {@code m} cannot be solved, the
	 * returned list will be empty.
	 * 
	 * @param m
	 *            the maze to solve
	 * @return {@link List} of {@link Location}s in an order that will lead from
	 *         the starting {@link Location} of {@code m} to the exit
	 *         {@link Location} of {@code m}.
	 */
	public static List<Location> canSolveMaze(Maze m) {
		Location start = m.getStart();
		// I cheated here!
		List<Location> visited = new LinkedList<Location>();
		List<Location> moves = new LinkedList<Location>();
		canSolveMaze(start, m, visited, moves);
		Collections.reverse(moves);
		return moves;
	}

	/**
	 * Given a {@link Location}, returns {@code true} if the maze can be solved
	 * by visiting this {@link Location} and {@code false} otherwise.
	 * 
	 * @param l
	 *            the {@link Location} to visit
	 * @param m
	 *            the {@link Maze} to solve
	 * @param visited
	 *            a {@link List} of {@link Location}s that have been previously
	 *            tried.
	 * @param moves
	 *            a {@link List} of moves representing a solution to the maze
	 * @return {@code true} if the maze can be solved by visiting this
	 *         {@link Location} and {@code false} otherwise.
	 */
	public static boolean canSolveMaze(Location l, Maze m,
			List<Location> visited, List<Location> moves) {
		if (visited.contains(l))
			return false;
		visited.add(l);
		if (m.isWall(l))
			return false;
		if (m.getExit().equals(l)) {
			moves.add(l);
			return true;
		}

		Location north = new Location(l.getRow() - 1, l.getCol());
		Location east = new Location(l.getRow(), l.getCol() + 1);
		Location west = new Location(l.getRow(), l.getCol() - 1);
		Location south = new Location(l.getRow() + 1, l.getCol());

		if (canSolveMaze(north, m, visited, moves)
				|| canSolveMaze(east, m, visited, moves)
				|| canSolveMaze(south, m, visited, moves)
				|| canSolveMaze(west, m, visited, moves)) {
			moves.add(l);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {

		String[] maze = new String[10];

		// Joe's sub par maze
		// maze[0] = "##########";
		// maze[1] = "#S       #";
		// maze[2] = "### #### #";
		// maze[3] = "#        #";
		// maze[4] = "# # ######";
		// maze[5] = "# #      #";
		// maze[6] = "#  ### # #";
		// maze[7] = "###### # #";
		// maze[8] = "#E     # #";
		// maze[9] = "##########";
		//

		// Kyle's sweet maze
		maze[0] = "##########";
		maze[1] = "#S###   ##";
		maze[2] = "# #   #  #";
		maze[3] = "# ##### ##";
		maze[4] = "#       ##";
		maze[5] = "###### #E#";
		maze[6] = "#      # #";
		maze[7] = "# #### # #";
		maze[8] = "#    #   #";
		maze[9] = "##########";

		// Builds kyle's maze
		Maze kylesMaze = new MazeImplementation(maze);
		// Prints out Kyle's maze
		System.out.println(kylesMaze);

		// Prints out the solution to the maze.
		System.out.println(canSolveMaze(kylesMaze));
	}

}
