package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedyPanda_1937 {

    static int n;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[][] map;
    static int[][] pathLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        pathLength = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int r, int c) {
        if (pathLength[r][c] != 0) {//이미 값이 저장되어 있는 경우에는 반환
            return pathLength[r][c];
        }
        pathLength[r][c] = 1;//최소 보장

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
                if (map[nr][nc] > map[r][c]) {
                    pathLength[r][c] = Math.max(pathLength[r][c], dfs(nr, nc) + 1);//상하좌우에서 가장 큰 수를 택해서 반환
                    dfs(nr, nc);
                }
            }
        }
        return pathLength[r][c];
    }
}
