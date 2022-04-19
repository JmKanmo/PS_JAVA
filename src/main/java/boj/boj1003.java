package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1003 {
    static Pair[] pairs = new Pair[41];
    static boolean[] visited = new boolean[41];

    static void solve() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < T; i++) {
                int N = Integer.parseInt(bufferedReader.readLine());
                visited = new boolean[41];
                pairs = new Pair[41];

                for (int j = 0; j < pairs.length; j++) {
                    pairs[j] = new Pair();
                }
                Pair pair = fibonacci(N);
                System.out.println(pair.zero + " " + pair.one);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Pair fibonacci(int n) {
        if (visited[n]) {
            return pairs[n];
        }

        if (n == 1) {
            visited[1] = true;
            pairs[1].one++;
            return pairs[1];
        } else if (n == 0) {
            visited[0] = true;
            pairs[0].zero++;
            return pairs[0];
        }

        visited[n] = true;

        Pair p1 = fibonacci(n - 1);
        Pair p2 = fibonacci(n - 2);

        pairs[n].one = p1.one + p2.one;
        pairs[n].zero = p1.zero + p2.zero;
        return pairs[n];
    }

    public static void main(String[] args) {
        solve();
    }

    static class Pair {
        int one;
        int zero;

        Pair() {
        }
    }
}
