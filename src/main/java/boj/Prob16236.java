package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 16236, 아기상어
 * BFS
 */
public class Prob16236 {
    static int N;
    static PosVector sharkPos = new PosVector();
    static int[][] board = new int[21][21];
    static Scanner scanner = new Scanner(System.in);
    static int answer = 0;
    static int[][] visited = new int[21][21];
    static int[][] step = new int[21][21];
    static int[] pos_idx1 = {0, 0, 1, -1}; // 동,서,남,북
    static int[] pos_idx2 = {1, -1, 0, 0};

    static int eatCnt;

    static void init() {
        N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            String str = scanner.nextLine();
            String[] strArr = str.split(" ");

            for (int j = 0; j < strArr.length; j++) {
                board[i][j] = Integer.parseInt(strArr[j]);

                if (board[i][j] == 9) {
                    sharkPos.idx1 = i;
                    sharkPos.idx2 = j;
                    sharkPos.size = 2;
                }
            }
        }
    }

    public static int bfs(PosVector start) {
        Queue<PosVector> q = new LinkedList<>();
        int cnt = 0;

        q.add(start);
        visited[sharkPos.idx1][sharkPos.idx2] = 1;

        while (q.isEmpty() != true) {
            PosVector posVector = q.poll();

            if (posVector.size != 9 && posVector.size != 0 && sharkPos.size > posVector.size) {
                cnt++;
            }

            for (int i = 0; i < 4; i++) {
                int next_idx1 = posVector.idx1 + pos_idx1[i];
                int next_idx2 = posVector.idx2 + pos_idx2[i];

                if (next_idx1 < 0 || next_idx1 >= N || next_idx2 < 0 || next_idx2 >= N)
                    continue;

                int next_size = board[next_idx1][next_idx2];

                if (visited[next_idx1][next_idx2] == 0 && sharkPos.size >= next_size) {
                    q.add(new PosVector(next_idx1, next_idx2, next_size));
                    visited[next_idx1][next_idx2] = 1;
                    step[next_idx1][next_idx2] = step[posVector.idx1][posVector.idx2] + 1;
                }
            }
        }
        return cnt;
    }

    public static void solve() {

        while (true) {
            int MIN = Integer.MAX_VALUE;
            PosVector eatPos = new PosVector();

            int retCnt = bfs(sharkPos);

            if (retCnt <= 0) {
                break;
            }

            // 먹을수잇는 물고기 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 먹을수 잇는 물고기인지 판단... 제일 왼쪽 위에서부터 검사 ㄱㄱ
                    if (board[i][j] != 0 && board[i][j] != 9 && sharkPos.size > board[i][j] && visited[i][j] == 1) {
                        if (MIN > step[i][j]) {
                            MIN = step[i][j];
                            eatPos.idx1 = i;
                            eatPos.idx2 = j;
                        }
                    }
                }
            }

            if (MIN < Integer.MAX_VALUE) {
                answer += MIN;
                board[eatPos.idx1][eatPos.idx2] = 0;
                eatCnt++;

                board[sharkPos.idx1][sharkPos.idx2] = 0;

                sharkPos.idx1 = eatPos.idx1;
                sharkPos.idx2 = eatPos.idx2;

                board[sharkPos.idx1][sharkPos.idx2] = 9;

                if (eatCnt == sharkPos.size) {
                    sharkPos.size++;
                    eatCnt = 0;
                }
            }

            // visited, step 초기화.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited[i][j] = 0;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    step[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }

    public static class PosVector {
        int idx1;
        int idx2;
        int size;

        public PosVector() {
        }

        public PosVector(int idx1, int idx2, int size) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.size = size;
        }
    }

}
