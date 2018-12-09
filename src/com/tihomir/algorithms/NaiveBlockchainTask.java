package com.tihomir.algorithms;

import java.util.*;
import java.util.Scanner;

/**
 * A naive blockchain software company has decided to use an improved
 * round-robin algorithm to determine who is eligible to generate the next
 * block. Initially, all N participants are put in a list in some arbitrary
 * order, and each of them in turn generates a block. After this first round,
 * the list of all N participants is split in two sublists: list A from 0 to K
 * inclusive, and list B from K+1 to N-1 inclusive, and a new list is created by
 * taking the first participant from list B, followed by the first participant
 * from list A, followed by the next one from list B, then the next one from
 * list A, and so on, alternating until the smaller of the lists A and B is
 * exhausted. The remaining participants from the other list are then added at
 * the end, and this resulting list is used as the ordering for the second
 * round. The process is repeated indefinitely, always using the same K.
 *
 * This gives better security than just using the N participants always in the
 * same order, however after many iterations the original ordering of the list
 * is reached again, and the process repeats. To maximize the number of blocks
 * generated before this happens, the company needs a function that calculates
 * after how many iterations the original ordering is reached. Your task as a
 * programmer is to write such a function. As a test case, please calculate the
 * result for N=1002 and K=900.
 *
 * You should submit your solution as a single java file, with a main method
 * that takes N and K as parameters. No maven projects please.
 * 
 * @author Tihomir Raychev
 *
 */
public class NaiveBlockchainTask {

	/**
	 * Returns the number of steps required to generate the original's order of the
	 * list specified as parametar.
	 *
	 * @param orig
	 * @param k
	 * @return
	 */
	private int test(List<Integer> orig, int k) {
		List<Integer> obfuscated = orig;
		int steps = 0;
		do {
			obfuscated = obfuscate(obfuscated, k);
			printList(obfuscated);
			steps++;
			if (steps == 14) {
				System.out.println("test");
			}
		} while (!obfuscated.equals(orig));
		return steps;
	}

	private void printList(List<Integer> list) {
		for (Integer i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private List<Integer> obfuscate(List<Integer> orig, int k) {
		List<Integer> A = new ArrayList<>();
		List<Integer> B = new ArrayList<>();
		List<Integer> mixed = new ArrayList<>();
		for (int i = 0, j = 0; i < orig.size(); i++) {
			if (i <= k) {
				A.add(i, orig.get(i));
			} else {
				B.add(j++, orig.get(i));
			}
		}
		for (int i = 0; i < orig.size(); i++) {
			if (i < B.size()) {
				mixed.add(B.get(i));
			}
			if (i < A.size()) {
				mixed.add(A.get(i));
			}
		}
		return mixed;
	}

	public int predict(List<Integer> orig, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = orig.size();
		int curr = 0;
		int i = 0, j = k + 1;
		while (curr < n) {
			if (i > k) {
				//map.put(j++, curr++);
				while (j < n) {
					map.put(j++, curr++);
				}
			}
			if (j == n) {
				while (i <= k) {
					map.put(i++, curr++);
				}
			}
			if (curr % 2 == 0) {
				map.put(j++, curr++);
			} else {
				map.put(i++, curr);
			}
		}
		int current = 0;
		int steps = 0;
		do {
			steps++;
			current = map.get(current);
		} while (current != 0);
		return steps;
	}

	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		// int k = sc.nextInt();
		// int n = sc.nextInt();
		// List<Integer> orig = new ArrayList<>();
		// for (int i = 0; i < n; i++) {
		// orig.add(i, sc.nextInt());
		// }
		NaiveBlockchainTask task = new NaiveBlockchainTask();
		List<Integer> orig = new ArrayList<>();
		for (int i = 0; i < 1002; i++) {
			orig.add(i);
		}

		// for (int j = 0; j <= 16; j++) {
		// System.out.println("Steps required for K=" + j + " and N=" + orig.size() + "
		// are: " + task.test(orig, j));
		// }
		System.out.println("Steps required " + task.test(orig, 899));
	}
}