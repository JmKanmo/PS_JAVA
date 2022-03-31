package boj;

import java.util.Scanner;

/**
 * Prob 17070, 파이프 옮기기
 * DFS
 */
public class Prob17070 {
    static int N;
    static Scanner scanner = new Scanner(System.in);
    static int[][] board = new int[18][18];
    static int answer;

    static void init() {
        N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= N; i++) {
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");

            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(strArr[j - 1]);
            }
        }
    }

    static void dfs(int idx1, int idx2, int state) {
        if (idx1 > N || idx2 > N)
            return;

        if (idx1 == N && idx2 == N) {
            answer++;
            return;
        }

        int next_idx1, next_idx2;

        if (state == 1) {
            // ����

            // ���� �̵�
            next_idx1 = idx1;
            next_idx2 = idx2 + 1;

            if (board[next_idx1][next_idx2] == 0) {
                dfs(next_idx1, next_idx2, 1);
            }

            // �밢�� �̵�
            next_idx1 = idx1 + 1;
            next_idx2 = idx2 + 1;

            if (board[idx1][idx2 + 1] == 0 && board[next_idx1][next_idx2] == 0 && board[idx1 + 1][idx2] == 0) {
                dfs(next_idx1, next_idx2, 3);
            }
        } else if (state == 2) {
            // ����

            // ���� �̵�
            next_idx1 = idx1 + 1;
            next_idx2 = idx2;

            if (board[next_idx1][next_idx2] == 0) {
                dfs(next_idx1, next_idx2, 2);
            }

            // �밢�� �̵�
            next_idx1 = idx1 + 1;
            next_idx2 = idx2 + 1;

            if (board[idx1][idx2 + 1] == 0 && board[next_idx1][next_idx2] == 0 && board[idx1 + 1][idx2] == 0) {
                dfs(next_idx1, next_idx2, 3);
            }
        } else if (state == 3) {
            // �밢��

            // ���� �̵�
            next_idx1 = idx1;
            next_idx2 = idx2 + 1;

            if (board[next_idx1][next_idx2] == 0) {
                dfs(next_idx1, next_idx2, 1);
            }

            // �밢�� �̵�
            next_idx1 = idx1 + 1;
            next_idx2 = idx2 + 1;

            if (board[idx1][idx2 + 1] == 0 && board[next_idx1][next_idx2] == 0 && board[idx1 + 1][idx2] == 0) {
                dfs(next_idx1, next_idx2, 3);
            }

            // ���� �̵�
            next_idx1 = idx1 + 1;
            next_idx2 = idx2;

            if (board[next_idx1][next_idx2] == 0) {
                dfs(next_idx1, next_idx2, 2);
            }
        }
    }

    static void solve() {
        dfs(1, 2, 1);
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }
}
