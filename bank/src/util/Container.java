package util;

public class Container<T extends Comparable<T>> {
	private int size = 2;
	private int placement = 0;
	@SuppressWarnings("unchecked")
	private T[] container = (T[]) new Comparable[size];
	
	public T getElement(int i){
		if (i >= 0 && i < placement) {
			return container[i];
		} else {
			return null;
		}
	}
	
	public void addElement(T element) {
		if (placement >= size) {
			createAndCopyContainer();
		}
		container[placement] = element;
		placement++;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getNumberOfElements() {
		return placement;
	}
	
	public void sort() {
		QuickSort<T> sorter = new QuickSort<>();
		sorter.sort(container);
	}

	private void createAndCopyContainer() {
		@SuppressWarnings("unchecked")
		T[] newContainer = (T[]) new Comparable[size*2];
		for (int i=0;i<size;i++) {
			newContainer[i] = container[i];
		}
		size = size*2;
		container = newContainer;
	}
}
