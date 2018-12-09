package com.tihomir.algorithms;

/**
 * Simple generic LinkedList implementation.
 *
 * @author Tihomir Raychev
 *
 * @param <T>
 */
public class LinkedList_<T> {

	int size;
	Node<T> connector;

	private static class Node<T> {

		private Node next;
		private T value;

		public Node(T value) {
			this.value = value;
			next = null;
		}
	}

	/**
	 * Create an empty list.
	 *
	 */
	public LinkedList_() {
		this.size = 0;
		connector = new Node<T>(null);
	}

	/**
	 * Method adds element at the end of the list.
	 *
	 * @param value
	 */
	public void add(T value) {
		if (size == 0) {
			connector.value = value;
			size++;
		} else {
			Node<T> ptr = connector;
			for (int i = 1; i < size; i++) {
				ptr = ptr.next;
			}
			Node<T> newNode = new Node<T>(value);
			ptr.next = newNode;
			size++;
		}
	}

	/**
	 * Method add element at the specified position.
	 *
	 * @param index
	 * @param value
	 */
	public void addAt(int index, T value) {
		if (index > size) {
			throw new RuntimeException("Specified index " + index + " is outside of the scope of the list");
		}

		Node<T> ptr = connector;
		for (int i = 0; i < index - 1; i++) {
			ptr = ptr.next;
		}
		Node<T> newNode = new Node<T>(value);
		newNode.next = ptr.next;
		ptr.next = newNode;
		size++;
	}

	/**
	 * Method removes element at the specified position in the list.
	 *
	 * @param index
	 */
	public void deleteAt(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("IOBException for index: " + index);
		}
		Node<T> ptr = connector;
		for (int i = 0; i < index - 1; i++) {
			ptr = ptr.next;
		}
		Node<T> nodeToRemove = ptr.next;
		ptr.next = ptr.next.next;
		nodeToRemove = null;
		size--;
	}

	public static void main(String[] args) {
		LinkedList_<Integer> list = new LinkedList_<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.addAt(2, 999);
		list.deleteAt(1);
		list.deleteAt(1);
		list.deleteAt(1);
		list.deleteAt(0);
	}

}
