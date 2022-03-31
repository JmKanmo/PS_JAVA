package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int N, M, K;
    static Board[][] board = new Board[51][51];

    static void init() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] splited = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(splited[0]);
            M = Integer.parseInt(splited[1]);
            K = Integer.parseInt(splited[2]);

            for (int i = 0; i < 51; i++) {
                for (int j = 0; j < 51; j++) {
                    board[i][j] = new Board(new LinkedList<>());
                }
            }

            for (int i = 0; i < M; i++) {
                String[] _parsed = bufferedReader.readLine().split(" ");
                int r = Integer.parseInt(_parsed[0]);
                int c = Integer.parseInt(_parsed[1]);
                int m = Integer.parseInt(_parsed[2]);
                int s = Integer.parseInt(_parsed[3]);
                int d = Integer.parseInt(_parsed[4]);

                FireBall fireBall = new FireBall(r, c, m, s, d);
                board[r][c].fireBallQueue.add(fireBall);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Pointer getPointer(int way) {
        switch (way) {
            case 0:
                return new Pointer(-1, 0);
            case 1:
                return new Pointer(-1, 1);
            case 2:
                return new Pointer(0, 1);
            case 3:
                return new Pointer(1, 1);
            case 4:
                return new Pointer(1, 0);
            case 5:
                return new Pointer(1, -1);
            case 6:
                return new Pointer(0, -1);
            case 7:
                return new Pointer(-1, -1);
        }
        return null;
    }

    static void fireBallMerge(Pointer pos) {
        // 파이어볼 합치기
        FireBall mergedFireBall = new FireBall();

        Queue<FireBall> fireBallQueue = board[pos.r][pos.c].fireBallQueue;
        int _size = board[pos.r][pos.c].fireBallQueue.size();

        boolean isHorsu = true;
        boolean isJjack = true;

        while (fireBallQueue.isEmpty() != true) {
            FireBall _pollQ = fireBallQueue.poll();
            mergedFireBall.r = _pollQ.r;
            mergedFireBall.c = _pollQ.c;
            mergedFireBall.m += _pollQ.m;
            mergedFireBall.s += _pollQ.s;
            // d는 지정 x
            int _d = mergedFireBall.d;

            if (_d % 2 != 0) {
                isJjack = false;
            } else {
                isHorsu = false;
            }
        }

        // true ==> 1,3,5,7
        // false ==> 0,2,4,6
        boolean isMergedWay = (isHorsu == false && isJjack == false) ? true : false;
        // 4개로 나뉘어진다.
        FireBall fireBall1 = new FireBall(
                mergedFireBall.r
                , mergedFireBall.c
                , mergedFireBall.m / 5
                , mergedFireBall.s / _size
                , isMergedWay == true ? 1 : 0);

        if (fireBall1.m > 0) {
            fireBallQueue.add(fireBall1);
        }

        FireBall fireBall2 = new FireBall(
                mergedFireBall.r
                , mergedFireBall.c
                , mergedFireBall.m / 5
                , mergedFireBall.s / _size
                , isMergedWay == true ? 3 : 2);

        if (fireBall2.m > 0) {
            fireBallQueue.add(fireBall2);
        }

        FireBall fireBall3 = new FireBall(
                mergedFireBall.r
                , mergedFireBall.c
                , mergedFireBall.m / 5
                , mergedFireBall.s / _size
                , isMergedWay == true ? 5 : 4);

        if (fireBall3.m > 0) {
            fireBallQueue.add(fireBall3);
        }

        FireBall fireBall4 = new FireBall(
                mergedFireBall.r
                , mergedFireBall.c
                , mergedFireBall.m / 5
                , mergedFireBall.s / _size
                , isMergedWay == true ? 7 : 6);

        if (fireBall4.m > 0) {
            fireBallQueue.add(fireBall4);
        }
    }

    static int getfireBallM() {
        int sum = 0;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                Queue<FireBall> q = board[i][j].fireBallQueue;

                while (q.isEmpty() != true) {
                    sum += q.poll().m;
                }
            }
        }
        return sum;
    }

    static void solve() {
        for (int count = 0; count < K; count++) {
            // 파이어볼 이동
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    Queue<FireBall> _q = board[i][j].fireBallQueue;
                    int _size = _q.size();

                    while (_size-- > 0) {
                        FireBall _movedFireball = board[i][j].fireBallQueue.poll();

                        if (_movedFireball.isMoved) {
                            board[i][j].fireBallQueue.add(_movedFireball);
                            continue;
                        }

                        int _d = _movedFireball.d;
                        int _s = _movedFireball.s;
                        Pointer _movedPointer = getPointer(_d);
                        Pointer _newPointer = new Pointer(_movedFireball.r + _movedPointer.r * _s, _movedFireball.c + _movedPointer.c * _s);
                        _movedFireball.r = _newPointer.r;
                        _movedFireball.c = _newPointer.c;
                        board[_newPointer.r][_newPointer.c].fireBallQueue.add(_movedFireball);
                        _movedFireball.isMoved = true;
                    }
                }
            }

            int n = 25;
            //2개 이상의 파이어볼이 있는 칸
            for (int k = 0; k <= N; k++) {
                for (int l = 0; l <= N; l++) {
                    int size = board[k][l].fireBallQueue.size();

                    if (size >= 2) {
                        fireBallMerge(new Pointer(k, l));
                    }
                }
            }
        }

        // 파이어볼 질량의 합 구하기
        System.out.println(getfireBallM());
    }

    public static void main(String[] args) {
        init();
        solve();
    }

    static class Board {
        Queue<FireBall> fireBallQueue;

        public Board(Queue<FireBall> fireBallQueue) {
            this.fireBallQueue = fireBallQueue;
        }
    }

    static class Pointer {
        int r;
        int c;

        Pointer() {
        }

        public Pointer(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class FireBall {
        int r;
        int c;
        int m;
        int s;
        int d;
        boolean isMoved;

        FireBall() {
        }

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
