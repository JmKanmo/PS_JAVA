package zerobase.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Practice11 {
    static char[][] board = new char[101][101];
    static boolean[][] visited = new boolean[101][101];
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static long N;
    static long M;

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long answer_w = 0;
        long answer_b = 0;

        try {
            String[] splited = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(splited[0]);
            M = Integer.parseInt(splited[1]);

            for (int i = 0; i < M; i++) {
                String str = bufferedReader.readLine();

                for (int j = 0; j < str.length(); j++) {
                    board[i][j] = str.charAt(j);
                }
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) {
                        // bfs
                        if (board[i][j] == 'W') {
                            answer_w += bfs(new Pointer(i, j));
                        } else {
                            answer_b += bfs(new Pointer(i, j));
                        }
                    }
                }
            }
            System.out.println(answer_w + " " + answer_b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long bfs(Pointer pointer) {
        Queue<Pointer> q = new LinkedList<>();
        long ret = 0;
        char color = board[pointer.idx1][pointer.idx2];

        q.add(pointer);
        visited[pointer.idx1][pointer.idx2] = true;

        while (q.isEmpty() != true) {
            Pointer ptr = q.poll();
            ret++;

            for (int i = 0; i < 4; i++) {
                Pointer next_ptr = new Pointer(ptr.idx1 + idx_1[i], ptr.idx2 + idx_2[i]);

                if (next_ptr.idx1 < 0 || next_ptr.idx1 >= M || next_ptr.idx2 < 0 || next_ptr.idx2 >= N) {
                    continue;
                }

                if (visited[next_ptr.idx1][next_ptr.idx2]) {
                    continue;
                }

                if (board[next_ptr.idx1][next_ptr.idx2] != color) {
                    continue;
                }

                visited[next_ptr.idx1][next_ptr.idx2] = true;
                q.add(next_ptr);
            }
        }
        return ret * ret;
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
