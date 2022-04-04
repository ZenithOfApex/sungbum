package BaekJoon4;

import java.io.*;
import java.util.StringTokenizer;

public class Mineral_2933 {
    static int R,C;
    static int answer = 0;
    static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        R = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                if (st.nextToken().equals(".")) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());

        int[] spears = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            spears[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
