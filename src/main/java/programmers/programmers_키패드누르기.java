package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class programmers_키패드누르기 {
    static int[] idx_1 = {0, 0, 1, -1};
    static int[] idx_2 = {1, -1, 0, 0};
    static char[][] board = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {'*', 0, '#'}
    };

    static int hand_left = '*';
    static int hand_right = '#';


    static Pointer getPointer(int ch) {
        switch (ch) {
            case 1:
                return new Pointer(0, 0);
            case 2:
                return new Pointer(0, 1);
            case 3:
                return new Pointer(0, 2);
            case 4:
                return new Pointer(1, 0);
            case 5:
                return new Pointer(1, 1);
            case 6:
                return new Pointer(1, 2);
            case 7:
                return new Pointer(2, 0);
            case 8:
                return new Pointer(2, 1);
            case 9:
                return new Pointer(2, 2);
            case '*':
                return new Pointer(3, 0);
            case 0:
                return new Pointer(3, 1);
            case '#':
                return new Pointer(3, 2);
        }
        return null;
    }

    static int bfs(Pointer start, Pointer end) {
        boolean[][] visited = new boolean[4][3];
        int[][] moved = new int[4][3];
        Queue<Pointer> q = new LinkedList<>();
        int answer = 0;

        visited[start.idx1][start.idx2] = true;
        q.add(start);

        while (q.isEmpty() != true) {
            Pointer pos = q.poll();

            for (int i = 0; i < 4; i++) {
                Pointer next_pos = new Pointer(pos.idx1 + idx_1[i], pos.idx2 + idx_2[i]);

                if (next_pos.idx1 < 0 || next_pos.idx1 >= 4 || next_pos.idx2 < 0 || next_pos.idx2 >= 3) {
                    continue;
                }

                if (visited[next_pos.idx1][next_pos.idx2] == true) {
                    continue;
                }

                q.add(next_pos);
                visited[next_pos.idx1][next_pos.idx2] = true;
                moved[next_pos.idx1][next_pos.idx2] = moved[pos.idx1][pos.idx2] + 1;
            }
        }

        answer = moved[end.idx1][end.idx2];
        return answer;
    }

    static String calc_hand(int number, String hand) {
        String hand_way = "";

        switch (number) {
            // 왼쪽
            case 1:
            case 4:
            case 7:
                hand_way = "L";
                hand_left = number;
                break;

            // 오른쪽
            case 3:
            case 6:
            case 9:
                hand_way = "R";
                hand_right = number;
                break;

            // 가운데 .. 추가로직
            case 2:
            case 5:
            case 8:
            case 0: {
                // bfs로 left, right 거리를 구한다.
                int left_distance = bfs(getPointer(hand_left), getPointer(number));
                int right_distance = bfs(getPointer(hand_right), getPointer(number));

                if (left_distance == right_distance) {
                    if (hand.equals("right")) {
                        hand_way = "R";
                        hand_right = number;
                    } else if (hand.equals("left")) {
                        hand_way = "L";
                        hand_left = number;
                    }
                } else {
                    if (left_distance > right_distance) {
                        hand_way = "R";
                        hand_right = number;
                    } else {
                        hand_way = "L";
                        hand_left = number;
                    }
                }
                break;
            }
        }

        return hand_way;
    }

    public String solution(int[] numbers, String hand) {
        String answer = "";

        for (int number : numbers) {
            answer += calc_hand(number, hand);
        }

        return answer;
    }

    public static void main(String[] args) {
        programmers_키패드누르기 main = new programmers_키패드누르기();
        System.out.println(main.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"));
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
