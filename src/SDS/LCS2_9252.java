package SDS;

import java.io.*;

public class LCS2_9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String B = br.readLine();
        int lengthA = A.length();
        int lengthB = B.length();
        int[][] dp = new int[lengthA + 1][lengthB + 1];
        int[][] direction = new int[lengthA + 1][lengthB + 1];

        final int fromA = 1;
        final int fromB = 2;
        final int equal = 3;

        for (int i = 1; i <= lengthA; i++) {
            for (int j = 1; j <= lengthB; j++) {
                if (dp[i - 1][j] >= dp[i][j - 1]) {
                    direction[i][j] = fromA;
                    dp[i][j] = dp[i - 1][j];
                } else {
                    direction[i][j] = fromB;
                    dp[i][j] = dp[i][j - 1];
                }
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    if (dp[i][j] < dp[i - 1][j - 1] + 1) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        direction[i][j] = equal;
                    }
                }
            }
        }

        StringBuffer LCS = new StringBuffer();
        for (int i = lengthA, j = lengthB; i >= 1 && j >= 1; ) {
            if (direction[i][j] == fromA) {
                i--;
            }
            if (direction[i][j] == fromB) {
                j--;
            }
            if (direction[i][j] == equal) {
                LCS.append(A.charAt(i - 1));
                i--;
                j--;
            }
        }

        bw.write(dp[lengthA][lengthB] + "\n");
        bw.write(LCS.reverse() + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
