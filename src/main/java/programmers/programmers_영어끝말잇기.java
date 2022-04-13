package programmers;

import java.util.HashSet;
import java.util.Set;

public class programmers_영어끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = null;
        Set<String> wordSet = new HashSet<>();
        int turn = 1;
        int idx = 1;
        String prev_str = null;

        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            boolean isCheck = check(wordSet, str);

            if(prev_str != null && prev_str.charAt(prev_str.length()-1) != str.charAt(0)) {
                isCheck = true;
            }

            if (isCheck) {
                answer = new int[]{idx, turn};
                break;
            }
            wordSet.add(words[i]);
            prev_str = str;

            idx++;

            if (idx > n) {
                idx = 1;
                turn++;
            }
        }

        if(answer == null) {
            answer = new int[]{0, 0};
        }
        return answer;
    }

    private boolean check(Set<String> wordSet, String str) {
        return wordSet.contains(str) || str.length() <= 1 ? true : false;
    }

    public static void main(String[] args) {
        programmers_영어끝말잇기 main = new programmers_영어끝말잇기();
//        int[] answer = main.solution(3,new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
//        int[]answer = main.solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"});
        int[]answer = main.solution(2,new String[]{"hello", "one", "even", "never", "now", "world", "draw"});
        System.out.println(answer[0] +", " + answer[1]);
    }
}
