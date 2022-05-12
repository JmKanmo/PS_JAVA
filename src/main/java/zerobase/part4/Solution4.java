package zerobase.part4;

import java.util.Arrays;

public class Solution4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        solution4.solution(10,new int[]{6,7,10,16,12});
    }

    public int solution(int N, int[] branches) {
        long answer = 0;
        long left = 1;
        long right = Arrays.stream(branches).max().getAsInt();

        while (left <= right) {
            long mid = (left + right) / 2;

            int cnt = 0;

            for (int i = 0; i < branches.length; i++) {
                cnt += (branches[i] / mid);
            }

            if (cnt >= N) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }

        if (right == 0) {
            answer = -1;
        }
        return (int) answer;
    }
}
