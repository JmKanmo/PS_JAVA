package zerobase.study;

import java.util.*;

public class Practice4 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer>[] stArr = new Stack[board[0].length];
        Stack<Integer> bucket = new Stack<>();

        for (int i = 0; i < board[0].length; i++) {
            stArr[i] = new Stack<Integer>();

            for (int j = board.length - 1; j >= 0; j--) {
                if (board[j][i] == 0)
                    continue;

                stArr[i].push(board[j][i]);
            }
        }

        for (int move : moves) {
            Stack<Integer> target = stArr[move - 1];

            if (target.isEmpty()) {
                continue;
            }

            int sticker = target.pop();

            if (!bucket.isEmpty() && sticker == bucket.peek()) {
                answer += 2;
                bucket.pop();
            } else {
                bucket.push(sticker);
            }
        }
        return answer;
    }

//    public static void main(String[] args) {
//        Practice4 practice4 = new Practice4();
//        System.out.println(practice4.solution(
//                new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}},
//                new int[]{1, 5, 3, 5, 1, 2, 1, 4}
//        ));
//    }
}
