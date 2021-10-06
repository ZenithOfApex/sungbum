package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lab_14502 {
    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int m;
    static int[][] map;
    static int[][] copyed;
    static List<Dot> virusList = new ArrayList<Dot>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int max = 0;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        copyed = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j]==2)
                    virusList.add(new Dot(i,j));
            }
            sc.nextLine();
        }

        setWall(0, 0);
        System.out.println(max);
    }

    static void setWall(int start, int depth) {
        if (depth == 3) {

            copyMap();

            for (Dot dot : virusList) {
                spreadVirus(dot.x, dot.y);
            }

            max = Math.max(max, getSafeArea());
            return;
        }

        for (int i = start; i < n * m; i++) {
            int x = i / m;
            int y = i % m;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                setWall(i + 1, depth + 1);
                map[x][y] = 0;
            }
        }
    }
    static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyed[i][j] = map[i][j];
            }
        }
    }

    static void spreadVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (copyed[nx][ny] == 0) {
                    copyed[nx][ny] = 2;
                    spreadVirus(nx, ny);
                }
            }
        }
    }

    static int getSafeArea() {
        int safe = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyed[i][j] == 0)
                    safe++;
            }
        }
        return safe;
    }
}
