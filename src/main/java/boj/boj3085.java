package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;

public class boj3085 {
    static int N;
    static char[][] board = new char[51][51];
    static int answer;

    static void init() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < N; i++) {
                String str = bufferedReader.readLine();
                for (int j = 0; j < str.length(); j++) {
                    char ch = str.charAt(j);
                    board[i][j] = ch;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void swap(int i, int j, int i1, int j1) {
        char ch = board[i][j];
        board[i][j] = board[i1][j1];
        board[i1][j1] = ch;
    }

    static void setMaxCandy() {
        int MAX_COUNT = 0;

        for (int i = 0; i < N; i++) {
            int elem_cnt = 1;

            for (int j = 0; j < N - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    elem_cnt++;
                }else{
                    elem_cnt = 1;
                }
                MAX_COUNT = Math.max(MAX_COUNT,elem_cnt);
            }
        }

        for (int i = 0; i < N; i++) {
            int elem_cnt = 1;

            for (int j = 0; j < N - 1; j++) {
                if (board[j][i] == board[j+1][i]) {
                    elem_cnt++;
                }else{
                    elem_cnt = 1;
                }
                MAX_COUNT = Math.max(MAX_COUNT,elem_cnt);
            }
        }
        answer = Math.max(answer,MAX_COUNT);
    }

    static void solve() {
        // 가로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (board[i][j] != board[i][j + 1]) {
                    swap(i, j, i, j + 1);
                    setMaxCandy();
                    swap(i, j, i, j + 1);
                }
            }
        }

        // 세로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (board[j][i] != board[j + 1][i]) {
                    swap(j, i, j + 1, i);
                    setMaxCandy();
                    swap(j, i, j + 1, i);
                }
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        init();
        solve();
        System.out.println(answer);
    }
}
