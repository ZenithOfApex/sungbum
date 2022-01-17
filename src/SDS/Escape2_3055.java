package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape2_3055 {
    static int r,c,answer,endX=0,endY=0, startX = 0, startY = 0;
    static char map[][];
    static boolean visited[][];
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        answer =0;
        map = new char[r][c];
        visited = new boolean[r][c];
        queue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'D') {
                    endX = i;
                    endY = j;
                } else if (map[i][j] == 'S') {
                    startX =i;
                    startY = j;
                }
            }
        }
        run();
        br.close();
    }

    private static void run() {
        queue.add(new Point(-1, -1));
        queue.add(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == -1) {
                water();
                answer++;
                if (!queue.isEmpty()) {
                    queue.add(point);
                }
                continue;
            }
            if (point.x == endX && point.y == endY) {
                System.out.println(answer - 1);
                return;
            }
            next(point.x, point.y);
        }
        System.out.println("KAKTUS");
    }

    private static void next(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (check(nx, ny) && !visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
    }

    private static void water() {
        char temp[][]=  new char[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == '*') {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (check(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == 'S')) {
                            temp[nx][ny] = '*';
                        }
                    }
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = temp[i][j];
            }
        }

    }

    private static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < r && y < c;
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
