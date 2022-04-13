package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj1987 {
    static int R, C;
    static char[][] board = new char[21][21];
    static boolean[][] visited = new boolean[21][21];
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};
    static int answer;

    static void init() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] splited = bufferedReader.readLine().split(" ");
            R = Integer.parseInt(splited[0]);
            C = Integer.parseInt(splited[1]);

            for (int i = 1; i <= R; i++) {
                String line = bufferedReader.readLine();

                for (int j = 0; j < line.length(); j++) {
                    board[i][j + 1] = line.charAt(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void dfs(Pointer ptr, int depth, Set<Character> alphabets) {
        answer = Math.max(answer, depth);

        for (int i = 0; i < 4; i++) {
            Pointer next_ptr = new Pointer(ptr.idx1 + idx_1[i], ptr.idx2 + idx_2[i]);

            if (next_ptr.idx1 < 1 || next_ptr.idx1 > R || next_ptr.idx2 < 1 || next_ptr.idx2 > C)
                continue;

            if (!visited[next_ptr.idx1][next_ptr.idx2] && !alphabets.contains(board[next_ptr.idx1][next_ptr.idx2])) {
                visited[next_ptr.idx1][next_ptr.idx2] = true;
                alphabets.add(board[next_ptr.idx1][next_ptr.idx2]);

                dfs(next_ptr, depth + 1, alphabets);

                visited[next_ptr.idx1][next_ptr.idx2] = false;
                alphabets.remove(board[next_ptr.idx1][next_ptr.idx2]);
            }
        }
    }

    public static void main(String[] args) {
        init();
        Set<Character> set = new HashSet<>();
        set.add(board[1][1]);
        dfs(new Pointer(1, 1), 1, set);
        System.out.println(answer);
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



