package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj2644 {
    static int N, M;
    static int p1, p2;

    static int answer = Integer.MAX_VALUE;

    static List<Integer>[] GP = new ArrayList[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            N = Integer.parseInt(bufferedReader.readLine());
            String[] splited = bufferedReader.readLine().split(" ");
            p1 = Integer.parseInt(splited[0]);
            p2 = Integer.parseInt(splited[1]);

            M = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < M; i++) {
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

            dfs(p1, p2, 0);

            if (answer == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void dfs(int start, int target, int depth) {
        visited[start] = true;

        if (start == target) {
            answer = depth;
            return;
        }

        for (int next : GP[start]) {
            if (visited[next] == true) {
                continue;
            }

            if (answer != Integer.MAX_VALUE) {
                break;
            }

            dfs(next, target, depth + 1);
        }
    }
}
