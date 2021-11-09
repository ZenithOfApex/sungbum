package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class numberingHouse_2667 {
    static int n;
    static int ret;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        ArrayList<Integer> houses = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visit = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }
        br.close();

        int count =0;
        ret=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    dfs(i,j);
                    houses.add(ret+1);
                    ret=0;
                    count++;

                }
            }
        }
        System.out.println(count);
        Collections.sort(houses);
        for (Integer house : houses) {
            System.out.println(house);
        }
    }

    private static void dfs(int r, int c) {
        visit[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
                if (map[nr][nc] == 1 && !visit[nr][nc]) {
                    ret++;
                    dfs(nr, nc);
                }
            }
        }
    }
}
