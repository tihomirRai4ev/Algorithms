package com.tihomir.algorithms;

/**
 * Simple implementation of LRU Cache. This cache implementation
 * differs from most common, as we keep elements sorted by their
 * most recent usage. This means the elements at the begining
 * will be most recetly used compared to botton ones. Example
 * element at index 2 is added before the element at index 3.
 * The head of the cache is most recently used element.
 *
 * @author Tihomir Raychev
 *
 */
public class LRUCache {

	/**
	 * If we try to put more than this number of elements in the cache, the least
	 * recently used will be removed and the new element will be added as most
	 * recent element.
	 */
	private final static int MAXIMUM_CAPACITY = 5; // Very small for testing purpose.
													// Also TODO: make configurable via constructor.

	/**
	 * Helper datastructure to keep elements of the Cache. Note: I avoid having
	 * double linked structure for memory efficientsy!
	 *
	 */
	private static class Node {
		int value; // TODO add generics.
		Node next;

		Node(int value) {
			this.value = value;
		}
	}

	/**
	 * First element of the Cache.
	 */
	private Node head;

	/* The number of slots in the cache, which are occupaid */
	private int size;

	public LRUCache(int value) {
		head = new Node(value);
	}

	/**
	 * Add new elemet in the cache. The element will be added at the beggining of
	 * the linked datastructure. If the Cache capacity is reached, the least used
	 * element will be removed from the cache.
	 *
	 * @param value
	 */
	public void add(int value) {
		if (size == MAXIMUM_CAPACITY) {
			Node ptr = head.next;
			Node beforeLast = head;
			while (ptr.next != null) {
				ptr = ptr.next;
				beforeLast = beforeLast.next;
			}
			beforeLast.next = null;
			ptr = null;
			size--;
		}
		// Add new item as most recent, in the front.
		Node mostRecent = new Node(value);
		mostRecent.next = head;
		head = mostRecent;
		size++;
	}

	/**
	 * Iteratively traverse the elements to find the item at the specified index.
	 * The newly requested element becomes the most recently used and goes at the
	 * beggining of the cache.
	 *
	 * @param idx
	 * @return element's value if found or -1 otherwise.
	 */
	public int getAt(int idx) {
		if (idx >= size) {
			return -1;
		}
		Node previous = head;
		Node ptr = head.next;
		for (int i = 0; i < idx; i++) {
			ptr = ptr.next;
			previous = previous.next;
		}
		int result = ptr.value;
		if (ptr.next != null) {
			previous.next = ptr.next;
		}
		// Push ptr in the beggining of the cache.
		ptr.next = head;
		head = ptr;
		return result;
	}

	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		StringBuilder helper = new StringBuilder();
		Node ptr = head;
		while (ptr != null) {
			helper.append(ptr.value).append("\n");
			ptr = ptr.next;
		}
		return helper.toString();
	}

	public static void main(String[] args) {
		// Very simple test case:
		LRUCache cache = new LRUCache(0);
		cache.add(1);
		cache.add(2);
		cache.add(3);
		cache.add(4);
		cache.add(5);
		cache.add(6);
		cache.getAt(2);
		cache.print();
	}
}