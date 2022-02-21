package BaekJoon3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GoodbyeDust_17144 {

    static int R, C, T;
    static int answer = 0;

    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static Queue<Dust> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        int bottomPosition = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    bottomPosition = i;
                }
            }
        }

        while (T-- > 0) {
            resetQueue();
            dustExpansion();
            map = activateMachine(bottomPosition-1,bottomPosition,map);
        }

        answer = getAnswer();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[][] activateMachine(int topPosition, int bottomPosition,int[][] map) {//공기청정기의 상단부와 하단부의 좌표 입력
        //입력 받은 좌표로 두개의 분기와 바람 방향으로 공기청정기 작동
        int[][] rtnMap = map;
        //상단부
        for (int i = topPosition; i > 0; i--) {
            rtnMap[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            rtnMap[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < topPosition; i++) {
            rtnMap[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            rtnMap[topPosition][i] = map[topPosition][i - 1];
            if(i==2) rtnMap[topPosition][i - 1] = 0;
        }
        map[topPosition][0] = -1;
        //하단부
        for (int i = bottomPosition + 1; i < R - 1; i++) {
            rtnMap[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            rtnMap[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i > bottomPosition; i--) {
            rtnMap[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            rtnMap[bottomPosition][i] = map[bottomPosition][i - 1];
            if (i == 2) {
                rtnMap[bottomPosition][i - 1] = 0;
            }
        }
        map[bottomPosition][0] = -1;

        return rtnMap;
    }

    private static int getAnswer() {
        int rtnValue = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    rtnValue += map[i][j];
                }
            }
        }
        return rtnValue;
    }

    private static void dustExpansion() {
        while (!queue.isEmpty()) {
            Dust polledDust = queue.poll();
            int count = 0;
            int spreadAmount = polledDust.amount / 5;

            for (int i = 0; i < 4; i++) {
                int nx = polledDust.x + dx[i];
                int ny = polledDust.y + dy[i];

                if (inRange(nx, ny) && map[nx][ny] != -1) {
                    map[nx][ny] = map[nx][ny] + spreadAmount;
                    count++;
                }
            }

            map[polledDust.x][polledDust.y] -= (count * spreadAmount);
        }
    }

    private static void resetQueue() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    queue.offer(new Dust(i, j, map[i][j]));
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static class Dust{
        int x;
        int y;
        int amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }
}
