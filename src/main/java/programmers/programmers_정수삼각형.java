package programmers;

public class programmers_정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];

        dp[0][0] = triangle[0][0];

        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                dp[i][j] = (triangle[i][j] + Math.max(
                        i - 1 < 0 || j - 1 < 0 ? 0 : dp[i - 1][j - 1],
                        i - 1 < 0 || j < 0 ? 0 : dp[i - 1][j]));
            }
        }

        for (int j = 0; j < dp[dp.length - 1].length; j++) {
            answer = Math.max(answer, dp[dp.length - 1][j]);
        }
        return answer;
    }

    public static void main(String[] args) {
        programmers_정수삼각형 main = new programmers_정수삼각형();
        main.solution(
                new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}
        );
    }
}
