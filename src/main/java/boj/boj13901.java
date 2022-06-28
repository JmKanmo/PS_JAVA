package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 짜증나는 문제 ... 몰라
 */
public class boj13901 {
    static int R, C;
    static Pointer start;
    static List<Integer> moves = new ArrayList<>();
    static boolean[][] visited = new boolean[1001][1001];
    static int[][] board = new int[1001][1001];
    static Pointer answer;

    static void init() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] parsed = bufferedReader.readLine().split(" ");

            R = Integer.parseInt(parsed[0]);
            C = Integer.parseInt(parsed[1]);

            int k = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < k; i++) {
                parsed = bufferedReader.readLine().split(" ");
                board[Integer.parseInt(parsed[0])][Integer.parseInt(parsed[1])] = 1;
            }

            parsed = bufferedReader.readLine().split(" ");
            start = new Pointer(
                    Integer.parseInt(parsed[0]),
                    Integer.parseInt(parsed[1])
            );

            parsed = bufferedReader.readLine().split(" ");

            for (int i = 0; i < parsed.length; i++) {
                moves.add(Integer.parseInt(parsed[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int[] next(int ptr) {
        switch (ptr) {
            case 1:
                return new int[]{-1, 0};
            case 2:
                return new int[]{1, 0};
            case 3:
                return new int[]{0, -1};
            case 4:
                return new int[]{0, 1};
        }
        return null;
    }

    static void solve() {
        Pointer ptr = start;

        visited[ptr.idx1][ptr.idx2] = true;

        int moveIdx = 0;
        int missCount = 0;

        while (true) {
            if (missCount >= 4) {
                break;
            }

            Integer move = moves.get(moveIdx);

            int[] move_next = next(move);
            Pointer next_ptr = new Pointer(
                    ptr.idx1 + move_next[0],
                    ptr.idx2 + move_next[1]);

            if (next_ptr.idx1 < 0 || next_ptr.idx1 >= R || next_ptr.idx2 < 0 || next_ptr.idx2 >= C) {
                continue;
            }

            if (visited[next_ptr.idx1][next_ptr.idx2]) {
                moveIdx = moveIdx + 1 >= 4 ? 0 : moveIdx + 1;
                missCount++;
                continue;
            }

            if (board[next_ptr.idx1][next_ptr.idx2] == 1) {
                moveIdx = moveIdx + 1 >= 4 ? 0 : moveIdx + 1;
                missCount++;
                continue;
            }

            if (next_ptr.idx1 <= 0 || next_ptr.idx1 >= R - 1
                    || next_ptr.idx2 <= 0 || next_ptr.idx2 >= C - 1) {
                ptr = next_ptr;
                visited[ptr.idx1][ptr.idx2] = true;
                moveIdx = moveIdx + 1 >= 4 ? 0 : moveIdx + 1;
                missCount++;
                continue;
            }
            ptr = next_ptr;
            visited[ptr.idx1][ptr.idx2] = true;
            missCount = 0;
        }
        answer = ptr;
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer.idx1 + " " + answer.idx2);
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
