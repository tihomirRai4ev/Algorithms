package com.tihomir.algorithms;

import java.util.HashMap;
import java.util.LinkedList;

public class WordDictionary {

	/**
	 * Trie root node.
	 */
	TrieNode root = new TrieNode();

	/**
	 * TrieNode internal representation.
	 */
	public class TrieNode {

		private HashMap<Character, TrieNode> children;
		private boolean isWord;

		public void setChildrens(HashMap<Character, TrieNode> child) {
			children = child;
		}

		public HashMap<Character, TrieNode> getChildren() {
			return this.children;
		}

		public void setEndOfWord(boolean isEnd) {
			isWord = isEnd;
		}

		public boolean isEndOfWord() {
			return isWord;
		}
	}

	/**
	 * Insert operation implementation.
	 *
	 * @param word
	 */
	public void insert(String word) {
		if (word == null) {
			return;
		}
		TrieNode current = root;

		for (int i = 0; i < word.length(); i++) {
			HashMap<Character, TrieNode> childrens = current.getChildren();
			if (childrens == null) {
				childrens = new HashMap<>();
				current.setChildrens(childrens);
			}
			current = childrens.computeIfAbsent(word.charAt(i), c -> new TrieNode());
		}
		current.setEndOfWord(true);
	}

	/**
	 * Method searches for an element in the Trie.
	 *
	 * @param word
	 * @return true if element is found, false otherwise.
	 */
	public boolean find(String word) {
		return findInternal(word, root);
	}

	/**
	 * Check for specific element from the provided TrieNode node.
	 *
	 * @param word
	 * @param from
	 * @return
	 */
	public boolean findInternal(String word, TrieNode from) {
		TrieNode current = from;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			if (node == null) {
				return false;
			}
			current = node;
		}
		return current.isEndOfWord();
	}

	/**
	 * <p>
	 * Method checks whether the word in format <i><b>{word + '..'}</i></b> can be
	 * retrieved from the Trie. The number of dots is the number of wildcards at the
	 * end of the word. Example: Let Trie contains words: <i>{asd, asdfgh, a}</i>
	 * for input word <i><b>'a.'</i></b> method will return false. For input word
	 * <i><b>'a..'</i></b> method should match asd and return true.
	 * </p>
	 *
	 * @see <code>find</code>
	 *
	 * @param word
	 * @return true if word is matched, false otherwise.
	 */
	private boolean findSuffix(String word) {
		TrieNode current = root;
		StringBuilder wordOnly = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != '.') {
				wordOnly.append(word.charAt(i));
			}
		}

		int suffixLength = word.length() - wordOnly.length();

		for (int i = 0; i < wordOnly.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			if (node == null) {
				return false;
			}
			current = node;
		}

		LinkedList<TrieNode> layer = new LinkedList<>();
		layer.add(current);
		for (int i = 0; i < suffixLength; i++) {
			LinkedList<TrieNode> next = new LinkedList<>();
			while (!layer.isEmpty()) {
				TrieNode node = layer.remove();
				HashMap<Character, TrieNode> childrens = node.getChildren();
				for (TrieNode curNode : childrens.values()) {
					next.add(curNode);
				}
			}
			layer.addAll(next);
		}

		for (int i = 0; i < layer.size(); i++) {
			if (layer.get(i).isEndOfWord()) {
				return true;
			}
		}

		return false;
	}

	/**
	  <p>
	 * Method checks whether the word in format <i><b>{'..' + word}</i></b> can be
	 * retrieved from the Trie. The number of dots is the number of wildcards at the
	 * beggining of the word. Example: Let Trie contains words: <i>{asd, asdfgh, a}</i>
	 * for input word <i><b>'.d'</i></b> method will return false. For input word
	 * <i><b>'..d'</i></b> method should match asd and return true.
	 * </p>
	 *
	 * @param word
	 * @return true if word is found, false otherwise.
	 */
	private boolean findPrefix(String word) {
		int deepLength = 0;
		StringBuilder wordOnly = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			Character ch = word.charAt(i);
			if (ch == '.') {
				deepLength++;
			} else {
				wordOnly.append(ch);
			}
		}

		LinkedList<TrieNode> layers = new LinkedList<>();
		layers.add(root);
		for (int i = 0; i < deepLength; i++) {
			LinkedList<TrieNode> layer = new LinkedList<>();
			while (!layers.isEmpty()) {
				TrieNode node = layers.remove();
				for (TrieNode next : node.getChildren().values()) {
					layer.add(next);
				}
			}
			layers.addAll(layer);
		}

		for (int i = 0; i < layers.size(); i++) {
			if (findInternal(wordOnly.toString(), layers.get(i))) {
				return true;
			}
		}

		return false;
	}
	
	/** Adds a word into the data structure. */
	public void addWord(String word) {
		insert(word);
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		if (word == null) {
			return false;
		}
		return findWithWildcards(word);
	}
	
	/**
	 * Searches for a word in the Trie. Wildcards are supported. Wildcard can be as
	 * a prefix or suffix. No mixed modes supported. 
	 * 
	 * @return true if word with the specified wildcard is found, false otherwise.
	 */
	public boolean findWithWildcards(String word) {
		if (!word.contains(".")) {
			return find(word);
		}
		if (word.startsWith(".")) {
			return findPrefix(word);
		} else {
			return findSuffix(word);
		}
	}

	public static void main(String[] args) {
		/**
		 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
		 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
		 * 
		 * [[],["a"]]
		 */
		WordDictionary trie = new WordDictionary();
		trie.insert("WordDictionary");
		trie.insert("search");
		System.out.print(trie.search(""));
		System.out.print(trie.search("a"));
	}
}