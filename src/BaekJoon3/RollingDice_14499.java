package BaekJoon3;

import java.io.*;
import java.util.StringTokenizer;

public class RollingDice_14499 {

    static int N, M, x, y, K;
    static int INF = Integer.MAX_VALUE;

    static int[] dice = new int[6];
    static int[] command;
    static int[][] map;
    static boolean[][] visited;

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
        visited = new boolean[N][M];

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
            if (solution(command) == INF) {
                continue;
            } else {
                bw.write(solution(command)+"\n");
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(int command) {
        if (command == 1) {//동쪽으로 엎어짐
            int nx = x;
            int ny = y+1;
            if (inRange(nx, ny)) {//범위 체크
                y++;
                int temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = temp;
                if (map[nx][ny] != 0) {
                    dice[0] = map[nx][ny];
                    map[nx][ny] = 0;
                } else {
                    map[nx][ny] = dice[0];
                }
            }else return INF;
        } else if (command == 2) {//서쪽으로 엎어짐
            int nx = x;
            int ny = y-1;
            if (inRange(nx,ny)) {//범위 체크
                y--;
                int temp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = temp;
                if (map[nx][ny] != 0) {
                    dice[0] = map[nx][ny];
                    map[nx][ny] = 0;
                } else {
                    map[nx][ny] = dice[0];
                }
            }else return INF;
        } else if (command == 3) {//북쪽으로 엎어짐
            int nx = x-1;
            int ny = y;
            if (inRange(nx,ny)) {//범위 체크
                x--;
                int temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = temp;
                if (map[nx][ny] != 0) {
                    dice[0] = map[nx][ny];
                    map[nx][ny] = 0;
                } else {
                    map[nx][ny] = dice[0];
                }
            }else return INF;
        } else if (command == 4) {//남쪽으로 엎어짐
            int nx = x+1;
            int ny = y;
            if (inRange(nx,ny)) {//범위 체크
                x++;
                int temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
                if (map[nx][ny] != 0) {
                    dice[0] = map[nx][ny];
                    map[nx][ny] = 0;
                } else {
                    map[nx][ny] = dice[0];
                }
            }else return INF;
        }

        //반환값은 주사위 윗면
        return dice[1];
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
