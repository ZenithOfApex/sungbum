package programmers;

import java.util.Arrays;

public class IntegerTriangle {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));

    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        int size = triangle.length;
        int[][] dp = new int[size][size];
        dp[0][0] = triangle[0][0];//초기값 입력

        for (int i = 1; i < size; i++) {//각 행의 좌측 끝은 선택지가 하나밖에 없음: 이전 level[0]더하기
            dp[i][0] = dp[i-1][0]+triangle[i][0];
        }

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < i+1; j++) {//triangle은 nxn 크기가 아니기에 j<i+1로 범위 잡음
                dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
            }
        }


        int[] answerList = dp[size - 1];
        Arrays.sort(answerList);
        answer = answerList[answerList.length - 1];

        return answer;
    }
}
