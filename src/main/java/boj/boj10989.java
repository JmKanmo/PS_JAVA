package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class boj10989 {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            int N = Integer.parseInt(bufferedReader.readLine());
            short[] arr = new short[N + 1];

            for (int i = 0; i < N; i++) {
                arr[i] = Short.parseShort(bufferedReader.readLine());
            }

            Arrays.sort(arr);

            for (int i = 1; i < arr.length; i++) {
                bufferedWriter.write(arr[i] + "\n");
            }

            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 계수 정렬
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] cntArr = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            cntArr[arr[i]]++;
        }

        int idx = 0;

        for (int i = 0; i < cntArr.length; i++) {
            while (cntArr[i] > 0) {
                arr[idx++] = i;
                cntArr[i]--;
            }
        }
    }
}
