package zerobase.part2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution3 {
    public int solution(int delay, int N) {
        int answer = 0;
        int time = 0;
        Queue<Ameba> amebaQueue = new LinkedList<>();

        amebaQueue.add(new Ameba());
        answer++;

        while (time < N) {
            int qSize = amebaQueue.size();

            while (qSize > 0) {
                Ameba ameba = amebaQueue.poll();

                if (ameba.isDelay) {
                    // N초간 delay
                    // delay 시간 체크 후, 만료시 분열
                    if (ameba.delay - 1 < 0) {
                        // 바로 분열
                        amebaQueue.add(new Ameba(false, 0));
                        answer++;
                        // delay후 분열
                        amebaQueue.add(new Ameba(true, delay));
                        answer++;
                    } else {
                        ameba.delay--;
                        amebaQueue.add(ameba);
                    }
                } else {
                    // 바로 분열
                    amebaQueue.add(new Ameba(false, 0));
                    answer++;
                    // delay후 분열
                    amebaQueue.add(new Ameba(true, delay));
                    answer++;
                }
                qSize--;
            }
            time++;
        }

        return answer;
    }

    static class Ameba {
        boolean isDelay;

        int delay;

        Ameba() {
        }

        public Ameba(boolean isDelay, int delay) {
            this.isDelay = isDelay;
            this.delay = delay;
        }
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        solution.solution(1, 2);
    }
}
