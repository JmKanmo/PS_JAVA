package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prob11052 {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(bufferedReader.readLine());

            int[] arr = new int[N+1];
            int[] dp = new int[arr.length+1];
            String[] parsed = bufferedReader.readLine().split(" ");

            for (int i = 0; i < parsed.length; i++) {
                arr[i+1] = Integer.parseInt(parsed[i]);
            }

            for (int i = 1; i < arr.length; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] = Math.max(dp[i], dp[i - j] + arr[j]);
                }
            }

            System.out.println(dp[N]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
