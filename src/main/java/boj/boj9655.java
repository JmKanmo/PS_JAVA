package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj9655 {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int N = Integer.parseInt(bufferedReader.readLine());
            if (N % 2 == 0) {
                System.out.println("CY");
            } else {
                System.out.println("SK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
