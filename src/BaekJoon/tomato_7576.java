package BaekJoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class tomato_7576 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] box;
    static int n;
    static int m;
    static Queue<coordinate> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int answer = 0;
        box = new int[n][m];
        queue = new LinkedList<coordinate>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                box[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 1) {
                    queue.add(new coordinate(i, j));
                }
            }
        }
        bfs();
    }

    public static boolean inBox(int x, int y) {
        if(x>=0 && y>=0 && x<n && y<m)
            return true;
        else return false;
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            coordinate coor = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = coor.x +dx[i];
                int newY = coor.y + dy[i];

                if (!inBox(newX, newY)) {
                    continue;
                }
                if (box[newX][newY]!=0) {
                    continue;
                }
                box[newX][newY] = box[coor.x][coor.y]+1;
                queue.add(new coordinate(newX, newY));
            }
        }
        int max= 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                max = Math.max(box[i][j], max);
            }
        }
        System.out.println(max-1);
    }

    public static class coordinate{
        int x;
        int y;

        public coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

