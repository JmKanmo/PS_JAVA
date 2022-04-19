package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1912 {
    public static void main(String[] args) {
        int N = 0;
        long sum = 0;
        long temp_sum = 0;
        int[] dp = new int[100001];

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            N = Integer.parseInt(bufferedReader.readLine());
            String[] parsed = bufferedReader.readLine().split(" ");

            for (int i = 0; i < parsed.length; i++) {
                dp[i] = Integer.parseInt(parsed[i]);
            }

            sum = dp[0];
            temp_sum = dp[0];

            for (int i = 1; i < N; i++) {
                sum = Math.max(sum + dp[i],dp[i]);
                temp_sum = Math.max(sum,temp_sum);
            }

            System.out.println(temp_sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
