package BaekJoon4;

import java.io.*;
import java.util.StringTokenizer;

public class MagicianSharkAndTornado_20057 {
    static int[][] map;
    static int N;
    static int answer = 0;
    static double[][] westDust = {{0, 0, 0.02, 0, 0}, {0, 0.1, 0.07, 0.01, 0}, {0.05, 0.45, 0, 0, 0}, {0, 0.1, 0.07, 0.01, 0}, {0, 0, 0.02, 0, 0}};

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int centerI = N / 2;
        int centerJ = N / 2;

        int startX = centerI;
        int startY = centerJ;
        int endX = startX-1;
        int endY = startY;
        int count = 0;
        int size = 1;
        int direction = 0;

        while (endX != 0 && endY != 0) {
            if (direction == 0) {
                endX = startX-size;
                endY = startY;
            } else if (direction == 1) {
                endX = startX;
                endY = startY+size;
            } else if (direction == 2) {
                endX = startX+size;
                endY = startY;
            } else {
                endX = startX;
                endY = startY-size;
            }
            rotatesDustMap(westDust,direction);
            applyDust(endX, endY, map[endX][endY]);

            //적용 다 끝나면
            direction = (++direction) % 4;
            if (count == 2) {
                size++;
                count=0;
            }
            //크기는 2번마다 하나씩 증가->count로 체크
            //방향은 매번 변경
            //endX랑 endY가 0,0 될때까지
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void moveTornado() {

    }


    private static void applyDust(int x, int y, int dust) {
        int percentSeven = (int) (dust * 0.07);
        int percentOne = (int) (dust * 0.01);
        int percentTen = (int) (dust * 0.1);
        int percentFive = (int) (dust * 0.05);
        int percentTwo = (int) (dust * 0.02);
        int alpha = dust - percentFive - percentSeven - percentOne - percentTen - percentTwo;


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!inRange(x - 2 + i, y - 2 + j)) {
                    answer += (int) (map[x - 2 + i][y - 2 + j] * westDust[i][j]);
                } else {
                    map[x - 2 + i][y - 2 + j] = (int) (map[x - 2 + i][y - 2 + j] * westDust[i][j]);
                }
            }
        }
    }

    private static void rotatesDustMap(double[][] dustMap, int dir) {
        double[][] tmp = new double[5][5];
        for (int k = 0; k < dir; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    tmp[4 - j][i] = dustMap[i][j];
                }
            }
            for (int i = 0; i < 5; i++) {
                System.arraycopy(tmp[i], 0, dustMap[i], 0, 5);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
