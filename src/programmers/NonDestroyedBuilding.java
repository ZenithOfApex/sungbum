package programmers;

public class NonDestroyedBuilding {


    public static void main(String[] args) {

        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};

        System.out.println(solution(board, skill));
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        int preSum[][] = new int[N + 1][M + 1];

        for (int[] turn : skill) {
            int type = turn[0];
            int r1 = turn[1];
            int c1 = turn[2];
            int r2 = turn[3];
            int c2 = turn[4];
            int degree = turn[5];

            if (type == 1) {
                preSum[r1][c1] += -degree;
                preSum[r2 + 1][c1] += degree;
                preSum[r1][c2+1] += degree;
                preSum[r2+1][c2+1] += -degree;

            } else if (type == 2) {
                preSum[r1][c1] += degree;
                preSum[r2 + 1][c1] += -degree;
                preSum[r1][c2+1] += -degree;
                preSum[r2+1][c2+1] += +degree;
            }
        }

        for (int i = 0; i < N + 1; i++) {
            int sum = 0;
            for (int j = 0; j < M + 1; j++) {
                sum += preSum[i][j];
                preSum[i][j] = sum;
            }
        }

        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += preSum[j][i];
                preSum[j][i] = sum;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (preSum[i][j] + board[i][j] > 0) {
                    answer++;
                }
            }
        }


        return answer;
    }
}
