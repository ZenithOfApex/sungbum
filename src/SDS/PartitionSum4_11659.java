package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartitionSum4_11659 {

    static int n,m ;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n];
        dp[0] = numbers[0];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] + numbers[i];
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == b) {
                System.out.println(numbers[a - 1]);
            } else if (a != 1) {
                System.out.println(dp[b-1]-dp[a-2]);
            } else if (a == 1) {
                System.out.println(dp[b - 1]);
            }
        }

    }
}
