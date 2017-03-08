
// 2D grid of object classes
// provides a constructor that sets the number of columns and rows of the grid
// proves functions to get and set an object at index x, y
public class Grid {
	Object[][] grid;
	int rows, columns;
	
	// constructor, allocates the grid using the specified size
	public Grid(int x, int y) {
		grid = new Object[x][y];
		rows = x;
		columns = y;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				grid[i][j] = new EmptySquare();
			}
		}
	}
	
	// gets object at position x, y
	public Object getObject(int x, int y) {
		if (x < 0 || x >= rows || y < 0 || y >= columns) {
			System.out.printf("location (%d, %d) is out of bounds.\n", x, y);
			return null;
		}
		
		return grid[x][y];
	}
	
	// sets the object at index x, y to o
	public void setObject(int x, int y, Object o) {
		if (x < 0 || x >= rows || y < 0 || y >= columns) {
			System.out.printf("location (%d, %d) is out of bounds\n", x, y);
			return;
		}
		
		grid[x][y] = o;
	}
}
