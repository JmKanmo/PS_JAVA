package zerobase.part3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution1 {
    public String[][] solution(String[] titles, String[] lyrics, String[] problems) {
        String[][] answer = new String[problems.length][];
        Trie[] trieArr = new Trie[titles.length];

        for (int i = 0; i < trieArr.length; i++) {
            String title = titles[i];
            String lyric = lyrics[i];
            trieArr[i] = new Trie();
            trieArr[i].insert(lyric);
        }

        for (int i = 0; i < problems.length; i++) {
            List<String> musicList = new ArrayList<>();
            String problem = problems[i]; // 일치해야하는 가사

            for (int j = 0; j < titles.length; j++) {
                String title = titles[j];
                String lyric = lyrics[j];
                Trie trie = new Trie();

                boolean ret = trieArr[j].partialSearch(problem);

                if (ret == true) {
                    musicList.add(title);
                }
            }

            answer[i] = new String[musicList.size()];

            for (int k = 0; k < musicList.size(); k++) {
                answer[i][k] = musicList.get(k);
            }
        }
        return answer;
    }

////    public static void main(String[] args) {
////        Solution solution = new Solution();
////        System.out.println(solution.solution(new String[]{"아모르파티", "아기상어", "올챙이와개구리", "산다는건"}, new String[]{"산다는게다그런거지누구나빈손으로와...(후략)",
////                        "아기상어뚜루루뚜루귀여운뚜루루뚜루...(후략)",
////                        "개울가에올챙이한마리꼬물꼬물헤엄치다...(후략)",
////                        "산다는건다그런거래요힘들고아픈날도많지만...(후략)"},
////                new String[]{"산다",
////                        "아기상어",
////                        "올챙이"}));
////    }
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

