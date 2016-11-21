package util;

public class QuickSort<T extends Comparable<T>> {

	public void sort(T[] unsortedArray) {
		qs(unsortedArray, 0, unsortedArray.length-1);
	}

	private void qs(T[] items, int left, int right) {
		int i, j;
		T x, y;
		i = left;
		j = right;
		x = items[(left + right) / 2];
		do {
			while ((items[i].compareTo(x) < 0) && (i < right))
				i++;
			while ((x.compareTo(items[j]) < 0) && (j > left))
				j--;
			if (i <= j) {
				y = items[i];
				items[i] = items[j];
				items[j] = y;
				i++;
				j--;
			}
		} while (i <= j);
		if (left < j)
			qs(items, left, j);
		if (i < right)
			qs(items, i, right);
	}
}
