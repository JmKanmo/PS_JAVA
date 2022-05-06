package zerobase.study;

import java.util.HashMap;
import java.util.Map;

public class Practice1 {
    Map<String, Integer> map = new HashMap<>();
    String[] strArr = {"A", "E", "I", "O", "U"};
    int cnt;

    void init(int depth, String str) {
        if(!str.equals("")) {
            map.put(str, ++cnt);
        }

        if (depth >= strArr.length) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            init(depth + 1, str + strArr[i]);
        }
    }

    public int solution(String word) {
        init(0,"");
        int answer = map.get(word);
        return answer;
    }

    public static void main(String[] args) {
    }
}
