package maze;

/**
 * The {@link Maze} interface provides the minimal functionality to describe a two dimensional maze
 * containing a Start, an Exit, and Walls.
 * @author jcollard
 *
 */
public interface Maze {
	
	/**
	 * Returns the starting {@link Location} for this {@link Maze} 
	 * @return the starting {@link Location} for this {@link Maze}
	 */
	public Location getStart();
	
	/**
	 * Returns the exit {@link Location} for this {@link MazE}
	 * @return the exit {@link Location} for this {@link MazE}
	 */
	public Location getExit();
	
	/**
	 * Given a {@link Location} returns {@code true} if the {@link Location} is a wall and
	 * {@code false} otherwise. 
	 * @param loc the {@link Location} to check
	 * @return {@code true} if {@code loc} is a wall and {@code false} otherwise. 
	 */
	public boolean isWall(Location loc);

}
