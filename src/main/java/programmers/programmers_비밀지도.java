package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class programmers_비밀지도 {
    // 숫자 -> 이진법 변환
    List<String> getStrList(int n, int number) {
        Stack<Integer> st = new Stack<>();
        List<String> ret = new ArrayList<>();

        while (st.size() < n && number != 1) {
            int mok = number / 2;
            int div = number % 2;
            st.push(div);
            number = mok;
        }

        st.push(number);

        while (st.size() < n) {
            st.push(0);
        }

        while (st.isEmpty() != true) {
            int N = st.pop().intValue();
            ret.add(N == 0 ? " " : "#");
        }
        return ret;
    }

    // 2개의 이진법 배열 원소 병합
    String[] merge(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            List<String> strList1 = getStrList(n, arr1[i]);
            List<String> strList2 = getStrList(n, arr2[i]);

            for (int j = 0; j < n; j++) {
                String s1 = strList1.get(j);
                String s2 = strList2.get(j);
                stringBuilder.append(s1.equals("#") || s2.equals("#") ? "#" : " ");
            }
            answer[i] = stringBuilder.toString();
        }
        return answer;
    }

    // => 숫자 -> 0과 1로 이루어진 배열을 출력.
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = merge(n, arr1, arr2);
        return answer;
    }

    public static void main(String[] args) {
        programmers_비밀지도 main = new programmers_비밀지도();
        for(String str : main.solution(6,new int[]{46,33,33,22,31,50} , new int[]{27,56,19,14,14,10})) {
            System.out.println(str);
        }
    }
}
