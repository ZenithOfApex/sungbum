package BaekJoon3;

import java.io.*;
import java.util.StringTokenizer;

public class RollingDice_14499 {
    static int N, M, x, y, K;

    static int[] dice = new int[6];
    static int[] command;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        command = new int[K];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //주사위 올려놓기

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                if (!inRange(x, y + 1)) {
                    continue;
                }
                y++;
                int temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
            } else if (command == 2) {
                if (!inRange(x, y - 1)) {
                    continue;
                }
                y--;
                int temp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = temp;
            } else if (command == 3) {
                if (!inRange(x - 1, y)) {
                    continue;
                }
                x--;
                int temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = temp;
            } else {
                if (!inRange(x + 1, y)) {
                    continue;
                }
                x++;
                int temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
            }
            if (map[x][y] != 0) {
                dice[0] = map[x][y];
                map[x][y] = 0;
            } else {
                map[x][y] = dice[0];
            }
            bw.write(dice[1] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
