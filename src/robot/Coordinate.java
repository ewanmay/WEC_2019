package robot;

/**
 * Class Coordinate that helps organize code in other classes as all blocks have a coordinate
 *
 */
public class Coordinate {
	/**
	 * the 'x' coordinate of a block
	 */
	int x;
	/**
	 * the 'y' coordinate of a block
	 */
	int y;
	
	/**
	 * Constructor that allows creation of a coordinate system type object
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	
	Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for the x-coordinate
	 * @return the x-coordinate
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for the y-coordinate
	 * @return the y-coordinate
	 */
	
	public int getY() {
		return y;
	}
}
