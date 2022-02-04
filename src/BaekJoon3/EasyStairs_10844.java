package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EasyStairs_10844 {
    static int N;
    static long[][] dp;
    static long MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        System.out.println(solution(N));

        br.close();
    }

    private static int solution(int n) {
        dp = new long[n+2][11];

        //dp 배열 초기화 dp[1]
        for (int i = 1; i < 10; i++) {
            dp[0][i-1] = i-1;
            dp[1][i] = 1;
        }


        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j - 1 >= 0) {
                    dp[i + 1][j - 1] += dp[i][j];
                }
                if (j + 1 <= 10) {
                    dp[i + 1][j + 1] += dp[i][j];
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (dp[n][i]%MOD);
//            sum += dp[n][i];
        }

        int answer = (int)(sum%MOD);

        return answer;
    }
}
