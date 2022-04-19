package zerobase.part1;

public class Prob4 {
    static int[][] board = new int[513][513];
    static int N;
    static int _number;

    public int solution(int n, int i, int j) {
        N = n;
        backTracking(n, new Pointer(n, n));
        int answer = board[i + 1][j + 1];
        return answer;
    }

    static void backTracking(int n, Pointer pos) {
        if (n == 2) {
            // 배열에 원소 대입
            setPointerToBoard(pos);
            return;
        }

        backTracking(n / 2, new Pointer(pos.idx1 - ((n / 2)), pos.idx2));
        backTracking(n / 2, new Pointer(pos.idx1 - ((n / 2)), pos.idx2 - ((n / 2))));
        backTracking(n / 2, new Pointer(pos.idx1, pos.idx2 - ((n / 2))));
        backTracking(n / 2, new Pointer(pos.idx1, pos.idx2));
    }


    static void setPointerToBoard(Pointer pointer) {
        board[pointer.idx1 - 1][pointer.idx2] = ++_number;
        board[pointer.idx1 - 1][pointer.idx2 - 1] = ++_number;
        board[pointer.idx1][pointer.idx2 - 1] = ++_number;
        board[pointer.idx1][pointer.idx2] = ++_number;
    }

    public static void main(String[] args) {
        Prob4 main = new Prob4();
        System.out.println(main.solution(8, 3, 6));
    }

    static class Pointer {
        int idx1;
        int idx2;

        Pointer() {
        }

        public Pointer(int idx1, int idx2) {
            this.idx1 = idx1;
            this.idx2 = idx2;
        }
    }
}
