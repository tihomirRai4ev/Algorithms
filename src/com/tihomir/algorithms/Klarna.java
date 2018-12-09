package com.tihomir.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Klarna {

	public static String maskify(String creditCardNumber) {
		if (creditCardNumber == "" || creditCardNumber == null || creditCardNumber.length() < 6) {
			return creditCardNumber;
		}
		StringBuilder str = new StringBuilder();
		str.append(creditCardNumber.charAt(0));
		for (int i = 1; i < creditCardNumber.length() - 4; i++) {
			char ch = creditCardNumber.charAt(i);
			if (Character.isDigit(ch)) {
				str.append('#');
			} else {
				str.append(ch);
			}
		}
		str.append(creditCardNumber.substring(creditCardNumber.length() - 4));
		return str.toString();
	}

	/**
	 * st is used with numbers ending in 1 (e.g. 1st, pronounced first) nd is used
	 * with numbers ending in 2 (e.g. 92nd, pronounced ninety-second) rd is used
	 * with numbers ending in 3 (e.g. 33rd, pronounced thirty-third) As an exception
	 * to the above rules, all the "teen" numbers ending with 11, 12 or 13 use -th
	 * (e.g. 11th, pronounced eleventh, 112th, pronounced one hundred [and] twelfth)
	 * th is used for all other numbers (e.g. 9th, pronounced ninth).
	 */
	public static String numberToOrdinal(Integer number) {
		int remainder = number % 100;
		if (remainder >= 10 && remainder <= 20) {
			return number + "th";
		}
		int tenRemainder = number % 10;
		switch (tenRemainder) {
		case 1:
			return number + "st";
		case 2:
			return number + "nd";
		case 3:
			return number + "rd";
		default:
			return number + "th";
		}
	}

	private static double evaluate(String expr) {
		if (expr == "") {
			return 0;
		}
		String[] tokens = expr.split("\\s");
		double res = 0.0;
		String operators = "+-*/";
		Stack<String> stack = new Stack<String>();
		for (String t : tokens) {
			if (!operators.contains(t)) {
				stack.push(t);
			} else {
				double a = Double.valueOf(stack.pop());
				double b = Double.valueOf(stack.pop());
				int idx = operators.indexOf(t);
				switch (idx) {
				case 0:
					stack.push(String.valueOf(a + b));
					break;
				case 1:
					stack.push(String.valueOf(b - a));
					break;
				case 2:
					stack.push(String.valueOf(a * b));
					break;
				case 3:
					stack.push(String.valueOf(b / a));
					break;
				}
			}
		}
		res = Double.valueOf(stack.pop());
		return res;
	}
	
	static boolean exists(int[] ints, int k) {
		int left = 0;
		int right = ints.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (ints[mid] == k) {
				return true;
			}
			if (ints[mid] < k) {
				left = mid + 1;
			}
			if (ints[mid] > k) {
				right = mid - 1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(exists(new int[] {1, 2, 3, 4 ,5, 6, 7, 8}, 8));
	}

}
