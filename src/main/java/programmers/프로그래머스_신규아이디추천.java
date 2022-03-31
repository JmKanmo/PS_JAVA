package programmers;

public class 프로그래머스_신규아이디추천 {
    public String solution(String new_id) {
        String answer = "";
        StringBuilder stringBuilder = new StringBuilder();
        answer = new_id.toLowerCase();

        for (int i = 0; i < answer.length(); i++) {
            char ch = answer.charAt(i);
            if (Character.isLowerCase(ch) || Character.isDigit(ch) ||
                    (ch == '-' || ch == '_' || ch == '.')) {
                stringBuilder.append(ch);
            }
        }
        answer = stringBuilder.toString();
        stringBuilder = new StringBuilder();

        for (int i = 0, dot_cnt = 0; i < answer.length(); i++) {
            char ch = answer.charAt(i);

            if (ch != '.') {
                dot_cnt = 0;
                stringBuilder.append(ch);
            } else if (ch == '.') {
                if (dot_cnt == 0) {
                    stringBuilder.append(ch);
                }
                dot_cnt++;
            }
        }
        answer = stringBuilder.toString();

        if(!answer.isEmpty() && answer.charAt(0) == '.') {
         answer = answer.substring(1,answer.length());
        }

        if(!answer.isEmpty() && answer.charAt(answer.length()-1) == '.')
            answer = answer.substring(0,answer.length()-1);

        if(answer.isEmpty()) {
            answer = "a";
        }

        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
        }

        if(answer.charAt(answer.length()-1) == '.')
            answer = answer.substring(0,answer.length()-1);

        if(answer.length() <=2) {
            char lastch = answer.charAt(answer.length()-1);
            StringBuilder stringBuilder1 = new StringBuilder(answer);

            while(stringBuilder1.length() <3) {
                stringBuilder1.append(lastch);
            }
            answer = stringBuilder1.toString();
        }
        return answer;
    }

    public static void main(String[] args) {
        try {
            프로그래머스_신규아이디추천 main = new 프로그래머스_신규아이디추천();
            System.out.println(main.solution("abcdefghijklmn.p"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}