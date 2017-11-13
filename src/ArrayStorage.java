import java.util.Random;

public class ArrayStorage {
	
	int[] elements;
	Random rand = new Random();
	
	public ArrayStorage(int nElements) {
		elements = new int[nElements];
		for (int i = 0; i < nElements; i++) {
			elements[i] = randInt(1, 100);
		}
	}
	
	public int randInt(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
}
