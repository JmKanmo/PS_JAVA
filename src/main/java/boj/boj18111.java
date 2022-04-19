package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj18111 {
    static int N;
    static int M;
    static int B;
    static int[][] board;
    static int MAX = 0;
    static int MIN = Integer.MAX_VALUE;
    static int answer = Integer.MAX_VALUE;
    static int answer_height;

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] _line = bufferedReader.readLine().split(" ");

            N = Integer.parseInt(_line[0]);
            M = Integer.parseInt(_line[1]);
            B = Integer.parseInt(_line[2]);
            board = new int[N + 1][M + 1];

            for (int i = 0; i < N; i++) {
                String line = bufferedReader.readLine();
                String[] parsed = line.split(" ");
                for (int j = 0; j < parsed.length; j++) {
                    int number = Integer.parseInt(parsed[j]);

                    if (number > MAX) {
                        MAX = number;
                    }

                    if (number < MIN) {
                        MIN = number;
                    }

                    board[i][j] = number;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {
        // 정답이 되는 높이의 후보군
        for (int block = MIN; block <= MAX; block++) {
            int build_cnt = 0; // 쌓아야되는 개수
            int remove_cnt = 0; // 제거해야되는 개수

            // 블록 board 반복
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int leave = board[i][j] - block;

                    if (leave < 0) {
                        remove_cnt += (-1 * leave);
                    } else {
                        build_cnt += leave;
                    }
                }
            }

            //  (B + 인벤토리에 넣어진 블록 >= 인벤토리에서 빠진 블록) 이러한 경우만 처리
            if (B + build_cnt >= remove_cnt) {
                int totalTime = (build_cnt * 2) + remove_cnt;

                if (answer >= totalTime) {
                    answer = totalTime;
                    answer_height = block;
                }
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer + " " + answer_height);
    }
}
