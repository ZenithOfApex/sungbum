package BaekJoon3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChickenDelivery_15686_Answer {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> houses;
    static ArrayList<Point> shops;
    static int answer;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        houses = new ArrayList<>();
        shops = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    houses.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    shops.add(new Point(i, j));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[shops.size()];

        checkDistance(0, 0);
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkDistance(int index, int count) {
        if (count == M) {
            int res = 0;
            for (int i = 0; i < houses.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < shops.size(); j++) {
                    if (visited[j]) {
                        int distance = Math.abs(houses.get(i).x - shops.get(j).x) + Math.abs(houses.get(i).y - shops.get(j).y);
                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            answer = Math.min(answer, res);
            return;
        }

        for (int i = index; i < shops.size(); i++) {
            visited[i] = true;
            checkDistance(index + 1, count + 1);
            visited[i] = false;
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
