package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] board =new char[51][51];

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            N = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < N; i++) {
                String line = bufferedReader.readLine();

                for(int j=0; j < line.length(); j++) {
                    board[i][j] = line.charAt(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void solve() {

    }

    public static void main(String[] args) {
        init();
        solve();
    }
}
