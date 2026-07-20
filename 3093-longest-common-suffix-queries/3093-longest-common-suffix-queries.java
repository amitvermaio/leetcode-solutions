class TrieNode {
    TrieNode[] children;
    int idx;
    boolean isEnd;
    public TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
        idx = -1;
    }
}

class Trie {
    public void insert(TrieNode root, String word, int idx, String[] words) { // as suffix
        TrieNode ptr = root;
        if (ptr.idx == -1 || words[ptr.idx].length() > word.length()) {
            ptr.idx = idx;
        }

        for (int i=word.length()-1; i>=0; i--) {
            char ch = word.charAt(i);

            if (ptr.children[ch - 'a'] == null) {
                ptr.children[ch - 'a'] = new TrieNode();
            }
            ptr = ptr.children[ch - 'a'];
            if (ptr.idx==-1 || words[ptr.idx].length() > word.length()) {
                ptr.idx = idx;
            }
        }

        ptr.isEnd = true;
    }

    public int query(TrieNode root, String word) {
        TrieNode ptr = root;
        int min = ptr.idx;
        for (int i=word.length()-1; i>=0; i--) {
            char ch = word.charAt(i);

            if (ptr.children[ch - 'a'] == null) {
                return min;
            }

            ptr = ptr.children[ch - 'a'];
            min = ptr.idx;
        }

        return ptr.idx;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        TrieNode root = new TrieNode();
        int n = wordsContainer.length;
        
        for (int i=0; i<n; i++) {
            String word = wordsContainer[i];
            trie.insert(root, word, i, wordsContainer);
        }    

        n = wordsQuery.length;
        int[] res = new int[n];
        int idx = 0;
        for (String word : wordsQuery) {
            res[idx++] = trie.query(root, word);
        }

        return res;
    }
}