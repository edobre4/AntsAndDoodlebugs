import java.util.Random;
// class Creature - superclass of Ant and Doodlebug
public class Creature {
	// member variables
	protected Point2D location;  // indicates the creature's location on the grid
	protected boolean hasMoved;  // flag if the creature has moved
	protected int daysToSpawn;   // number of days until the creature will spawn another creature
	protected int spawnPeriod;   // number of days between spawns
	protected String name;       // name of the creature
	
	// default constructor
	public Creature() {
		location = null;
		hasMoved = false;
		daysToSpawn = 10;
		spawnPeriod = 10;
		name = "Creature";
	}
	
	// getter for name
	public String getName() {
		return name;
	}
	
	// set the location of this creature
	public boolean setLocation(Grid g, int x, int y) {
		// getObject() already does bound checking so we can skip that here
		Object o = g.getObject(x, y);
		if (o == null)
			return false;
		// if square is empty set the location to this creature
		if (o.getClass().toString().equals("class EmptySquare")) {
			g.setObject(x, y, this);
			location = new Point2D(x, y);
			return true;
		}
		return false;
	}
	
	// spawn a creature of the same type on adjacent square
	public void spawn(Grid g) {
		// check the upper square
		if (this.location.y_coord() - 1 >= 0 && g.getObject(location.x_coord(), location.y_coord() - 1).getClass().toString().equals("class EmptySquare")){
			if (this.name.equals("Ant")) {
				Ant a = new Ant();
				a.setLocation(g, location.x_coord(), location.y_coord() - 1);
			}
			else if (this.name.equals( "Doodlebug") ){
				Doodlebug d = new Doodlebug();
				d.setLocation(g, location.x_coord(), location.y_coord() - 1);
			}
		}
		// check lower square
		else if (this.location.y_coord() + 1 < 20 && g.getObject(location.x_coord(), location.y_coord() + 1).getClass().toString().equals("class EmptySquare")) {
			if (this.name.equals("Ant")) {
				Ant a = new Ant();
				a.setLocation(g, location.x_coord(), location.y_coord() + 1);
			}
			else if (this.name.equals("Doodlebug")) {
				Doodlebug d = new Doodlebug();
				d.setLocation(g, location.x_coord(), location.y_coord() + 1);
			}
		}
		// left square
		else if (this.location.x_coord() - 1 >= 0 && g.getObject(location.x_coord() - 1, location.y_coord()).getClass().toString().equals("class EmptySquare")) {
			if (this.name.equals("Ant")) {
				Ant a = new Ant();
				a.setLocation(g, location.x_coord() - 1, location.y_coord());
			}
			else if (this.name.equals("Doodlebug")) {
				Doodlebug d = new Doodlebug();
				d.setLocation(g, location.x_coord() - 1, location.y_coord());
			}
		}
		// check right square
		else if (this.location.x_coord() + 1 < 20 && g.getObject(location.x_coord() + 1, location.y_coord()).getClass().toString().equals("class EmptySquare")) {
			if (this.name.equals("Ant")) {
				Ant a = new Ant();
				a.setLocation(g, location.x_coord() + 1, location.y_coord());
			}
			else if (this.name.equals("Doodlebug")) {
				Doodlebug d = new Doodlebug();
				d.setLocation(g, location.x_coord() + 1, location.y_coord());
			}
		}
		// if no new creature was spawned, return without changing the days to spawn
		else
			return;
		daysToSpawn += spawnPeriod;
	}
	
	// choose a random direction to move
	// if the location is out of bounds or occupied choose a direction
	// again until all directions have been checked
	// if there is no available square to move, the creature will stay at its current position
	public void move(Grid g) {
		if (hasMoved)
			return;
		daysToSpawn--;
		if (daysToSpawn == 0) {
			spawn(g);
		}
		int []checkedDirections = new int[4];
		for(int i = 0; i < 4; i++) 
			checkedDirections[i] = -1;
		int cnt = 0;
		boolean notFoundEmpty = true;
		Random r = new Random();

		// repeat until 4 directions are checked or an empty square is found
		while (cnt < 4 && notFoundEmpty) {
			// choose new direction
			int direction = Math.abs(r.nextInt() % 4);
			
			boolean repeat = false;
			for (int i = 0; i < cnt; i++) {
				if (checkedDirections[i] == direction)
					repeat = true;
			}
			if (repeat)
				continue;
			switch(direction) {
			case 0:
				// UP
				if (location.y_coord() - 1 >= 0 && 
					    g.getObject(location.x_coord(), 
						location.y_coord() - 1).getClass().toString().equals("class EmptySquare")){
					g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
					g.setObject(location.x_coord(), location.y_coord() - 1, this);
					location.y_coord(location.y_coord() - 1);
					notFoundEmpty = false;
					break;
				}
				checkedDirections[cnt++] = 0;
				break;
			case 1:
				// Down
				if (location.y_coord() + 1 < 20 &&
					g.getObject(location.x_coord(),
					location.y_coord() + 1).getClass().toString().equals("class EmptySquare")) {
					// move down
					g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
					g.setObject(location.x_coord(), location.y_coord() + 1, this);
					location.y_coord(location.y_coord() + 1);
					notFoundEmpty = false;
					break;
				}
				checkedDirections[cnt++] = 1;
				break;
			case 2:
				// Left
				if (location.x_coord() - 1 >= 0 &&
					g.getObject(location.x_coord() - 1, 
					location.y_coord()).getClass().toString().equals("class EmptySquare")) {
					g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
					g.setObject(location.x_coord() - 1, location.y_coord(), this);
					location.x_coord(location.x_coord() - 1);
					notFoundEmpty = false;
					break;
				}
				checkedDirections[cnt++] = 2;
				break;
			case 3:
				// Right
				if (location.x_coord() + 1 < 20 &&
					g.getObject(location.x_coord() + 1, 
					location.y_coord()).getClass().toString().equals("class EmptySquare")) {
					g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
					g.setObject(location.x_coord() + 1, location.y_coord(), this);
					location.x_coord(location.x_coord() + 1);
					notFoundEmpty = false;
					break;
				}
				checkedDirections[cnt++] = 3;
				break;	
			}
		}
		hasMoved = true;
	}
}
