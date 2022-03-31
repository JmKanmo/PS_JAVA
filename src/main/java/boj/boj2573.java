package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj2573 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] board = new int[301][301];
    static int[][] temp_board = null;
    static boolean[][] visited;
    static int[][] moved;
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};
    static int answer = 0;

    static void init() {
        try {
            String _line = bufferedReader.readLine();
            N = Integer.parseInt(_line.split(" ")[0]);
            M = Integer.parseInt(_line.split(" ")[1]);

            for (int i = 0; i < N; i++) {
                String line = bufferedReader.readLine();

                String[] parsed = line.split(" ");
                for (int j = 0; j < parsed.length; j++) {
                    board[i][j] = Integer.parseInt(parsed[j]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int get_melt_ice(Pointer pos) {
        int ice = board[pos.idx1][pos.idx2];

        for (int i = 0; i < 4; i++) {
            Pointer next_pos = new Pointer(pos.idx1 + idx_1[i], pos.idx2 + idx_2[i]);

            if (next_pos.idx1 < 0 || next_pos.idx1 >= N || next_pos.idx2 < 0 || next_pos.idx2 >= M) {
                continue;
            }

            int melt = board[next_pos.idx1][next_pos.idx2];

            if (melt > 0) {
                continue;
            }

            if (ice - 1 < 0) {
                continue;
            }
            ice--;
        }
        return ice;
    }

    static void melt_ice() {
        temp_board = new int[301][301];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                temp_board[i][j] = get_melt_ice(new Pointer(i, j));
            }
        }
    }

    static void copy_ice() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = temp_board[i][j];
            }
        }
    }

    static void bfs(Pointer pos, int number) {
        Queue<Pointer> q = new LinkedList<>();
        visited[pos.idx1][pos.idx2] = true;
        moved[pos.idx1][pos.idx2] = number;
        q.add(pos);

        while (q.isEmpty() != true) {
            Pointer cur_pos = q.poll();

            for (int i = 0; i < 4; i++) {
                Pointer next_pos = new Pointer(cur_pos.idx1 + idx_1[i], cur_pos.idx2 + idx_2[i]);

                if (next_pos.idx1 < 0 || next_pos.idx1 >= N || next_pos.idx2 < 0 || next_pos.idx2 >= M) {
                    continue;
                }

                if (board[next_pos.idx1][next_pos.idx2] == 0 || visited[next_pos.idx1][next_pos.idx2]) {
                    continue;
                }

                visited[next_pos.idx1][next_pos.idx2] = true;
                moved[next_pos.idx1][next_pos.idx2] = moved[cur_pos.idx1][cur_pos.idx2];
                q.add(next_pos);
            }
        }
    }

    static int flood_fill() {
        int splited_ice = 0;
        visited = new boolean[301][301];
        moved = new int[301][301];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] != true && board[i][j] != 0) {
                    splited_ice++;
                    bfs(new Pointer(i, j), splited_ice);
                }
            }
        }
        return splited_ice;
    }

    static void solve() {
        int year = 0;

        while (true) {
            // 빙산 녹이기
            year++;
            melt_ice();
            copy_ice();

            // 플러드필로 ... 빙산의 분리여부, 몇개로 분리되었는지 구한다.
            int splited_ice = flood_fill();

            if (splited_ice == 0) {
                answer = 0;
                break;
            }

            if (splited_ice >= 2) {
                answer = year;
                break;
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }

    static class Pointer {
        int idx1;
        int idx2;

        Pointer() {
        }

        public Pointer(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }
}
