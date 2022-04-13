package programmers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class programmers_프린터 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Pointer> q = new LinkedList<>();
        Queue<Pointer> printed_q = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            q.add(new Pointer(priorities[i], i));
        }

        while (q.isEmpty() != true) {
            Pointer p = q.poll();

            int filtered_size = q.stream().filter(elem -> elem.priority > p.priority).collect(Collectors.toList()).size();

            if(filtered_size > 0) {
                q.add(p);
            }else{
                printed_q.add(p);
            }
        }

//        printed_q.stream().forEach(elem -> {
//            System.out.println(elem.toString());
//        });

        int idx = 1;

        while(printed_q.isEmpty() != true) {
            Pointer p = printed_q.poll();
            if(p.idx == location) {
                answer = idx;
                continue;
            }
            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        programmers_프린터 main = new programmers_프린터();
        int answer = main.solution(new int[]{2,1,3,2},2);
        System.out.println(answer);
    }

    static class Pointer {
        int priority;
        int idx;

        Pointer() {
        }

        public Pointer(int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Pointer{" +
                    "priority=" + priority +
                    ", idx=" + idx +
                    '}';
        }
    }
}
