package zerobase.part2;

import java.util.*;

class Solution1 {
    public int solution(int N, int[][] trust) {
        int answer = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int setSize = N - 1;

        for (int i = 0; i < trust.length; i++) {
            int[] arr = trust[i];
            targetMap.put(arr[0], 0);
            targetMap.put(arr[1], 0);


            set.add(arr[0]);
            set.add(arr[1]);

            List<Integer> list = map.get(arr[0]);

            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(arr[1]);
            map.put(arr[0], list);
        }

        for (Integer elem : set) {
            List<Integer> list = map.get(elem);

            if (list == null) continue;
            for (Integer listElem : list) {
                targetMap.put(listElem, targetMap.get(listElem) + 1);
            }
        }

        boolean flag = false;

        for (Integer setElem : set) {
            int cnt = targetMap.get(setElem);
            if (cnt == setSize) {
                answer = setElem;
                flag = true;
                break;
            }
        }

        if (flag = true) {
            return answer;
        } else {
            return -1;
        }

    }
}
