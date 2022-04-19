package zerobase.part1;

public class Prob3 {
    static int[] arr =new int[11];

    public int solution(int N) {
        set();
        int answer = arr[N];
        return answer;
    }

    public void set() {
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;

        for(int i=4; i<=10; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
    }

    public static void main(String[] args) {
        Prob3 main = new Prob3();
        System.out.println(main.solution(4));
    }
}
