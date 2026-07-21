class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    int count;
}

class Trie {
    public TrieNode getNode() {
        TrieNode node = new TrieNode();
        node.children = new TrieNode[26];
        node.isEnd = false;
        node.count = 0;

        return node;
    }

    public void insert(TrieNode root, String word) {
        TrieNode ptr = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.children[ch - 'a'] == null) {
                ptr.children[ch - 'a'] = getNode();
            }
            ptr = ptr.children[ch - 'a'];
            ptr.count += 1;
        }

        ptr.isEnd = false;
    }

    public int score(TrieNode root, String word) {
        TrieNode ptr = root;
        int ans = 0;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            ptr = ptr.children[ch - 'a'];
            ans += ptr.count;
        }

        return ans;
    }
}

class Solution {
    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        TrieNode root = trie.getNode();

        for (String word : words) {
            trie.insert(root, word);
        }

        int n = words.length;
        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            ans[i] = trie.score(root, words[i]);
        }

        return ans;
    }
}