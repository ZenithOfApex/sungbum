package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuyingCards_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int[] cardPrice = new int[n+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            cardPrice[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        System.out.println(solution(n, cardPrice));
    }

    public static int solution(int n, int[] cardPrice) {
        int answer =0;
        int[] dp = new int[n+1];
        dp[0] = cardPrice[0];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + cardPrice[j]);
            }
        }
        answer = dp[n];

        return answer;
    }
}
