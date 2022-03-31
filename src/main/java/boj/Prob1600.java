package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Prob1600, 말이 되고픈 원숭이
 * BFS, 3차원 배열
 */
public class Prob1600 {
    static int K;
    static int W, H;
    static Scanner scanner = new Scanner(System.in);
    static int[][] board = new int[201][201];
    static boolean[][][] visited = new boolean[31][201][201];
    static int[][][] step = new int[31][201][201];

    static int[] horsed_idx1 = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] horsed_idx2 = {1, -1, 2, -2, 2, -2, 1, -1};

    static int[] monkey_idx1 = {0, -1, 0, 1};
    static int[] monkey_idx2 = {1, 0, -1, 0};

    static int answer;

    static void init() {
        K = scanner.nextInt();
        scanner.nextLine();
        String[] strArr = scanner.nextLine().split(" ");
        W = Integer.parseInt(strArr[0]);
        H = Integer.parseInt(strArr[1]);

        for (int i = 0; i < H; i++) {
            String str = scanner.nextLine();
            strArr = str.split(" ");

            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(strArr[j]);
            }
        }
    }

    static int bfs(Position position) {
        Queue<Position> q = new LinkedList<>();
        q.add(position);
        visited[0][position.idx1][position.idx2] = true;

        while (q.isEmpty() != true) {
            Position curPos = q.poll();
            int curLimit = curPos.limit;

            if (curPos.idx1 == H - 1 && curPos.idx2 == W - 1) {
                return step[curLimit][curPos.idx1][curPos.idx2];
            }

            Position nextPos = null;

            if (curLimit < K) {
                // 말처럼 이동
                for (int i = 0; i < 8; i++) {
                    int nextLimit = curLimit + 1;
                    nextPos = new Position(curPos.idx1 + horsed_idx1[i], curPos.idx2 + horsed_idx2[i], curLimit + 1);

                    if (nextPos.idx1 < 0 || nextPos.idx1 >= H || nextPos.idx2 < 0 || nextPos.idx2 >= W) {
                        continue;
                    }

                    if (board[nextPos.idx1][nextPos.idx2] != 1 && visited[nextLimit][nextPos.idx1][nextPos.idx2] == false) {
                        visited[nextLimit][nextPos.idx1][nextPos.idx2] = true;
                        step[nextLimit][nextPos.idx1][nextPos.idx2] = step[curLimit][curPos.idx1][curPos.idx2] + 1;
                        q.add(nextPos);
                    }
                }
            }
            // 원숭이 이동 (인접)
            for (int i = 0; i < 4; i++) {
                int nextLimit = curLimit;
                nextPos = new Position(curPos.idx1 + monkey_idx1[i], curPos.idx2 + monkey_idx2[i], curLimit);

                if (nextPos.idx1 < 0 || nextPos.idx1 >= H || nextPos.idx2 < 0 || nextPos.idx2 >= W) {
                    continue;
                }

                if (board[nextPos.idx1][nextPos.idx2] != 1 && visited[nextLimit][nextPos.idx1][nextPos.idx2] == false) {
                    visited[nextLimit][nextPos.idx1][nextPos.idx2] = true;
                    step[nextLimit][nextPos.idx1][nextPos.idx2] = step[curLimit][curPos.idx1][curPos.idx2] + 1;
                    q.add(nextPos);
                }
            }
        }
        return -1;
    }

    static void solve() {
        answer = bfs(new Position(0, 0, 0));
        System.out.println(answer);
    }

    public static void main(String[] args) {
        init();
        solve();
    }

    static class Position {
        int idx1;
        int idx2;
        int limit;

        Position() {
        }

        Position(int idx1, int idx2, int limit) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.limit = limit;
        }
    }
}
