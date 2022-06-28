package zerobase.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Practice12 {
    static int N, S, D;
    static List<Integer>[] GP = new ArrayList[100001];

    static int answer;

    static boolean[] visited = new boolean[100001];
    static int[] depths = new int[100001];

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] splited = bufferedReader.readLine().split(" ");

            N = Integer.parseInt(splited[0]);
            S = Integer.parseInt(splited[1]);
            D = Integer.parseInt(splited[2]);

            for (int i = 0; i < N - 1; i++) {
                splited = bufferedReader.readLine().split(" ");
                int a = Integer.parseInt(splited[0]);
                int b = Integer.parseInt(splited[1]);

                if (GP[a] == null) {
                    GP[a] = new ArrayList<>();
                }
                GP[a].add(b);

                if (GP[b] == null) {
                    GP[b] = new ArrayList<>();
                }
                GP[b].add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        dfs(S);
        System.out.println(answer * 2);
    }

    static int dfs(int index) {
        visited[index] = true;
        for (int vertex : GP[index]) {
            if (visited[vertex] == false) {
                depths[index] = Math.max(depths[index], dfs(vertex) + 1);
            }
        }

        if (index != S && depths[index] >= D) {
            answer++;
        }
        return depths[index];
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}