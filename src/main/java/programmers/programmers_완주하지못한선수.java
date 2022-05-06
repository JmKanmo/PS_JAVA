package programmers;

import java.util.HashMap;
import java.util.Map;

public class programmers_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> participantMap = new HashMap<>();


        for (String str : participant) {
            if (participantMap.get(str) == null) {
                participantMap.put(str, 1);
            } else {
                Integer n = participantMap.remove(str);
                participantMap.put(str, n + 1);
            }
        }

        for (String str : completion) {
            if (participantMap.get(str) != null) {
                Integer n = participantMap.remove(str);
                participantMap.put(str, n - 1);
            }
        }

        for (String str : completion) {
            if (participantMap.get(str) > 0) {
                answer = str;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
