package BaekJoon;

import java.util.Queue;
import java.util.Scanner;

public class contamination_18405 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static int n,k,second,targetX, targetY;
    static Queue<coordinate> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
        second = sc.nextInt();
        targetX = sc.nextInt();
        targetY = sc.nextInt();
        sc.close();

        for (int timeCheck = 0; timeCheck < second; timeCheck++) {
            for (int virus = 1; virus <= k; virus++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] == virus && !visited[i][j]) {
                            for (int k = 0; k < 4; k++) {
                                int newX = i + dx[k];
                                int newY = j + dy[k];
                                if (!inBox(newX, newY)) {
                                    continue;
                                }
                                if (map[newX][newY] != 0) {
                                    continue;
                                }
                                visited[newX][newY] = true;
                                map[newX][newY] = virus;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited[i][j]=false;
                }
            }
        }
        System.out.println(map[targetX-1][targetY-1]);
    }

    public static boolean inBox(int x, int y) {
        if(x>=0 && y>=0 && x<n && y<n)
            return true;
        else return false;
    }
}
