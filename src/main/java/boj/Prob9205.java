package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Prob9205 {
    static int T;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<Pointer> temp = new ArrayList<>();
    static List<Integer>[] gp;

    public static void solve() {
        try {
            T = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < T; i++) {
                int n = Integer.parseInt(bufferedReader.readLine());
                gp = new ArrayList[200];
                temp.clear();

                for (int j = 0; j < n + 2; j++) {
                    String str = bufferedReader.readLine();
                    String[] splited = str.split(" ");
                    int a = Integer.parseInt(splited[0]);
                    int b = Integer.parseInt(splited[1]);
                    temp.add(new Pointer(a, b));
                }

                // 완전 탐색
                for (int j = 0; j < n + 2; j++) {
                    for (int k = j + 1; k < n + 2; k++) {
                        Pointer p1 = temp.get(j);
                        Pointer p2 = temp.get(k);
                        int dist = Math.abs(p2.x - p1.x) + Math.abs(p2.y - p1.y);
                        if (dist <= 1000) {
                            if (gp[j] == null) {
                                gp[j] = new ArrayList<>();
                            }

                            gp[j].add(k);

                            if (gp[k] == null) {
                                gp[k] = new ArrayList<>();
                            }
                            gp[k].add(j);
                        }
                    }
                }
                System.out.println(bfs(n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 0번째는 상근이네 집,  N번째는 펜타포트 락
    static String bfs(int N) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 2];
        q.add(0);
        visited[0] = true;

        while (q.isEmpty() != true) {
            int cur = q.poll();

            if (cur == N + 1) {
                return "happy";
            }

            if(gp[cur] == null) {
                continue;
            }

            for (Integer next : gp[cur]) {
                if(visited[next] != true) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        return "sad";
    }

    public static void main(String[] args) {
        solve();
    }

    static class Pointer {
        int x;
        int y;

        Pointer() {
        }

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
