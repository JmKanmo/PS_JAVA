package zerobase.study;

import java.util.Scanner;

public class Practice9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int answer = 0;

        for (int i = 0; i < str.length(); i++) {
            int start = i;
            int end = str.length() - 1;
            boolean flag = false;

            while (start < end) {
                if (str.charAt(start) != str.charAt(end)) {
                    break;
                }
                start++;
                end--;
            }

            if (start >= end) {
                flag = true;
            }

            if(flag) {
                answer = i + str.length();
                break;
            }
        }
        System.out.println(answer);
    }
}
