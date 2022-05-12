package zerobase.study;

public class Practice6 {
    public int[] solution(int n) {
        int[] answer = new int[(n * (n + 1)) / 2];
        int[][] matrix = new int[n][n];

        int x = -1, y = 0, num = 0;

        for (int i = 0; i < n; i++) {
            int div = i % 3;

            switch (div) {
                case 0: {
                    for (int j = i; j < n; j++) {
                        matrix[++x][y] = ++num;
                    }
                }
                break;
                case 1: {
                    for (int j = i; j < n; j++) {
                        matrix[x][++y] = ++num;
                    }
                }
                break;
                case 2: {
                    for (int j = i; j < n; j++) {
                        matrix[--x][--y] = ++num;
                    }
                }
                break;
            }
        }

        for (int i = 0, idx = 0; i < n; i++) {
            for(int j=0; j < i+1; j++) {
                answer[idx++] = matrix[i][j];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Practice6 practice6 = new Practice6();
        System.out.println(practice6.solution(6));
    }

}
