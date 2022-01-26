package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class Floyd_11404 {

    static int n, m;
    static int[][] map;
    static StringBuffer answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        int a, b, c;

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (map[a][b] == 0 || map[a][b] > c) {
                map[a][b] = c;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (j != k && map[j][i] != 0 && map[i][k] != 0) {
                        if (map[j][k] == 0 || map[j][k] > map[j][i] + map[i][k]) {
                            map[j][k] = map[j][i] + map[i][k];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            answer = new StringBuffer();
            for (int j = 1; j <= n; j++) {
                answer.append(map[i][j] + " ");
            }
            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
