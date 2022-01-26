package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IntegerTriangle_1932 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int[][] triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                if (st.hasMoreTokens()) {
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }else triangle[i][j] = 0;
            }
        }

        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];//초기값 입력

        for (int i = 1; i < n; i++) {//각 행의 좌측 끝은 선택지가 하나밖에 없음: 이전 level[0]더하기
            dp[i][0] = dp[i-1][0]+triangle[i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i+1; j++) {//triangle은 nxn 크기가 아니기에 j<i+1로 범위 잡음
                dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
            }
        }


        int[] answerList = dp[n - 1];
        Arrays.sort(answerList);
        answer = answerList[answerList.length - 1];

        return answer;
    }
}
