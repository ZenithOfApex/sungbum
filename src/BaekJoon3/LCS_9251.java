package BaekJoon3;

import java.io.*;

public class LCS_9251 {

    static int[][] LCS;
    static int N, M;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputStr1 = br.readLine();
        String inputStr2 = br.readLine();

        N = inputStr1.length();
        M = inputStr2.length();

        LCS = new int[N+1][M+1];
        answer = 0;

        //margin 설정
        for (int i = 0; i <= N; i++) {
            LCS[i][0] = 0;
        }
        for (int i = 0; i <= M; i++) {
            LCS[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (inputStr1.charAt(i - 1) != inputStr2.charAt(j - 1)) {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                } else {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                }
            }
        }

        answer = LCS[N][M];

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
