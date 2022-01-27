package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class App_7079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int totalCost = 0;
        int memory[] = new int[n + 1];
        int cost[] = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }
        int dp[][] = new int[n + 1][totalCost + 1];

        int answer = totalCost + 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalCost; j++) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if (j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cost[i]] + memory[i]);
                }
            }
        }

        for (int i = 1; i <= totalCost; i++) {
            if (dp[n][i] >= m) {
                answer = i;
                break;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
