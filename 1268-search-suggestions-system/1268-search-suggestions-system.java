class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    PriorityQueue<String> pq;
    public TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
        pq = new PriorityQueue<>(Collections.reverseOrder());
    }
}

class Trie {
    public void insert(TrieNode root, String word) {
        TrieNode ptr = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);

            if (ptr.children[ch - 'a'] == null) {
                ptr.children[ch - 'a'] = new TrieNode();
            }
            ptr = ptr.children[ch - 'a'];
            if (!ptr.pq.contains(word)) ptr.pq.add(word);
            if (ptr.pq.size() > 3) {
                ptr.pq.poll();
            }
        }
        ptr.isEnd = true;
    }

    public List<String> search(TrieNode root, String word) {
        TrieNode ptr = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (ptr.children[ch - 'a'] == null) return new ArrayList<>();
            ptr = ptr.children[ch - 'a'];
        }
        List<String> list = new ArrayList<>();
        PriorityQueue<String> temp = new PriorityQueue<>(Collections.reverseOrder());
        while (!ptr.pq.isEmpty()) {
            String mw = ptr.pq.poll();
            temp.add(mw);
            list.add(mw);
        }

        ptr.pq = temp;

        Collections.reverse(list);
        return list;
    }
}

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        Trie trie = new Trie();
        for (String p : products) {
            trie.insert(root, p);
        }

        List<List<String>> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            sb.append(c);
            ans.add(trie.search(root, sb.toString()));
        }
        return ans;
    }
}