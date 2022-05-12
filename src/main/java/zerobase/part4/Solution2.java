package zerobase.part4;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution2 {
    public String solution(int[] numbers) {
        String[] strNumbers = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strNumbers, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        return Stream.of(strNumbers).collect(Collectors.joining());
    }


    public static void main(String[] args) {

    }
}
