package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Mineral_2933 {
    static int R,C;
    static int N;
    static char[][] map;
    static int cluster[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            deleteMineral(R - height, i % 2);
            setCluster();
        }

        printMap();
        br.close();
    }

    private static void deleteMineral(int height, int side) {
        if (side == 0) {
            for (int i = 0; i < C; i++) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    return;
                }
            }
        } else {
            for (int i = C-1; i >= 0; i--) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    return;
                }
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void setCluster() {
        cluster = new int[R][C];
        int cnt = 1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && cluster[i][j] == 0) {
                    if(findCluster(i,j,cnt)) return;
                }
                cnt++;
            }
        }
    }

    private static boolean findCluster(int row, int col, int cnt) {
        int low_height = Integer.MIN_VALUE;

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> list = new ArrayList<>();

        queue.add(new Node(row, col));
        cluster[row][col] = cnt;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            low_height = Math.max(low_height, node.x);

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (!inRange(nx, ny)) {
                    continue;
                }

                if (cluster[nx][ny] == 0 && map[nx][ny] == 'x') {
                    cluster[nx][ny] = cnt;
                    queue.add(new Node(nx, ny));
                }
            }
            list.add(node);
        }

        if (low_height != R - 1) {
            downMap(list);
            return true;
        }
        return false;
    }

    private static void downMap(ArrayList<Node> list) {
        int down = 1;

        for (Node node : list) {
            map[node.x][node.y] = '.';
        }

        outerLoop:
        while (true) {
            for (Node node : list) {
                if (node.x + down == R || map[node.x + down][node.y] == 'x') {
                    down--;
                    break outerLoop;
                }
            }

            down++;
        }

        for (Node node : list) {
            map[node.x + down][node.y]='x';
        }
    }

    private static boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < R && ny >= 0 && ny < C;
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
