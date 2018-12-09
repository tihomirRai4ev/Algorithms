package com.tihomir.algorithms;
import java.util.*;

class Solution {
    static class Trie {
        TrieNode root;
        
        public Trie() {root = new TrieNode();}
        
        public void insert(String word) {
            TrieNode cur = root;
            
            for (int i = 0; i < word.length(); i++) {
                if (cur.children[word.charAt(i) - 'a'] == null)    
                    cur.children[word.charAt(i) - 'a'] = new TrieNode();
                cur = cur.children[word.charAt(i) - 'a'];
            }
            
            cur.endFlag = true;
        }
        
        public String successor(String word) {
            TrieNode cur = root;
            StringBuilder sb = new StringBuilder();
            // find successor, once hit an end flag, meaning there is a key in the dictionary which is also the prefix of the word
            for (int i = 0; i < word.length(); i++) {
                if (cur.children[word.charAt(i) - 'a'] == null)
                    return word;
                sb.append(word.charAt(i));
                if (cur.children[word.charAt(i) - 'a'].endFlag)
                    break;
                else
                    cur = cur.children[word.charAt(i) - 'a'];
            }
            
            return sb.toString();
        }
    }
    
    static class TrieNode {
        TrieNode[] children;
        boolean endFlag;
        
        public TrieNode() {children = new TrieNode[26];}
    }
    
    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String word : dict)
            trie.insert(word);
        
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        
        for (int i = 0; i < words.length; i++) {
            sb.append(trie.successor(words[i]));
            if (i < words.length-1)
                sb.append(" ");
        }
        
        return sb.toString();
        
    }
}