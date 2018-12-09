package com.tihomir.algorithms;

import java.util.HashMap;
import java.util.Map;

public class Reccursion {

	public static int fatorial(int n) {
		if (n == 1) {
			return 1;
		}
		return n * fatorial(n - 1);
	}

	public static int fib(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 0) {
			return 0;
		}
		return fib(n - 2) + fib(n - 1);
	}

	private static int fibExecutor() {
		int t1 = 0;
		int t2 = 1;
		return fibonacciModified(4, t1, t2, new HashMap<Integer, Integer>());
	}

	public static int fibonacciModified(int n, int t1, int t2, Map<Integer, Integer> map) {
		if (n == 0) {
			return t1;
		}
		if (n == 1) {
			return t2;
		}
		if (map.containsKey(n)) {
			return map.get(n);
		}
		Integer fibForN = fibonacciModified(n - 1, t1, t2, map) * fibonacciModified(n - 1, t1, t2, map)
				+ fibonacciModified(n - 2, t1, t2, map);
		map.put(n, fibForN);
		return fibForN;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fibExecutor());
	}

}
