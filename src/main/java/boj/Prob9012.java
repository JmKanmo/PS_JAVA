package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Prob9012 {
    static List<String> strList = new ArrayList<>();

    static void solve() {
        for (String str : strList) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                switch (ch) {
                    case '(':
                        stack.push(ch);
                        break;

                    case ')':
                        if (!stack.isEmpty() && stack.peek() == '(') {
                            stack.pop();
                        }else{
                            stack.push(ch);
                        }
                        break;
                }
            }
            if (stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int n = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < n; i++) {
                String line = bufferedReader.readLine();
                strList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}
