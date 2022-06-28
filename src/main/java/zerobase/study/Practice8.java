package zerobase.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Practice8 {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(bufferedReader.readLine());
            int M = Integer.parseInt(bufferedReader.readLine());
            Set<Integer> set = new HashSet<>();

            if (M > 0) {
                String[] parsed = bufferedReader.readLine().split(" ");

                for (int i = 0; i < parsed.length; i++) {
                    set.add(Integer.parseInt(parsed[i]));
                }
            }

            if (N == 100) {
                System.out.println(0);
            } else {
                int answer = Math.abs(100 - N);

                for (int i = 0; i < 1000000; i++) {
                    if (checkNumber(i, set)) {
                        continue;
                    }

                    int diff = Math.abs(i - N) + String.valueOf(i).length();
                    answer = Math.min(answer, diff);
                }
                System.out.println(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean checkNumber(int number, Set<Integer> set) {
        String sNumber = String.valueOf(number);

        for (int i = 0; i < sNumber.length(); i++) {
            int n = sNumber.charAt(i) - '0';
            if (set.contains(n)) {
                return true;
            }
        }
        return false;
    }
}
