package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class DanceDanceRevolution_2342 {

    static int n;
    static int[][][] dp;
    static int[] direction;
    static int answer;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        direction = new int[100000 + 1];
        answer = INF;

        int now;
        st = new StringTokenizer(br.readLine(), " ");
        input :
        for (int i = 1; ; i++) {
            now = Integer.parseInt(st.nextToken());
            if (now == 0) {
                n = i - 1;
                break input;
            }
            direction[i] = now;
        }
        dp = new int[n + 1][5][5];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[1][0][direction[1]] = 2;
        dp[1][direction[1]][0] = 2;

        int next;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (dp[i][j][k] != INF) {
                        next = direction[i + 1];
                        if (k != next) {
                            dp[i + 1][next][k] = Math.min(dp[i + 1][next][k], dp[i][j][k] + getCost(j, next));
                        }
                        if (j != next) {
                            dp[i + 1][j][next] = Math.min(dp[i + 1][j][next], dp[i][j][k] + getCost(k, next));
                        }
                    }
                }
            }
        }

        for (int j = 0; j <= 4; j++) {
            for (int k = 0; k <= 4; k++) {
                answer = Math.min(answer, dp[n][j][k]);
            }
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCost(int from, int to) {
        if (from == to) {
            return 1;
        } else if (from == 0) {
            return 2;
        } else if (Math.abs(from - to) == 2) {
            return 4;
        }else return 3;
    }
}
