class TrieNode {
    TrieNode[] children;
    boolean isEnd = false;

    public TrieNode() {
        children = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();    
    }
    
    public void insert(String word) {
        TrieNode crawl = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (crawl.children[ch - 'a'] == null) {
                crawl.children[ch - 'a'] = new TrieNode();
            }
            crawl = crawl.children[ch - 'a'];
        }

        crawl.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode crawl = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (crawl.children[ch - 'a'] == null) return false;
            crawl = crawl.children[ch - 'a'];
        }

        return crawl.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode crawl = root;
        for (int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);

            if (crawl.children[ch - 'a'] == null) return false;
            crawl = crawl.children[ch - 'a'];
        }

        return true;
    }
}