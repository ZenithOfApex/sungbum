package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape_3055 {
    private static int R,C, x, y, wx, wy, tx,ty;
    private static int[][] map;
    private static boolean[][] visited;
    private static boolean[][] waterVisited;
    private static Queue<Pos> q;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];
        waterVisited = new boolean[R][C];


        for (int i = 0; i < R; i++) {
            String temp = br.readLine();
            for (int j = 0; j < C; j++) {
                if (String.valueOf(temp.charAt(j)).equals("D")) {
                    map[i][j] = 0;
                    tx = i;
                    ty = j;
                } else if (String.valueOf(temp.charAt(j)).equals(".")) {
                    map[i][j] = 0;
                } else if (String.valueOf(temp.charAt(j)).equals("*")) {
                    wx = i;
                    wy = j;
                    map[i][j] = -1;
                } else if (String.valueOf(temp.charAt(j)).equals("S")) {
                    x = i;
                    y= j;
                    map[i][j] = 0;
                } else if (String.valueOf(temp.charAt(j)).equals("X")) {
                    map[i][j] = -2;
                }
            }
        }
        q = new LinkedList<>();
        q.offer(new Pos(wx, wy));
        q.offer(new Pos(x, y));
        visited[x][y] = true;
        waterVisited[wx][wy] = true;

        while (!q.isEmpty()) {
            Pos curPos = q.poll();

            if (map[curPos.x][curPos.y] == -1) {//물인 경우
                for (int i = 0; i < 4; i++) {
                    int nx = curPos.x + dx[i];
                    int ny = curPos.y + dy[i];

                    if (inRange(nx, ny) || map[nx][ny] !=0 || map[nx][ny]==-2) {
                        continue;
                    }
                    if ((nx == tx && ny == ty) || (nx==x && ny ==y)) {
                        continue;
                    }
                    if (waterVisited[nx][ny]) {
                        continue;
                    }
                    waterVisited[nx][ny] = true;
                    map[nx][ny] = -1;
                    q.offer(new Pos(nx, ny));
                }
            }else{//고슴도치인 경우
                for (int i = 0; i < 4; i++) {
                    int nx = curPos.x + dx[i];
                    int ny = curPos.y + dy[i];

                    if (inRange(nx, ny)|| map[nx][ny]==-2) {
                        continue;
                    }
                    if (map[nx][ny] != 0) {
                        continue;
                    }
                    if (visited[nx][ny]) {
                        continue;
                    }
                    visited[nx][ny] = true;
                    map[nx][ny] = map[curPos.x][curPos.y]+1;
                    q.offer(new Pos(nx, ny));
//                    if (nx == tx && ny == ty) {
////                        System.out.println(map[nx][ny]);
//                        break;
//                    }
                }
            }
            for (int[] ints : map) {
                for (int anInt : ints) {
                    System.out.print(anInt);
                }
                System.out.println();
            }
            System.out.println();

        }
        if (map[tx][ty] == 0) {
            System.out.println("KAKTUS");
        }else System.out.println(map[tx][ty]);
    }

    public static boolean inRange(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C;
    }

    public static class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
