package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj2665 {
    static int N;
    static char[][] board = new char[51][51];
    static int[][] steps = new int[51][51];
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < N; i++) {
                String str = bufferedReader.readLine();

                for (int j = 0; j < str.length(); j++) {
                    board[i][j] = str.charAt(j);
                }
            }

            for(int i=0; i< N; i++) {
                Arrays.fill(steps[i], Integer.MAX_VALUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        Queue<Pointer> q = new LinkedList<>();
        q.add(new Pointer(0, 0));
        steps[0][0] = 0;

        while (q.isEmpty() != true) {
            Pointer cur_ptr = q.poll();

            for (int i = 0; i < 4; i++) {
                Pointer next_ptr = new Pointer(cur_ptr.idx1 + idx_1[i], cur_ptr.idx2 + idx_2[i]);

                if (next_ptr.idx1 < 0 || next_ptr.idx1 >= N || next_ptr.idx2 < 0 || next_ptr.idx2 >= N) {
                    continue;
                }

                if (board[next_ptr.idx1][next_ptr.idx2] == '1') {
                    if (steps[next_ptr.idx1][next_ptr.idx2] > steps[cur_ptr.idx1][cur_ptr.idx2]) {
                        steps[next_ptr.idx1][next_ptr.idx2] = steps[cur_ptr.idx1][cur_ptr.idx2];
                        q.add(next_ptr);
                    }
                } else {
                    if (steps[next_ptr.idx1][next_ptr.idx2] > steps[cur_ptr.idx1][cur_ptr.idx2] + 1) {
                        steps[next_ptr.idx1][next_ptr.idx2] = steps[cur_ptr.idx1][cur_ptr.idx2] + 1;
                        q.add(next_ptr);
                    }
                }
            }
        }

        System.out.println(steps[N-1][N-1]);
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
