package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Prob1194, 달이 차오른다, 가자.
 * BFS, 3차원 배열, 비트연산
 */
public class Prob1194 {
    static Scanner scanner = new Scanner(System.in);
    static char[][] board = new char[51][51];
    static boolean[][][] visited = new boolean[64][51][51];
    static int[][][] step = new int[64][51][51];
    static int N, M;
    static Pointer start;
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};

    static void init() {
        N = scanner.nextInt();
        M = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            String str = scanner.nextLine();

            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);

                if (board[i][j] == '0') {
                    start = new Pointer(i, j, (byte) 0x00);
                }
            }
        }
    }

    static boolean containKey(char moonKey, char key) {
        moonKey -= 'A';
        byte moonBit = (byte) (Math.pow(2, moonKey));
        return (moonBit & key) > 0 ? true : false;
    }

    static int bfs(Pointer start) {
        Queue<Pointer> q = new LinkedList<>();
        step[0][start.idx1][start.idx2] = 0;
        visited[0][start.idx1][start.idx2] = true;
        q.add(start);

        while (q.isEmpty() != true) {
            Pointer curPointer = q.poll();

            if (board[curPointer.idx1][curPointer.idx2] == '1') {
                return step[curPointer.key][curPointer.idx1][curPointer.idx2];
            }

            for (int i = 0; i < 4; i++) {
                Pointer nextPointer = new Pointer(curPointer.idx1 + idx_1[i], curPointer.idx2 + idx_2[i], curPointer.key);

                if (nextPointer.idx1 < 0 || nextPointer.idx1 >= N || nextPointer.idx2 < 0 || nextPointer.idx2 >= M) {
                    continue;
                }

                if (board[nextPointer.idx1][nextPointer.idx2] == '#') {
                    continue;
                }

                if (board[nextPointer.idx1][nextPointer.idx2] == '.') {
                    //빈 곳일 때
                    if (!visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2]) {
                        q.add(nextPointer);
                        visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = true;
                        step[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = step[curPointer.key][curPointer.idx1][curPointer.idx2] + 1;
                    }
                } else if (board[nextPointer.idx1][nextPointer.idx2] >= 'a' && board[nextPointer.idx1][nextPointer.idx2] <= 'f') {
                    // 열쇠일 때
                    nextPointer.key |= (byte) (Math.pow(2, board[nextPointer.idx1][nextPointer.idx2] - 'a'));

                    if (!visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2]) {
                        q.add(nextPointer);
                        visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = true;
                        step[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = step[curPointer.key][curPointer.idx1][curPointer.idx2] + 1;
                    }
                } else if (board[nextPointer.idx1][nextPointer.idx2] >= 'A' && board[nextPointer.idx1][nextPointer.idx2] <= 'F') {
                    // 문일 때
                    boolean isContainKey = containKey(board[nextPointer.idx1][nextPointer.idx2], (char) nextPointer.key);

                    if (isContainKey) {
                        if (!visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2]) {
                            q.add(nextPointer);
                            visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = true;
                            step[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = step[curPointer.key][curPointer.idx1][curPointer.idx2] + 1;
                        }
                    }
                } else {
                    // 0,1(출발지,도착지)
                    if (!visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2]) {
                        q.add(nextPointer);
                        visited[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = true;
                        step[nextPointer.key][nextPointer.idx1][nextPointer.idx2] = step[curPointer.key][curPointer.idx1][curPointer.idx2] + 1;
                    }
                }
            }
        }
        return -1;
    }

    static void solve() {
        int answer = bfs(start);
        System.out.println(answer);
    }

    public static void main(String[] args) {
        init();
        solve();
    }

    static class Pointer {
        int idx1;
        int idx2;
        byte key;

        Pointer() {
        }

        public Pointer(int idx1, int idx2, byte key) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.key = key;
        }
    }
}
