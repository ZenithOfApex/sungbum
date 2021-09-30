package BaekJoon;

import java.util.Scanner;

public class organicCabbage_1012 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n,m,position;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCaseSize = sc.nextInt();
        for (int testCase = 0; testCase < testCaseSize; testCase++) {
            m = sc.nextInt();
            n = sc.nextInt();
            position = sc.nextInt();

            map = new int[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < position; i++) {
                int a = sc.nextInt();
                int b= sc.nextInt();
                map[b][a] = 1;
            }
            int count =0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (0 <= newX && newX <n && 0<=newY && newY <m) {
                dfs(newX, newY);
            }
        }
    }
}
