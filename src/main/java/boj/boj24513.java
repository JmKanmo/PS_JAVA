package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 해설 보고 다시 풀어볼 것. (실패)
 */
public class boj24513 {
    static int N, M;
    static char[][] board = new char[1001][1001];
    static boolean[][][] visited = new boolean[4][1001][1001];
    static Queue<Pointer> one_vq = new LinkedList<>();
    static Queue<Pointer> two_vq = new LinkedList<>();
    static Queue<Pointer> three_vq = new LinkedList<>();

    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] splited = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(splited[0]);
            M = Integer.parseInt(splited[1]);

            for (int i = 1; i <= N; i++) {
                String[] parsed = bufferedReader.readLine().split(" ");

                for (int j = 0; j < M; j++) {
                    char ch = (char) Integer.parseInt(parsed[j]);
                    board[i][j + 1] = ch;

                    if (board[i][j + 1] == 1) {
                        visited[1][i][j + 1] = true;
                        one_vq.add(new Pointer(i, j + 1));
                    } else if (board[i][j + 1] == 2) {
                        visited[2][i][j + 1] = true;
                        two_vq.add(new Pointer(i, j + 1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        while (true) {
            int one_vq_size = one_vq.size();
            int two_vq_size = two_vq.size();

            if (one_vq_size == 0 && two_vq_size == 0) {
                break;
            }

            while (one_vq_size-- > 0) {
                Pointer cur_ptr = one_vq.poll();

                if (visited[3][cur_ptr.idx1][cur_ptr.idx2]) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    Pointer next_ptr = new Pointer(cur_ptr.idx1 + idx_1[i], cur_ptr.idx2 + idx_2[i]);

                    if (next_ptr.idx1 < 1 || next_ptr.idx1 > N || next_ptr.idx2 < 1 || next_ptr.idx2 > M) {
                        continue;
                    }

                    if (visited[2][next_ptr.idx1][next_ptr.idx2]) {
                        visited[1][next_ptr.idx1][next_ptr.idx2] = false;
                        visited[2][next_ptr.idx1][next_ptr.idx2] = false;
                        visited[3][next_ptr.idx1][next_ptr.idx2] = true;
                        continue;
                    }

                    if (visited[1][next_ptr.idx1][next_ptr.idx2] || board[next_ptr.idx1][next_ptr.idx2] == (char) -1
                            || visited[3][next_ptr.idx1][next_ptr.idx2]) {
                        continue;
                    }

                    visited[1][next_ptr.idx1][next_ptr.idx2] = true;
                    one_vq.add(next_ptr);
                }
            }

            while (two_vq_size-- > 0) {
                Pointer cur_ptr = two_vq.poll();

                if (visited[3][cur_ptr.idx1][cur_ptr.idx2]) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    Pointer next_ptr = new Pointer(cur_ptr.idx1 + idx_1[i], cur_ptr.idx2 + idx_2[i]);

                    if (next_ptr.idx1 < 1 || next_ptr.idx1 > N || next_ptr.idx2 < 1 || next_ptr.idx2 > M) {
                        continue;
                    }

                    if (visited[1][next_ptr.idx1][next_ptr.idx2]) {
                        visited[1][next_ptr.idx1][next_ptr.idx2] = false;
                        visited[2][next_ptr.idx1][next_ptr.idx2] = false;
                        visited[3][next_ptr.idx1][next_ptr.idx2] = true;
                        continue;
                    }

                    if (visited[2][next_ptr.idx1][next_ptr.idx2] || board[next_ptr.idx1][next_ptr.idx2] == (char) -1
                            || visited[3][next_ptr.idx1][next_ptr.idx2]) {
                        continue;
                    }

                    visited[2][next_ptr.idx1][next_ptr.idx2] = true;
                    two_vq.add(next_ptr);
                }
            }
        }

        // 각 마을 상태 구하기
        int one_size = 0;
        int two_size = 0;
        int three_size = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (visited[1][i][j]) {
                    one_size++;
                } else if (visited[2][i][j]) {
                    two_size++;
                } else if (visited[3][i][j]) {
                    three_size++;
                }
            }
        }

        System.out.println(one_size + " " + two_size + " " + three_size);
    }

    public static void main(String[] args) {
        init();
        solve();
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
