package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj6588 {
    static int MAX_PRIME_RANGE = 1000000;
    static boolean[] primes = new boolean[1000001];
    static List<Integer> primeList = new ArrayList<>();

    static void setPrime() {
        primes[1] = true;

        for (int i = 2; i <= Math.sqrt(MAX_PRIME_RANGE); i++) {
            for (int j = i * 2; j <= MAX_PRIME_RANGE; j += i) {
                primes[j] = true;
            }
        }

        for (int i = 1; i <= MAX_PRIME_RANGE; i++) {
            if (primes[i] == false) {
                primeList.add(i);
            }
        }
    }

    static void solve(int n) {
        int MIN = 0;
        int _a = -1, _b = -1;

        for (Integer prime : primeList) {
            if (prime > n) {
                break;
            }

            int diff = n - prime;

            if (!primes[diff]) {
                _a = prime;
                _b = diff;
                break;
            }
        }

        if (_a < 0 && _b < 0) {
            System.out.print("Goldbach's conjecture is wrong.");
        } else {
            System.out.println(n + " = " + _a + " + " + _b);
        }
    }

    static void init() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                int n = Integer.parseInt(bufferedReader.readLine());

                if (n == 0) {
                    break;
                }

                solve(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setPrime();
        init();
    }
}
