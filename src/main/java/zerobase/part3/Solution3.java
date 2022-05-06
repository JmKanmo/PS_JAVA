package zerobase.part3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution3 {
//    public String[][] solution(String[] words, String[] queries) {
//        String[][] answer = {};
//        Pointer[] pointers = new Pointer[queries.length];
//        Trie[] tries = new Trie[words.length];
//
//        for (int i = 0; i < queries.length; i++) {
//            String query = queries[i];
//            pointers[i] = findCh(query, '?');
//        }
//
//        for (int i = 0; i < words.length; i++) {
//            tries[i] = new Trie();
//            tries[i].insert(words[i]);
//        }
//
//        answer = new String[queries.length][];
//
//        for (int i = 0; i < queries.length; i++) {
//            List<String> strList = new ArrayList<>();
//            String query = queries[i];
//            int queryCnt = query.length();
//            Pointer pointer = pointers[i];
//
//            for (int j = 0; j < words.length; j++) {
//                int wordCnt = words[j].length();
//                String word = words[j];
//                Trie trie = tries[j];
//
//                if (queryCnt == wordCnt && trie.partialSearch(pointer.str)) {
//                    strList.add(word);
//                }
//            }
//            answer[i] = new String[strList.size()];
//
//            for (int k = 0; k < strList.size(); k++) {
//                answer[i][k] = strList.get(k);
//            }
//        }
//        return answer;
//    }
//
//    public Pointer findCh(String str, char findCh) {
//        int answer = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (char ch : str.toCharArray()) {
//            if (ch == findCh) answer++;
//            else {
//                stringBuilder.append(ch);
//            }
//        }
//        return new Pointer(stringBuilder.toString(), answer);
//    }
//
////    public static void main(String[] args) {
////        Solution3 solution3 = new Solution3();
////        solution3.solution(new String[]{"hello", "hear", "hell", "good", "goose", "children", "card", "teachable"}
////        , new String[]{"he??", "g???", "childre?", "goo????", "?"});
////    }
}
//
//class Pointer {
//    String str;
//    int chCnt;
//
//    Pointer() {
//    }
//
//    public Pointer(String str, int chCnt) {
//        this.str = str;
//        this.chCnt = chCnt;
//    }
//}
//
//class Trie {
//    Node root;
//
//    Trie() {
//        root = new Node();
//    }
//
//    void insert(String str) {
//        Node cur = root;
//
//        for (int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//
//            cur.map.putIfAbsent(ch, new Node());
//            cur = cur.map.get(ch);
//
//            if (i == str.length() - 1) {
//                cur.isTerminal = true;
//                break;
//            }
//        }
//    }
//
//    // 완전한 단어를 찾는 search 메소드
//    boolean search(String str) {
//        Node cur = root;
//
//        for (int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//
//            if (cur.map.containsKey(ch)) {
//                cur = cur.map.get(ch);
//            } else {
//                return false;
//            }
//
//            if (i == str.length() - 1) {
//                if (!cur.isTerminal) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    boolean partialSearch(String str) {
//        Node cur = root;
//
//        for (int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//
//            if (cur.map.get(ch) == null) {
//                return false;
//            }
//            cur = cur.map.get(ch);
//        }
//        return true;
//    }
//
//    // true : 삭제 성공, false : 삭제 실패
//    public boolean delete(String str) {
//        return delete(root, str, 0);
//    }
//
//    public boolean delete(Node node, String str, int idx) {
//        char ch = str.charAt(idx);
//
//        if (!node.map.containsKey(ch)) {
//            return false;
//        }
//
//        Node cur = node.map.get(ch);
//        idx++;
//
//        if (idx == str.length()) {
//            if (!cur.isTerminal) {
//                return false;
//            }
//
//            cur.isTerminal = false;
//
//            if (cur.map.isEmpty()) {
//                node.map.remove(ch);
//            }
//        } else {
//            if (!this.delete(cur, str, idx)) {
//                return false;
//            }
//
//            if (!cur.isTerminal && cur.map.isEmpty()) {
//                node.map.remove(ch);
//            }
//        }
//        return true;
//    }
//
//    static class Node {
//        HashMap<Character, Node> map;
//        boolean isTerminal;
//
//        Node() {
//            map = new HashMap<>();
//            isTerminal = false;
//        }
//    }
//}
