package zerobase;

public class zerobase_practice {

//    public void solution1() {
//        String[] strs = {"apple", "april", "app", "ace", "bear", "best"};
//
//        Trie trie = new Trie();
//
//        for (String str : strs) {
//            trie.insert(str);
//        }
//
//        System.out.println(trie.partialSearch("app")); // true
//        System.out.println(trie.partialSearch("be")); // true
//        System.out.println(trie.partialSearch("pre")); // false
//    }
//
//    public void solution2() {
//        String sentence = "the cattle was rattled by the battery";
//        StringBuilder stringBuilder = new StringBuilder();
//        String[] dictionary = {"cat", "bat", "rat"};
//
//        for (String word : sentence.split(" ")) {
//            Trie trie = new Trie();
//            trie.insert(word);
//
//            boolean flag = false;
//            String str = null;
//
//            for (String dict : dictionary) {
//                if (trie.partialSearch(dict)) {
//                    flag = true;
//                    str = dict;
//                    break;
//                }
//            }
//
//            if (flag) {
//                stringBuilder.append(" " + str);
//            } else {
//                stringBuilder.append(" " + word);
//            }
//        }
//        System.out.println(stringBuilder);
//    }
//
//
//    public void solution3() {
//        String[] strs = {"apple", "banana", "kiwi"};
//        String[] targets = {"applk", "bpple", "apple", "banan", "kiww"};
//        Trie trie = new Trie();
//
//        for (String str : strs) {
//            trie.insert(str);
//        }
//
//        for (String target : targets) {
//            boolean ret = examineWord(trie.root, target, 0, false);
//            System.out.println(ret + ", ");
//        }
//    }
//
//    // 재귀함수를 작성하는 것이 중요
//    public boolean examineWord(Trie.Node node, String target, int i, boolean flag) {
//        if (i < target.length()) {
//            if (node.map.containsKey(target.charAt(i))) {
//                if (examineWord(node.map.get(target.charAt(i)), target, i + 1, flag)) {
//                    return true;
//                }
//            }
//
//            if (!flag) {
//                for (char ch : node.map.keySet()) {
//                    if (ch != target.charAt(i) && examineWord(node.map.get(ch), target, i + 1, true)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }
//        return flag && node.isTerminal;
//    }
//
//    public static void main(String[] args) {
//        zerobase_practice solution = new zerobase_practice();
//        solution.solution3();
//    }
}
