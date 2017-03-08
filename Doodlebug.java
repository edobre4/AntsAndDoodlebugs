// Doodlebug class, subclass of Creature
public class Doodlebug extends Creature {
	// doodlebugs starve after 3 days of not eating
	private int daysUntilStarve;
	
	// default constructor
	public Doodlebug() {
		location = null;
		name = "Doodlebug";
		daysToSpawn = 8;
		spawnPeriod = 8;
		hasMoved = false;
		daysUntilStarve = 3;
	}
	
	// doodlebugs hunt ants, if there are no ants around the doodlebug will try to move
	public boolean hunt(Grid g) {
		if (hasMoved)
			return false;
		// check if the upper square contains an ant
		if (location.y_coord() - 1 >= 0 && g.getObject(location.x_coord(), location.y_coord() - 1).getClass().toString().equals("class Ant")) {
				g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
				g.setObject(location.x_coord(), location.y_coord() - 1, this);
				location.y_coord(location.y_coord() - 1);
				hasMoved = true;
		} 
		// check if the lower square contains an ant
		
		else if (location.y_coord() +1 < 20 && g.getObject(location.x_coord(), location.y_coord() + 1).getClass().toString().equals("class Ant")) {
				g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
				g.setObject(location.x_coord(), location.y_coord() + 1, this);
				location.y_coord(location.y_coord() + 1);
				hasMoved = true;
		}
		// check if the left square contains an ant
		else if (location.x_coord() - 1 >= 0 && g.getObject(location.x_coord() - 1, location.y_coord()).getClass().toString().equals("class Ant")) {
				g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
				g.setObject(location.x_coord() - 1, location.y_coord(), this);
				location.x_coord(location.x_coord() - 1);
				hasMoved = true;
		}
		// check if the right square contains an ant
		else if (location.x_coord() + 1 < 20 && g.getObject(location.x_coord() + 1, location.y_coord()).getClass().toString().equals("class Ant")) {
			    g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
			    g.setObject(location.x_coord() + 1, location.y_coord(), this);
			    location.x_coord(location.x_coord() + 1);
			    hasMoved = true;
		}
		// if an ant was found, decrement from days to spawn and check if the bug needs to spawn
		// if an ant was not found the bug will try to move, where it will check if it needs to spawn
		if (hasMoved) {
			daysToSpawn--;
			if (daysToSpawn == 0)
				spawn(g);
		}
		return hasMoved;
	}
	
	// overloaded method from super class
	// doodlebugs can starve after not eating for 3 days
	// and will attempt to hunt before moving
	// if hunt was successful the bug will not try to move
	public void move(Grid g) {
		daysUntilStarve--;
		if (daysUntilStarve == 0)
			starve(g);
		else {
			// check if there is an ant in an adjacent square and hunt
			boolean ateAnAnt = hunt(g);
			if (! ateAnAnt)
				super.move(g);
			else
				daysUntilStarve = 3;
		}
	}
	
	// delete the doodlebug if it starved
	public void starve(Grid g) {
		g.setObject(location.x_coord(), location.y_coord(), new EmptySquare());
	}
}
