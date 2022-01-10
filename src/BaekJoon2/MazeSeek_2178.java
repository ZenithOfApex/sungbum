package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MazeSeek_2178 {
    static int n,m,x,y;
    static int[][] map;
    static Queue<Point> q;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        x= 0;
        y= 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String tempStr = st.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(tempStr.charAt(j)));
            }
        }

        q.offer(new Point(x, y));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Point pt = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pt.x + dx[i];
                int ny = pt.y + dy[i];


                if (inRange(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                q.offer(new Point(nx, ny));
                map[nx][ny] = map[pt.x][pt.y]+1;
                visited[nx][ny] = true;
            }
        }
        System.out.println(map[n-1][m-1]);
        br.close();
    }

    public static boolean inRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
