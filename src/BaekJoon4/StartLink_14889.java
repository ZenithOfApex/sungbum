package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StartLink_14889 {
    static int N, answer = 0;
    static int starScore;
    static int linkScore;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];//사람 번호도 1번부터 N번 행렬도 1부터 N이라고 가정하고 풀자
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void getMinimumScoreGap(ArrayList<Integer> teamStar, ArrayList<Integer> teamLink) {

    }
}
