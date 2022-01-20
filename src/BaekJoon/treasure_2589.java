package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class treasure_2589 {
    static char[][] map;
    static boolean[][] visited;
    static int n;
    static int m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int minTime;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        map = new char[n][m];
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                visited[i][j] = false;
            }
        }//방문 2차원 배열 false로 초괴화

        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        sc.close();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if (map[i][j] == 'L') {
                    visited = new boolean[n][m];
                    int time = bfs(new Node(i, j, 0));
                    minTime = Math.max(minTime, time);
                }
            }
        }
    }

    public static int bfs(Node node) {
        int ret = 0;
        Queue<Node> q = new LinkedList<Node>();
        q.add(node);
        visited[node.x][node.y] = true;

        while (!q.isEmpty()) {
            Node node1 = q.poll();
            for(int d=0;d<4;d++){
                int newX = node1.x + dx[d];
                int newY = node1.y + dy[d];

                if (newX < 0 || newX > n || newY < 0 || newY > n) {
                    continue;
                }
                if (isIn(newX, newY) && !visited[newX][newY] && map[newX][newY] == 'L') {
                    visited[newX][newY] = true;
                    q.add(new Node(newX, newY, node1.cnt++));
                    ret = Math.max(ret, node1.cnt + 1);
                }
            }
        }
        return ret;
    }

    private static boolean isIn(int newX, int newY) {
        if (newX < 0 || newX > n || newY < 0 || newY > m) {
            return false;
        }else return true;
    }

    public static class Node{
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
