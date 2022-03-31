package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob17472 {
    static int N, M;
    static int[][] board = new int[11][11];
    static boolean[][] visited = new boolean[11][11];
    static int[][] step = new int[11][11];
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};
    static List<Pointer> posList = new ArrayList<>();

    // 크루스칼 변수
    static List<Krus_GP> krusGPList = new ArrayList<>();
    static int[] parent = new int[7];

    static int answer;

    static void init() {
        try {
            String str;
            String[] strArr = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(strArr[0]);
            M = Integer.parseInt(strArr[1]);

            for (int i = 0; i < N; i++) {
                str = bufferedReader.readLine();
                strArr = str.split(" ");

                for (int j = 0; j < strArr.length; j++) {
                    board[i][j] = Integer.parseInt(strArr[j]);
                }
            }

            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean checkOutOfRange(Pointer pointer) {
        return pointer.idx1 < 0 || pointer.idx1 >= N || pointer.idx2 < 0 || pointer.idx2 >= M;
    }

    static void bfs(Pointer start, int number) {
        Queue<Pointer> q = new LinkedList<>();
        visited[start.idx1][start.idx2] = true;
        step[start.idx1][start.idx2] = number;
        q.add(start);

        while (q.isEmpty() != true) {
            Pointer curPos = q.poll();
            posList.add(curPos);

            for (int i = 0; i < 4; i++) {
                Pointer nextPos = new Pointer(curPos.idx1 + idx_1[i], curPos.idx2 + idx_2[i], curPos.number);

                if (checkOutOfRange(nextPos)) {
                    continue;
                }

                if (board[nextPos.idx1][nextPos.idx2] == 0) {
                    continue;
                }

                if (!visited[nextPos.idx1][nextPos.idx2]) {
                    visited[nextPos.idx1][nextPos.idx2] = true;
                    step[nextPos.idx1][nextPos.idx2] = step[curPos.idx1][curPos.idx2];
                    q.add(nextPos);
                }
            }
        }
    }

    static int getParent(int idx) {
        if (parent[idx] == idx) {
            return idx;
        } else {
            return getParent(parent[idx]);
        }
    }

    static void solve() {
        // 플러드 필
        int numberOfIsland = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    // bfs
                    bfs(new Pointer(i, j, numberOfIsland), numberOfIsland);
                    numberOfIsland += 1;
                }
            }
        }

        // 번호 지정된 것들 마다 동서남북으로 뻗어있는 간선목록 정리
        for (Pointer pointer : posList) {
            // 동서남북 각각의 길이 계산
            int _prevIdx1 = pointer.idx1;
            int _prevIdx2 = pointer.idx2;

            for (int i = 0; i < 4; i++) {
                int len = 0;
                pointer.idx1 = _prevIdx1;
                pointer.idx2 = _prevIdx2;

                pointer.idx1 += idx_1[i];
                pointer.idx2 += idx_2[i];

                while (!checkOutOfRange(pointer) && board[pointer.idx1][pointer.idx2] == 0) {
                    pointer.idx1 += idx_1[i];
                    pointer.idx2 += idx_2[i];
                    len += 1;
                }

                if (checkOutOfRange(pointer)) {
                    continue;
                }

                if (board[pointer.idx1][pointer.idx2] == 1 && step[pointer.idx1][pointer.idx2] != pointer.number) {
                    Krus_GP krus_gp = new Krus_GP(pointer.number, step[pointer.idx1][pointer.idx2], len);

                    if (krus_gp.len > 1) {
                        krusGPList.add(krus_gp);
                    }
                }
            }
        }

        // krus gp list 정렬
        Collections.sort(krusGPList, new Comparator<Krus_GP>() {
            @Override
            public int compare(Krus_GP o1, Krus_GP o2) {
                return Integer.compare(o1.len, o2.len);
            }
        });

        int min_val = 0;

        // kruskal start
        for (Krus_GP krusGP : krusGPList) {
            int idx1 = getParent(krusGP.src);
            int idx2 = getParent(krusGP.dest);

            if (idx1 != idx2) {
                // merge
                parent[idx1] = idx2;
                min_val += krusGP.len;
            }
        }

        // 모든섬이 연결되었는지 확인
        int s = getParent(parent[1]);

        for (int i = 2; i < numberOfIsland; i++) {
            if (s != getParent(parent[i])) {
                answer = -1;
                return;
            }
        }
        answer = min_val;
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }

    static class Pointer {
        int idx1;
        int idx2;
        int number;

        Pointer() {
        }

        public Pointer(int idx1, int idx2, int number) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.number = number;
        }
    }

    static class Krus_GP {
        int src;
        int dest;
        int len;

        Krus_GP() {
        }

        public Krus_GP(int src, int dest, int len) {
            this.src = src;
            this.dest = dest;
            this.len = len;
        }
    }
}