package zerobase.part3;
import java.util.HashMap;

class Solution2 {
    public int[] solution(String[] words, String[] queries) {
        Trie[] leftTrie = new Trie[words.length]; // left -> right
        Trie[] rightTrie = new Trie[words.length]; // right -> left
        int[] answer = new int[queries.length];
        String[][] querySets = new String[2][queries.length];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder rightWordStrBuilder = new StringBuilder();

            leftTrie[i] = new Trie();
            leftTrie[i].insert(word);

            for (int j = word.length() - 1; j >= 0; j--) {
                rightWordStrBuilder.append(word.charAt(j));
            }

            rightTrie[i] = new Trie();
            rightTrie[i].insert(rightWordStrBuilder.toString());
        }

        for(int i=0; i < queries.length; i++) {
            String query = queries[i];
            String nQuery = query.replaceAll("\\*", "");
            StringBuilder rightWordStrBuilder = new StringBuilder();

            querySets[0][i] = (nQuery);

            for (int j = nQuery.length() - 1; j >= 0; j--) {
                rightWordStrBuilder.append(nQuery.charAt(j));
            }

            querySets[1][i] = rightWordStrBuilder.toString();
        }

        for (int i = 0; i < queries.length; i++) {
            boolean flag = false; // true <- 왼쪽에*,  false <- 오른쪽에 *
            String query = queries[i];
            int hitCnt = 0;

            if (query.charAt(0) == '*') {
                flag = true;
            }

            if (query.charAt(query.length() - 1) == '*') {
                flag = false;
            }

            if (flag) {
                // right trie
                for (int j = 0; j < rightTrie.length; j++) {
                    if (words[j].length() == (querySets[1][i].length()+1) && rightTrie[j].partialSearch(querySets[1][i])) {
                        hitCnt++;
                    }
                }
            } else if(!flag){
                // left trie
                for (int j = 0; j < leftTrie.length; j++) {
                    if (words[j].length() == (querySets[0][i].length()+1) &&  leftTrie[j].partialSearch(querySets[0][i])) {
                        hitCnt++;
                    }
                }
            }
            answer[i] = hitCnt;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.solution(new String[]{"hello", "hell", "good", "goose", "children", "card", "teachable"}
        , new String[] {"hell*", "goo*", "*able", "qua*"});
    }
}

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    void insert(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            cur.map.putIfAbsent(ch, new Node());
            cur = cur.map.get(ch);

            if (i == str.length() - 1) {
                cur.isTerminal = true;
                break;
            }
        }
    }

    // 완전한 단어를 찾는 search 메소드
    boolean search(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (cur.map.containsKey(ch)) {
                cur = cur.map.get(ch);
            } else {
                return false;
            }

            if (i == str.length() - 1) {
                if (!cur.isTerminal) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean partialSearch(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (cur.map.get(ch) == null) {
                return false;
            }
            cur = cur.map.get(ch);
        }
        return true;
    }

    // true : 삭제 성공, false : 삭제 실패
    public boolean delete(String str) {
        return delete(root, str, 0);
    }

    public boolean delete(Node node, String str, int idx) {
        char ch = str.charAt(idx);

        if (!node.map.containsKey(ch)) {
            return false;
        }

        Node cur = node.map.get(ch);
        idx++;

        if (idx == str.length()) {
            if (!cur.isTerminal) {
                return false;
            }

            cur.isTerminal = false;

            if (cur.map.isEmpty()) {
                node.map.remove(ch);
            }
        } else {
            if (!this.delete(cur, str, idx)) {
                return false;
            }

            if (!cur.isTerminal && cur.map.isEmpty()) {
                node.map.remove(ch);
            }
        }
        return true;
    }

    static class Node {
        HashMap<Character, Node> map;
        boolean isTerminal;

        Node() {
            map = new HashMap<>();
            isTerminal = false;
        }
    }
}