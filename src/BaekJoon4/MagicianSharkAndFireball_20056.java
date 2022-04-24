package BaekJoon4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MagicianSharkAndFireball_20056 {
    static int N, M, K;
    static int[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static Queue<Fireball> fireballQueue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        //r,c,m,s.d
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballQueue.add(new Fireball(r, c, m, s, d));
        }

        while (K-- > 0) {
            moveAndCombineFireball();
            divideFireball();
        }
        getTotalMeasure();

    }

    private static void moveAndCombineFireball(Fireball fireball) {
        while (!fireballQueue.isEmpty()) {

        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }

    private static class Fireball{
        int row;
        int col;
        int measure;
        int speed;
        int direction;

        public Fireball(int row, int col, int measure, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.measure = measure;
            this.speed = speed;
            this.direction = direction;
        }

        public Fireball(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
