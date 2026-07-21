class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    public TrieNode() {
        children = new TrieNode[10];
        isEnd = false;
    }
}

class Trie {
    public void insert(TrieNode root, String word) {
        TrieNode ptr = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.children[ch - '0'] == null) {
                ptr.children[ch - '0'] = new TrieNode();
            }
            ptr = ptr.children[ch - '0'];
        }
        ptr.isEnd = true;
    }

    public int prefixLength(TrieNode root, String word) {
        TrieNode ptr = root;
        int length = 0;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.children[ch - '0'] == null) {
                return length;
            }
            ptr = ptr.children[ch - '0'];
            length++;
        }

        return length;
    }
}

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie trie = new Trie();
        TrieNode root = new TrieNode();
        for (int num : arr2) {
            trie.insert(root, num+"");
        }

        int ans = 0;
        for (int num : arr1) {
            int len = trie.prefixLength(root, num+"");
            ans = Math.max(ans, len);
        }

        return ans;
    }
}