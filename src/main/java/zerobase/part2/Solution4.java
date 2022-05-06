package zerobase.part2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution4 {
    static int[] idx1_x = {0, 0, 1, -1};
    static int[] idx1_y = {1, -1, 0, 0};

    static int[] idx2_x = {-1, -1, 1, 1};
    static int[] idx2_y = {1, -1, 1, -1};
    static boolean isFind = false;

    public int solution(int x1, int y1, int x2, int y2) {
        int time = 0;

        x1 += 1000;
        y1 += 1000;
        x2 += 1000;
        y2 += 1000;

        Queue<Pointer> q1 = new LinkedList<>();
        Queue<Pointer> q2 = new LinkedList<>();
        boolean[][] visited1 = new boolean[2001][2001];
        boolean[][] visited2 = new boolean[2001][2001];

        q1.add(new Pointer(x1, y1));
        visited1[x1][y1] = true;

        q2.add(new Pointer(x2, y2));
        visited2[x2][y2] = true;


        while (true) {
            if (isFind)
                break;

            int q1_size = q1.size();

            while (q1_size > 0) {
                Pointer cur = q1.poll();

                for (int i = 0; i < 4; i++) {
                    Pointer ptr = new Pointer(cur.idx1 + idx1_x[i], cur.idx2 + idx1_y[i]);

                    if (ptr.idx1 < 0 || ptr.idx1 > 2000 || ptr.idx2 < 0 || ptr.idx2 > 2000) {
                        continue;
                    }

                    if (visited1[ptr.idx1][ptr.idx2]) {
                        continue;
                    }

                    visited1[ptr.idx1][ptr.idx2] = true;
                    q1.add(ptr);
                }
                q1_size--;
            }

            int q2_size = q2.size();

            while (q2_size > 0) {
                Pointer cur = q2.poll();

                q1.stream().forEach(elem -> {
                    if (elem.idx1 == cur.idx1 && elem.idx2 == cur.idx2) {
                        isFind = true;
                    }
                });

                if (isFind == true) {
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    Pointer ptr = new Pointer(cur.idx1 + idx2_x[i], cur.idx2 + idx2_y[i]);

                    if (ptr.idx1 < 0 || ptr.idx1 > 2000 || ptr.idx2 < 0 || ptr.idx2 > 2000) {
                        continue;
                    }

                    if (visited2[ptr.idx1][ptr.idx2]) {
                        continue;
                    }

                    visited2[ptr.idx1][ptr.idx2] = true;
                    q2.add(ptr);
                }
                q2_size--;
            }
            time++;
        }
        return time;
    }


    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        System.out.println(solution.solution(2, 4, 5, -3));
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
