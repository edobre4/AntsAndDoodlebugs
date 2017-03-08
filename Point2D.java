
// Point 2D class
// provides a contructor that sets the x and y coordinates (and a default constructor that sets x and y to 0)
// provides getters and setters for x and y
public class Point2D {
	// member variables
	private int x;
	private int y;
	
	// default constructor
	public Point2D() {
		x = 0;
		y = 0;
	}
	

	// 2 arg constructor that set x and y to the parameters passed
	public Point2D(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	// getter for x
	public int x_coord() {
		return x;
	}
	
	// setter for x
	public void x_coord(int n) {
		x = n;
	}
	
	// getter for y
	public int y_coord() {
		return y;
	}
	
	// setter for y
	public void y_coord(int n) {
		y = n;
	}
}
