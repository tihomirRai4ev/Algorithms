package com.tihomir.algorithms;

/**
 * Simple ArrayList generic implementation. Not thread safe.
 *
 * @author Tihomir Raychev
 *
 * @param <T>
 */
public class ArrayList_<T> {

	/**
	 * Default Size of the array, if not specified.
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 10;

	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words in
	 * an array. Attempts to allocate larger arrays may result in OutOfMemoryError:
	 * Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * Defaulr resize factor, if not specified.
	 */
	private static final int DEFAULT_RESIZE_FACTOR = 2;

	private T[] data;
	private int capacity;

	/////// Constructors ///////////////

	public ArrayList_() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayList_(int capacity) {
		if (capacity > MAX_ARRAY_SIZE) {
			throw new RuntimeException(
					"Requested capacity is greater then Integer max value. As a" + "result out of memory could occur.");
		}
		this.capacity = capacity;
		data = (T[]) new Object[capacity];
	}

	////// Logical methods /////////////

	/**
	 * Obtaining the element's value at specified index, if exists.
	 *
	 * @param index
	 * @return
	 */
	public T get(int index) {
		if (index < 0 || index >= capacity) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return data[index];
	}

	/**
	 * Add new element to the array list at specified index. If the index is outside
	 * of the scope of the array list, we perform resizing before adding the
	 * element.
	 *
	 * @param index
	 * @param value
	 */
	public void addAt(int index, T value) {
		if (index < capacity) {
			data[index] = value;
		} else {
			int newSize;
			double resizeFactor = index / this.capacity;
			if (!(resizeFactor == 1)) {
				newSize = (int) (capacity * resizeFactor + 1);
			} else {
				if (capacity * DEFAULT_RESIZE_FACTOR > MAX_ARRAY_SIZE) {
					newSize = index + 1;
				} else {
					newSize = capacity * DEFAULT_RESIZE_FACTOR;
				}
			}
			if (newSize > MAX_ARRAY_SIZE) {
				throw new RuntimeException("Something nasty occured");
			}
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[newSize];
			for (int i = 0; i < capacity; i++) {
				temp[i] = data[i];
			}
			temp[index] = value;
			data = temp;
			capacity = newSize;
		}
	}

	/**
	 * Delete element's value at the specified index. This method does not perform
	 * resizing.
	 *
	 * @param index
	 */
	public void deleteAt(int index) {
		if (capacity <= index) {
			throw new ArrayIndexOutOfBoundsException();
		}
		data[index] = null;
	}

	/**
	 * Performs deletion of the element at the specified position. The array is
	 * being resized.
	 *
	 * @param index
	 */
	public void deleteAtWithResizing(int index) {
		if (capacity <= index) {
			throw new ArrayIndexOutOfBoundsException();
		}
		@SuppressWarnings("unchecked")
		T[] newData = (T[]) new Object[capacity - 1];
		for (int i = 0, j = 0; i < capacity; i++) {
			if (i == (index)) {
				continue;
			}
			newData[j] = data[i];
			j++;
		}
		this.data = newData;
		this.capacity--;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public static void main(String[] args) {
		ArrayList_<Integer> list = new ArrayList_<Integer>();
		list.addAt(0, 1);
		list.addAt(1, 2);
		list.addAt(2, 3);
		list.addAt(15, 4);
		list.deleteAtWithResizing(2);
		list.addAt(2, 1);
	}
}
