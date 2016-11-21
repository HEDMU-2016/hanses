package util;

public class BubbleSort<T extends Comparable<T>> {

	public void sort(T[] unsortedArray) {
		// This is the Bubble sort.
		for (int i = 1; i < unsortedArray.length; i++)
			for (int j = unsortedArray.length - 1; j >= i; j--) {
				if (unsortedArray[j - 1].compareTo(unsortedArray[j]) > 0) { // if out of order
					// exchange elements
					T placeholder = unsortedArray[j - 1];
					unsortedArray[j - 1] = unsortedArray[j];
					unsortedArray[j] = placeholder;
				}
			}
	}

}
