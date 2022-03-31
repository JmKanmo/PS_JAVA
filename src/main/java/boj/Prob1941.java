package boj;

import java.util.*;

/**
 * Prob 1941, 소문난 칠공주
 * DFS,BFS
 */
public class Prob1941 {
    static char[] arr = new char[25];
    static Scanner scanner = new Scanner(System.in);
    static int answer;
    static List<Integer> list = new ArrayList<>();
    static char[][] board = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static boolean[][] temp_visited = new boolean[5][5];
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static void init() {
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            String str = scanner.nextLine();

            for (int j = 0; j < 5; j++) {
                arr[idx++] = str.charAt(j);
                board[i][j] = str.charAt(j);
            }
        }
    }

    static boolean bfs(Position p) {
        int cnt = 0;
        int dasom_cnt = 0;
        Queue<Position> q = new LinkedList<>();
        q.add(p);
        visited[p.idx1][p.idx2] = true;

        while (q.isEmpty() != true) {
            Position curPos = q.poll();
            cnt += 1;
            dasom_cnt = board[curPos.idx1][curPos.idx2] == 'S' ? dasom_cnt + 1 : dasom_cnt; // 다솜

            for (int i = 0; i < 4; i++) {
                Position newPos = new Position(curPos.idx1 + idx_1[i], curPos.idx2 + idx_2[i]);

                if (newPos.idx1 < 0 || newPos.idx1 >= 5 || newPos.idx2 < 0 || newPos.idx2 >= 5)
                    continue;

                if (temp_visited[newPos.idx1][newPos.idx2] == true && visited[newPos.idx1][newPos.idx2] == false) {
                    visited[newPos.idx1][newPos.idx2] = true;
                    q.add(newPos);
                }
            }
        }

        return cnt == 7 && dasom_cnt >= 4;
    }

    static void dfs(int cnt, int idx) {
        if (cnt >= 7) {
            // 가로  /세로 인접 check
            int first = list.get(0);

            for (Integer pos : list) {
                Position p = new Position();
                int idx1 = pos / 5;
                int idx2 = pos % 5;
                p.idx1 = idx1;
                p.idx2 = idx2;
                temp_visited[idx1][idx2] = true;
            }

            // bfs 호출
            if (bfs(new Position(first / 5, first % 5))) {
                answer++;
            }
            initBoard(); // visited,temp_visited initialize
            return;
        }

        for (int i = idx + 1; i < arr.length; i++) {
            list.add(i);
            dfs(cnt + 1, i);
            list.remove(list.size() - 1);
        }
    }

    static void solve() {
        for (int i = 0; i < arr.length; i++) {
            list.add(i);
            dfs(1, i);
            list.remove(list.size() - 1);
        }
    }

    static void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                temp_visited[i][j] = false;
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }

    static class Position {
        int idx1;
        int idx2;

        Position() {
        }

        public Position(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }
}
