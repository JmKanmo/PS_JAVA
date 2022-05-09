package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Prob3055 {
    static int R, C;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static char[][] board = new char[51][51];
    static Queue<Pointer> q1 = new LinkedList<>(); // 고슴도치 큐
    static Queue<Pointer> q2 = new LinkedList<>(); // 물 큐
    static Pointer biber_ptr;
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};
    static int answer;
    static boolean[][] visited1 = new boolean[51][51];
    static boolean[][] visited2 = new boolean[51][51];

    static void init() {
        try {
            String line = bufferedReader.readLine();
            String[] splited = line.split(" ");

            R = Integer.parseInt(splited[0]);
            C = Integer.parseInt(splited[1]);

            for (int i = 0; i < R; i++) {
                line = bufferedReader.readLine();

                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    board[i][j] = ch;

                    if (ch == 'S') {
                        q1.add(new Pointer(i, j));
                        visited1[i][j] = true;
                    } else if (ch == '*') {
                        q2.add(new Pointer(i, j));
                        visited2[i][j] = true;
                    } else if (ch == 'D') {
                        biber_ptr = new Pointer(i, j);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        int time = 0;

        while (true) {
            //  물 이동
            int q2_size = q2.size();

            while (q2_size > 0) {
                Pointer cur_ptr = q2.poll();

                for (int i = 0; i < 4; i++) {
                    Pointer next_ptr = new Pointer(cur_ptr.idx1 + idx_1[i], cur_ptr.idx2 + idx_2[i]);

                    if (next_ptr.idx1 < 0 || next_ptr.idx1 >= R || next_ptr.idx2 < 0 || next_ptr.idx2 >= C) {
                        continue;
                    }

                    if (visited2[next_ptr.idx1][next_ptr.idx2] ||
                            board[next_ptr.idx1][next_ptr.idx2] == 'X' ||
                            board[next_ptr.idx1][next_ptr.idx2] == 'D') {
                        continue;
                    }

                    visited2[next_ptr.idx1][next_ptr.idx2] = true;
                    board[next_ptr.idx1][next_ptr.idx2] = '*';
                    q2.add(next_ptr);
                }
                q2_size--;
            }

            // 고슴도치 이동
            int q1_size = q1.size();

            while (q1_size > 0) {
                Pointer cur_ptr = q1.poll();

                if (cur_ptr.idx1 == biber_ptr.idx1 && cur_ptr.idx2 == biber_ptr.idx2) {
                    answer = time;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    Pointer next_ptr = new Pointer(cur_ptr.idx1 + idx_1[i], cur_ptr.idx2 + idx_2[i]);

                    if (next_ptr.idx1 < 0 || next_ptr.idx1 >= R || next_ptr.idx2 < 0 || next_ptr.idx2 >= C) {
                        continue;
                    }

                    if (visited1[next_ptr.idx1][next_ptr.idx2] ||
                            board[next_ptr.idx1][next_ptr.idx2] == 'X' ||
                            board[next_ptr.idx1][next_ptr.idx2] == '*') {
                        continue;
                    }

                    visited1[next_ptr.idx1][next_ptr.idx2] = true;
                    q1.add(next_ptr);
                }
                q1_size--;
            }
            time++;
            if (q1.isEmpty() && q2.isEmpty())
                break;
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        if (answer == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    static class Pointer {
        int idx1;
        int idx2;

        public Pointer() {
        }

        public Pointer(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }
}
