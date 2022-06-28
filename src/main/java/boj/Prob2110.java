package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Prob2110 {
    static int N, C;
    static ArrayList<Integer> list = new ArrayList<>();
    static int answer;

    static void init() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] parsed = bufferedReader.readLine().split(" ");

            N = Integer.parseInt(parsed[0]);
            C = Integer.parseInt(parsed[1]);

            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(bufferedReader.readLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean check(int interval) {
        int cnt = 1;
        int start = list.get(0);

        for(int i=1; i< N; i++) {
            int end = list.get(i);
            int diff = end - start;

            if(diff >= interval) {
                cnt++;
                start = end;
            }
        }
        return cnt >= C ? true : false;
    }

    static void solve() {
        Collections.sort(list);

        int left = 1;
        int right = list.get(list.size() - 1) - list.get(0); // 1,000,000,000로 해도 별 차이 없음.

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }
}
