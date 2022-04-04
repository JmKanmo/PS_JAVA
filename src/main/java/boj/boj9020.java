package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj9020 {
    static boolean[] primes = new boolean[10001];

    // 소수 구하기
    static void init() {
        primes[1] = true;

        for (int i = 2; i <= Math.sqrt(10000); i++) {
            for (int j = i * 2; j <= 10000; j += i)
                primes[j] = true;
        }
    }

    static void solve(int n) {
        int min = Integer.MAX_VALUE;
        int MIN_1 = 0, MIN_2 = 0;

        for (int i = 2; i <= n; i++) {
            if (primes[i] == false) {
                int j = n - i;
                if (primes[j] == false) {
                    // 두 소수의 합
                    int diff = Math.abs(i - j);

                    if (diff < min) {
                        MIN_1 = i;
                        MIN_2 = j;
                        min = diff;
                    }
                }
            }
        }
        System.out.println(MIN_1 +" " + MIN_2);
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            init();

            int t = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(bufferedReader.readLine());
                solve(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
