package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prob1874 {
    static int N;
    static List<Integer> nList = new ArrayList<>();

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 1; i <= N; i++) {
                int number = Integer.parseInt(bufferedReader.readLine());
                nList.add(number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        Stack<Integer> stack = new Stack<>();
        Stack<Character> answer = new Stack<>();
        int counter = 0;

        for (Integer number : nList) {
            if (counter > number && stack.isEmpty()) {
                System.out.println("NO");
                return;
            }

            while (stack.isEmpty() || stack.peek() < number) {
                answer.push('+');
                stack.push(++counter);
            }

            while (!stack.isEmpty() && stack.peek() >= number) {
                answer.push('-');
                stack.pop();
            }
        }

        // 정답출력
        Stack<Character> reversed_st = new Stack<>();

        while (answer.isEmpty() != true) {
            reversed_st.push(answer.pop());
        }

        while (reversed_st.isEmpty() != true) {
            System.out.println(reversed_st.pop());
        }
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}
