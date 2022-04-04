package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj14889 {
    static int N;
    static int[][] board = new int[22][22];
    static boolean[] visited = new boolean[22];
    static int answer = 987654321;
    static List<Integer> list = new ArrayList<>();

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 1; i <= N; i++) {
                String[] s = bufferedReader.readLine().split(" ");

                for (int j = 1; j <= s.length; j++) {
                    int n = Integer.parseInt(s[j - 1]);
                    board[i][j] = n;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int getMinimumDiff(List<Integer> list) {
        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            int idx1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                int idx2 = list.get(j);
                int v = board[idx1][idx2] + board[idx2][idx1];
                sum += v;
            }
        }
        return sum;
    }

    static void dfs(int idx, int depth) {
        if (depth >= N / 2) {
            List<Integer> start_list = new ArrayList<>();
            List<Integer> link_list = new ArrayList<>();

            // list에 담긴 idx 값으로 능력치 구하기
            for (int i = 1; i <= N; i++) {
                if (visited[i]) {
                    start_list.add(i);
                } else {
                    link_list.add(i);
                }
            }
            answer = Math.min(answer, Math.abs(getMinimumDiff(start_list) - getMinimumDiff(link_list)));
            return;
        }

        for (int i = idx; i <= N; i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    static void solve() {
        dfs(1, 0);
        System.out.println(answer);
    }

    public static void main(String[] args) {
        init();
        solve();
    }
}