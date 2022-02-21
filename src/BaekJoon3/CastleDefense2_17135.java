package BaekJoon3;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CastleDefense2_17135 {

    static int N, M, D;
    static int[][] map;
    static int enemy;
    static int answer;

    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        enemy = 0;
        answer = Integer.MIN_VALUE;
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    enemy += 1;
                }
            }
        }

        setArcher(new boolean[M], 0, 3);
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void setArcher(boolean[] selected, int start, int pick) {
        if (pick == 0) {
            int[] archer = new int[3];
            int idx = 0;

            for (int i = 0; i < M; i++) {
                if (selected[i]) {
                    archer[idx++] = i;
                }
            }

            play(archer);
            return;
        }

        for (int i = start; i < M; i++) {
            selected[i] = true;
            setArcher(selected, i + 1, pick - 1);
            selected[i] = false;
        }
    }

    private static void play(int[] archer) {
        int[][] playMap = copyMap();
        boolean[][] died;
        int totalKillCount = 0;

        for (int turn = 0; turn < N; turn++) {
            died = new boolean[N][M];

            for (int y : archer) {
                if (playMap[N - 1][y] == 1) {
                    died[N - 1][y] = true;
                } else {
                    searchBFS(new Point(N - 1, y), died, playMap);
                }
            }

            int killCnt = kill(died, playMap);

            totalKillCount += killCnt;

            move(playMap);
        }

        answer = Math.max(answer, totalKillCount);
        return;
    }

    private static void move(int[][] playMap) {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (playMap[i][j] == 1) {
                    playMap[i][j] = 0;

                    if (i + 1 < N) {
                        playMap[i + 1][j] = 1;
                    }
                }
            }
        }
    }

    private static int kill(boolean[][] died, int[][] playMap) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (died[i][j]) {
                    cnt++;
                    playMap[i][j] = 0;
                }
            }
        }
        return cnt;
    }

    private static void searchBFS(Point start, boolean[][] died, int[][] playMap) {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[N][M];

        queue.add(start);
        visited[start.x][start.y] = true;

        for (int cnt = 1; cnt < D; cnt++) {
            int size = queue.size();

            for (int j = 0; j < size; j++) {
                int px = queue.peek().x;
                int py = queue.peek().y;

                for (int i = 0; i < 3; i++) {
                    int nx = px + dx[i];
                    int ny = py + dy[i];

                    if (!isIn(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (playMap[nx][ny] == 1) {
                        died[nx][ny] = true;
                        return;
                    }

                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }


    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < M;
    }

    private static int[][] copyMap() {
        int[][] map2 = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, map2[i], 0, M);
        }
        return map2;
    }
}
