package boj;

import java.io.*;
import java.util.*;

public class Prob1764 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static Set<String> aSet = new HashSet<>();
    static Set<String> bSet = new HashSet<>();

    static void init() {
        try {
            String[] parsed = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(parsed[0]);
            M = Integer.parseInt(parsed[1]);

            for (int i = 0; i < N; i++) {
                aSet.add(bufferedReader.readLine());
            }

            for (int j = 0; j < M; j++) {
                bSet.add(bufferedReader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        TreeSet<String> overWrapSet = new TreeSet<String>((a,b) -> {
            return a.compareTo(b);
        });

        int aSetSize = aSet.size();
        int bSetSize = bSet.size();

        if (aSetSize < bSetSize) {
            for (String str : aSet) {
                if (bSet.contains(str)) {
                    overWrapSet.add(str);
                }
            }
        } else {
            for (String str : bSet) {
                if (aSet.contains(str)) {
                    overWrapSet.add(str);
                }
            }
        }

        try {
            bufferedWriter.write(overWrapSet.size()+"\n");

            for (String str : overWrapSet) {
                bufferedWriter.write(str+"\n");
            }

            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}
