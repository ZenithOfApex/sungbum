package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibo2_2748 {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new long[n+2];
        dp[0] =0;
        dp[1] = 1;
        if (n == 1 || n == 0) {
            System.out.println(dp[n]);
        } else {
            for (int i = 2; i < n + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            long result = dp[n];
            System.out.println(result);
        }

        br.close();
    }
}
