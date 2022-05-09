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

