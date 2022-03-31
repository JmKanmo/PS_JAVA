package ps;

import java.util.Scanner;

/**
 * 17822. 원판돌리기
 * 구현, 배열
 */
public class Prob17822 {
    static Scanner scanner = new Scanner(System.in);
    static int N, M, T;
    static int[][] board = new int[51][51];
    static int[][] temp_board = new int[51][51];
    static boolean[][] visited = new boolean[51][51];
    static Rotate[] rotates = new Rotate[51];
    static int[] next_left = {0, 0, 1, -1}; // 동서남북
    static int[] next_right = {1, -1, 0, 0};
    static int answer;

    static void init() {
        N = scanner.nextInt();
        M = scanner.nextInt();
        T = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");

            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(strArr[j]);
                board[i][j] = val;
            }
        }

        for (int i = 0; i < T; i++) {
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");
            rotates[i] = new Rotate();
            rotates[i].x = Integer.parseInt(strArr[0]);
            rotates[i].d = Integer.parseInt(strArr[1]);
            rotates[i].k = Integer.parseInt(strArr[2]);
        }
    }

    static void solve() {
        for (int i = 0; i < T; i++) {
            int x = rotates[i].x;
            int d = rotates[i].d;
            int k = rotates[i].k;

            loop(x, d, k);
            clear();
            // temp_board -> board 내용 복사
            copy();
            // visited 초기화
            initVisited();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += board[i][j];
            }
        }
    }

    static void initVisited() {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                visited[j][k] = false;
            }
        }
    }

    static void copy() {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                board[j][k] = temp_board[j][k];
            }
        }
    }

    static void clear() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int val = temp_board[i][j];
                int temp_cnt = 0;

                if (val == 0)
                    continue;

                for (int k = 0; k < 4; k++) {
                    int next_idx1 = i + next_left[k];
                    int next_idx2 = j + next_right[k];

                    next_idx2 = next_idx2 < 0 ? M - 1 : next_idx2;
                    next_idx2 = next_idx2 >= M ? 0 : next_idx2;

                    if (next_idx1 < 0 || next_idx1 >= N || next_idx2 < 0 || next_idx2 >= M)
                        continue;

                    int next_val = temp_board[next_idx1][next_idx2];

                    if (next_val == val) {
                        visited[next_idx1][next_idx2] = true;
                        temp_cnt++;
                    }
                }

                if (temp_cnt > 0) {
                    // 본인 포함해 인접한점 모두 visited = true 설정
                    visited[i][j] = true;
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            // 없는 경우
            double eval = 0;
            double SUM = 0;
            int cnt_ = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    SUM += temp_board[i][j];

                    if (temp_board[i][j] > 0) {
                        cnt_++;
                    }
                }
            }

            eval = SUM / cnt_;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int val = temp_board[i][j];

                    if (val == 0)
                        continue;

                    if (val > eval) {
                        temp_board[i][j]--;
                    } else if (val < eval) {
                        temp_board[i][j]++;
                    }
                }
            }
        } else {
            // 인접한(visited = true인 것들) 모두 clear해주기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == true) {
                        temp_board[i][j] = 0;
                    }
                }
            }
        }
    }

    static void loop(int x, int d, int k) {
        for (int i = 0; i < N; i++) {
            int _i = i + 1;

            if (_i % x == 0) {
                // rotate, d방향으로 k칸 회전
                rotate(i, d, k);
            } else {
                // 그냥 복사 (회전 안하는 경우)
                for (int j = 0; j < M; j++) {
                    temp_board[i][j] = board[i][j];
                }
            }
        }
    }

    static void rotate(int i, int d, int k) {
        if (d == 0) {
            // 시계 방향
            for (int idx1 = 0; idx1 < M; idx1++) {
                int temp_idx1 = idx1;
                int old_val = board[i][temp_idx1];

                for (int idx2 = 0; idx2 < k; idx2++) {
                    temp_idx1++;

                    if (temp_idx1 >= M) {
                        temp_idx1 = 0;
                    }
                }
                temp_board[i][temp_idx1] = old_val;
            }
        } else if (d == 1) {
            // 반시계 방향
            for (int idx1 = 0; idx1 < M; idx1++) {
                int temp_idx1 = idx1;
                int old_val = board[i][temp_idx1];

                for (int idx2 = 0; idx2 < k; idx2++) {
                    temp_idx1--;

                    if (temp_idx1 < 0) {
                        temp_idx1 = M - 1;
                    }
                }
                temp_board[i][temp_idx1] = old_val;
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }

    public static class Rotate {
        int x;
        int d;
        int k;
    }
}
