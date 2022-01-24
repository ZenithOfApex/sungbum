package BaekJoon3;

import java.io.*;
import java.util.*;

public class SafeArea_2468 {

    static int n,count;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];


        int maxRain = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int inputNum = Integer.parseInt(st.nextToken());
                map[i][j] = inputNum;
                maxRain = Math.max(maxRain, inputNum);
            }
        }

        int max =0;
        for (int height = 0; height <= maxRain+1; height++) {
            visited = new boolean[n][n];
            count =0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && map[i][j] > height) {
                        dfs(i, j, height);
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
        }
        System.out.println(max);
        br.close();
    }

    static void dfs(int x, int y, int height) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny) || visited[nx][ny]) {
                continue;
            }
            if (map[nx][ny] > height) {
                dfs(nx, ny, height);
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
