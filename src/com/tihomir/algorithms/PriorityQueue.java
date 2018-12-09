package com.tihomir.algorithms;

/**
 * Simple implementation of Priority Queue based on internal node structure.
 * TODO: implement using heap.
 *
 * @author Tihomir Raychev
 */
public class PriorityQueue<T> {

	/**
	 * Initial capacity of the queue if not specified.
	 *
	 */
	private final static int INITIAL_CAPACITY = 20;

	/**
	 * ---- Constructors ----
	 */

	public PriorityQueue() {
		this(INITIAL_CAPACITY);
	}

	public PriorityQueue(int capacity) {
		queue = new Table[capacity];
	}

	/**
	 * Queue datastructure holder.
	 *
	 */
	Table<T>[] queue;

	public class Table<Type extends T> {
		int priority;
		Type value;

		Table(Type value, int priority) {
			this.priority = priority;
			this.value = value;
		}
	}

	/**
	 * Add item to the queue with the priority specified.
	 *
	 * @param val
	 * @param priority
	 */
	public void add(T val, int priority) {
		
	}

	public static void main(String[] args) {

	}

}
