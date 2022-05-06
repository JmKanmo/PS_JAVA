package zerobase.part3;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Integer> str = new ArrayList<>();
        str.add(100);
        str.add(200);
        str.add(300);
        str.add(400);

        Integer[] strArr = new Integer[str.size()];
        str.toArray(strArr);

        for (Integer num : str) {
            System.out.println(num);
        }
    }
}
