package zerobase.study;

public class Practice3 {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= s.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder(); // 압축 문자열 저장
            int repeatCnt = 1;
            String prevSubStr = "";

            if (s.length() < i) {
                break;
            }

            for (int j = 0; j < s.length(); ) {
                String subStr = "";

                if ((j + i) > s.length()) {
                    // str.length()- j
                    subStr = s.substring(j, j + (s.length() - j));
                } else {
                    subStr = s.substring(j, j + i);
                }

                if (prevSubStr.equals(subStr)) {
                    repeatCnt++;
                } else {
                    if (!prevSubStr.isEmpty()) {
                        stringBuilder.append((repeatCnt == 1 ? "" : repeatCnt) + prevSubStr);
                    }
                    prevSubStr = subStr;
                    repeatCnt = 1;
                }
                j += i;

                if (j >= s.length()) {
                    stringBuilder.append((repeatCnt == 1 ? "" : repeatCnt) + (prevSubStr.isEmpty() ? subStr : prevSubStr));
                }
            }
            answer = Math.min(answer, stringBuilder.length());
        }
        return answer;
    }

    public static void main(String[] args) {
        Practice3 practice3 = new Practice3();
        System.out.println(practice3.solution("xababcdcdababcdcd"));
    }
}
