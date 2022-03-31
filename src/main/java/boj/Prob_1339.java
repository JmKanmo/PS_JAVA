package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 단어수학
 * 그리디 & 수학
 */
public class Prob_1339 {
    static int N;
    static String[] strArr;
    static Map<Character, Integer> hashMap = new HashMap<>();
    static Map<Character, Integer> nHashMap = new HashMap<>();
    static int MAX_NUMBER = 8;
    static int MAX_ALPHA_NUMBER = 9;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static long answer = 0;

    static void init() {
        try {
            N = Integer.parseInt(bufferedReader.readLine().split(" ")[0]);
            strArr = new String[N];

            for (int i = 0; i < N; i++) {
                strArr[i] = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 수학 + 그리디
    static void solve() {
        Arrays.sort(strArr, (o1,o2) -> {
            if (o1.length() > o2.length()) {
                return -1;
            } else if (o1.length() == o2.length()) {
                return 0;
            } else {
                return 1;
            }
        });

        for (int i = 0; i < strArr.length; i++) {
            for (int j = 0; j < strArr[i].length(); j++) {
                char ch = strArr[i].charAt(j);
                int n = (int) (Math.pow(10, strArr[i].length() - j - 1));

                if (nHashMap.get(ch) == null) {
                    nHashMap.put(ch, n);
                } else {
                    int val = nHashMap.get(ch);
                    val += n;
                    nHashMap.put(ch, val);
                }
            }
        }

        TreeSet<Integer> values = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 == o2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        values.addAll(nHashMap.values());

        for (Integer value : values) {
            for (Iterator it = nHashMap.keySet().iterator(); it.hasNext(); ) {
                Character ch = (Character) it.next();
                Integer nVal = nHashMap.get(ch);

                if (value == nVal) {
                    hashMap.put(ch, MAX_ALPHA_NUMBER--);
                }
            }
        }

        MAX_NUMBER = strArr[0].length();

        while (MAX_NUMBER > 0) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                int strLen = str.length();

                if (strLen >= MAX_NUMBER) {
                    int subLen = strLen - MAX_NUMBER;
                    char ch = str.charAt(subLen);
                    int alphaNum = hashMap.get(ch);
                    answer += ((int) Math.pow(10, MAX_NUMBER - 1) * alphaNum);
                }
            }
            MAX_NUMBER--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        solve();
        System.out.println(answer);
    }
}
