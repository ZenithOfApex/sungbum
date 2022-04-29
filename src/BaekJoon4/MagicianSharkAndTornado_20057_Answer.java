package BaekJoon4;

import java.io.*;
import java.util.StringTokenizer;

public class MagicianSharkAndTornado_20057_Answer {
    static int N, map[][], result;
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {-1, 0, 1, 0};
    static int percent[] = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    static int moveX[][] = {
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {0, 0, 1, 1, 3, 1, 1, 2, 2, 2},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {0, 0, -1, -1, -3, -1, -1, -2, -2, -2}
    };
    static int moveY[][] = {
            {0, 0, -1, -1, -3, -1, -1, -2, -2, -2},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {0, 0, 1, 1, 3, 1, 1, 2, 2, 2},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(1, 0, 0, 0, N / 2, N / 2);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve(int nCnt, int cnt, int d, int dCnt, int x, int y) {
        if (x == 0 && y == 0) {
            return;
        }

        if (dCnt == nCnt) {
            if(++d==4) d=0;
            dCnt=0;
            cnt++;
        }
        if (cnt == 2 && nCnt != N - 1) {
            cnt=0;
            nCnt++;
        }

        int nx = x + dx[d];
        int ny = y + dy[d];

        int sand = map[nx][ny];
        int outSand = 0;

        for (int i = 0; i < 9; i++) {
            int sx = x + moveX[d][i];
            int sy = y + moveY[d][i];

            int plusSand = (int) (sand * ((double) percent[i] / 100));
            outSand += plusSand;
            if (sx < 0 || sy < 0 || sx >= N || sy >= N) {
                result += plusSand;
                continue;
            }
            map[sx][sy] += plusSand;
        }
        if (x + moveX[d][9] < 0 || y + moveY[d][9] < 0 || x + moveX[d][9] >= N || y + moveY[d][9] >= N) {
            result += sand - outSand;
        } else {
            map[x + moveX[d][9]][y + moveY[d][9]] += sand - outSand;
        }
        map[nx][ny] = 0;

        solve(nCnt, cnt, d, dCnt + 1, nx, ny);
    }
}
