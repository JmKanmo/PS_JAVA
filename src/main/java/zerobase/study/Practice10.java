package zerobase.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Practice10 {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String s = bufferedReader.readLine();
            int N = Integer.parseInt(s.split(" ")[0]);
            int K = Integer.parseInt(s.split(" ")[1]);
            int[][] dp = new int[101][100001];
            int[] w = new int[N + 1];
            int[] v = new int[N + 1];


            for (int i = 1; i <= N; i++) {
                String[] splited = bufferedReader.readLine().split(" ");
                w[i] = Integer.parseInt(splited[0]);
                v[i] = Integer.parseInt(splited[1]);
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    dp[i][j] = dp[i - 1][j];

                    if (j - w[i] >= 0) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                    }
                }
            }
            System.out.println(dp[N][K]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}