package BaekJoon3;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CastleDefense_17135 {

    static int N, M, D;
    static int answer;
    static int enemyCount = 0;

    static int[][] map;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        answer = Integer.MIN_VALUE;
        boolean[] archerArr = new boolean[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    enemyCount++;
                }
            }
        }

        settingArcherPosition(archerArr, 0, 3);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    //궁수들을 combination으로 뽑아서 setting
    private static void settingArcherPosition(boolean[] archerPosition, int start, int count) {
        if (count == 0) {
            int[] archer = new int[3];
            int index = 0;

            for (int i = 0; i < M; i++) {
                if (archerPosition[i]) {
                    archer[index++] = i;//궁수들의 좌표를 입력
                }
            }
            attackEnemy(archer);
            return;
        }

        for (int i = start; i < M; i++) {
            archerPosition[i] = true;
            settingArcherPosition(archerPosition, i + 1, count - 1);
            archerPosition[i] = false;
        }
    }

    private static void attackEnemy(int[] archer) {
        //map을 복사해서 사용해야 된다
        int[][] newMap = copy();
        boolean[][] visited;
        int totalKillCount = 0;

        for (int turn = 0; turn < N; turn++) {
            visited = new boolean[N][M];

            for (int y : archer) {
                //바로 위 칸이 적이라면 바로 제거
                if (newMap[N - 1][y] == 1) {
                    visited[N - 1][y] = true;
                } else {
                    bfs(new Point(N - 1, y), visited, newMap);
                }
            }

            int enemyKilled = kill(visited, newMap);
            totalKillCount += enemyKilled;
            enemyShift(newMap);
        }

        answer = Math.max(answer, totalKillCount);
        return;
    }

    private static void bfs(Point start, boolean[][] visited, int[][] newMap) {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] mapVisited = new boolean[N][M];

        queue.add(start);
        mapVisited[start.x][start.y] = true;

        for (int count = 1; count < D; count++) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int peekX = queue.peek().x;
                int peekY = queue.peek().y;

                for (int j = 0; j < 3; j++) {
                    int nx = peekX + dx[j];
                    int ny = peekY + dy[j];

                    if (inRange(nx, ny) || mapVisited[nx][ny]) {
                        continue;
                    }

                    if (newMap[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        return;
                    }

                    queue.add(new Point(nx, ny));
                    mapVisited[nx][ny] = true;
                }
            }
        }
    }

    private static int kill(boolean[][] visited, int[][] newMap) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    count++;
                    newMap[i][j] = 0;
                }
            }
        }
        return count;
    }

    private static int[][] copy() {
        int[][] rtnMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, rtnMap[i], 0, M);
        }
        return rtnMap;
    }

    private static void enemyShift(int[][] newMap) {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (newMap[i][j] == 1) {
                    newMap[i][j] = 0;

                    if (i + 1 < N) {
                        newMap[i + 1][j] = 1;
                    }
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
