package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntoOne_1463 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 3];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        if(n>4){
            setDp(n);
        }

        System.out.println(dp[n]);
        br.close();
    }

    static void setDp(int n) {
        for (int i = 4; i <= n; i++) {
            if (i % 3 == 0 && i % 2 == 0) {
                if (dp[i / 3] < dp[i / 2]) {
                    dp[i] = dp[i / 3] + 1;
                } else dp[i] = dp[i / 2] + 1;
            } else if (i % 3 == 0) {
                if (dp[i - 1] < dp[i / 3]) {
                    dp[i] = dp[i - 1] + 1;
                } else dp[i] = dp[i / 3] + 1;
            } else if (i % 2 == 0) {
                if (dp[i - 1] < dp[i / 2]) {
                    dp[i] = dp[i - 1] + 1;
                } else dp[i] = dp[i / 2] + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
    }
}
