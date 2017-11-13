import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MergeArrays {

	int[][] twoDimensionalArray;
	int singleArrayOneByOne[];
	int singleArrayDivideAndConquer[];
	int kArrays = 0;
	int nElements = 0;
	Random rand = new Random();
	Scanner keyboard = new Scanner(System.in);

	public MergeArrays() {
		System.out.println("Number of arrays: ");
		this.kArrays = keyboard.nextInt();
		System.out.println("Elements in each array: ");
		this.nElements = keyboard.nextInt();
		int min = -1000;
		int max = 1000;
		twoDimensionalArray = new int[kArrays][nElements];
		for (int i = 0; i < kArrays; i++) {
			for (int j = 0; j < nElements; j++) {
				twoDimensionalArray[i][j] = randInt(min, max);
			}
		}
		for (int i = 0; i < kArrays; i++) {
//			mergeSort(twoDimensionalArray[i]);
			Arrays.sort(twoDimensionalArray[i]);
		}
	}

	public int randInt(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public int[] mergeArraysOneByOne(int[][] twoDimArray) {
		int[] array1 = twoDimArray[0];
		int[] finalArray;
		for (int i = 1; i < twoDimensionalArray.length; i++) {
			int array2[] = twoDimensionalArray[i];
			int array1Index = 0;
			int array2Index = 0;
			int tempArray[] = new int[array1.length + array2.length];
			int tempArrayIndex = 0;
			while (array1Index < array1.length && array2Index < array2.length) {
				if (array1[array1Index] < array2[array2Index]) {
					tempArray[tempArrayIndex] = array1[array1Index];
					array1Index++;
				} else {
					tempArray[tempArrayIndex] = array2[array2Index];
					array2Index++;
				}
				tempArrayIndex++;
			}
			System.arraycopy(array2, array2Index, tempArray, tempArrayIndex, array2.length - array2Index);
			System.arraycopy(array1, array1Index, tempArray, tempArrayIndex, array1.length - array1Index);
			array1 = tempArray;
		}
		finalArray = array1;
		System.out.print("One by one merged array: ");
		for (int i = 0; i < finalArray.length; i++) {
			System.out.print("[" + finalArray[i] + "] ");
		}
		System.out.println();
		return finalArray;
	}

	public int[] mergeArraysDivideAndConquer(int[][] twoDimArray) {
		int height = twoDimArray.length;
		int width = twoDimArray[0].length;
		int[] copyArray = new int[height * width];
		int indexTracker = 0;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copyArray[indexTracker] = twoDimArray[i][j];
				indexTracker++;
			}
		}
		
		int[] mergeSortArray = mergeSort(copyArray);
		System.out.print("Divide and conquer merged array: ");
		
		for (int i = 0; i < mergeSortArray.length; i++) {
			System.out.print("[" + mergeSortArray[i] + "] ");
		}
		
		System.out.println();
		return mergeSortArray;
	}

	public int[] mergeSort(int[] array) {
		int size = array.length;
		
		if (size < 2) {
			return array;
		}
		
		int middle = size / 2;
		int leftSize = middle;
		int rightSize = size - middle;
		int[] left = new int[leftSize];
		int[] right = new int[rightSize];
		
		for (int i = 0; i < middle; i++) {
			left[i] = array[i];
		}
		
		for (int i = middle; i < size; i++) {
			right[i - middle] = array[i];
		}

		mergeSort(left);
		mergeSort(right);
		merge(left, right, array);
		return array;
	}

	public void merge(int[] left, int[] right, int[] array) {
		int leftSize = left.length;
		int rightSize = right.length;
		int leftIndex = 0;
		int rightIndex= 0;
		int arrayIndex = 0;
		
		while (leftIndex < leftSize && rightIndex < rightSize) {
		    if (left[leftIndex] <= right[rightIndex]) {
		        array[arrayIndex] = left[leftIndex];
		        leftIndex++;
		        arrayIndex++;
		    } else {
		        array[arrayIndex] = right[rightIndex];
		        rightIndex++;
		        arrayIndex++;
		    }
		}
		while (leftIndex < leftSize) {
		    array[arrayIndex] = left[leftIndex];
		    arrayIndex++;
		    leftIndex++;
		}
		while (rightIndex < rightSize) {
		    array[arrayIndex] = right[rightIndex];
		    rightIndex++;
		    arrayIndex++;
		}
	}

	public String toString() {
		String print = "";
		for (int i = 0; i < kArrays; i++) {
			print += "Array " + (i + 1) + ": ";
			for (int j = 0; j < nElements; j++) {
				print += "[" + twoDimensionalArray[i][j] + "] ";
			}
			print += "\n";
		}
		return print;
	}

	public static void main(String[] args) {
		MergeArrays ma = new MergeArrays();
		System.out.println(ma);
		
		ma.mergeArraysOneByOne(ma.twoDimensionalArray);
		
		ma.mergeArraysDivideAndConquer(ma.twoDimensionalArray);		
	}
}
