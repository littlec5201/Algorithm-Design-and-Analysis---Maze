import java.util.Random;

public class Room {
	Random rand = new Random();

	public static boolean upVisited(int[][] locations, int[] current) {
		if (current[1] - 1 < 0) {
			return true;
		} else {
			int x = 0;
			for (int i = 0; i < locations.length; i++) {
				if (locations[i][0] == current[0] && locations[i][1] == (current[1] - 1)) {
					x++;
				}
			}
			if (x > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean downVisited(int[][] locations, int[] current, int n) {
		if (current[1] + 1 > n - 1) {
			return true;
		} else {
			int x = 0;
			for (int i = 0; i < locations.length; i++) {
				if (locations[i][0] == current[0] && locations[i][1] == (current[1] + 1)) {
					x++;
				}
			}
			if (x > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean leftVisited(int[][] locations, int[] current) {
		if (current[0] - 1 < 0) {
			return true;
		} else {
			int x = 0;
			for (int i = 0; i < locations.length; i++) {
				if (locations[i][0] == (current[0] - 1) && locations[i][1] == current[1]) {
					x++;
				}
			}
			if (x > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static boolean rightVisited(int[][] locations, int[] current, int n) {
		if (current[0] + 1 > n - 1) {
			return true;
		} else {
			int x = 0;
			for (int i = 0; i < locations.length; i++) {
				if (locations[i][0] == (current[0] + 1) && locations[i][1] == current[1]) {
					x++;
				}
			}
			if (x > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static void mazeSolver(int n, int[][] visited, int[] start, int[] treasure, int moveNumber, int moveBack) {
		if (start[0] == treasure[0] && start[1] == treasure[1]) {
			System.out.println("Current location: (" + start[0] + ", " + start[1] + ")");
			System.out.println("Treasure found!");
		} else {
			int[] current = start;
			int currentMove = moveNumber;
			int beenThere = 0;
			for (int i = 0; i < visited.length; i++) {
				if (visited[i][0] == current[0] && visited[i][1] == current[1]) {
					beenThere++;
				}
			}
			System.out.println("Current location: (" + current[0] + ", " + current[1] + ")");
			if (beenThere == 0) {
				System.out.println("Adding to visited");
				visited[currentMove][0] = start[0];
				visited[currentMove][1] = start[1];
			}

			boolean up = upVisited(visited, current);
			boolean down = downVisited(visited, current, n);
			boolean left = leftVisited(visited, current);
			boolean right = rightVisited(visited, current, n);

			if (!up || !down || !left || !right) {
				Random rand = new Random();

				int x = 1;
				while (x == 1) {
					int direction = rand.nextInt(4);
					if (direction == 0 && !up) {
						current[1] = current[1] - 1;
						currentMove++;
						mazeSolver(n, visited, current, treasure, currentMove, currentMove);
						x = 0;
					} else if (direction == 1 && !down) {
						current[1] = current[1] + 1;
						currentMove++;
						mazeSolver(n, visited, current, treasure, currentMove, currentMove);
						x = 0;
					} else if (direction == 2 && !left) {
						current[0] = current[0] - 1;
						currentMove++;
						mazeSolver(n, visited, current, treasure, currentMove, currentMove);
						x = 0;
					} else if (direction == 3 && !right) {
						current[0] = current[0] + 1;
						currentMove++;
						mazeSolver(n, visited, current, treasure, currentMove, currentMove);
						x = 0;
					}
				}
			} else {
				int goBack = moveBack - 1;
				current[0] = visited[goBack][0];
				current[1] = visited[goBack][1];
				mazeSolver(n, visited, current, treasure, currentMove, goBack);
			}
		}
	}

	public static int[] generateStart(int n) {
		Random rand = new Random();
		int[] start = new int[2];
		start[0] = rand.nextInt(n);
		start[1] = rand.nextInt(n);
		return start;
	}

	public static int[] generateTreasure(int n) {
		Random rand = new Random();
		int[] treasure = new int[2];
		treasure[0] = rand.nextInt(n);
		treasure[1] = rand.nextInt(n);
		return treasure;
	}
}
