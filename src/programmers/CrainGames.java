package programmers;

import java.util.ArrayList;
import java.util.Stack;

public class CrainGames {
    static Stack<Integer> dolls = new Stack<>();
    static int answer = 0;
    static ArrayList<Stack<Integer>> stackList = new ArrayList<>();

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        answer = solution(board, moves);
        System.out.println(answer);
    }

    private static int solution(int[][] board, int[] moves) {
        int N = board.length;

        //stackList 초기화
        for (int i = 0; i <= N; i++) {
            stackList.add(new Stack<>());
        }

        //stackList에 값 대입
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    stackList.get(i + 1).push(board[j][i]);
                }
            }
        }
        activateCrain(moves);

        return answer;
    }

    private static void activateCrain(int[] moves) {
        for (int move : moves) {
            if (stackList.get(move).isEmpty()) {
                continue;
            } else {
                int pickedDoll = stackList.get(move).pop();
                inputDolls(pickedDoll);
            }
        }
    }

    private static void inputDolls(int n) {
        if (dolls.isEmpty()) {
            dolls.push(n);
        }else{
            if (dolls.peek() == n) {
                dolls.pop();
                answer += 2;
            } else {
                dolls.push(n);
            }
        }
    }
}
