package zerobase.part1;

public class Prob1 {
    public int solution(int n) {
        boolean[] primes = new boolean[101];
        primes[1] = true;


        for (int i = 2; i <= Math.sqrt(100); i++) {
            for (int j = i * 2; j <= 100; j += i) {
                primes[j] = true;
            }
        }

        if (n == 1) {
            return 0;
        }

        int answer = 0;

        for (int i = 2; i < n; i++) {
            if (!primes[i]) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Prob1 main = new Prob1();
        System.out.println(main.solution(20));
    }
}
