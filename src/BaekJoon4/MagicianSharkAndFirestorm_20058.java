package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class MagicianSharkAndFirestorm_20058 {
    static int N, Q, LEN;
    static int[][] map;
    static boolean[][] visited;
    static int sum = 0;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        LEN =  (int)Math.pow(2, N);

        map = new int[LEN][LEN];

        for (int i = 0; i < LEN; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < LEN; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int level = Integer.parseInt(st.nextToken());
            divideArea(level);
            reduceIce();
        }

        bw.write(sum + "\n");
        bw.write(searchBiggestArea() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void divideArea(int level) {
        int len = (int) Math.pow(2, level);
        for (int y = 0; y < LEN; y += len) {
            for (int x = 0; x < LEN; x += len) {
                rotate(y, x, level);
            }
        }
    }

    private static void rotate(int sY, int sX, int level) {
        int y = sY;
        int x = sX;
        List<Integer> temp;
        int len = (int) Math.pow(2, level);

        while (len >= 2) {
            temp = new ArrayList<>();
            int j = x;
            int i = y + len-1;
            for (int _i = y; _i < y + len - 1; _i++) {
                temp.add(map[_i][x]);
            }
            for (int _i = y; _i < y + len - 1; _i++) {
                map[_i][x] = map[y + len - 1][j++];
            }
            for (int _j = x; _j < x + len - 1; _j++) {
                map[y + len - 1][_j] = map[i--][x + len - 1];
            }
            for (int _i = y + len - 1; _i >= y; _i--) {
                map[_i][x + len - 1] = map[y][j--];
            }
            int idx= 0;
            for (int _j = x + len - 1; _j > x; _j--) {
                map[y][-j] = temp.get(idx++);
            }
            y++;
            x++;
            len-=2;
        }
    }

    private static int searchBiggestArea() {
        int max = 0;
        visited = new boolean[LEN][LEN];
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (map[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                int size = bfs(i, j);
                max = Math.max(size, max);
            }
        }
        return max;
    }

    private static int bfs(int i, int j) {
        int size = 0;
        visited[i][j] = true;
        LinkedList<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));

        while (!q.isEmpty()) {
            Pair now = q.poll();

            int y = now.y;
            int x = now.x;
            size++;

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (!inRange(ny, nx)) {
                    continue;
                }
                if (visited[ny][nx] || map[ny][nx] == 0) {
                    continue;
                }
                visited[ny][nx] = true;
                q.add(new Pair(ny, nx));
            }
        }
        return size;
    }

    private static void reduceIce() {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                if(isReduce(i,j)){
                    sum--;
                    list.add(new Pair(i, j));
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            map[list.get(i).y][list.get(i).x]--;
        }
    }

    private static boolean isReduce(int i, int j) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int ny = i + dy[d];
            int nx = j + dx[d];
            if (!inRange(ny, nx)) {
                continue;
            }
            if (map[ny][nx] > 0) {
                cnt++;
            }
        }
        if (cnt >= 3) {
            return false;
        } else return true;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < LEN && y >= 0 && y < LEN;
    }

    private static class Pair{
        int x, y;

        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
