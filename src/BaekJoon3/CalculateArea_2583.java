package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CalculateArea_2583 {

    static int N,M,K;
    static int[][] map;
    static int[] coordinate;
    static int x,y;
    static Queue<Pos> queue;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> areaList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        coordinate = new int[4];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                coordinate[j] = Integer.parseInt(st.nextToken());
            }
            markingMap(map, coordinate);
        }

        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    queue.add(new Pos(i, j));
                    int count =0;
                    while (!queue.isEmpty()) {
                        Pos curPos = queue.poll();
                        x = curPos.x;
                        y = curPos.y;
                        map[x][y] = 1;

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (inRange(nx, ny)) {
                                continue;
                            }
                            if (visited[nx][ny] || map[nx][ny]!=0) {
                                continue;
                            }
                            queue.add(new Pos(nx, ny));
                            visited[nx][ny] = true;
                            count++;
                        }
                    }
                    areaList.add(++count);
                }else continue;
            }
        }
        Collections.sort(areaList);
        System.out.println(areaList.size());
        for (Integer area : areaList) {
            System.out.print(area+" ");
        }
        br.close();
    }

    public static void markingMap(int[][] map, int[] coordinate) {
        int x1 = coordinate[0];
        int y1 = coordinate[1];
        int x2 = coordinate[2];
        int y2 = coordinate[3];

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                map[j][i] = 1;
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
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
