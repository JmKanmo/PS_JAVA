package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prob1759 {
    static int L, C;
    static List<String> alphaList = new ArrayList<>();
    static List<String> list = new ArrayList<>();
    static Set<String> moeumSet = new HashSet<>();

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] parsed = bufferedReader.readLine().split(" ");
            L = Integer.parseInt(parsed[0]);
            C = Integer.parseInt(parsed[1]);
            parsed = bufferedReader.readLine().split(" ");

            for (int i = 0; i < parsed.length; i++) {
                list.add(parsed[i]);
            }
            Collections.sort(list);
            moeumSet.addAll(Arrays.asList("a", "e", "i", "o", "u"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve(int n, int depth) {
        if (depth >= L) {
            // TODO
            int hitMoeum = 0;
            int hitZaeum = 0;
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < alphaList.size(); i++) {
                if (moeumSet.contains(alphaList.get(i))) {
                    hitMoeum++;
                } else {
                    hitZaeum++;
                }
                stringBuilder.append(alphaList.get(i));
            }

            if (hitMoeum >= 1 && hitZaeum >= 2) {
                System.out.println(stringBuilder);
            }
            return;
        }

        for (int i = n; i < list.size(); i++) {
            alphaList.add(list.get(i));
            solve(i + 1, depth + 1);
            alphaList.remove(alphaList.size() - 1);
        }
    }

    public static void main(String[] args) {
        init();
        solve(0, 0);
    }
}
