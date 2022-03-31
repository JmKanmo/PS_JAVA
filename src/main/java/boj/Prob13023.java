package boj;

import java.util.*;

/**
 * Prob 13023, ABCDE
 * DFS
 */
public class Prob13023 {
    static int N, M;
    static Scanner scanner = new Scanner(System.in);
    static List<Integer> GP[] = new ArrayList[2001];
    static boolean[] visited = new boolean[2001];
    static boolean answer;

    static void init() {
        String[] sArr = scanner.nextLine().split(" ");

        N = Integer.parseInt(sArr[0]);
        M = Integer.parseInt(sArr[1]);

        for (int i = 0; i < M; i++) {
            String[] strArr = scanner.nextLine().split(" ");
            int a = Integer.parseInt(strArr[0]);
            int b = Integer.parseInt(strArr[1]);

            if (GP[a] == null) {
                GP[a] = new ArrayList<>();
            }

            GP[a].add(b);

            if (GP[b] == null) {
                GP[b] = new ArrayList<>();
            }

            GP[b].add(a);
        }
    }

    static void dfs(int pos, int depth) {
        if (depth >= 5) {
            answer = true;
            return;
        }

        List<Integer> l = GP[pos];

        if(l == null) {
            return;
        }

        for (Integer n : l) {
            if (visited[n] != true) {
                visited[n] = true;
                dfs(n, depth + 1);
                visited[n] = false;
            }
        }
    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            if(answer != true) {
                visited[i] = true;
                dfs(i, 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();

        if (answer) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
