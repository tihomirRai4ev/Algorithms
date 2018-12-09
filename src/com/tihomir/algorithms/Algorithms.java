package com.tihomir.algorithms;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Each method solves an Algorithmic Leetcode problem. Complexity Distribution :
 * 60 % medium, 30 % easy, 10% hard.
 *
 * @author Tihomir Raychev
 *
 */
public class Algorithms {

	/**
	 *
	 * LEETCODE TOP 100 tasks with relatively efficient solutions.
	 *
	 */

	public int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> helper = new HashMap<Integer, Integer>();
		boolean contains = false;
		int[] result = new int[2];
		int lookingFor = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (helper.containsKey(target - nums[i])) {
				contains = true;
				result[0] = i;
				lookingFor = target - nums[i];
				break;
			}
			helper.put(nums[i], target - nums[i]);
		}
		if (contains) {
			for (int k = 0; k < nums.length; k++) {
				if (nums[k] == lookingFor) {
					result[1] = k;
					break;
				}
			}
		}
		return result;
	}

	public int reverse(int x) {
		long result2 = 0;
		int result = 0;
		int ptr = x;
		while (!(ptr >= 0 && ptr <= 9) || !(ptr <= 0 && ptr >= -9)) {
			result = result * 10 + ptr % 10;
			result2 = result2 * 10 + ptr % 10;
			ptr = ptr / 10;
		}
		if (result != result2) {
			// overflow
			return 0;
		}
		return result;
	}

	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		} else {
			if (x == reverse(x)) {
				return true;
			}
		}
		return false;
	}

	static HashMap<Character, Integer> ht = new HashMap<Character, Integer>();
	static {
		ht.put('I', 1);
		ht.put('X', 10);
		ht.put('C', 100);
		ht.put('M', 1000);
		ht.put('V', 5);
		ht.put('L', 50);
		ht.put('D', 500);
	}

	public int romanToInt(String s) {
		int intNum = 0;
		int prev = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			int temp = ht.get(s.charAt(i));
			if (temp < prev)
				intNum -= temp;
			else
				intNum += temp;
			prev = temp;
		}
		return intNum;
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}
		StringBuilder lp = new StringBuilder();
		lp.append(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			if (strs[i].startsWith(lp.toString())) {
				// lp is still the longest prefix.
				continue;
			} else {
				StringBuilder newLp = new StringBuilder();
				for (int k = 0; k < lp.length() && k < strs[i].length(); k++) {
					if (strs[i].charAt(k) == lp.charAt(k)) {
						newLp.append(lp.charAt(k));
					} else {
						break;
					}
				}
				if (newLp.length() == 0) {
					return "";
				} else {
					lp = newLp;
				}
			}
		}
		return lp.toString();
	}

	private boolean isClosingBracket(Character s) {
		return (s == ')' || s == ']' || s == '}');
	}

	private Character reverseBracket(Character c) {
		if (c == '(') {
			return ')';
		} else if (c == ')') {
			return '(';
		} else if (c == '{') {
			return '}';
		} else if (c == '}') {
			return '{';
		} else if (c == '[') {
			return ']';
		} else if (c == ']') {
			return '[';
		} else
			return null;
	}

	public boolean isValid(String s) {
		if (s.length() == 1) {
			return false;
		}
		if (s.length() == 0) {
			return true;
		}
		if (isClosingBracket(s.charAt(0))) {
			return false;
		}
		Stack<Character> expectedSequence = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (!isClosingBracket(s.charAt(i))) {
				expectedSequence.push(reverseBracket(s.charAt(i)));
			} else {
				if (!expectedSequence.isEmpty() && expectedSequence.pop() == s.charAt(i)) {
					continue;
				} else {
					return false;
				}
			}
		}
		return expectedSequence.isEmpty();
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * Merge two sorted lists. Fuckin dummy solution!
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(1);
		ListNode copy = result;
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		while (l1 != null && l2 != null) {
			if (l1.val > l2.val) {
				copy.next = l2;
				l2 = l2.next;
			} else {
				copy.next = l1;
				l1 = l1.next;
			}
			copy = copy.next;
		}
		if (l1 != null) {
			copy.next = l1;
		}
		if (l2 != null) {
			copy.next = l2;
		}
		return result.next;
	}

	/**
	 * Accepted solution which doesn't create new array. [1,1,2] -> [1,2,the
	 * rest(int this case 2)]
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates2(int[] nums) {
		if (nums.length <= 1) {
			return nums.length;
		}
		int prev = nums[0];
		int result = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != prev) {
				prev = nums[i];
				nums[result++] = prev;
			}
		}
		return result;
	}

	public int removeElement(int[] nums, int val) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[res++] = nums[i];
			}
		}
		return res;
	}

	public int strStr2(String haystack, String needle) {
		if (needle == null || needle.length() == 0) {
			return 0;
		}
		if (haystack == null || haystack.length() == 0 || needle.length() > haystack.length()) {
			return -1;
		}
		int index = -1;
		for (int i = 0; i < haystack.length(); i++) {
			boolean found = false;
			if (haystack.charAt(i) == needle.charAt(0)) {
				index = i;
				for (int j = 0, k = i; j < needle.length(); j++) {
					if (needle.charAt(j) == haystack.charAt(k)) {
						found = true;
						k++;
					} else {
						found = false;
						break;
					}
					if (k == haystack.length() && j + 1 < needle.length()) {
						found = false;
						break;
					}
				}
			}
			if (found) {
				return index;
			}
		}
		return -1;
	}

	public int strStr(String haystack, String needle) {
		if (needle == null) {
			return -1;
		}
		if (needle == "") {
			return 0;
		}
		char[] haystackArray = haystack.toCharArray();
		char[] needleArray = needle.toCharArray();
		int checkLength = haystackArray.length - needleArray.length;
		int idx;
		for (int i = 0; i <= checkLength; i++) {
			idx = 0;
			int k = i;
			while (idx < needleArray.length && needleArray[idx] == haystackArray[k]) {
				idx++;
				k++;
			}
			if (idx == needleArray.length) {
				return i;
			}
		}
		return -1;
	}

	public int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int mid;
		do {
			mid = (right + left) / 2;
			if (nums[mid] < target) {
				left = mid;
			} else if (nums[mid] > target) {
				right = mid;
			} else {
				return mid;
			}
		} while (right - left > 1);
		// Not found, return index, in which this item should belong!
		if (nums[right] - target > target - nums[left]) {
			if (nums[left] >= target) {
				return left;
			} else {
				return left + 1;
			}
		} else {
			if (right == nums.length - 1) {
				if (nums[right] >= target) {
					return right;
				} else {
					return right + 1;
				}
			}
			return right;
		}
	}

	public int searchInsert2(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int m;
		while (left <= right) {
			m = (left + right) / 2;
			if (nums[m] == target) {
				return m;
			} else if (nums[m] < target) {
				left = m + 1;
			} else {
				right = m - 1;
			}
		}
		return left;
	}

	public int maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE;
		int runningSum = 0;
		for (int i = 0; i < nums.length; i++) {
			runningSum += nums[i];
			max = Math.max(runningSum, max);
			if (runningSum < 0) {
				runningSum = 0;
			}
		}
		return max;
	}

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		StringBuilder b1 = new StringBuilder();
		while (l1 != null) {
			b1.append(l1.val);
			l1 = l1.next;
		}
		b1.reverse();
		StringBuilder b2 = new StringBuilder();
		while (l2 != null) {
			b2.append(l2.val);
			l2 = l2.next;
		}
		b2.reverse();
		String res = String.valueOf(Integer.parseInt(b1.toString()) + Integer.parseInt(b2.toString()));
		StringBuilder b = new StringBuilder(res);
		b.reverse();
		ListNode result = null;
		ListNode previous = result;
		for (int k = 0; k < b.length(); k++) {
			ListNode lnode = new ListNode(Integer.parseInt(String.valueOf(b.charAt(k))));
			if (previous != null) {
				previous.next = lnode;
			}
			previous = lnode;
			if (result == null) {
				result = lnode;
			}
		}
		return result;
	}

	/////////////////////////// Leetcode Medium and Hard

	/**
	 * ListNode is defined by leetcode engine and cannot be changed.
	 *
	 * @param l1
	 * @param l2
	 * @return ListNode
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode p, dummy = new ListNode(0);
		p = dummy;
		while (l1 != null || l2 != null || carry != 0) {
			if (l1 != null) {
				carry += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				carry += l2.val;
				l2 = l2.next;
			}
			p.next = new ListNode(carry % 10);
			carry /= 10;
			p = p.next;
		}
		return dummy.next;
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int min1 = nums1[0];
		int min2 = nums2[0];
		int size = nums1.length + nums2.length;

		int overlapStart1 = Arrays.binarySearch(nums1, min2);
		int overlapStart2 = Arrays.binarySearch(nums2, min1);

		overlapStart1 = overlapStart1 < 0 ? -(overlapStart1 + 1) : overlapStart1;
		overlapStart2 = overlapStart2 < 0 ? -(overlapStart2 + 1) : overlapStart2;
		if (overlapStart1 == nums1.length) {
			if (size % 2 != 0) {
				return getCombinedElement(nums1, nums2, size / 2);
			} else {
				return (getCombinedElement(nums1, nums2, size / 2) + getCombinedElement(nums1, nums2, size / 2 + 1))
						/ 2;
			}
		} else if (overlapStart1 == 0) {
			if (size % 2 != 0) {
				return getCombinedElement(nums2, nums1, size / 2);
			} else {
				return (getCombinedElement(nums2, nums1, size / 2) + getCombinedElement(nums2, nums1, size / 2 + 1))
						/ 2;
			}
		}

		int k = size / 2 - Math.max(0, overlapStart1 + overlapStart2 - 2);

		return findMedian(nums1, nums2, overlapStart1, overlapStart2, k, size);
	}

	private int getCombinedElement(int[] nums1, int[] nums2, int index) {
		int size = nums1.length + nums2.length;
		if (nums1.length <= size / 2) {
			return nums2[-nums1.length + size / 2];
		} else {
			return nums1[size / 2];
		}
	}

	private double findMedian(int[] nums1, int[] nums2, int overlapStart1, int overlapStart2, int k, int size) {
		int left1 = overlapStart1;
		int left2 = overlapStart2;
		int checkId = k / 2;
		while (checkId >= 1) {
			int newLeft1 = left1 + checkId;
			int newLeft2 = left2 + checkId;

			if (nums1[newLeft1] < nums2[newLeft2]) {
				left1 = newLeft1;
			} else {
				left2 = newLeft2;
			}
			checkId /= 2;
		}
		if (size % 2 == 0) {
			return (nums1[left1] + nums2[left2]) / 2;
		} else {
			if (nums1[left1] < nums2[left2]) {
				return nums1[left1];
			} else {
				return nums2[left2];
			}
		}
	}

	/**
	 * Trivial Bianry Search.
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = (right + left) / 2;
			if (nums[mid] > target) {
				right = mid - 1;
				continue;
			}
			if (nums[mid] < target) {
				left = mid + 1;
				continue;
			}
			return mid;
		}
		return -1;
	}

	public int myAtoi(String str) {
		char[] chars = str.trim().toCharArray();
		int length = chars.length;
		if (length == 0) {
			return 0;
		}
		int start = 0;
		boolean sign = true;
		boolean overflow = false;
		if (chars[0] == '-') {
			sign = false;
			start = 1;
		}
		if (chars[0] == '+') {
			start = 1;
		}
		int sum = 0;
		while (start < length) {
			char c = chars[start++];
			if (c < '0' || c > '9') {
				break;
			}
			int tmp = sum * 10 + (c - '0');
			if (tmp < 0 || (tmp - (c - '0')) / 10 != sum) {
				overflow = true;
				break;
			}
			sum = tmp;
		}
		if (overflow) {
			return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		return sign ? sum : -sum;
	}

	public String reverseWords(String s) {
		String ptr = s.trim();
		StringBuilder b = new StringBuilder();
		List<String> words = new ArrayList<>();
		boolean added = false;
		for (int i = 0; i < ptr.length(); i++) {
			if (ptr.charAt(i) == ' ') {
				words.add(b.toString());
				b = new StringBuilder();
				if (!added) {
					words.add(" ");
					added = true;
				}
			} else {
				b.append(ptr.charAt(i));
				added = false;
			}
		}
		words.add(b.toString());
		StringBuilder res = new StringBuilder();
		for (int k = words.size() - 1; k >= 0; k--) {
			res.append(words.get(k));
		}
		return res.toString();
	}

	private int getNumberLength(int numb) {
		if (numb == 0) {
			return 1;
		}
		int res = 0;
		int ptr = numb;
		while (ptr != 0) {
			res++;
			ptr = ptr / 10;
		}
		return res;
	}

	private int getMltiplier(int a, int b) {
		if (a == 0) {
			return 0;
		}
		int aptr = a;
		int bptr = b;
		int i = 1;
		int diff;
		do {
			diff = aptr - bptr * i++;
		} while (diff >= bptr);
		return i - 1;
	}

	private int getFirstDigit(int numb) {
		int k = getNumberLength(numb);
		int p = 1;
		for (int i = 0; i < k - 1; i++) {
			p = p * 10;
		}
		return numb / p;
	}

	private int getTail(int numb) {
		int k = getNumberLength(numb);
		int p = 1;
		for (int i = 0; i < k - 1; i++) {
			p = p * 10;
		}
		return numb % p;
	}

	public int divide(int dividend, int divisor) {
		if (divisor > dividend) {
			return 0;
		}
		if (dividend == divisor) {
			return 1;
		}
		int res = 0;
		int ptr = dividend;
		int divisorLength = getNumberLength(divisor);
		int dividendLength = getNumberLength(dividend);
		int k = 1;
		for (int i = 0; i < dividendLength - divisorLength; i++) {
			k = k * 10;
		}
		int numb;
		int carry;
		int p;
		numb = ptr / k;
		carry = ptr % k;
		boolean lastDigit = false;
		do {
			if (numb < divisor) {
				if (getNumberLength(carry) == 1) {
					lastDigit = true;
				}
				numb = numb * 10 + getFirstDigit(carry);
				carry = getTail(carry);
			}
			p = getMltiplier(numb, divisor);
			res = res * 10 + p;
			numb = numb - divisor * p;
			if (lastDigit) {
				break;
			}
		} while (true);
		return res;
	}

	public int divide2(int dividend, int divisor) {
		boolean isNeg = (dividend < 0) ^ (divisor < 0);
		long bottom = Math.abs((long) divisor);
		long top = Math.abs((long) dividend);

		if (top == 0 || bottom > top)
			return 0;
		if (bottom == 1)
			return (int) (isNeg ? (0 - top) : Math.min(top, Integer.MAX_VALUE));

		int result = 0;
		int multiplier = 1;
		long bigBottom = bottom;

		while (top >= bottom) {
			top -= bigBottom;
			result += multiplier;

			bigBottom += bigBottom;
			multiplier += multiplier;

			if (bigBottom > top) {
				bigBottom = bottom;
				multiplier = 1;
			}
		}
		return isNeg ? (0 - result) : result;
	}

	public int[] matchingStrings(String[] strings, String[] queries) {
		Map<String, Integer> stringsMap = new HashMap<String, Integer>();
		for (int i = 0; i < strings.length; i++) {
			if (stringsMap.containsKey(strings[i])) {
				int newVal = stringsMap.get(strings[i]);
				newVal++;
				stringsMap.put(strings[i], newVal);
			} else {
				stringsMap.put(strings[i], 1);
			}
		}
		int[] result = new int[queries.length];
		for (int j = 0; j < queries.length; j++) {
			if (stringsMap.get(queries[j]) == null) {
				result[j] = 0;
			} else {
				result[j] = stringsMap.get(queries[j]);
			}
		}
		return result;
	}

	private static Long fibExecutor() {
		int t1 = 0;
		int t2 = 1;
		return fibonacciModified(5, t1, t2, new HashMap<Integer, Long>());
	}

	public static Long fibonacciModified(int n, int t1, int t2, Map<Integer, Long> map) {
		if (n == 0) {
			return new Long(t1);
		}
		if (n == 1) {
			return new Long(t2);
		}

		if (map.containsKey(n)) {
			return map.get(n);
		}
		Long fibForN = fibonacciModified(n - 1, t1, t2, map) * fibonacciModified(n - 1, t1, t2, map)
				+ fibonacciModified(n - 2, t1, t2, map);
		map.put(n, fibForN);

		return fibForN;
	}

	public boolean checkSubarraySum(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>() {
			{
				put(0, -1);
			}
		};
		;
		int runningSum = 0;
		for (int i = 0; i < nums.length; i++) {
			runningSum += nums[i];
			if (k != 0)
				runningSum %= k;
			Integer prev = map.get(runningSum);
			if (prev != null) {
				if (i - prev > 1)
					return true;
			} else
				map.put(runningSum, i);
		}
		return false;
	}

	public boolean checkSubarraySum2(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int runningSum = nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (runningSum % k == 0) {
				return true;
			}
			map.put(runningSum % k, i);
			runningSum %= k;
		}
		return false;
	}

	///////////////////////////// SOME BFS/DFS Leetcode problems

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int minDepth = 1;
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		while (!list.isEmpty()) {
			LinkedList<TreeNode> nextLayer = new LinkedList<>();
			while (!list.isEmpty()) {
				TreeNode removed = list.remove();
				if (removed.left == null && removed.right == null) {
					return minDepth;
				}
				if (removed.left != null) {
					nextLayer.add(removed.left);
				}
				if (removed.right != null) {
					nextLayer.add(removed.right);
				}
			}
			minDepth++;
			list.addAll(nextLayer);
		}
		return minDepth;
	}

	public List<Integer> largestValues(TreeNode root) {
		LinkedList<TreeNode> list = new LinkedList<>();
		LinkedList<Integer> maxElementsPerlayer = new LinkedList<>();
		list.add(root);
		while (!list.isEmpty()) {
			LinkedList<TreeNode> nextLayer = new LinkedList<>();
			int currentMax = Integer.MIN_VALUE;
			while (!list.isEmpty()) {
				TreeNode removed = list.remove();
				if (removed.val > currentMax) {
					currentMax = removed.val;
				}
				if (removed.left != null) {
					nextLayer.add(removed.left);
				}
				if (removed.right != null) {
					nextLayer.add(removed.right);
				}
			}
			maxElementsPerlayer.add(currentMax);
			list.addAll(nextLayer);
		}
		return maxElementsPerlayer;
	}

	public int findBottomLeftValue(TreeNode root) {
		TreeNode leftmost = root;
		boolean added = false;
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> nextlayer = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode removed = layers.remove();
				if (removed.left != null) {
					if (!added) {
						leftmost = removed.left;
						added = true;
					}
					nextlayer.add(removed.left);
				}
				if (removed.right != null) {
					if (!added) {
						leftmost = removed.right;
						added = true;
					}
					nextlayer.add(removed.right);
				}
			}
			layers.addAll(nextlayer);
			added = false;
		}
		return leftmost.val;
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		LinkedList<List<Integer>> result = new LinkedList<>();
		if (root == null) {
			return result;
		}
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		boolean flag = true;
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> nextLayer = new LinkedList<>();
			LinkedList<Integer> nextLayerToAdd = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode node;
				if (flag) {
					node = layers.remove();
					nextLayerToAdd.add(node.val);
					if (node.left != null) {
						nextLayer.addLast(node.left);
					}
					if (node.right != null) {
						nextLayer.addLast(node.right);
					}
				} else {
					node = layers.removeLast();
					nextLayerToAdd.add(node.val);
					if (node.right != null) {
						nextLayer.addFirst(node.right);
					}
					if (node.left != null) {
						nextLayer.addFirst(node.left);
					}
				}
			}
			flag = !flag;
			result.add(nextLayerToAdd);
			layers.addAll(nextLayer);
		}
		return result;
	}

	private int calculateWordsDiff(String a, String b) {
		int res = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				res++;
			}
		}
		return res;
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> result = new LinkedList<List<String>>();
		List<List<String>> layers = new LinkedList<List<String>>();
		LinkedList<String> x = new LinkedList<String>();
		x.add(beginWord);
		layers.add(x);
		while (!layers.isEmpty()) {
			List<String> layer = layers.remove(0);
			result.add(layer);
			for (int i = 0; i < layer.size(); i++) {
				List<String> next = new LinkedList<>();
				for (int j = 0; j < wordList.size(); j++) {
					if (calculateWordsDiff(layer.get(i), wordList.get(j)) == 1) {
						next.add(wordList.remove(j));
					}
				}
				layers.add(next);
			}
		}
		return result;
	}

	// TODO not done:
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		int i = 0;
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> nextLayer = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode layer = layers.remove();
				if (map.containsKey(i)) {
					List<Integer> list = map.get(i);
					list.add(layer.val);
				} else {
					List<Integer> list = new LinkedList<>();
					list.add(layer.val);
					map.put(i, list);
				}
				if (layer.left != null) {
					nextLayer.add(layer.left);
				}
				if (layer.right != null) {
					nextLayer.add(layer.right);
				}
			}
			i++;
			layers.addAll(nextLayer);
		}
		return null;
	}

	private boolean isPalindrom(String str) {
		for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	public String longestPalindrome(String s) {
		String lp = "";
		StringBuilder runningWord = new StringBuilder();
		for (int j = 0; j < s.length(); j++) {
			for (int i = j; i < s.length(); i++) {
				runningWord.append(s.charAt(i));
				if (isPalindrom(runningWord.toString())) {
					lp = (runningWord.length() > lp.length()) ? runningWord.toString() : lp;
				}
			}
			runningWord = new StringBuilder();
		}
		return lp;
	}

	public String longestPalindromeDPSolution(String s) {
		boolean[][] dp = new boolean[s.length()][s.length()];
		int maxStart = 0;
		int maxEnd = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length() - i; j++) {
				if (i == 0 || (s.charAt(j) == s.charAt(j + i) && (i == 1 || dp[j + 1][j + i - 1]))) {
					dp[j][j + i] = true;
					maxStart = j;
					maxEnd = j + i;
				}
			}
		}
		return s.substring(maxStart, maxEnd + 1);
	}

	private boolean compare(String a, String b) {
		char[] aArr = a.toLowerCase().toCharArray(), bArr = b.toLowerCase().toCharArray();
		if (aArr.length != bArr.length)
			return false;
		int[] counts = new int[26]; // An array to hold the number of occurrences of each character
		for (int i = 0; i < aArr.length; i++) {
			counts[aArr[i] - 97]++; // Increment the count of the character at i
			counts[bArr[i] - 97]--; // Decrement the count of the character at i
		}
		// If the strings are anagrams, the counts array will be full of zeros
		for (int i = 0; i < 26; i++)
			if (counts[i] != 0)
				return false;
		return true;
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new LinkedList<List<String>>();
		if (strs.length == 0) {
			return result;
		}
		boolean[] dp = new boolean[strs.length];
		for (int i = 0; i < strs.length; i++) {
			LinkedList<String> words = new LinkedList<>();
			if (dp[i]) {
				continue;
			}
			words.add(strs[i]);
			for (int j = i + 1; j < strs.length; j++) {
				if (dp[j]) {
					continue;
				}
				if (compare(strs[i], strs[j])) {
					dp[j] = true;
					words.add(strs[j]);
				}
			}
			result.add(words);
		}
		return result;
	}

	public List<List<String>> groupAnagrams2(String[] strs) {
		int index = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		List<List<String>> result = new ArrayList<>();
		for (String str : strs) {
			String strSorted = toSortString(str);
			if (map.get(strSorted) == null) {
				map.put(strSorted, index);
				List<String> ar = new ArrayList<String>();
				ar.add(str);
				result.add(ar);
				index++;
			} else {
				List<String> ar = result.get(map.get(strSorted));
				ar.add(str);
			}
		}
		return result;
	}

	public String toSortString(String s) {
		String result = "";
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		for (char c : arr) {
			result += c;
		}
		return result;
	}

	private static Map<Character, String> constructMap(String str) {
		Map<Character, String> strMap = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (!strMap.containsKey(str.charAt(i))) {
				strMap.put(str.charAt(i), String.valueOf(i));
			} else {
				StringBuilder ptr = new StringBuilder(strMap.get(str.charAt(i)));
				ptr.append(String.valueOf(i));
				strMap.put(str.charAt(i), ptr.toString());
			}
		}
		return strMap;
	}

	public static boolean areAnagrams(String w1, String w2) {
		if (w1.length() != w2.length()) {
			return false;
		}
		Set<Character> w1Set = new HashSet<>();
		Set<Character> w2Set = new HashSet<>();
		for (int i = 0; i < w1.length(); i++) {
			w1Set.add(w1.charAt(i));
			w2Set.add(w2.charAt(i));
		}
		if (w1Set.size() != w2Set.size()) {
			return false;
		}
		Iterator<Character> it = w1Set.iterator();
		while (it.hasNext()) {
			char c = it.next();
			if (!w2Set.contains(c)) {
				return false;
			}
		}
		return true;
	}

	private char[] escapeWhiteSpaces(char[] arr) {
		// Example "asd asd" -> "asd%20asd".
		int numbOfWhiteSpaces = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ' ') {
				numbOfWhiteSpaces++;
			}
		}
		char[] newArr = new char[arr.length + numbOfWhiteSpaces * 3 - numbOfWhiteSpaces];
		for (int i = 0, j = 0; i < arr.length && j < newArr.length; i++) {
			if (arr[i] == ' ') {
				newArr[j++] = '%';
				newArr[j++] = '2';
				newArr[j++] = '0';
			} else {
				newArr[j++] = arr[i];
			}
		}
		return newArr;
	}

	public static String findLongestCommonSubstring(String str1, String str2) { // O (n^2)
		Map<Character, String> str2Map = constructMap(str2);
		String max = "";
		String current = "";
		for (int i = 0; i < str1.length(); i++) { // O(n) where n = length of str1.
			String indexes = null;
			if (str2Map.containsKey(str1.charAt(i))) {
				indexes = str2Map.get(str1.charAt(i));
				current = String.valueOf(str1.charAt(i));
				if (max.length() <= current.length()) {
					max = current;
				}
				String prevCurrent = current;
				for (int k = 0; k < indexes.length(); k++) { // O (Number of duplicate items in str2) = const
					for (int j = Integer.parseInt(String.valueOf(indexes.charAt(k))) + 1, p = i + 1; j < str2.length()
							&& p < str1.length(); j++, p++) { // O (n - m) where m = already checked indexes.
						if (str1.charAt(p) == str2.charAt(j)) {
							current = current + String.valueOf(str1).charAt(p);
							if (max.length() <= current.length()) {
								max = current;
							}
						} else {
							break;
						}
					}
					current = prevCurrent;
				}
			}
			current = "";
		}
		return max;
	}

	/////////////////////////////////////////////////////////

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int i = 0;
		int diff = Integer.MAX_VALUE;
		int res = 0;
		while (i < nums.length - 2) {
			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				int tmp = nums[i] + nums[l] + nums[r];
				int absDiff = Math.abs(target - tmp);
				if (absDiff < diff) {
					diff = absDiff;
					res = tmp;
				}
				if (tmp > target) {
					r--;
				} else if (tmp < target) {
					l++;
				} else {
					return tmp;
				}
			}
			i++;
		}
		return res;
	}

	/**
	 * Given an array nums of n integers, are there elements a, b, c in nums such
	 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero.
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		Set<List<Integer>> set = new HashSet<>(); // cheat
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int left = j + 1;
				int right = nums.length - 1;
				int target = -(nums[i] + nums[j]);
				while (left <= right) {
					int mid = left + (right - left) / 2;
					if (nums[mid] == target) {
						set.add(Arrays.asList(nums[i], nums[mid], nums[j]));
						break;
					}
					if (nums[mid] > target) {
						right = mid - 1;
					}
					if (nums[mid] < target) {
						left = mid + 1;
					}
				}
			}
		}
		return new ArrayList<>(set);
	}

	public static int findMaxLength(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0)
				nums[i] = -1;
		}
		Map<Integer, Integer> sumToIndex = new HashMap<>();
		sumToIndex.put(0, -1);
		int sum = 0, max = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sumToIndex.containsKey(sum)) {
				max = Math.max(max, i - sumToIndex.get(sum));
			} else {
				sumToIndex.put(sum, i);
			}
		}

		return max;
	}

	// Beats 99.87% of java solutions xd.
	public static boolean backspaceCompare(String S, String T) {
		int s = 0;
		int t = 0;
		for (int i = S.length() - 1, j = T.length() - 1; i >= 0 || j >= 0;) {
			char char1;
			char char2;
			if (i < 0) {
				char1 = ' ';
			} else {
				char1 = S.charAt(i);
			}
			if (j < 0) {
				char2 = ' ';
			} else {
				char2 = T.charAt(j);
			}
			if (char1 == '#') {
				s++;
				i--;
				continue;
			}
			if (char2 == '#') {
				t++;
				j--;
				continue;
			}
			if (s > 0) {
				s--;
				i--;
				continue;
			}
			if (t > 0) {
				t--;
				j--;
				continue;
			}
			if (char1 != char2) {
				return false;
			}
			i--;
			j--;
		}
		return true;
	}

	public static int lengthOfLastWord(String s) {
		int length = 0;
		boolean flag = false;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) != ' ') {
				length++;
				flag = true;
			} else {
				if (flag) {
					break;
				}
			}
		}
		return length;
	}

	/**
	 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
	 * left; TreeNode right; TreeNode(int x) { val = x; } }
	 */
	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(nums[0]);
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		int i = 1;
		while (!list.isEmpty()) {
			LinkedList<TreeNode> layer = new LinkedList<>();
			while (!list.isEmpty()) {
				TreeNode node = list.remove();
				if (i >= nums.length) {
					return root;
				} else {
					TreeNode left = new TreeNode(nums[i++]);
					node.left = left;
					layer.add(left);
				}
				if (i >= nums.length) {
					return root;
				} else {
					TreeNode right = new TreeNode(nums[i++]);
					node.right = right;
					layer.add(right);
				}
			}
			list.addAll(layer);
		}
		return root;
	}

	public static boolean isAdditiveNumber(String num) {
		String first;
		String second;
		for (int i = 1; i < num.length(); i++) {
			first = num.substring(0, i);
			for (int j = i + 1; j < num.length(); j++) {
				String newfirst = first;
				second = num.substring(i, j);
				if (second.startsWith("0") && second.length() > 1
						|| (newfirst.startsWith("0") && newfirst.length() > 1)) {
					break;
				}
				int k = j;
				while (true) {
					String expectedNext = String.valueOf(Long.parseLong(newfirst) + Long.parseLong(second));
					int idx = k + expectedNext.length();
					if (idx > num.length()) {
						break;
					}
					String actualNext = num.substring(k, idx);
					if (actualNext.equals(expectedNext)) {
						if (num.endsWith(expectedNext)) {
							return true;
						} else {
							newfirst = second;
							second = expectedNext;
							k = k + expectedNext.length();
						}
					} else {
						break;
					}
				}
			}
		}
		return false;
	}

	public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(-1);
		BigInteger sum1 = new BigInteger("0");
		while (l1 != null) {
			sum1 = sum1.multiply(new BigInteger("10")); // BigInteger is immutable.
			sum1 = sum1.add(new BigInteger(String.valueOf(l1.val)));
			l1 = l1.next;
		}
		BigInteger sum2 = new BigInteger("0");
		sum2.multiply(new BigInteger("10"));
		while (l2 != null) {
			sum2 = sum2.multiply(new BigInteger("10"));
			sum2 = sum2.add(new BigInteger(String.valueOf(l2.val)));
			l2 = l2.next;
		}
		String resSum = String.valueOf(sum1.add(sum2));
		ListNode previous = res;
		for (int i = 0; i < resSum.length(); i++) {
			ListNode newNode = new ListNode(Integer.parseInt(String.valueOf(resSum.charAt(i))));
			previous.next = newNode;
			previous = newNode;
		}
		return res.next;
	}

	/**
	 * Method returns List<Double> : avarage per level in BST.
	 *
	 * @param root
	 * @return
	 */
	public List<Double> averageOfLevels(TreeNode root) {
		LinkedList<Double> result = new LinkedList<>();
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> nextLayer = new LinkedList<>();
			int i = 0;
			double sumLayer = 0;
			while (!layers.isEmpty()) {
				i++;
				TreeNode node = layers.remove();
				if (node.left != null) {
					nextLayer.add(node.left);
				}
				if (node.right != null) {
					nextLayer.add(node.right);
				}
				sumLayer += node.val;
			}
			result.add(sumLayer / i);
			layers.addAll(nextLayer);
		}
		return result;
	}

	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		res[0] = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] *= right;
			right *= nums[i];
		}
		return res;
	}

	/**
	 * Input: candidates = [2,3,5], target = 8, A solution set is: [ [2,2,2,2],
	 * [2,3,3], [3,5] ]
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		int i = 0, size = candidates.length, sum = 0;
		Stack<Integer> combi = new Stack<>(), indices = new Stack<>();
		List<List<Integer>> result = new ArrayList<>();
		while (i < size) {
			if (sum + candidates[i] >= target) {
				if (sum + candidates[i] == target) {
					combi.push(candidates[i]);
					result.add(new ArrayList<>(combi));
					combi.pop();
				}
				// indices stack and combination stack should have the same size all the time
				if (!indices.empty()) {
					sum -= combi.pop();
					i = indices.pop();
					while (i == size - 1 && !indices.empty()) {
						i = indices.pop();
						sum -= combi.pop();

					}
				}
				i++;
			} else {
				combi.push(candidates[i]);
				sum += candidates[i];
				indices.push(i);
			}
		}
		return result;
	}

	public static boolean isPalindrome(String s) {
		if (s == null || s.isEmpty() || s.trim().length() < 1) {
			return true;
		}
		char[] charArray = s.toLowerCase().toCharArray();
		int i = 0;
		int j = charArray.length - 1;

		while (i < j) {
			if (!Character.isLetterOrDigit(charArray[i])) {
				i++;
				continue;
			}
			if (!Character.isLetterOrDigit(charArray[j])) {
				j--;
				continue;
			}
			if (charArray[i] != charArray[j]) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	public static int[] intersect(int[] nums1, int[] nums2) {
		LinkedList<Integer> res = new LinkedList<>();
		HashMap<Integer, Integer> map1 = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();
		for (int i = 0; i < nums1.length; i++) {
			if (map1.containsKey(nums1[i])) {
				Integer ii = map1.get(nums1[i]);
				ii++;
				map1.put(nums1[i], ii);
			} else {
				map1.put(nums1[i], 1);
			}
		}
		for (int i = 0; i < nums2.length; i++) {
			if (map2.containsKey(nums2[i])) {
				Integer ii = map2.get(nums2[i]);
				ii++;
				map2.put(nums2[i], ii);
			} else {
				map2.put(nums2[i], 1);
			}
		}
		for (Entry<Integer, Integer> entry : map1.entrySet()) {
			int counter = 0;
			if (map2.containsKey(entry.getKey())) {
				Integer counter2 = map2.get(entry.getKey());
				counter = Math.min(entry.getValue(), counter2);
				while (counter-- > 0) {
					res.add(entry.getKey());
				}
			}
		}
		int[] arrRes = new int[res.size()];
		for (int i = 0; i < arrRes.length; i++) {
			arrRes[i] = res.get(i);
		}
		return arrRes;
	}

	public static TreeNode deleteNode2(TreeNode root, int key) {
		TreeNode last = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val == key)
				break;
			last = cur;
			if (cur.val > key)
				cur = cur.left;
			else
				cur = cur.right;
		}
		if (cur == null)
			return root;
		if (cur.left == null && cur.right == null) {
			if (last == null)
				return null;
			if (last.left == cur)
				last.left = null;
			else
				last.right = null;
		} else {
			if (cur.right == null) {
				cur.val = cur.left.val;
				cur.right = cur.left.right;
				cur.left = cur.left.left;
			} else {
				if (cur.left == null) {
					cur.val = cur.right.val;
					cur.left = cur.right.left;
					cur.right = cur.right.right;
				} else {
					TreeNode node = cur.right;
					while (node.left != null && node.left.left != null)
						node = node.left;
					if (node.left == null) {
						cur.val = node.val;
						cur.right = node.right;
					} else {
						cur.val = node.left.val;
						node.left = node.left.right;
					}
				}
			}
		}
		return root;
	}

	public static TreeNode deleteNode(TreeNode root, int key) {
		TreeNode prev = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.val == key) {
				break;
			}
			prev = cur;
			if (cur.val < key) {
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}
		if (cur == null) {
			return root;
		}
		if (cur.left == null && cur.right == null) {
			if (prev == null) {
				return null;
			}
			if (prev.left.val == key) {
				prev.left = null;
			} else {
				prev.right = null;
			}
			return root;
		}
		if (cur.left != null && cur.right == null) {
			cur.val = cur.left.val;
			cur.left = cur.left.left;
			cur.right = cur.left.right;
			return root;
		}
		if (cur.left == null && cur.right != null) {
			cur.val = cur.right.val;
			cur.left = cur.right.left;
			cur.right = cur.right.right;
			return root;
		} else {
			TreeNode leftmostPrev = cur.right;
			while (leftmostPrev.left != null && leftmostPrev.left.left != null) {
				leftmostPrev = leftmostPrev.left;
			}
			TreeNode newnode;
			if (leftmostPrev.left == null) {
				newnode = new TreeNode(leftmostPrev.val);
				newnode.left = cur.left;
				newnode.right = leftmostPrev.right;
			} else {
				newnode = new TreeNode(leftmostPrev.left.val);
				newnode.left = cur.left;
				newnode.right = cur.right;
				leftmostPrev.left = null;
			}
			if (prev == null) {
				return newnode;
			}
			if (prev.left.val == key) {
				prev.left = newnode;
			} else {
				prev.right = newnode;
			}
		}
		return root;
	}

	// Some tasks from Amazon found in glassdoor.com.

	// 1, 5, 7, 9 || 3, 9 , 10, 11
	// [1, 3, 5, 7, 9, 9, 10]
	public List<Integer> mergeSortedList(List<Integer> a, List<Integer> b) {
		int aLen = a.size();
		int bLen = b.size();
		List<Integer> result = new LinkedList<>();
		if (a.get(aLen - 1) <= b.get(0)) {
			result.addAll(a);
			result.addAll(b);
			return result;
		} else {
			if (a.get(0) >= b.get(bLen - 1)) {
				result.addAll(b);
				result.addAll(a);
				return result;
			} else {
				int i = 0;
				int j = 0;
				while (i + j <= aLen + bLen - 2 && i < aLen && j < bLen) {
					if (a.get(i) <= b.get(j)) {
						result.add(a.get(i++));
					} else {
						result.add(b.get(j++));
					}
				}
				if (i < aLen) {
					while (i != aLen) {
						result.add(a.get(i++));
					}
				} else {
					if (j < bLen) {
						while (j != bLen) {
							result.add(b.get(j++));
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find characters that intersect in two string arrays - think memory and speed.
	 * 
	 * @param arr1
	 * @param arr2
	 * @return Arrays Intersection.
	 */
	public static Set<Character> findIntersectedCharacters(String[] arr1, String[] arr2) {
		HashSet<Character> set1 = new HashSet<>();
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length(); j++) {
				set1.add(arr1[i].charAt(j));
			}
		}
		Set<Character> res = new HashSet<>();
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[i].length(); j++) {
				char ch = arr2[i].charAt(j);
				if (set1.contains(ch)) {
					res.add(ch);
				}
			}
		}
		return res;
	}

	/**
	 * write a code in any interested language about how to find two numbers in an
	 * array whose sum is equal to a given number.
	 *
	 * @param args
	 */
	public static int[] findTwoNumbWithSumUpToTarget(int[] arr, int target) {
		int[] res = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(target - arr[i])) {
				res[0] = arr[i];
				res[1] = target - arr[i];
				return res;
			} else {
				map.put(arr[i], target - arr[i]);
			}
		}
		return null;
	}

	/**
	 * Add Sum two Strings of integers. Sum can overflow Long. Cannot use
	 * BigInteger() or it makes no-sense.
	 *
	 */
	public static String addStrings2(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		StringBuilder result = new StringBuilder();
		int carry = 0;
		for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0; j--, i--) {
			int next1 = (i >= 0) ? Integer.parseInt(String.valueOf(num1.charAt(i))) : 0;
			int next2 = (j >= 0) ? Integer.parseInt(String.valueOf(num2.charAt(j))) : 0;
			int num = next1 + next2;
			num += carry;
			carry = 0;
			int next;
			if (num > 9) {
				next = num % 10;
				carry = num / 10;
			} else {
				next = num;
			}
			result.append(next);
		}
		if (carry != 0) {
			result.append(carry);
		}
		return result.reverse().toString();
	}

	public int arrayPairSum(int[] nums) {
		Arrays.sort(nums);
		int res = 0;
		for (int i = 0; i < nums.length; i = i + 2) {
			res += nums[i];
		}
		return res;
	}

	/**
	 * Construct Binary Tree (not sorted) - Helper for testing.
	 *
	 * @return
	 */
	public static TreeNode constructBinaryTree() {
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(-2);
		TreeNode node2 = new TreeNode(3);
		// TreeNode node3 = new TreeNode(2);
		root.left = node1;
		root.right = node2;
		// node1.left = node2;
		return root;
	}

	public static TreeNode constructBinaryTree2() {
		TreeNode root = new TreeNode(1);
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(4);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(7);
		TreeNode node5 = new TreeNode(8);
		root.right = node1;
		node1.right = node2;
		root.left = node3;
		node3.right = node4;
		node3.left = node5;
		return root;
	}

	// Verify whether Binary Tree is balanced using DFS.
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		Stack<TreeNode> layers = new Stack<>();
		layers.push(root);
		Map<Integer, Integer> nodeValToDepth = new HashMap<>();
		List<Integer> depths = new LinkedList<>();
		int d = 0;
		while (!layers.isEmpty()) {
			TreeNode node = layers.pop();
			d++;
			nodeValToDepth.put(node.val, d);
			if (node.left == null && node.right == null) {
				depths.add(d);
				if (!layers.isEmpty()) {
					// Because we increase 'd' before adding the mapping.
					d = nodeValToDepth.get(layers.peek().val) - 1;
					continue;
				} else {
					break;
				}
			}
			if (node.left != null) {
				layers.push(node.left);
				nodeValToDepth.put(node.left.val, d + 1);
			}
			if (node.right != null) {
				layers.push(node.right);
				nodeValToDepth.put(node.right.val, d + 1);
			}
		}
		if (depths.size() == 1) {
			// Tree could be like list, so we verify this using the map values.
			for (Integer depth : nodeValToDepth.values()) {
				if (depth > 2) {
					// This mean we have list with more than 2 nodes, which is unbalanced.
					return false;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < depths.size(); i++) {
			if (depths.get(i) >= max) {
				max = depths.get(i);
			}
			if (depths.get(i) <= min) {
				min = depths.get(i);
			}
		}
		if (max - min > 1) {
			return false;
		}
		return true;
	}

	// Using BFS
	public static boolean isBalanced2(TreeNode root) {
		if (root == null) {
			return true;
		}
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		int min = Integer.MAX_VALUE;
		int max = 0;
		int counter = 0;
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> layer = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode node = layers.remove();
				if (node.left == null || node.right == null) {
					if (min > counter) {
						min = counter;
					}
					max = counter;
				}
				if (node.left != null) {
					layer.add(node.left);
				}
				if (node.right != null) {
					layer.add(node.right);
				}
			}
			layers.addAll(layer);
			counter++;
		}
		if (max - min >= 2) {
			return false;
		}
		return true;
	}

	// Avoiding toxic iteration approaches:
	public static boolean isBalanced3(TreeNode root) {
		if (root == null) {
			return true;
		}
		int left = getDepth(root.left);
		int right = getDepth(root.right);
		if (left == -1 || right == -1) {
			return false;
		}
		return Math.abs(left - right) <= 1;
	}

	public static int getDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int l = getDepth(root.left);
		int r = getDepth(root.right);
		if (l == -1 || r == -1) {
			return -1;
		}
		if (Math.abs(l - r) > 1) {
			return -1;
		}
		return 1 + Math.max(l, r);
	}

	public static boolean isPrime(int numb) {
		for (int i = 2; i * i <= numb; i++) {
			if (numb % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static int countPrimes(int n) {
		if (n < 2) {
			return 0;
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime(i)) {
				count++;
			}
		}
		return count;
	}

	// 1 1 2 1 8 9 2 za k = 2 -> 1, 2
	// Desired complexity is O(n).
	public static List<Integer> topKFrequent1(int[] nums, int k) {
		if (k > nums.length) {
			return null;
		}
		Map<Integer, Integer> numToFrequancy = new HashMap<Integer, Integer>();
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			Integer freq = numToFrequancy.get(nums[i]);
			if (freq == null) {
				freq = 1;
			} else {
				freq++;
			}
			numToFrequancy.put(nums[i], freq);
		}
		List<Integer>[] bucket = new List[nums.length + 1];
		for (int n : numToFrequancy.keySet()) {
			int freq = numToFrequancy.get(n);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(n);
		}
		for (int i = bucket.length - 1; i > 0 && k > 0; --i) {
			if (bucket[i] != null) {
				List<Integer> list = bucket[i];
				res.addAll(list);
				k -= list.size();
			}
		}

		return res;
	}

	/**
	 * Given a non-empty list of words, return the k most frequent elements.
	 *
	 * Your answer should be sorted by frequency from highest to lowest. If two
	 * words have the same frequency, then the word with the lower alphabetical
	 * order comes first. Note: You may assume k is always valid, 1 ≤ k ≤ number of
	 * unique elements. Input words contain only lowercase letters. Follow up: Try
	 * to solve it in O(n log k) time and O(n) extra space.
	 *
	 */
	public static List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);
		}
		List<String>[] bucket = new List[words.length];
		for (String word : map.keySet()) {
			int freq = map.get(word);
			if (bucket[freq] == null) {
				bucket[freq] = new ArrayList<>();
			}
			bucket[freq].add(word);
		}
		List<String> res = new ArrayList<>();
		for (int i = bucket.length - 1; res.size() < k; i--) {
			List<String> b = bucket[i];
			if (b == null) {
				continue;
			}
			Collections.sort(b);
			if (b.size() + res.size() > k) {
				int boundary = k - res.size();
				int p = 0;
				while (p < boundary) {
					res.add(b.get(p++));
				}
				break;
			} else {
				res.addAll(b);
			}
		}
		return res;
	}

	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[k - 1];
	}

	/**
	 * Replacing words usign prefix tree.
	 *
	 */
	public static String replaceWords(List<String> dict, String sentence) {
		Trie trie = new Trie();
		for (String word : dict) {
			trie.insert(word);
		}
		String[] wordsFromSentence = sentence.split("\\s+");
		for (int i = 0; i < wordsFromSentence.length; i++) {
			for (int j = 1; j < wordsFromSentence[i].length(); j++) {
				String rootWord = wordsFromSentence[i].substring(0, j);
				if (trie.find(rootWord)) {
					wordsFromSentence[i] = rootWord;
					break;
				}
			}
		}
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < wordsFromSentence.length;) {
			res.append(wordsFromSentence[i]);
			i++;
			if (i < wordsFromSentence.length) {
				res.append(" ");
			}
		}
		return res.toString();
	}

	/**
	 * Basic calculator impl.
	 */
	public int calculate(String s) {
		int prev = 0, ans = 0;
		char op = '+';
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ')
				continue;
			if (Character.isDigit(c)) {
				int num = c - '0';
				while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
					num = num * 10 + (s.charAt(i + 1) - '0');
					i++;
				}
				if (op == '+') {
					ans += num;
					prev = num;
				} else if (op == '-') {
					ans -= num;
					prev = -num;
				} else if (op == '*') {
					ans = ans - prev + prev * num;
					prev = prev * num;
				} else {
					ans = ans - prev + prev / num;
					prev = prev / num;
				}
			} else {
				op = c;
			}
		}
		return ans;
	}

	/**
	 * Given a non-empty array of non-negative integers nums, the degree of this
	 * array is defined as the maximum frequency of any one of its elements. Your
	 * task is to find the smallest possible length of a (contiguous) subarray of
	 * nums, that has the same degree as nums.
	 */
	public static int findShortestSubArray(int[] nums) {
		Map<Integer, List<Integer>> numToDistance = new HashMap<>();
		Map<Integer, Integer> numToFreq = new HashMap<>();
		// TODO do in 1 traverse instead of 2.
		for (int i = 0; i < nums.length; i++) {
			List<Integer> idx = numToDistance.get(nums[i]);
			if (idx != null) {
				idx.add(i + 1);
				numToDistance.put(nums[i], idx);
			} else {
				idx = new ArrayList<>();
				idx.add(i + 1);
				numToDistance.put(nums[i], idx);
			}
		}
		for (int i = 0; i < nums.length; i++) {
			Integer freq = numToFreq.get(nums[i]);
			if (freq != null) {
				int newFreq = freq + 1;
				numToFreq.put(nums[i], newFreq);
			} else {
				numToFreq.put(nums[i], 1);
			}
		}
		int maxFreqNumber = 0;
		int minDist = 0;
		for (Map.Entry<Integer, Integer> entry : numToFreq.entrySet()) {
			if (entry.getValue() > maxFreqNumber) {
				maxFreqNumber = entry.getValue();
				List<Integer> list = numToDistance.get(entry.getKey());
				minDist = list.get(list.size() - 1) - list.get(0);
				continue;
			}
			if (entry.getValue() == maxFreqNumber) {
				int dist;
				List<Integer> list = numToDistance.get(entry.getKey());
				dist = list.get(list.size() - 1) - list.get(0);
				if (dist < minDist) {
					minDist = dist;
					maxFreqNumber = entry.getValue();
				}
			}
		}
		return minDist + 1;
	}

	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (!set.add(nums[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given an array of integers and an integer k, find out whether there are two
	 * distinct indices i and j in the array such that nums[i] = nums[j] and the
	 * absolute difference between i and j is at most k. Example: new int[] { 1, 2,
	 * 3, 1, 2, 3 }, 2 -> false;
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> helper = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (!helper.add(nums[i])) {
				return true;
			}
			if (helper.size() > k) {
				helper.remove(nums[i - k]);
			}
		}
		return false;
	}

	/**
	 * Given an array of integers, find out whether there are two distinct indices i
	 * and j in the array such that the absolute difference between nums[i] and
	 * nums[j] is at most t and the absolute difference between i and j is at most
	 * k.
	 */
	public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		Set<Integer> helper = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			Iterator<Integer> iter = helper.iterator();
			while (iter.hasNext()) {
				long next = iter.next();
				long calc = (next <= num) ? num - next : next - num;
				if (calc <= t) {
					return true;
				}
			}
			helper.add(num);
			if (helper.size() > k) {
				helper.remove(nums[i - k]);
			}
		}
		return false;
	}

	/**
	 * Same as above using TreeSet - faster.
	 */
	public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
		TreeSet<Long> helper = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			long numb = (long) nums[i];
			Long floor = helper.floor(numb);
			Long ceil = helper.ceiling(numb);
			if ((floor != null && numb - floor <= t) || (ceil != null && ceil - numb <= t)) {
				return true;
			}
			helper.add(numb);
			if (i >= k) {
				helper.remove((long) nums[i - k]);
			}
		}
		return false;
	}

	public static int firstUniqChar(String s) {
		Map<Character, Integer> charToIdx = new HashMap<>();
		Integer ii = new Integer(-1);
		for (int i = 0; i < s.length(); i++) {
			if (charToIdx.get(s.charAt(i)) != ii && charToIdx.putIfAbsent(s.charAt(i), i) != null) {
				charToIdx.put(s.charAt(i), -1);
			}
		}
		int minIdx = -1;
		for (Map.Entry<Character, Integer> entry : charToIdx.entrySet()) {
			if (entry.getValue() != -1) {
				if (entry.getValue() < minIdx || minIdx < 0) {
					minIdx = entry.getValue();
				}
			}
		}
		return minIdx;
	}

	// Fastest solution.
	public int firstUniqChar2(String s) {
		char[] chArr = s.toCharArray();
		int[] keep = new int[26];
		for (char ch : chArr) {
			keep[ch - 97]++;
		}
		for (int i = 0; i < chArr.length; i++) {
			if (keep[chArr[i] - 97] <= 1)
				return i;
		}
		return -1;
	}

	private static Map.Entry<Character, Integer> getBiggestByVal(Map<Character, Integer> map) {
		Map.Entry<Character, Integer> e = new Map.Entry<Character, Integer>() {
			@Override
			public Character getKey() {
				return null;
			}

			@Override
			public Integer getValue() {
				return null;
			}

			@Override
			public Integer setValue(Integer value) {
				return null;
			}
		};
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			if (e.getValue() == null || e.getValue() < entry.getValue()) {
				e = entry;
			}
		}
		return e;
	}

	public static String frequencySort(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			Integer ii = map.get(s.charAt(i));
			if (ii == null) {
				ii = new Integer(1);
			} else {
				ii++;
			}
			map.put(s.charAt(i), ii);
		}
		StringBuilder str = new StringBuilder();
		while (!map.isEmpty()) {
			Map.Entry<Character, Integer> e = getBiggestByVal(map);
			for (int j = e.getValue(); j > 0; j--) {
				str.append(e.getKey());
			}
			map.remove(e.getKey());
		}
		return str.toString();
	}

	public String frequencySort2(String s) {
		int[] freq = new int[256];
		for (char c : s.toCharArray()) {
			freq[c]++;
		}
		ArrayList<Integer>[] buckets = new ArrayList[s.length() + 1];
		for (int i = 0; i < freq.length; i++) {
			int frequency = freq[i];
			if (frequency > 0) {
			}
			List<Integer> bucket = buckets[frequency];
			if (bucket == null) {
				buckets[frequency] = new ArrayList<>();
			}
			buckets[frequency].add(i);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = buckets.length - 1; i >= 0; i--) {
			List<Integer> chars = buckets[i];
			if (chars == null || chars.size() == 0) {
				continue;
			}
			int frequency = i;
			for (int ascii : chars) {
				char c = (char) ascii;
				int charsLeft = frequency;
				while (charsLeft > 0) {
					sb.append(c);
					charsLeft--;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Beats 99.99 java solutions.
	 */
	public static int peakIndexInMountainArray(int[] A) {
		int mid = A.length / 2;
		while (mid >= 0 && mid < A.length) {
			if (A[mid - 1] < A[mid] && A[mid] > A[mid + 1]) {
				return mid;
			}
			if (A[mid - 1] > A[mid]) {
				mid--;
				continue;
			}
			if (A[mid + 1] > A[mid]) {
				mid++;
				continue;
			}
		}
		return 0;
	}

	/**
	 * Given an array of integers nums sorted in ascending order, find the starting
	 * and ending position of a given target value. Your algorithm's runtime
	 * complexity must be in the order of O(log n). // My comment: not really
	 * possible. If the target is not found in the array, return [-1, -1].
	 */
	public static int[] searchRange(int[] nums, int target) {
		int[] res = new int[] { -1, -1 };
		if (nums.length == 1 && nums[0] == target) {
			res[0] = 0;
			res[1] = 0;
		}
		if (nums.length > 1) {
			int left = 0;
			int right = nums.length - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (nums[mid] == target) {
					int low = mid;
					int high = mid;
					while (true) {
						if (low > 0 && nums[low - 1] == target) {
							low--;
						} else {
							break;
						}
					}
					while (true) {
						if (high <= nums.length - 2 && nums[high + 1] == target) {
							high++;
						} else {
							break;
						}
					}
					res[0] = low;
					res[1] = high;
					break;
				}
				if (nums[mid] > target) {
					right = mid - 1;
					continue;
				}
				if (nums[mid] < target) {
					left = mid + 1;
				}
			}
		}
		return res;
	}

	private int guess(int mid) {
		// TODO Auto-generated method stub
		// Part of leetcode engine. This is only for compilation.
		return 0;
	}

	/**
	 * Very simple just BS. 'guess' function is from leetcode engine.
	 */
	public int guessNumber(int n) {
		int left = 0;
		int right = n;
		while (left <= right) {
			int mid = (int) (((long) left + (long) right) / 2L);
			int guess = guess(mid);
			if (guess == 0) {
				return mid;
			}
			if (guess == -1) {
				right = mid - 1;
				continue;
			}
			if (guess == 1) {
				left = mid + 1;
			}
			System.out.println(mid);
		}
		return -1;
	}

	/**
	 * Given an unsorted array of integers, find the length of longest increasing
	 * subsequence. Note: There may be more than one LIS combination, it is only
	 * necessary for you to return the length. Your algorithm should run in O(n2)
	 * complexity. Follow up: Could you improve it to O(n log n) time complexity?
	 */
	public static int lengthOfLIS(int[] nums) {
		int[] tails = new int[nums.length];
		int size = 0;
		for (int num : nums) {
			int i = 0;
			int j = size;
			while (i != j) {
				int m = (i + j) / 2;
				if (tails[m] < num) {
					i = m + 1;
				} else {
					j = m;
				}
			}
			tails[i] = num;
			if (i == size) {
				++size;
			}
		}
		return size;
	}

	/**
	 * Given a linked list, remove the n-th node from the end of list and return its
	 * head.
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ArrayList<ListNode> list = new ArrayList<>();
		ListNode ptr = head;
		while (ptr != null) {
			list.add(ptr);
			ptr = ptr.next;
		}
		int removeIdx = list.size() - n;
		if (removeIdx - 1 < 0) {
			return head.next;
		}
		ListNode previous = list.get(removeIdx - 1);
		ListNode removed = list.get(removeIdx);
		previous.next = removed.next;
		removed = null;
		return head;
	}

	public static int findPairs(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				result++;
				set.remove(nums[i]);
			}
			set.add(nums[i] - Math.abs(k));
			set.add(k + nums[i]);
		}
		return result;
	}

	public static int arrangeCoins(int n) {
		int left = 1;
		int right = n;
		while (left <= right) {
			int mid = (left + right) / 2;
			int midSum = (mid * (mid + 1)) / 2;
			int next = mid + 1;
			if (midSum <= n && (next * (next + 1)) / 2 > n) {
				return mid;
			}
			if (midSum > n) {
				right = mid - 1;
				continue;
			}
			if (midSum < n) {
				left = mid + 1;
			}
		}
		return 0;
	}

	public int singleNumber(int[] nums) {
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) {
			res ^= nums[i];
		}
		return res;
	}

	/**
	 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j,
	 * k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero. To make problem
	 * a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All
	 * integers are in the range of -228 to 228 - 1 and the result is guaranteed to
	 * be at most 231 - 1.
	 */
	public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		// O(n^2)
		Map<Integer, Integer> ab = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				int sum = A[i] + B[j];
				Integer count = ab.get(sum);
				if (count != null) {
					ab.put(sum, count + 1);
				} else {
					ab.put(sum, 1);
				}
			}
		}
		int result = 0;
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				int sum = C[i] + D[j];
				Integer count = ab.get(-sum);
				if (count != null) {
					result += count;
				}
			}
		}
		return result;
	}

	/**
	 * Given an array nums of n integers and an integer target, are there elements
	 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
	 * quadruplets in the array which gives the sum of target.
	 */
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Map<Integer, Integer[]> sumToDigits1 = new HashMap<>();
		Set<List<Integer>> res = new HashSet<>();
		List<List<Integer>> expectedRes = new ArrayList<>();
		int len1 = nums.length;
		for (int i = 0; i < len1; i++) {
			for (int j = i + 1; j < len1; j++) {
				int sum = nums[i] + nums[j];
				Integer[] ii = new Integer[] { nums[i], nums[j] };
				sumToDigits1.put(sum, ii);
			}
		}
		int len2 = nums.length;
		for (int i = 0; i < len2; i++) {
			for (int j = i + 1; j < len2; j++) {
				int sum = nums[i] + nums[j];
				Integer[] val = sumToDigits1.get(target - sum);
				if (val != null) {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(val[0]);
					list.add(val[1]);
					list.add(nums[i]);
					list.add(nums[j]);
					res.add(list);
				}
			}
		}
		expectedRes.addAll(res);
		return expectedRes;
	}

	/**
	 * Reverse a String.
	 */
	public static String reverseString(String s) {
		char[] arr = s.toCharArray();
		StringBuilder res = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; i--) {
			res.append(arr[i]);
		}
		return res.toString();
	}

	/**
	 * Given a string, find the length of the longest substring without repeating
	 * characters. Examples: Given "abcabcbb", the answer is "abc", which the length
	 * is 3. Given "bbbbb", the answer is "b", with the length of 1. Given "pwwkew",
	 * the answer is "wke", with the length of 3. Note that the answer must be a
	 * substring, "pwke" is a subsequence and not a substring.
	 */
	public static int lengthOfLongestSubstring(String s) {
		char[] arr = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		int curr = 0;
		int startIdx = 0;
		for (int i = 0; i < arr.length; i++) {
			Integer index = map.get(arr[i]);
			if (index == null || (index != null && index < startIdx)) {
				map.put(arr[i], i);
				curr++;
				if (curr > max) {
					max = curr;
				}
			} else {
				int idx = map.get(arr[i]);
				startIdx = idx;
				curr = i - idx;
				map.put(arr[i], i);
			}
		}
		return max;
	}

	/**
	 * Leetcode's RandomListNode datastructure impl.
	 */
	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};

	/**
	 * A linked list is given such that each node contains an additional random
	 * pointer which could point to any node in the list or null. Return a deep copy
	 * of the list.
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode node = head;
		while (node != null) {
			map.put(node, new RandomListNode(node.label));
			node = node.next;
		}
		node = head;
		while (node != null) {
			map.get(node).next = map.get(node.next);
			map.get(node).random = map.get(node.random);
			node = node.next;
		}
		return map.get(head);
	}

	/**
	 * Given a list of sorted characters letters containing only lowercase letters,
	 * and given a target letter target, find the smallest element in the list that
	 * is larger than the given target. Letters also wrap around. For example, if
	 * the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
	 */
	public static char nextGreatestLetter(char[] letters, char target) {
		int left = 0;
		int right = letters.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (letters[mid] <= target) {
				left = mid + 1;
				continue;
			}
			if (letters[mid] > target) {
				right = mid - 1;
			}
		}
		int idx = (left > right) ? left : right;
		if (idx >= letters.length) {
			idx = 0;
		} else {
			if (idx < 0) {
				idx = letters.length - 1;
			}
		}
		return letters[idx];
	}

	/**
	 * Finds the largest common substring using DP.
	 */
	public static Set<String> longestCommonSubstrings(String s, String t) {
		int[][] table = new int[s.length()][t.length()];
		int longest = 0;
		Set<String> result = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				if (s.charAt(i) != t.charAt(j)) {
					continue;
				}
				table[i][j] = (i == 0 || j == 0) ? 1 : 1 + table[i - 1][j - 1];
				if (table[i][j] > longest) {
					longest = table[i][j];
					result.clear();
				}
				if (table[i][j] == longest) {
					result.add(s.substring(i - longest + 1, i + 1));
				}
			}
		}
		return result;
	}

	/**
	 * Given a string s and a string t, check if s is subsequence of t. You may
	 * assume that there is only lower case English letters in both s and t. t is
	 * potentially a very long (length ~= 500,000) string, and s is a short string
	 * (<=100). A subsequence of a string is a new string which is formed from the
	 * original string by deleting some (can be none) of the characters without
	 * disturbing the relative positions of the remaining characters. (ie, "ace" is
	 * a subsequence of "abcde" while "aec" is not). Example 1: s = "abc", t =
	 * "ahbgdc" -> Return true.
	 */
	public static boolean isSubsequence(String s, String t) {
		if (s.length() == 0) {
			return true;
		}
		char[] ch1 = s.toCharArray();
		char[] ch2 = t.toCharArray();
		int i = 0;
		int j = 0;
		while (i < ch1.length && j < ch2.length) {
			if (ch1[i] != ch2[j]) {
				j++;
			} else {
				i++;
				j++;
			}
		}
		return i == ch1.length;
	}

	/**
	 * Given an unsorted array of integers, find the number of longest increasing
	 * subsequence. Example 1: Input: [1,3,5,4,7] Output: 2 Explanation: The two
	 * longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
	 */
	public static int findNumberOfLIS(int[] nums) {
		int res = 0;
		int max_len = 0;
		int[] len = new int[nums.length];
		int[] cnt = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			len[i] = cnt[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					if (len[i] == len[j] + 1) {
						cnt[i] += cnt[j];
					}
					if (len[i] < len[j] + 1) {
						len[i] = len[j] + 1;
						cnt[i] = cnt[j];
					}
				}
			}
			if (max_len == len[i])
				res += cnt[i];
			if (max_len < len[i]) {
				max_len = len[i];
				res = cnt[i];
			}
		}
		return res;
	}

	/**
	 * Find duplicate element in linear time.
	 */
	public static int findDuplicate(int[] nums) {
		List<Integer>[] bucket = new ArrayList[nums.length];
		for (int i = 0; i < nums.length; i++) {
			List<Integer> list = bucket[nums[i]];
			if (list != null && list.size() > 0) {
				return nums[i];
			} else {
				list = new ArrayList<>();
				list.add(i);
				bucket[nums[i]] = list;
			}
		}
		return 0;
	}

	/**
	 * A peak element is an element that is greater than its neighbors. Given an
	 * input array nums, where nums[i] ≠ nums[i+1], find a peak element and return
	 * its index. The array may contain multiple peaks, in that case return the
	 * index to any one of the peaks is fine. You may imagine that nums[-1] =
	 * nums[n] = -∞.
	 */
	public static int findPeakElement(int[] nums) {
		if (nums.length == 0 || nums == null)
			return -1;
		int left = 0, right = nums.length - 1, mid = nums.length / 2;
		if (left == right) {
			return 0;
		}
		while (true) {
			if (left + 1 == right) {
				return (Math.max(nums[left], nums[right]) == nums[left]) ? left : right;
			}
			mid = (left + right) / 2;
			if (nums[mid] < nums[mid + 1]) {
				left = mid;
			} else {
				right = mid;
			}
		}
	}

	/**
	 * Input: [4,5,6,7,0,1,2] Output: 0 3 4 5 1 2
	 */
	public static int findMin(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = (right + left) / 2;
			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}
			if (nums[right] < nums[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return nums[left];
	}

	/**
	 * Suppose an array sorted in ascending order is rotated at some pivot unknown
	 * to you beforehand. (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). You
	 * are given a target value to search. If found in the array return its index,
	 * otherwise return -1. You may assume no duplicate exists in the array. Your
	 * algorithm's runtime complexity must be in the order of O(log n).
	 */
	public static int search2(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[left] <= nums[mid]) {
				if (target < nums[mid] && target >= nums[left]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (target > nums[mid] && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	/**
	 * Given two strings s and t, determine if they are isomorphic. Two strings are
	 * isomorphic if the characters in s can be replaced to get t. All occurrences
	 * of a character must be replaced with another character while preserving the
	 * order of characters. No two characters may map to the same character but a
	 * character may map to itself.
	 */
	public static boolean isIsomorphic2(String s, String t) {
		Map<Character, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			Set<Integer> set = map.get(s.charAt(i));
			if (set == null) {
				set = new HashSet<>();
				set.add(i);
				map.put(s.charAt(i), set);
			} else {
				set.add(i);
			}
		}
		Map<Character, Set<Integer>> map2 = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			Set<Integer> set = map2.get(t.charAt(i));
			if (set == null) {
				set = new HashSet<>();
				set.add(i);
				map2.put(t.charAt(i), set);
			} else {
				set.add(i);
			}
		}
		if (map.size() != map2.size()) {
			return false;
		}
		for (int i = 0; i < t.length(); i++) {
			Character fromS = s.charAt(i);
			Character fromT = t.charAt(i);
			Set<Integer> set = map.get(fromS);
			Set<Integer> set2 = map2.get(fromT);
			if (!set.equals(set2)) {
				return false;
			}
		}
		return true;
	}

	public static int[] findErrorNums(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int max = Integer.MIN_VALUE;
		int duplicate = 0;
		for (Integer i : nums) {
			Integer ii = map.get(i);
			if (ii == null) {
				map.put(i, 1);
			} else {
				map.put(i, ii + 1);
				duplicate = i;
			}
			if (max < i) {
				max = i;
			}
		}
		for (int i = 1; i < max; i++) {
			if (!map.containsKey(i)) {
				max = i;
			}
		}
		if (map.containsKey(max)) {
			max++;
		}
		return new int[] { duplicate, max };
	}

	/**
	 * Given an array consisting of n integers, find the contiguous subarray of
	 * given length k that has the maximum average value. And you need to output the
	 * maximum average value.
	 */
	public static double findMaxAverage(int[] nums, int k) {
		double res = -Double.MAX_VALUE;
		int steps = 1;
		double current = 0;
		int p = 0;
		for (int i = 0; i < nums.length; i++) {
			current += nums[i];
			if (steps++ == k) {
				if (res <= current / k) {
					res = current / k;
				}
				current -= nums[p++];
				steps--;
			}
		}
		return res;
	}

	/**
	 * Given two words word1 and word2, find the minimum number of steps required to
	 * make word1 and word2 the same, where in each step you can delete one
	 * character in either string.
	 */
	public static int minDistance(String word1, String word2) {
		// TODO
		return 0;
	}

	/**
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
	 * one sorted array. Note: The number of elements initialized in nums1 and nums2
	 * are m and n respectively. You may assume that nums1 has enough space (size
	 * that is greater or equal to m + n) to hold additional elements from nums2.
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int len = m + n - 1;
		m--;
		n--;
		while (n >= 0) {
			if (m >= 0 && nums1[m] > nums2[n]) {
				nums1[len] = nums1[m];
				m--;
			} else {
				nums1[len] = nums2[n];
				n--;
			}
			len--;
		}
	}

	/**
	 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some
	 * elements appear twice and others appear once. Find all the elements of [1, n]
	 * inclusive that do not appear in this array. Could you do it without extra
	 * space and in O(n) runtime? You may assume the returned list does not count as
	 * extra space.
	 */
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		int[] arr = new int[nums.length];
		List<Integer> result = new ArrayList<>();
		for (int ii : nums) {
			arr[ii - 1] = ii;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				result.add(i + 1);
			}
		}
		return result;
	}

	/**
	 * A zero-indexed array A of length N contains all integers from 0 to N-1. Find
	 * and return the longest length of set S, where S[i] = {A[i], A[A[i]],
	 * A[A[A[i]]], ... } subjected to the rule below. Suppose the first element in S
	 * starts with the selection of element A[i] of index = i, the next element in S
	 * should be A[A[i]], and then A[A[A[i]]]… By that analogy, we stop adding right
	 * before a duplicate element occurs in S.
	 */
	public int arrayNesting(int[] nums) {
		Set<Integer> set = new HashSet<>();
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			while (true) {
				if (set.add(num)) {
					num = nums[num];
				} else {
					if (max <= set.size()) {
						max = set.size();
					}

					set.clear();
					break;
				}
			}
		}
		return max;
	}

	// Definition for an interval.
	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	/**
	 * Given a collection of intervals, merge all overlapping intervals. Example 1:
	 * 
	 * Input: [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
	 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
	 * Example 2: Input: [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4]
	 * and [4,5] are considerred overlapping.
	 */
	public static List<Interval> merge(List<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// Probably o1.start - o2.start is enough thought.
				if (o1.start == o2.start && o1.end == o2.end) {
					return 0;
				}
				if (o1.start > o2.start) {
					return 1;
				}
				if (o1.start < o2.start) {
					return -1;
				}
				if (o1.end > o2.end) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		for (int i = 0; i < intervals.size(); i++) {
			for (int j = i + 1; j < intervals.size(); j++) {
				Interval a = intervals.get(i);
				Interval b = intervals.get(j);
				if (a.end >= b.start || (a.end < b.end && a.start == b.start)) {
					Interval newInterval = new Interval((a.start <= b.start) ? a.start : b.start,
							(b.end > a.end) ? b.end : a.end);
					intervals.remove(i);
					intervals.remove(j - 1);
					intervals.add(i, newInterval);
					i--;
					break;
				}
			}
		}
		return intervals;
	}

	/**
	 * Given an array of integers and an integer k, you need to find the total
	 * number of continuous subarrays whose sum equals to k. Example 1: Input:nums =
	 * [1,1,1], k = 2 Output: 2 Note: The length of the array is in range [1,
	 * 20,000]. The range of numbers in the array is [-1000, 1000] and the range of
	 * the integer k is [-1e7, 1e7].
	 */
	public static int subarraySum(int[] nums, int k) {
		int sum = 0;
		int result = 0;
		Map<Integer, Integer> preSum = new HashMap<>();
		preSum.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (preSum.containsKey(sum - k)) {
				result += preSum.get(sum - k);
			}
			preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
		}
		return result;
	}

	/**
	 * Implement toLowerCase.
	 */
	public static String toLowerCase(String str) {
		char[] a = str.toCharArray();
		for (int i = 0; i < a.length; i++)
			if ('A' <= a[i] && a[i] <= 'Z') {
				a[i] = (char) (a[i] - 'A' + 'a');
			}
		return new String(a);
	}

	/**
	 * Given two strings s and t which consist of only lowercase letters. String t
	 * is generated by random shuffling string s and then add one more letter at a
	 * random position. Find the letter that was added in t.
	 */
	public static char findTheDifference(String s, String t) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (Character c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		HashMap<Character, Integer> map2 = new HashMap<>();
		for (Character c : t.toCharArray()) {
			map2.put(c, map2.getOrDefault(c, 0) + 1);
		}
		for (Map.Entry<Character, Integer> entry : map2.entrySet()) {
			Character c = entry.getKey();
			int count = entry.getValue();
			Integer count2 = map.get(c);
			if (count2 == null) {
				return c;
			} else {
				if (count2 != count) {
					return c;
				}
			}
		}
		return 'a';
	}

	/**
	 * SAME as above problem, this time with faster solution using bitwise
	 * operations. Given two strings s and t which consist of only lowercase
	 * letters. String t is generated by random shuffling string s and then add one
	 * more letter at a random position. Find the letter that was added in t.
	 */
	public char findTheDifference2(String s, String t) {
		int n = t.length();
		char c = t.charAt(n - 1);
		for (int i = 0; i < n - 1; ++i) {
			c ^= s.charAt(i);
			c ^= t.charAt(i);
		}
		return c;
	}

	/**
	 * Given a string s and a non-empty string p, find all the start indices of p's
	 * anagrams in s. Strings consists of lowercase English letters only and the
	 * length of both strings s and p will not be larger than 20,100. The order of
	 * output does not matter.
	 */
	public static List<Integer> findAnagrams(String s, String p) {
		List<Integer> res = new ArrayList<>();
		if (p.length() > s.length() || s.isEmpty()) {
			return res;
		}
		for (int i = 0; i < s.length(); i++) {
			StringBuilder next = new StringBuilder();
			for (int j = i, steps = 0; steps < p.length() && j < s.length(); j++, steps++) {
				next.append(s.charAt(j));
			}
			if (areAnagramsCorrect(next.toString(), p)) {
				res.add(i);
			}
		}
		return res;
	}

	/**
	 * Check if the two string are anagrams.
	 */
	public static boolean areAnagramsCorrect(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		HashMap<Character, Integer> map1 = new HashMap<>();
		HashMap<Character, Integer> map2 = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
			map2.put(t.charAt(i), map2.getOrDefault(t.charAt(i), 0) + 1);
		}
		for (Character c : map1.keySet()) {
			Integer count1 = map1.get(c);
			Integer count2 = map2.get(c);
			if (count2 == null || count2 != count1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Same as above but faster. Given a string s and a non-empty string p, find all
	 * the start indices of p's anagrams in s. Strings consists of lowercase English
	 * letters only and the length of both strings s and p will not be larger than
	 * 20,100. The order of output does not matter.
	 */
	public static List<Integer> findAnagrams2(String s, String p) {
		List<Integer> list = new ArrayList<>();
		if (p.length() > s.length() || s.isEmpty()) {
			return list;
		}
		int[] hash = new int[256];
		for (char c : p.toCharArray()) {
			hash[c]++;
		}
		int left = 0, right = 0, count = p.length();
		while (right < s.length()) {
			if (hash[s.charAt(right++)]-- >= 1)
				count--;
			if (count == 0)
				list.add(left);
			if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
				count++;
		}
		return list;
	}

	/**
	 * N-ary Tree node implementation.
	 */
	static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	/**
	 * Given a n-ary tree, find its maximum depth. The maximum depth is the number
	 * of nodes along the longest path from the root node down to the farthest leaf
	 * node.
	 */
	public static int maxDepth(Node root) {
		int depth = 0;
		LinkedList<Node> layers = new LinkedList<>();
		layers.add(root);
		while (!layers.isEmpty()) {
			LinkedList<Node> layer = new LinkedList<>();
			while (!layers.isEmpty()) {
				Node next = layers.remove();
				if (next.children != null) {
					layer.addAll(next.children);
				}
			}
			layers.addAll(layer);
			depth++;
		}
		return depth;
	}

	/**
	 * Sum of two strings. Needed for below multiplication of strings. Code has
	 * linear time complexity O(n) where n = MAX(num1.length, num2.length)).
	 */
	public static String addStrings(String num1, String num2) {
		char[] ar1 = num1.toCharArray();
		char[] ar2 = num2.toCharArray();
		int carry = 0;
		int n = ar1.length - 1;
		int m = ar2.length - 1;
		StringBuilder str = new StringBuilder();
		while (n >= 0 || m >= 0) {
			int n1 = Character.getNumericValue(((n >= 0) ? ar1[n] : '0'));
			int n2 = Character.getNumericValue(((m >= 0) ? ar2[m] : '0'));
			int sum = n1 + n2 + carry;
			if (sum >= 10) {
				carry = sum / 10;
			} else {
				carry = 0;
			}
			str.insert(0, sum % 10);
			n--;
			m--;
		}
		if (carry != 0) {
			str.insert(0, carry);
		}
		return str.toString();
	}

	/**
	 * Given two non-negative integers num1 and num2 represented as strings, return
	 * the product of num1 and num2, also represented as a string. The length of
	 * both num1 and num2 is < 110. Both num1 and num2 contain only digits 0-9. Both
	 * num1 and num2 do not contain any leading zero, except the number 0 itself.
	 * You must not use any built-in BigInteger library or convert the inputs to
	 * integer directly.
	 */
	public static String multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		char[] ar1 = num1.toCharArray();
		char[] ar2 = num2.toCharArray();
		int carry = 0;
		LinkedList<String> sums = new LinkedList<>();
		int counter = 0;
		for (int i = ar2.length - 1; i >= 0; i--) {
			StringBuilder str = new StringBuilder();
			int n1 = Character.getNumericValue(ar2[i]);
			for (int j = ar1.length - 1; j >= 0; j--) {
				int n2 = Character.getNumericValue(ar1[j]);
				int multiplication = n1 * n2 + carry;
				if (multiplication >= 10) {
					carry = multiplication / 10;
				} else {
					carry = 0;
				}
				str.insert(0, multiplication % 10);
			}
			if (carry != 0) {
				str.insert(0, carry);
				carry = 0;
			}
			if (str.toString() != "0") {
				for (int p = 0; p < counter; p++) {
					str.append("0");
				}
			}
			sums.add(str.toString());
			counter++;
		}
		if (sums.size() <= 1) {
			return sums.removeFirst();
		} else {
			String s1 = sums.removeFirst();
			String s2 = sums.removeFirst();
			String next = addStrings(s1, s2);
			String res = next;
			while (!sums.isEmpty()) {
				String next2 = sums.removeFirst();
				res = addStrings(next, next2);
				next = res;
			}
			return res;
		}
	}

	/**
	 * Given an array of integers that is already sorted in ascending order, find
	 * two numbers such that they add up to a specific target number. The function
	 * twoSum should return indices of the two numbers such that they add up to the
	 * target, where index1 must be less than index2. Note: Your returned answers
	 * (both index1 and index2) are not zero-based. You may assume that each input
	 * would have exactly one solution and you may not use the same element twice.
	 */
	public static int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			int num = numbers[i];
			int tar = target - num;
			int left = i + 1;
			int right = numbers.length - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (numbers[mid] == tar) {
					res[0] = i + 1;
					res[1] = mid + 1;
					return res;
				}
				if (numbers[mid] > tar) {
					right = mid - 1;
					continue;
				}
				if (numbers[mid] < tar) {
					left = mid + 1;
				}
			}
		}
		return res;
	}

	/**
	 * Find the minimum length word from a given dictionary words, which has all the
	 * letters from the string licensePlate. Such a word is said to complete the
	 * given string licensePlate Here, for letters we ignore case. For example, "P"
	 * on the licensePlate still matches "p" on the word. It is guaranteed an answer
	 * exists. If there are multiple answers, return the one that occurs first in
	 * the array. The license plate might have the same letter occurring multiple
	 * times. For example, given a licensePlate of "PP", the word "pair" does not
	 * complete the licensePlate, but the word "supper" does.
	 */
	public static String shortestCompletingWord(String licensePlate, String[] words) {
		licensePlate = licensePlate.toLowerCase();
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < licensePlate.length(); i++) {
			char c = licensePlate.charAt(i);
			if (c >= 'a' && c <= 'z') {
				Integer count = map.get(c);
				if (count == null) {
					map.put(c, 1);
				} else {
					map.put(c, count + 1);
				}
			}
		}
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};
		Arrays.sort(words, comp);
		for (String word : words) {
			boolean flag = true;
			for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				char c = entry.getKey();
				int count = entry.getValue();
				int realCount = 0;
				for (Character ch : word.toCharArray()) {
					if (ch == c) {
						realCount++;
					}
				}
				if (realCount < count) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return word;
			}
		}
		return "";
	}

	/**
	 * Given an n-ary tree, return the level order traversal of its nodes' values.
	 * (ie, from left to right, level by level). Advanced BFS algorithm.
	 */
	public static List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		LinkedList<Integer> rooted = new LinkedList<>();
		rooted.add(root.val);
		res.add(rooted);
		LinkedList<Node> layers = new LinkedList<>();
		layers.add(root);
		while (!layers.isEmpty()) {
			LinkedList<Node> layer = new LinkedList<>();
			while (!layers.isEmpty()) {
				Node node = layers.remove();
				List<Node> childs = node.children;
				if (childs != null) {
					layer.addAll(childs);
				}
			}
			LinkedList<Integer> list = new LinkedList<>();
			for (int i = 0; i < layer.size(); i++) {
				Integer ii = layer.get(i).val;
				list.add(ii);
			}
			if (!list.isEmpty()) {
				res.add(list);
			}
			layers.addAll(layer);
		}
		return res;
	}

	/**
	 * Given a string, you need to reverse the order of characters in each word
	 * within a sentence while still preserving whitespace and initial word order.
	 */
	public static String reverseWords2(String s) {
		String[] split = s.split("\\s");
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			StringBuilder str1 = new StringBuilder(split[i]);
			str1.reverse();
			str.append(str1);
			if (i + 1 < split.length) {
				str.append(" ");
			}
		}
		return str.toString();
	}

	/**
	 * Given a positive integer n, find the least number of perfect square numbers
	 * (for example, 1, 4, 9, 16, ...) which sum to n.
	 */
	public static int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 1; i + j * j <= n; j++) {
				dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
			}
		}
		return dp[n];
	}

	/**
	 * Given a set of distinct integers, nums, return all possible subsets (the
	 * power set). Note: The solution set must not contain duplicate subsets.
	 */
	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<Integer>());
		for (int num : nums) {
			List<List<Integer>> tmp = new ArrayList<>();
			for (List<Integer> sub : res) {
				List<Integer> a = new ArrayList<>(sub);
				a.add(num);
				tmp.add(a);
			}
			res.addAll(tmp);
		}
		return res;
	}

	/**
	 * Given a list of daily temperatures, produce a list that, for each day in the
	 * input, tells you how many days you would have to wait until a warmer
	 * temperature. If there is no future day for which this is possible, put 0
	 * instead. For example, given the list temperatures = [73, 74, 75, 71, 69, 72,
	 * 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0]. Note: The length of
	 * temperatures will be in the range [1, 30000]. Each temperature will be an
	 * integer in the range [30, 100].
	 */
	public static int[] dailyTemperatures(int[] temperatures) {
		int[] res = new int[temperatures.length];
		for (int i = 0; i < temperatures.length; i++) {
			int temp = temperatures[i];
			int days = 0;
			for (int j = i + 1, counter = 1; j < temperatures.length; j++, counter++) {
				if (temperatures[j] > temp) {
					days = counter;
					break;
				}
			}
			res[i] = days;
		}
		return res;
	}

	/**
	 * Given a linked list, return the node where the cycle begins. If there is no
	 * cycle, return null. Note: Do not modify the linked list. Follow up: Can you
	 * solve it without using extra space.
	 */
	public static ListNode detectCycle(ListNode head) {
		if (head == null) {
			return null;
		}
		int i = 1;
		ListNode front = head.next;
		ListNode back = head;
		while (front != null) {
			if (front.equals(back)) {
				return front;
			}
			if (i++ % 2 == 0) {
				back = back.next;
			}
			front = front.next;
		}
		return null;
	}

	/**
	 * Given a linked list, rotate the list to the right by k places, where k is
	 * non-negative.
	 */
	public static ListNode rotateRight(ListNode head, int k) {
		int size = 1;
		ListNode n = head;
		while (n.next != null) {
			n = n.next;
			size++;
		}
		int K = k % size;
		ListNode ptr = head;
		for (int i = 0; i < K; i++) {
			ptr = ptr.next;
		}
		ListNode from = ptr.next;
		ListNode last = from;
		ptr.next = null;
		while (last.next != null) {
			last = last.next;
		}
		last.next = head;
		return from;
	}

	/**
	 * Rotate array. Slow but no extra memory.
	 */
	public static void rotate(int[] nums, int k) {
		if (k == 0 || nums.length < 2 || k % nums.length == 0) {
			return;
		}
		int K = k % nums.length;
		while (K > 0) {
			for (int i = 0; i < nums.length; i++) {
				int last = nums[nums.length - 1];
				int ptr = nums[i];
				nums[i] = last;
				nums[nums.length - 1] = ptr;
			}
			K--;
		}
	}

	/**
	 * Given a binary search tree, write a function kthSmallest to find the kth
	 * smallest element in it. Note: You may assume k is always valid, 1 ≤ k ≤ BST's
	 * total elements.
	 */
	public static int kthSmallest(TreeNode root, int k) {
		/*
		 * In this algorithm we do not consider that the tree is binary search. Thus the
		 * algorithm is not optimal.
		 */
		TreeSet<Integer> helper = new TreeSet<>();
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> layer = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode node = layers.remove();
				helper.add(node.val);
				if (node.left != null) {
					layer.add(node.left);
				}
				if (node.right != null) {
					layer.add(node.right);
				}
			}
			layers.addAll(layer);
		}
		Iterator<Integer> iter = helper.iterator();
		int res = 0;
		int i = 0;
		while (i < k) {
			res = iter.next().intValue();
			i++;
		}
		return res;
	}

	/**
	 * Below we have three very similar algorithms (2xBFS and DFS) for traversing
	 * Binary Tree (lever order and vertical).
	 */

	/**
	 * Given a binary tree, return the level order traversal of its nodes' values.
	 * (ie, from left to right, level by level).
	 */
	public List<List<Integer>> levelOrder2(TreeNode root) {
		List<List<Integer>> res = new LinkedList<>();
		if (root == null) {
			return res;
		}
		LinkedList<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		List<Integer> l = new LinkedList<>();
		l.add(root.val);
		res.add(l);
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> layer = new LinkedList<>();
			LinkedList<Integer> next = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode node = layers.remove();
				if (node.left != null) {
					layer.add(node.left);
					next.add(node.left.val);
				}
				if (node.right != null) {
					layer.add(node.right);
					next.add(node.right.val);
				}
			}
			layers.addAll(layer);
			if (!next.isEmpty()) {
				res.add(next);
			}
		}
		return res;
	}

	/**
	 * Given a binary tree, return the level order traversal of its nodes' values.
	 * (ie, from left to right, level by level).
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		LinkedList<List<Integer>> res = new LinkedList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> layers = new LinkedList<>();
		layers.add(root);
		List<Integer> l = new LinkedList<>();
		l.add(root.val);
		res.add(l);
		while (!layers.isEmpty()) {
			LinkedList<TreeNode> layer = new LinkedList<>();
			LinkedList<Integer> next = new LinkedList<>();
			while (!layers.isEmpty()) {
				TreeNode node = layers.poll();
				if (node.left != null) {
					layer.add(node.left);
					next.add(node.left.val);
				}
				if (node.right != null) {
					layer.add(node.right);
					next.add(node.right.val);
				}
			}
			layers.addAll(layer);
			if (!next.isEmpty()) {
				res.addFirst(next);
			}
		}
		return res;
	}

	/**
	 * Given a binary tree, return the vertical level order traversal of its nodes'
	 * values. (ie, from top to bottom).
	 */
	public static void verticalOrderTraversal(TreeNode root) {
		Stack<TreeNode> layers = new Stack<>();
		layers.push(root);
		while (!layers.isEmpty()) {
			TreeNode node = layers.pop();
			if (node.right != null) {
				layers.push(node.right);
			}
			if (node.left != null) {
				layers.push(node.left);
			}
		}
	}

	/**
	 * Given two binary trees and imagine that when you put one of them to cover the
	 * other, some nodes of the two trees are overlapped while the others are not.
	 *
	 * You need to merge them into a new binary tree. The merge rule is that if two
	 * nodes overlap, then sum node values up as the new value of the merged node.
	 * Otherwise, the NOT null node will be used as the node of new tree. Note: The
	 * merging process must start from the root nodes of both trees.
	 */
	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null || t2 == null) {
			return t1 == null ? t2 : t1;
		}
		TreeNode node = new TreeNode(t1.val + t2.val);
		node.left = mergeTrees(t1.left, t2.left);
		node.right = mergeTrees(t1.right, t2.right);
		return node;
	}

	/**
	 * Given two binary trees and imagine that when you put one of them to cover the
	 * other, some nodes of the two trees are overlapped while the others are not.
	 *
	 * You need to merge them into a new binary tree. The merge rule is that if two
	 * nodes overlap, then sum node values up as the new value of the merged node.
	 * Otherwise, the NOT null node will be used as the node of new tree. Note: The
	 * merging process must start from the root nodes of both trees.
	 */
	public static TreeNode mergeTreesIterative(TreeNode t1, TreeNode t2) {
		if (t1 == null || t2 == null) {
			return t1 == null ? t2 : t1;
		}
		TreeNode merged = new TreeNode(t1.val + t2.val);
		LinkedList<LinkedList<Integer>> listNodes = new LinkedList<>();
		LinkedList<TreeNode> layers1 = new LinkedList<>();
		layers1.add(t1);
		LinkedList<TreeNode> layers2 = new LinkedList<>();
		layers2.add(t2);
		while (!layers1.isEmpty() && !layers2.isEmpty()) {
			LinkedList<TreeNode> layer1 = new LinkedList<>();
			LinkedList<TreeNode> layer2 = new LinkedList<>();
			LinkedList<Integer> listNode = new LinkedList<>();
			while (!layers1.isEmpty() && !layers2.isEmpty()) {
				if (!layers2.isEmpty() && !layers1.isEmpty()) {
					TreeNode fromt1 = layers1.remove();
					TreeNode fromt2 = layers2.remove();
					if (fromt1.left != null && fromt2.left != null) {
						layer1.add(fromt1.left);
						layer2.add(fromt2.left);
						listNode.add(fromt1.left.val + fromt2.left.val);
					} else {
						if (fromt1.left != null) {
							layer1.add(fromt1.left);
							listNode.add(fromt1.left.val);
						} else {
							if (fromt2.left != null) {
								layer2.add(fromt2.left);
								listNode.add(fromt2.left.val);
							} else {
								listNode.add(null);
							}
						}
					}
					if (fromt1.right != null && fromt2.right != null) {
						layer1.add(fromt1.right);
						layer2.add(fromt2.right);
						listNode.add(fromt1.right.val + fromt2.right.val);
					} else {
						if (fromt2.right != null) {
							layer2.add(fromt2.right);
							listNode.add(fromt2.right.val);
						} else {
							if (fromt2.left != null) {
								layer2.add(fromt2.left);
								listNode.add(fromt2.left.val);
							} else {
								listNode.add(null);
							}
						}
					}
				} else {
					if (!layers2.isEmpty()) {
						TreeNode fromt1 = layers1.remove();
						if (fromt1.left != null) {
							layer1.add(fromt1.left);
							listNode.add(fromt1.left.val);
						} else {
							listNode.add(null);
						}
						if (fromt1.right != null) {
							layer1.add(fromt1.right);
							listNode.add(fromt1.right.val);
						} else {
							listNode.add(null);
						}
					} else {
						TreeNode fromt2 = layers2.remove();
						if (fromt2.left != null) {
							layer2.add(fromt2.left);
							listNode.add(fromt2.left.val);
						} else {
							listNode.add(null);
						}
						if (fromt2.right != null) {
							layer2.add(fromt2.right);
							listNode.add(fromt2.right.val);
						} else {
							listNode.add(null);
						}
					}
				}
			}
			if (!layer1.isEmpty()) {
				layers1.addAll(layer1);
			}
			if (!layer2.isEmpty()) {
				layers2.addAll(layer2);
			}
			if (!listNode.isEmpty()) {
				listNodes.add(listNode);
			}
		}
		LinkedList<TreeNode> layers3 = new LinkedList<>();
		layers3.add(merged);
		int idx = 0;
		while (!layers3.isEmpty()) {
			LinkedList<TreeNode> layer3 = new LinkedList<>();
			int id = 0;
			while (!layers3.isEmpty()) {
				TreeNode node = layers3.remove();
				if (listNodes.size() > idx) {
					List<Integer> sublist = listNodes.get(idx);
					if (sublist.size() > id) {
						Integer valleft = sublist.get(id++);
						if (valleft != null) {
							TreeNode newnode = new TreeNode(valleft);
							node.left = newnode;
							layer3.add(newnode);
						}
						Integer valright = listNodes.get(idx).get(id++);
						if (valright != null) {
							TreeNode newnode = new TreeNode(valright);
							node.right = newnode;
							layer3.add(newnode);
						}
					}
				}
			}
			layers3.addAll(layer3);
			idx++;
		}
		return merged;
	}

	/**
	 * Given an array of characters, compress it in-place. The length after
	 * compression must always be smaller than or equal to the original array. Every
	 * element of the array should be a character (not int) of length 1. After you
	 * are done modifying the input array in-place, return the new length of the
	 * array. Follow up: Could you solve it using only O(1) extra space?
	 * 
	 */
	public static int compress(char[] chars) {
		StringBuilder str = new StringBuilder();
		str.append(chars[0]);
		boolean first = true;
		for (int i = 0, j = 1; j < chars.length;) {
			if (chars[i] == chars[j]) {
				int count = 1;
				char prev = chars[j];
				while (j < chars.length && prev == chars[j]) {
					count++;
					prev = chars[j];
					j++;
				}
				if (!first) {
					str.append(chars[i]);
				}
				str.append(String.valueOf(count));
				first = false;
				i = j;
				j++;
			} else {
				if (!first) {
					str.append(chars[i]);
				}
				first = false;
				i++;
				j++;
			}
		}
		for (int i = 0; i < chars.length; i++) {
			if (i < str.length()) {
				chars[i] = str.charAt(i);
			} else {
				chars[i] = ' ';
			}
		}
		return str.length();
	}

	public static int compress2(char[] chars) {
		char lastChar = chars[0];
		int lastCharCount = 1;
		int currentIndex = 0;
		for (int i = 1; i <= chars.length; i++) {
			if (i == chars.length || lastChar != chars[i]) {
				chars[currentIndex++] = lastChar;
				if (lastCharCount > 1) {
					for (char ch : new String(lastCharCount + "").toCharArray()) {
						chars[currentIndex++] = ch;
					}
				}
				if (i != chars.length)
					lastChar = chars[i];
				lastCharCount = 1;
			} else {
				lastCharCount++;
			}
		}
		return currentIndex;
	}

	/**
	 * Let's call any (contiguous) subarray B (of A) a mountain if the following
	 * properties hold: B.length >= 3 There exists some 0 < i < B.length - 1 such
	 * that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1] (Note
	 * that B could be any subarray of A, including the entire array A.) Given an
	 * array A of integers, return the length of the longest mountain. Return 0 if
	 * there is no mountain.
	 */
	public static int longestMountain(int[] A) {
		Set<Integer> peakidx = new HashSet<>();
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i - 1] < A[i] && A[i + 1] < A[i]) {
				peakidx.add(i);
			}
		}
		int max = 0;
		Iterator<Integer> itr = peakidx.iterator();
		while (itr.hasNext()) {
			int currlen = 1;
			int peakid = itr.next();
			int left = peakid - 1;
			int right = peakid + 1;
			int prevl = A[peakid];
			int prevr = A[peakid];
			while (left >= 0 && prevl > A[left]) {
				prevl = A[left];
				left--;
				currlen++;
			}
			while (right < A.length && prevr > A[right]) {
				prevr = A[right];
				right++;
				currlen++;
			}
			if (max < currlen) {
				max = currlen;
			}
		}
		return max;
	}

	/**
	 * Given a sorted array nums, remove the duplicates in-place such that
	 * duplicates appeared at most twice and return the new length. Do not allocate
	 * extra space for another array, you must do this by modifying the input array
	 * in-place with O(1) extra memory.
	 */
	public static int removeDuplicates(int[] nums) {
		if (nums.length < 3) {
			return nums.length;
		}
		int count = 1;
		int setter = 1;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				count++;
			} else {
				count = 1;
			}
			if (count < 3) {
				nums[setter++] = nums[i + 1];
			}
		}
		return setter;
	}

	/**
	 * Given a 2D board and a word, find if the word exists in the grid.
	 *
	 * The word can be constructed from letters of sequentially adjacent cell, where
	 * "adjacent" cells are those horizontally or vertically neighboring. The same
	 * letter cell may not be used more than once.
	 */
	public static boolean exist(char[][] board, String word) {
		LinkedList<int[]> layers = new LinkedList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int idx = 0;
				if (board[i][j] == word.charAt(idx)) {
					idx++;
					layers.add(new int[] { i, j });
					boolean[][] table = new boolean[board.length][board[0].length];
					table[i][j] = true;
					while (!layers.isEmpty()) {
						LinkedList<int[]> layer = new LinkedList<>();
						while (!layers.isEmpty()) {
							int[] next = layers.remove();
							int row = next[0];
							int col = next[1];
							if (idx == word.length()) {
								if (board[row][col] == word.charAt(word.length() - 1)) {
									return true;
								} else {
									return false;
								}
							}
							// Toxic:
							if (col + 1 < board[row].length && row + 1 < board.length
									&& board[row + 1][col + 1] == word.charAt(idx) && !table[row + 1][col + 1]) {
								layer.add(new int[] { row + 1, col + 1 });
								table[row + 1][col + 1] = true;
							}
							if (col + 1 < board[row].length && board[row][col + 1] == word.charAt(idx)
									&& !table[row][col + 1]) {
								layer.add(new int[] { row, col + 1 });
								table[row][col + 1] = true;
							}
							if (col - 1 >= 0 && board[row][col - 1] == word.charAt(idx) && !table[row][col - 1]) {
								layer.add(new int[] { row, col - 1 });
								table[row][col - 1] = true;
							}
							if (row - 1 >= 0 && board[row - 1][col] == word.charAt(idx) && !table[row - 1][col]) {
								layer.add(new int[] { row - 1, col });
								table[row - 1][col] = true;
							}
							if (row + 1 < board.length && col - 1 >= 0 && board[row + 1][col - 1] == word.charAt(idx)
									&& !table[row + 1][col - 1]) {
								layer.add(new int[] { row + 1, col - 1 });
								table[row + 1][col - 1] = true;
							}
							if (row + 1 < board.length && board[row + 1][col] == word.charAt(idx)
									&& !table[row + 1][col]) {
								layer.add(new int[] { row + 1, col });
								table[row + 1][col] = true;
							}
							if (row + 1 < board.length && board[row + 1][col] == word.charAt(idx)
									&& !table[row + 1][col]) {
								layer.add(new int[] { row + 1, col });
								table[row + 1][col] = true;
							}
							if (row - 1 >= 0 && col + 1 < board[row].length
									&& board[row - 1][col + 1] == word.charAt(idx) && !table[row - 1][col + 1]) {
								layer.add(new int[] { row - 1, col + 1 });
								table[row - 1][col + 1] = true;
							}
						}
						if (layer.isEmpty()) {
							break;
						}
						layers.addAll(layer);
						if (!(idx + 1 == word.length())) {
							char next = word.charAt(idx + 1);
							if (layer.size() > 1 && word.charAt(idx) == next) {
								int id = idx;
								char c = word.charAt(id);
								int size = layer.size();
								while (c == word.charAt(id + 1) && size > 0) {
									size--;
									idx++;
								}
								idx = (idx >= word.length()) ? word.length() : idx;
								continue;
							}
						}
						idx++;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Given a non-empty array of integers, return the third maximum number in this
	 * array. If it does not exist, return the maximum number. The time complexity
	 * must be in O(n).
	 */
	public static int thirdMax(int[] nums) {
		Integer max1 = null;
		Integer max2 = null;
		Integer max3 = null;
		for (int n : nums) {
			if ((max1 != null && max1 == n) || (max2 != null && max2 == n) || (max3 != null && max3 == n)) {
				continue;
			}
			if (max1 == null || n > max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (max2 == null || n > max2) {
				max3 = max2;
				max2 = n;
			} else if (max3 == null || n > max3) {
				max3 = n;
			}
		}
		return (max3 == null) ? ((max1 != null) ? max1 : max2) : max3;
	}

	/**
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
	 * Do it in-place. Follow up:
	 * 
	 * A straight forward solution using O(mn) space is probably a bad idea. A
	 * simple improvement uses O(m + n) space, but still not the best solution.
	 * Could you devise a constant space solution?
	 */
	public static void setZeroes(int[][] matrix) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					int row = 0;
					while (row < matrix.length) {
						Set<Integer> idx = map.get(row);
						if (idx == null) {
							idx = new HashSet<>();
							idx.add(j);
							map.put(row, idx);
						} else {
							idx.add(j);
						}
						row++;
					}
					int col = 0;
					while (col < matrix[i].length) {
						Set<Integer> idx = map.get(i);
						if (idx == null) {
							idx = new HashSet<>();
							idx.add(col);
							map.put(i, idx);
						} else {
							idx.add(col);
						}
						col++;
					}
				}
			}
		}
		for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
			Integer row = entry.getKey();
			Set<Integer> cols = entry.getValue();
			Iterator<Integer> itr = cols.iterator();
			while (itr.hasNext()) {
				matrix[row][itr.next()] = 0;
			}
		}
	}

	/**
	 * Given a paragraph and a list of banned words, return the most frequent word
	 * that is not in the list of banned words. It is guaranteed there is at least
	 * one word that isn't banned, and that the answer is unique. Words in the list
	 * of banned words are given in lowercase, and free of punctuation. Words in the
	 * paragraph are not case sensitive. The answer is in lowercase.
	 *
	 */
	public static String mostCommonWord(String paragraph, String[] banned) {
		Map<String, Integer> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		for (String ban : banned) {
			set.add(ban);
		}
		// Paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < paragraph.length(); i++) {
			char ch = Character.toLowerCase(paragraph.charAt(i));
			if (ch <= 'z' && ch >= 'a') {
				str.append(ch);
			} else {
				if (str.length() > 0 && !set.contains(str.toString())) {
					Integer ii = map.get(str.toString());
					if (ii != null) {
						ii += 1;
						map.put(str.toString(), ii);
					} else {
						map.put(str.toString(), 1);
					}
				}
				str = new StringBuilder();
			}
		}

		// Duplication.
		if (str.length() > 0) {
			Integer ii = map.get(str.toString());
			if (ii != null) {
				ii += 1;
				map.put(str.toString(), ii);
			} else {
				map.put(str.toString(), 1);
			}
		}
		int max = 0;
		String freq = null;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				freq = entry.getKey();
			}
		}
		return freq;
	}

	/**
	 * Your are given an array of positive integers nums. Count and print the number
	 * of (contiguous) subarrays where the product of all the elements in the
	 * subarray is less than k.
	 */
	public static int numSubarrayProductLessThanK2(int[] nums, int k) {
		int result = 0;
		int curr = 1;
		for (int i = 0, j = 0; i < nums.length;) {
			curr *= nums[j];
			if (curr < k) {
				result++;
				j++;
				if (j == nums.length) {
					i++;
					j = i;
					curr = 1;
				}
			} else {
				i++;
				j = i;
				curr = 1;
			}
		}
		return result;
	}

	/**
	 * Same as above but more efficient. O(n) speed O(1) memory;
	 */
	public static int numSubarrayProductLessThanK(int[] nums, int k) {
		int left = 0, right = 0, prod = 1, count = 0;
		while (right < nums.length) {
			prod *= nums[right++];
			while (left < right && prod >= k) {
				prod /= nums[left++];
			}
			count += right - left;
		}
		return count;
	}

	/**
	 * Given an integer array, find three numbers whose product is maximum and
	 * output the maximum product. This ugly code beats 100% of java solutions. O(1)
	 * memory and O(n) speed.
	 */
	public static int maximumProduct(int[] nums) {
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		for (int num : nums) {
			if (max1 < num) {
				max3 = max2;
				max2 = max1;
				max1 = num;
			} else {
				if (max2 < num) {
					max3 = max2;
					max2 = num;
				} else {
					if (max3 < num) {
						max3 = num;
					}
				}
			}
			if (min1 > num) {
				min2 = min1;
				min1 = num;
				continue;
			}
			if (min2 > num) {
				min2 = num;
			}
		}
		return (min1 * min2 * max1 < max3 * max2 * max1) ? max3 * max2 * max1 : min1 * min2 * max1;
	}

	/**
	 * Given an array with n integers, your task is to check if it could become
	 * non-decreasing by modifying at most 1 element.
	 *
	 * We define an array is non-decreasing if array[i] <= array[i + 1] holds for
	 * every i (1 <= i < n).
	 */
	public static boolean checkPossibility(int[] nums) {
		int counter = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1]) {
				counter++;
				if (i >= 1 && nums[i + 1] < nums[i - 1]) {
					nums[i + 1] = nums[i];
				}
			}
		}
		return (counter > 1) ? false : true;
	}

	/**
	 * Method generates all permutations of a string and prints them.
	 */
	public static void generatePermutations(String str) {
		permuteString("", str);
	}

	public static void permuteString(String beginningString, String endingString) {
		if (endingString.length() <= 1) {
			System.out.println(beginningString + endingString);
		} else {
			for (int i = 0; i < endingString.length(); i++) {
				String newString = endingString.substring(0, i) + endingString.substring(i + 1);
				permuteString(beginningString + endingString.charAt(i), newString);
			}
		}
	}

	/**
	 * Given an array of n positive integers and a positive integer s, find the
	 * minimal length of a contiguous subarray of which the sum ≥ s. If there isn't
	 * one, return 0 instead. Example: Input: s = 7, nums = [2,3,1,2,4,3] Output: 2
	 * Explanation: the subarray [4,3] has the minimal length under the problem
	 * constraint. Solution beats 99.97 of all java solutions in Leetcode. O(n)
	 * speed and O(1) memory.
	 */
	public static int minSubArrayLen(int s, int[] nums) {
		int len = Integer.MAX_VALUE;
		int curr = 0;
		for (int left = 0, right = 0; left <= right && left < nums.length;) {
			if (curr < s && right < nums.length) {
				curr += nums[right++];
			} else {
				if (len > right - left && curr >= s) {
					len = right - left;
				}
				curr -= nums[left];
				left++;
			}
		}
		return (len == Integer.MAX_VALUE) ? 0 : len;
	}

	/**
	 * Given string S and a dictionary of words words, find the number of words[i]
	 * that is a subsequence of S. Note:
	 *
	 * All words in words and S will only consists of lowercase letters. The length
	 * of S will be in the range of [1, 50000]. The length of words will be in the
	 * range of [1, 5000]. The length of words[i] will be in the range of [1, 50].
	 */
	public int numMatchingSubseq2(String S, String[] words) {
		int res = 0;
		for (String word : words) {
			if (isSubseq2(S, word)) {
				res++;
			}
		}
		return res;
	}

	/**
	 * Helper method identifies whether word b is subsequence of word a.
	 */
	public static boolean isSubseq(String a, String b) {
		int n = 0;
		int m = 0;
		boolean flag = false;
		while (m < b.length() && n < a.length()) {
			if (a.charAt(n) != b.charAt(m)) {
				n++;
			} else {
				m++;
				n++;
				if (m == b.length()) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * Same as above. Helper method identifies whether word b is subsequence of word
	 * a.
	 */
	public static boolean isSubseq2(String a, String b) {
		Map<Character, Queue<Integer>> map = new HashMap<>();
		for (int i = 0; i < a.length(); i++) {
			char ch = a.charAt(i);
			Queue<Integer> queue = map.get(ch);
			if (queue == null) {
				queue = new LinkedList<>();
				queue.add(i);
				map.put(ch, queue);
			} else {
				queue.add(i);
			}
		}
		int previd = -1;
		for (int i = 0; i < b.length(); i++) {
			char ch = b.charAt(i);
			if (!map.containsKey(ch)) {
				return false;
			} else {
				Queue<Integer> queue = map.get(ch);
				Integer id = queue.poll();
				if (id == null) {
					return false;
				}
				if (previd >= id) {
					i--;
					continue;
				}
				previd = id;
			}
		}
		return true;
	}

	/**
	 * Given string S and a dictionary of words words, find the number of words[i]
	 * that is a subsequence of S. Note:
	 *
	 * All words in words and S will only consists of lowercase letters. The length
	 * of S will be in the range of [1, 50000]. The length of words will be in the
	 * range of [1, 5000]. The length of words[i] will be in the range of [1, 50].
	 */
	public int numMatchingSubseq(String S, String[] words) {
		int n = S.length();
		int[][] next = new int[n + 1][26];
		char[] sc = S.toCharArray();

		Arrays.fill(next[n], -1);
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < 26; j++) {
				next[i][j] = next[i + 1][j];
			}
			next[i][sc[i] - 'a'] = i + 1;
		}
		int res = 0;
		for (int i = 0; i < words.length; i++) {
			if (isSubseq(next, words[i]))
				res++;
		}
		return res;
	}

	/**
	 * Same as Above but with dp.
	 */
	public boolean isSubseq(int[][] next, String s) {
		char[] sc = s.toCharArray();
		int j = 0;
		for (int i = 0; i < sc.length; i++) {
			char c = sc[i];
			if (next[j][c - 'a'] > -1) {
				j = next[j][c - 'a'];
			} else {
				return false;
			}
		}
		return true;
	}

	static class Employee {
		// It's the unique id of each node;
		// unique id of this employee
		public int id;
		// the importance value of this employee
		public int importance;
		// the id of direct subordinates
		public List<Integer> subordinates;
	};

	/**
	 * You are given a data structure of employee information, which includes the
	 * employee's unique id, his importance value and his direct subordinates' id.
	 * For example, employee 1 is the leader of employee 2, and employee 2 is the
	 * leader of employee 3. They have importance value 15, 10 and 5, respectively.
	 * Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has
	 * [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is
	 * also a subordinate of employee 1, the relationship is not direct. Now given
	 * the employee information of a company, and an employee id, you need to return
	 * the total importance value of this employee and all his subordinates.
	 */
	public static int getImportance(List<Employee> employees, int id) {
		int result = 0;
		Map<Integer, Employee> map = new HashMap<>();
		for (int i = 0; i < employees.size(); i++) {
			Employee emp = employees.get(i);
			map.put(emp.id, emp);
		}
		LinkedList<Employee> layers = new LinkedList<>();
		layers.add(map.get(id));
		while (!layers.isEmpty()) {
			LinkedList<Employee> layer = new LinkedList<>();
			while (!layers.isEmpty()) {
				Employee emp = layers.remove();
				result += emp.importance;
				if (emp.subordinates != null && !emp.subordinates.isEmpty()) {
					for (Integer idx : emp.subordinates) {
						layer.add(map.get(idx));
					}
				}
			}
			layers.addAll(layer);
		}
		return result;
	}

	/**
	 * Write a function that takes an array of integers and returns that array
	 * rotated by N positions. For example, if N=2, given the input array [1, 2, 3,
	 * 4, 5, 6] the function should return [5, 6, 1, 2, 3, 4].
	 *
	 * @param args
	 */

	public static void rotate2(int[] array, int factor) {
		if (array == null || factor < 0) {
			return;
		}
		int nf = factor % array.length;
		int[] result = new int[array.length];
		int n = result.length;
		int j = 0;
		for (int i = n - nf; i < n; i++) {
			result[j++] = array[i];
		}
		for (int i = 0; i < n - nf; i++) {
			result[j++] = array[i];
		}
	}

	/**
	 * Check whether the array is decreasing or incresing only.
	 *
	 */
	public static boolean isMonotonic(int[] A) {
		boolean checkIncreasing = A[0] <= A[A.length - 1];
		for (int i = 0, j = 1; j < A.length; i++, j++) {
			if (checkIncreasing) {
				if (A[i] > A[j]) {
					return false;
				}
			} else {
				if (A[i] < A[j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Medium: Add one to number represented as array of digits.
	 *
	 */
	public static int[] plusOne(int[] numb) {
		int carry = 1;
		int[] result = new int[numb.length];
		for (int i = numb.length - 1; i >= 0; i--) {
			int orig = carry + numb[i];
			int next = orig % 10;
			carry = orig / 10;
			result[i] = next;
		}
		if (carry != 0) {
			int[] newres = new int[numb.length + 1];
			newres[0] = carry;
			for (int i = 0; i < result.length; i++) {
				newres[i + 1] = result[i];
			}
			return newres;
		}
		return result;
	}
	
	/**
	 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
	 * sum equals the given sum.
	 *
	 * Note: A leaf is a node with no children.
	 */
	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		List<Integer> current = new ArrayList<>();
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node.left == null && node.right == null) {
				List<Integer> toAdd = new ArrayList<>();
				toAdd.addAll(current);
				toAdd.add(node.val);
				if (!result.isEmpty()) {
					List<Integer> last = result.get(result.size() - 1);
					List<Integer> first = result.get(0);
					int backtrack = first.size() - toAdd.size();
					for (int i = 0; i < backtrack; i++) {
						toAdd.add(i, last.get(i));
					}
				}
				result.add(toAdd);
				current = new ArrayList<>();
			} else {
				current.add(node.val);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		for (int i = 0; i < result.size(); i++) {
			int currSum = 0;
			for (int j = 0; j < result.get(i).size(); j++) {
				currSum += result.get(i).get(j);
			}
			if (currSum != sum) {
				result.remove(i);
				i--;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		//TreeNode node = constructBinaryTree();
		TreeNode node2 = constructBinaryTree();
		// char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A',
		// 'D', 'E', 'E' } };
		List<Employee> employees = new LinkedList<>();
		Employee emp = new Employee();
		emp.id = 1;
		emp.importance = 3;
		Employee emp2 = new Employee();
		emp2.id = 2;
		emp2.importance = 4;
		employees.add(emp2);
		LinkedList<Integer> ids = new LinkedList<>();
		ids.add(emp2.id);
		emp.subordinates = ids;
		employees.add(emp);
		employees.add(emp2);
		System.out.println(getImportance(employees, 1));

		pathSum(node2, 1);
	}
}