package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class MatrixMultiplySequence_11049 {
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int matrixR[] = new int[n + 1];
        int matrixC[] = new int[n + 1];
        int d[][] = new int[n + 1 + 1][n + 1 + 1];
        final int INF = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            matrixR[i] = Integer.parseInt(st.nextToken());
            matrixC[i] = Integer.parseInt(st.nextToken());
        }

        //D[i][j] = i번 matrix에서 j번 매트릭스까지 곱할때 최소 연산
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                d[i][j] = INF;
                for (int k = i; k <= j; k++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k + 1][j] + matrixR[i] * matrixC[k] * matrixC[j]);
                }
            }
        }

        bw.write(d[1][n] + "\n");
        bw.flush();
        bw.close();
        br.close();

    }
}
