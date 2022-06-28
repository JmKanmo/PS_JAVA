package zerobase.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Practice13 {

    static int checkStr(String str, String check) {
        return str.indexOf(check);
    }

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String[] checkStrs = new String[]{"U", "C", "P", "C"};
            String str = bufferedReader.readLine().replaceAll(" ", "");

            for (String checkStr : checkStrs) {
                int index = checkStr(str, checkStr);

                if (index == -1) {
                    System.out.println("I hate UCPC");
                    return;
                }
                str = str.substring(index + 1);
            }
            System.out.println("I love UCPC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
