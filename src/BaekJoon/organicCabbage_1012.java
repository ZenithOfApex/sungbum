//package BaekJoon;
//
//import java.util.Scanner;
//
//public class organicCabbage_1012 {
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, 1, 0, -1};
//    static int n,m,position;
//    static int[][] map;
//    static boolean[][] visited;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int testCaseSize = sc.nextInt();
//        for (int testCase = 0; testCase < testCaseSize; testCase++) {
//            m = sc.nextInt();
//            n = sc.nextInt();
//            position = sc.nextInt();
//
//            map = new int[n][m];
//            visited = new boolean[n][m];
//            for (int i = 0; i < position; i++) {
//                int a = sc.nextInt();
//                int b= sc.nextInt();
//                map[b][a] = 1;
//            }
//            int count =0;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    if (map[i][j] == 1 && !visited[i][j]) {
//                        dfs(i, j);
//                        count++;
//                    }
//                }
//            }
//            System.out.println(count);
//        }
//    }
//
//    public static void dfs(int x, int y){
//        visited[x][y] = true;
//        for (int i = 0; i < 4; i++) {
//            int newX = x + dx[i];
//            int newY = y + dy[i];
//            if (0 <= newX && newX <n && 0<=newY && newY <m) {
//                dfs(newX, newY);
//            }
//        }
//    }
//}
package BaekJoon;

import java.util.Scanner;

public class organicCabbage_1012 {
    static int m;
    static int n;
    static int k;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        for (int testcase = 0; testcase < test; testcase++) {
            m = sc.nextInt();
            n = sc.nextInt();
            k = sc.nextInt();

            map = new int[m][n];
            visit = new boolean[m][n];

            for (int i = 0; i < k; i++) {
                map[sc.nextInt()][sc.nextInt()] = 1;
            }
            int count =0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 1 && !visit[i][j]) {
                        dfs(i,j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void dfs(int r, int c) {
        visit[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nc >= 0 && nr < m && nc < n) {
                if (map[nr][nc] == 1 && !visit[nr][nc]) {
                    dfs(nr, nc);
                }
            }
        }
    }

}
