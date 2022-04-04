package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class programmers_캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> _list = new ArrayDeque<>();

        for (int i = 0; i < cities.length; i++) {
            String str = cities[i];
            str = str.toLowerCase();

            if(cacheSize == 0) {
                answer+=5;
                continue;
            }

            if (_list.contains(str) == false) {
                // cache miss
                answer+=5;

                if(_list.size() >= cacheSize) {
                    _list.pollLast();
                }
                _list.addFirst(str);
            } else {
                _list.remove(str);
                _list.addFirst(str);
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
