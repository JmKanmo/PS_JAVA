package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class programmers_게임맵최단거리 {
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};


    static int bfs(int[][] maps, Pointer start, Pointer end) {
        int answer = 0;
        boolean[][] visited = new boolean[102][102];
        int[][] moved = new int[102][102];
        Queue<Pointer> q = new LinkedList<>();


        visited[start.idx1][start.idx2] = true;
        moved[start.idx1][start.idx2] = 1;
        q.add(start);

        while (q.isEmpty() != true) {
            Pointer pos = q.poll();

            for (int i = 0; i < 4; i++) {
                Pointer next_pos = new Pointer(pos.idx1 + idx_1[i], pos.idx2 + idx_2[i]);

                if (next_pos.idx1 < 0 || next_pos.idx1 >= end.idx1 + 1 || next_pos.idx2 < 0 || next_pos.idx2 >= end.idx2 + 1)
                    continue;

                if (next_pos.idx1 >= maps.length || next_pos.idx2 >= maps[maps.length - 1].length) continue;

                if (visited[next_pos.idx1][next_pos.idx2] || maps[next_pos.idx1][next_pos.idx2] == 0)
                    continue;

                visited[next_pos.idx1][next_pos.idx2] = true;
                moved[next_pos.idx1][next_pos.idx2] = moved[pos.idx1][pos.idx2] + 1;
                q.add(next_pos);
            }
        }

        answer = moved[end.idx1][end.idx2];
        answer = answer == 0 ? -1 : answer;
        return answer;
    }

    public int solution(int[][] maps) {
        return bfs(maps, new Pointer(0, 0), new Pointer(maps.length - 1, maps[maps.length - 1].length - 1));
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