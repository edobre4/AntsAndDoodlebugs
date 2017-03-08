import java.awt.Color;
import java.util.Random;

// Emanuil Dobrev
// CS 211
// Proj 6
// Fall 2015

public class AntsAndDoodlebugsSimulation {

	// display the grid
	public static void display(Grid g, GridDisplay display) {
		for (int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				Object o = g.getObject(i, j);
				if (o.getClass().toString().equals("class EmptySquare"))
					display.setColor(i, j, Color.LIGHT_GRAY);
				else if (o.getClass().toString().equals("class Ant"))
					display.setColor(i, j, Color.BLACK);
				else if (o.getClass().toString().equals("class Doodlebug"))
					display.setColor(i, j, Color.RED);
			}
		}
	}
	
	// Main function
	public static void main(String[] args) {
		int sleepTime = 100;

		// command line argument can be used to set the sleep time
		// ex: -d 100
		if(args.length == 2) {
			String arg0 = args[0];
			if (arg0.equals("-d")) {
				String arg1 = args[1];
				sleepTime = Integer.parseInt(arg1);
				
			}
		}
		
		// set up the grid
		Grid g = new Grid(20, 20);
		Random rnd = new Random();
		
		GridDisplay display = new GridDisplay(20, 20);
		
		// spawn 5 doodlebugs
		for (int i = 0; i < 5; i++ ) {
			Doodlebug d = new Doodlebug();
			boolean repeat = true;
			while (repeat) {
				int row = Math.abs(rnd.nextInt() % 20);
				int col = Math.abs(rnd.nextInt() % 20);
				repeat = ! d.setLocation(g, row, col);
			}
		}
		
		// spawn 100 ants
		for (int i = 0; i < 100; i++ ) {
			Ant a = new Ant();
			boolean repeat = true;
			while (repeat) {
				int row = Math.abs(rnd.nextInt() % 20);
				int col = Math.abs(rnd.nextInt() % 20);
				repeat = ! a.setLocation(g, row, col);
			}
		}
		display(g, display);
		int day = 1;
		while(true) {
			System.out.printf("Day %d\n", day);
			day++;
			// run the simulation forever
			
			// set hasMoved to each creature to false at the beginning of day
			for(int i = 0; i < 20; i++) {
				for(int j = 0; j < 20; j++) {
					Object o = g.getObject(i, j);
					if (!o.getClass().toString().equals("class EmptySquare")) {
						Creature c = (Creature) o;
						c.hasMoved = false;
					}
				}
			}
			
			// move each creature (move checks for spawns, starve, hunt etc.
			for(int i = 0; i < 20; i++) {
				for(int j = 0; j < 20; j++) {
					Object o = g.getObject(i, j);
					if (!o.getClass().toString().equals("class EmptySquare")) {
						Creature c = (Creature) o;
						if (c.hasMoved)
							continue;
						c.move(g);
						display(g, display);
						GridDisplay.mySleep(sleepTime);
					}
				}
			}
		}
	}

}
