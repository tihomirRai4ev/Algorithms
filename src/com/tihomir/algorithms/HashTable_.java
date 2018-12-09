package com.tihomir.algorithms;

/**
 * Simple HashTable implementation.
 *
 * @author Tihomir Raychev
 *
 */
public class HashTable_ {

	/**
	 * HashTable's contents.
	 */
	private Table[] hashTable;

	/**
	 * Default capacity of the table, if not specified.
	 */
	private static final int DEFAULT_CAPACITY = 0xa;

	/**
	 * Minimum number of slots which can be unused in the table.
	 */
	private static final int MIN_FREE_SLOTS = 0x5; // TODO make configurable.

	/**
	 * Current capacity of the table.
	 */
	private int capacity;

	/**
	 * Current slots occupaid in the table.
	 */
	private int freeSlots;

	/**
	 * Resize factor.
	 */
	private int factor = 2;

	/**
	 * Current number of elements per bucket. Note this is not the number of total
	 * elements.
	 */
	private int size;

	public HashTable_() {
		this(DEFAULT_CAPACITY, 2);
	}

	/**
	 * ////////////////////// Constructor //////////////////////
	 *
	 * @param capacity
	 * @param factor
	 */
	public HashTable_(int capacity, int factor) {
		hashTable = new Table[capacity];
		initTable(hashTable, capacity);
		this.capacity = capacity;
		this.freeSlots = capacity;
		this.factor = factor;
		this.size = 0;
	}

	/**
	 * Allocate memory for the table.
	 *
	 */
	private void initTable(Table[] table, int size) {
		for (int i = 0; i < size; i++) {
			table[i] = new Table();
		}
	}

	/**
	 * Helper data-structure used to cope with collisions.
	 *
	 */
	private static class Table {
		Table next;
		Integer value; // TODO generics
	}

	/**
	 * Resize the table, if number of free slots is less than MIN_FREE_SLOTS.
	 */
	private void resizeFast() {
		int newCapacity = capacity * factor;
		Table[] nt = new Table[newCapacity];
		initTable(nt, newCapacity);
		for (int i = 0; i < capacity; i++) {
			nt[i] = hashTable[i];
		}
		freeSlots = newCapacity - size;
		capacity = newCapacity;
	}

	public void put(Integer value) {
		if (freeSlots <= MIN_FREE_SLOTS) {
			resizeFast();
		}
		int index = value.hashCode() % capacity;
		Table table = hashTable[index];
		if (table.value == null) {
			table.value = value;
			freeSlots--; //TODO freeSlots and size both is redundant!
			size++;
		} else {
			Table t = table;
			while (t.next != null) {
				t = t.next;
			}
			Table n = new Table();
			n.value = value;
			t.next = n;
			/*
			 * freeSlots and size are not related to internal buckets and should not be incremented.
			 */
		}
	}

	public static void main(String[] args) {
		HashTable_ table = new HashTable_();
		table.put(1);
		table.put(2);
	}

}
