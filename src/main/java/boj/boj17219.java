package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class boj17219 {
    static Map<String, String> hashMap = new HashMap<>();
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;

    static void solve() {
        try {
            String[] parsed = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(parsed[0]);
            M = Integer.parseInt(parsed[1]);

            for (int i = 0; i < N; i++) {
                String[] _parsed = bufferedReader.readLine().split(" ");
                hashMap.put(_parsed[0], _parsed[1]);
            }

            for (int i = 0; i < M; i++) {
                String output = bufferedReader.readLine();
                bufferedWriter.write(hashMap.get(output)+"\n");
            }
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
