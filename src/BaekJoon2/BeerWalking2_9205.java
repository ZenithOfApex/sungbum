package BaekJoon2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BeerWalking2_9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        ArrayList<Point> a;
        boolean[][] isSearch;

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            a = new ArrayList<>();

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                a.add(new Point(x, y));
            }

            isSearch = new boolean[N + 2][N + 2];

            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    if (calcDist(a.get(i), a.get(j)) <= 1000) {
                        isSearch[i][j] = isSearch[j][i] = true;
                    }
                }
            }

            floyd(isSearch, N);

            sb.append((isSearch[0][N + 1] ? "happy" : "sad") + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int calcDist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static void floyd(boolean[][] isSearch, int N) {
        for (int k = 0; k < N + 2; k++) {
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (isSearch[i][k] && isSearch[k][j]) {
                        isSearch[i][j] = true;
                    }
                }
            }
        }
    }
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
