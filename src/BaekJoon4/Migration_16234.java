package BaekJoon4;

import java.util.*;
import java.io.*;

public class Migration_16234 {
    static int N, R, L;
    static int answer = 0;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Pos> connected;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = solution();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() {
        int result = 0;
        while (true) {
            boolean moving = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        openBoundaryAndChange(i, j);
                        moving = true;
                    }
                }
            }
            if(!moving) return result;
            result++;
        }
    }

    //map을 하나하나 읽어서 주변에 붙어있는 나라 중 diff 범위 내라면 국경 오픈
    //bfs를 통해 하나의 기준으로 연결되어 있는 나라를 구해서 그 평균으로 값 변경
    private static void openBoundaryAndChange(int x, int y) {
        Queue<Pos> q = new LinkedList<>();
        connected = new ArrayList<>();

        q.offer(new Pos(x, y));
        connected.add(new Pos(x, y));
        visited[x][y] = true;

        int sum = map[x][y];
        while (!q.isEmpty()) {
            Pos curPos = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny]) {
                    int diff = Math.abs(map[curPos.x][curPos.y] - map[nx][ny]);
                    if (diff >= L && diff <= R) {
                        q.offer(new Pos(nx, ny));
                        connected.add(new Pos(nx, ny));
                        sum += map[nx][ny];
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        if (connected.size() > 1) {
            int avg = sum / connected.size();
            for (Pos p : connected) {
                map[p.x][p.y] = avg;
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
