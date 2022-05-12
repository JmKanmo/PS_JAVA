package zerobase.part4;

public class Solution1 {
    public boolean solution(String s, String t) {
        boolean answer = true;
        char[]chArr_a = new char[52];
        char[] chArr_b  = new char[52];

        calAlphabet(s,chArr_a);
        calAlphabet(t,chArr_b);

        for(int i=0; i < chArr_a.length; i++) {
                if(chArr_a[i] != chArr_b[i]) {
                    return false;
                }
        }

        return true;
    }

    public void calAlphabet(String str, char[] vec) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                vec[str.charAt(i) - 'A']++;
            else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                vec[str.charAt(i) - 'a' + 26]++;
        }
    }

    public static <T> void countingSort(char[] arr) {
        char max_Ch = 0;

        for (char ch : arr) {
            if (max_Ch < ch) {
                max_Ch = ch;
            }
        }

        char[] cntArr = new char[max_Ch + 1];

        for (int i = 0; i < arr.length; i++) {
            cntArr[arr[i]]++;
        }

        int idx = 0;

        for (int i = 0; i < cntArr.length; i++) {
            while (cntArr[i] > 0) {
                arr[idx++] = (char) i;
                cntArr[i]--;
            }
        }
    }
}
