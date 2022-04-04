package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public Stack<String> getStrList(int n, int number) {
        int cnt = 0;
        Stack<String> ret = new Stack<>();

        while (number / 2 != 1) {
            int mok = number / 2;
            int div = number % 2;
            ret.push(div == 0 ? " " : "#");
            number = mok;
            cnt++;
        }

        ret.push(number % 2 == 0 ? " " : "#");
        ret.push(number / 2 == 0 ? " " : "#");

        return ret;
    }


    // => 숫자 -> 0과 1로 이루어진 배열을 출력.
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Stack<String> ret = main.getStrList(5, 20);

        while (ret.isEmpty() != true) {
            System.out.print(ret.pop());
        }
    }
}
