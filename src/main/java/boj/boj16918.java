package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj16918 {
    static int R, C, N;
    static Boomb[][] board = new Boomb[201][201];
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] parsed = bufferedReader.readLine().split(" ");
            R = Integer.parseInt(parsed[0]);
            C = Integer.parseInt(parsed[1]);
            N = Integer.parseInt(parsed[2]);

            for (int i = 0; i < R; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    board[i][j] = new Boomb(line.charAt(j), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void installBoomb(int time) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j].ch == '.') {
                    board[i][j].setTime(time);
                }
                board[i][j].setCh('O');
            }
        }
    }

    static void printBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(board[i][j].ch);
            }
            System.out.println();
        }
    }

    static void explodeBoomb(int time) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j].ch == 'O' && time - 3 == board[i][j].time) {
                    board[i][j].ch = '.';
                    board[i][j].time = 0;

                    for (int k = 0; k < 4; k++) {
                        int moved_i = i + idx_1[k];
                        int moved_j = j + idx_2[k];

                        if (moved_i < 0 || moved_i >= R || moved_j < 0 || moved_j >= C)
                            continue;

                        if (board[moved_i][moved_j].ch == 'O' && time - 3 == board[moved_i][moved_j].time)
                            continue;

                        board[moved_i][moved_j].ch = '.';
                        board[moved_i][moved_j].time = 0;
                    }
                }
            }
        }
    }

    static void solve() {
        int time = 1;

        if (N == time) {
            printBoard();
            return;
        }

        // 폭탄 설치
        installBoomb(++time);

        if (N == time) {
            printBoard();
            return;
        }

        while (true) {
            time++;
            // 폭탄 폭발 (3의 배수)
            explodeBoomb(time);

//            printBoard();
//            System.out.println();

            if (N == time) {
                printBoard();
                return;
            }

            // 폭탄 설치
            installBoomb(++time);

            if (N == time) {
                printBoard();
                return;
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
    }

    static class Boomb {
        char ch;
        int time;

        Boomb() {
        }

        public Boomb(char ch, int time) {
            this.ch = ch;
            this.time = time;
        }

        public char getCh() {
            return ch;
        }

        public void setCh(char ch) {
            this.ch = ch;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
