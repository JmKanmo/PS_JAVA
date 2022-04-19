package zerobase.part1;

import java.util.HashSet;
import java.util.Set;

public class Prob2 {
    static Set<String> set = new HashSet<>();

    public long solution(String[] names) {
        long answer = 0;

        for (String name : names) {
            set.add(name);
        }

        answer = getComb(set.size(), 4);
        return answer;
    }

    public long getComb(int n, int r) {
        return (factCalculator(n)) / ((factCalculator(n - r) * factCalculator(r)));
    }

    long factCalculator(int n) {
        long store_fact = 1;
        int i = 1;
        while (i <= n) {
            store_fact = store_fact * i;
            i++;
        }
        return store_fact;
    }
}
