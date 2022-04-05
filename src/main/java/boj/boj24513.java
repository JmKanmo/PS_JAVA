package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 해설 보고 다시 풀어볼 것. (실패)
 */
public class boj24513 {
    static class Virus{
        int virus;
        int time;
        int r;
        int c;

        public Virus(int virus, int time) {
            this.virus = virus;
            this.time = time;
        }

        public Virus(int r, int c, int virus, int time) {
            super();
            this.r= r;
            this.c= c;
            this.virus = virus;
            this.time = time;
        }
    }

    static int R, C;
    static Virus[][] map;
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static Queue<Virus> queue = new LinkedList<>();
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new Virus[R][C];
        isVisited = new boolean[R][C];

        for(int r=0; r<R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++) {
                map[r][c] = new Virus(Integer.parseInt(st.nextToken()), 0);
                if(map[r][c].virus == 1 || map[r][c].virus==2) {
                    queue.offer(new Virus(r,c,map[r][c].virus, 0));
                }
            }
        }

        bfs();

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c].virus == 1) {
                    count1++;
                }
                else if(map[r][c].virus == 2) {
                    count2++;
                }
                else if(map[r][c].virus == 3) {
                    count3++;
                }
            }
        }

        System.out.println(count1+" "+count2+" "+count3);
    }

    static void bfs() {

        while(!queue.isEmpty()) {

            Virus now = queue.poll();
            int time = now.time;
            isVisited[now.r][now.c] = true;
            // 3번바이러스는 퍼지지 않는다.
            if(map[now.r][now.c].virus == 3)
                continue;

            for(int i=0; i<4; i++) {
                int nr = now.r + dir[i][0];
                int nc = now.c + dir[i][1];
                int virus = now.virus;

                if(nr<0 || nc<0 || nr>=R || nc>=C || isVisited[nr][nc] || map[nr][nc].virus == -1)
                    continue;

                // 아직 감염되지 않았을 경우
                if(map[nr][nc].virus == 0) {
                    map[nr][nc].virus = virus;
                    map[nr][nc].time = now.time+1;
                    queue.offer(new Virus(nr, nc, map[nr][nc].virus, map[nr][nc].time));
                }
                // 감염이 되어있는데 완전히 감염되었는지 여부를 확인
                else if(map[nr][nc].virus != virus && map[nr][nc].virus != 3) {
                    // 완전히 감염되지 않았을 경우 3번 바이러스로 변이(시간의 차이가 1밖에안난다면 아직 완전히 감염되지 않은 것이다.)
                    if(map[nr][nc].time > time && map[nr][nc].time - time == 1) {
                        map[nr][nc].virus = 3;
                    }

                }


            }


        }


    }
}
