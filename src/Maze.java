import java.util.Random;

public class Maze {
	
	Random rand = new Random();
	String [][] maze;
	int n;
	
	public Maze(int n) {
		if (n < 5) {
			n = 5;
		}
		this.n = n;
		maze = new String[(n * 2) + 1][(n * 2) + 1];

		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze.length; x++) {
				if (x == 0 || y == 0 || x == maze.length - 1 || y == maze.length - 1) {
					maze[x][y] = "#";
				} else if (x % 2 != 1 || y % 2 != 1) {
					maze[x][y] = "w";
				} else {
					maze[x][y] = " ";
				}
			}
		}
		int x = 0;
		int rand1 = 0;
		int rand2 = 0;
		while (x == 0) {
			rand1 = randInt(1, maze.length - 1);
			rand2 = randInt(1, maze.length - 1);
			if (maze[rand1][rand2] == " ") {
				break;
			}
		}
		maze[rand1][rand2] = "S";
		while (x == 0) {
			rand1 = randInt(1, maze.length - 1);
			rand2 = randInt(1, maze.length - 1);
			if (maze[rand1][rand2] == " ") {
				break;
			}
		}
		maze[rand1][rand2] = "*";
	}
	
	public int randInt(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public void depthFirstSearch() {
		
	}
	
	@Override
	public String toString() {
		String mazePrint = "";
		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze.length; x++) {
				mazePrint += maze[x][y];
				mazePrint += " ";
				if (x == maze.length - 1) {
					mazePrint += "\n";
				}
			}
		}
		return mazePrint;
	}
	
	public static void main(String args[]) {
		Maze print = new Maze(10);
		System.out.println(print);
	}
}
