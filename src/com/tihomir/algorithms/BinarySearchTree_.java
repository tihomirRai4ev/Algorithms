package com.tihomir.algorithms;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Simple implementation of Binary Search tree.
 *
 * @author Tihomir Raychev
 *
 */
public class BinarySearchTree_ {

	private Node root;
	private int size; // TODO unused.

	public BinarySearchTree_() {
		size = 0;
	}

	public BinarySearchTree_(int value) {
		root = new Node(value);
		size = 1;
	}

	private static class Node {
		Node left;
		Node right;
		int value;

		Node(int value) {
			this.value = value;
			left = null;
			right = null;
		}
	}

	/**
	 * Method adds new element in the tree.
	 *
	 * @param value
	 */
	public void add(int value) {
		if (root == null) {
			root = new Node(value);
		} else {
			addRec(root, value);
		}
	}

	/**
	 * Internal representation of the logic to add new element in the tree.
	 *
	 * @param node
	 * @param value
	 */
	private void addRec(Node node, int value) {
		if (node.left == null && node.right == null) {
			if (node.value > value) {
				Node left = new Node(value);
				node.left = left;
			} else {
				Node right = new Node(value);
				node.right = right;
			}
			size++;
		} else {
			if (node.value == value) {
				return;
			}
			if (node.value > value) {
				if (node.left == null) {
					Node newNode = new Node(value);
					node.left = newNode;
					size++;
				} else {
					addRec(node.left, value);
				}
			} else {
				if (node.right == null) {
					Node newNode = new Node(value);
					node.right = newNode;
					size++;
				} else {
					addRec(node.right, value);
				}
			}
		}
	}

	void remove(int key) {
		root = removeRec(root, key);
	}

	Node removeRec(Node root, int value) {
		if (root == null) {
			return root;
		}

		if (value < root.value) {
			root.left = removeRec(root.left, value);
		} else if (value > root.value) {
			root.right = removeRec(root.right, value);
		} else {
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			root.value = minValue(root.right);
			root.right = removeRec(root.right, root.value);
		}

		return root;
	}

	public int getHeightIteratively() {
		if (root == null) {
			return 0;
		}
		Stack<ArrayList<Node>> layers = new Stack<ArrayList<Node>>();
		ArrayList<Node> layer = new ArrayList<Node>();
		layer.add(root);
		layers.add(layer);
		int height = 1;
		while (!layers.isEmpty()) {
			ArrayList<Node> nodes = layers.pop();
			ArrayList<Node> nextLayer = new ArrayList<Node>();
			boolean increment = false;
			for (Node node : nodes) {
				if (node.left != null) {
					nextLayer.add(node.left);
					increment = true;
				}
				if (node.right != null) {
					nextLayer.add(node.right);
					increment = true;
				}
				if (increment) {
					height++;
				}
			}
			if (!nextLayer.isEmpty()) {
				layers.add(nextLayer);
			}
		}

		return height;
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0;
		}
		return (1 + Math.max(getHeight(node.left), getHeight(node.right)));
	}

	int minValue(Node root) {
		int minv = root.value;
		while (root.left != null) {
			minv = root.left.value;
			root = root.left;
		}
		return minv;
	}

	public static void main(String[] args) {
		BinarySearchTree_ tree = new BinarySearchTree_();
		tree.add(5);
		tree.add(2);
		tree.add(8);
		tree.add(9);
		tree.add(6);
		tree.add(10);
		tree.add(12);
		tree.add(13);
		tree.remove(8);
		System.out.println(tree.getHeightIteratively());
		System.out.println(tree.getHeight());
	}

}