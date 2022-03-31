package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Prob11559 {
    static char[][] board = new char[12][6];
    static char[][] temp_board = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static void init() {
        try {
            for (int i = 0; i < 12; i++) {
                String str = bufferedReader.readLine();
                for (int j = 0; j < str.length(); j++) {
                    char ch = str.charAt(j);
                    board[i][j] = ch;
                    temp_board[i][j] = ch;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean bfs(Pointer pointer) {
        Queue<Pointer> q = new LinkedList<>();
        int cnt = 0;
        char color = board[pointer.x][pointer.y];
        List<Pointer> temp_pos = new ArrayList<>();
        q.add(pointer);
        visited[pointer.x][pointer.y] = true;

        while (q.isEmpty() != true) {
            Pointer cur_pos = q.poll();
            temp_pos.add(new Pointer(cur_pos.x, cur_pos.y));
            cnt++;

            for (int i = 0; i < 4; i++) {
                Pointer next_pos = new Pointer(cur_pos.x + idx_1[i], cur_pos.y + idx_2[i]);

                if (next_pos.x < 0 || next_pos.x >= 12 || next_pos.y < 0 || next_pos.y >= 6) {
                    continue;
                }

                if (board[next_pos.x][next_pos.y] == '.') {
                    continue;
                }

                if (visited[next_pos.x][next_pos.y] != true && board[next_pos.x][next_pos.y] == color) {
                    visited[next_pos.x][next_pos.y] = true;
                    q.add(next_pos);
                }
            }
        }

        if (cnt >= 4) {
            for (Pointer pos : temp_pos) {
                temp_board[pos.x][pos.y] = '.';
            }
        }
        return cnt >= 4 ? true : false;
    }

    static void down() {
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                int idx1 = i, idx2 = j;

                if (board[idx1][idx2] == '.')
                    continue;

                int next_idx1 = idx1;

                while (next_idx1 + 1 < 12 && board[next_idx1 + 1][idx2] == '.') {
                    next_idx1++;
                }

                if (board[next_idx1][idx2] == '.') {
                    char temp_ch = board[idx1][idx2];
                    board[next_idx1][idx2] = temp_ch;
                    board[idx1][idx2] = '.';
                }
            }
        }
    }

    static void solution() {
        int answer = 0;

        while (true) {
            int cnt = 0;

            // 뿌요 연결 ㄱㄱ
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.' && visited[i][j] != true) {
                        if (bfs(new Pointer(i, j))) {
                            cnt++;
                        }
                    }
                }
            }

            // temp_board -> board
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    board[i][j] = temp_board[i][j];
                }
            }

            // down
            if(cnt>0)
                down();

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    temp_board[i][j] = board[i][j];
                }
            }

            visited = new boolean[12][6];

            if (cnt == 0) {
                break;
            }
            answer ++;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        try {
            init();
            solution();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Pointer {
        int x;
        int y;

        public Pointer() {
        }

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
