package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prob1149 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board = new int[1001][3];
    static int[][] dp = new int[1001][3];
    static int N;

    static void init() {
        try {
            N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < N; i++) {
                String[] splited = bufferedReader.readLine().split(" ");

                for (int j = 0; j < splited.length; j++) {
                    board[i][j] = Integer.parseInt(splited[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dp[i][0] = board[i][0];
                dp[i][1] = board[i][1];
                dp[i][2] = board[i][2];
            } else {
                dp[i][0] = (board[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]));
                dp[i][1] = (board[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]));
                dp[i][2] = (board[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]));
            }
        }

        System.out.println(Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}
