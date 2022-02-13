package BaekJoon3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChickenDelivery2_15686 {
    static int N, M;
    static int minimumChickenDistance;
    static int[][] map;
    static boolean[] visited;

    static ArrayList<Pos> houses = new ArrayList<>();
    static ArrayList<Pos> shops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if (input == 1) {
                    houses.add(new Pos(i, j));
                } else if (input == 2) {
                    shops.add(new Pos(i, j));
                }
            }
        }

        minimumChickenDistance = Integer.MAX_VALUE;
        visited = new boolean[shops.size()];

        checkDistance(0, 0);

        bw.write(minimumChickenDistance + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

//    private static int getDistance(Pos p1, Pos p2) {
//        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
//    }

    private static void checkDistance(int index, int count) {
        if (count == M) {
            int distance = 0;
            for (Pos house : houses) {
                int temp = Integer.MAX_VALUE;
                for (int i = 0; i < shops.size(); i++) {
                    if (visited[i]) {
                        int res = Math.abs(house.x - shops.get(i).x) + Math.abs(house.y - shops.get(i).y);
                        temp = Math.min(res, temp);
                    }
                }
//                for (Pos shop : shops) {
//                    if (shop.survived) {
//                        temp = Math.min(temp, getDistance(shop, house));
//                    }
//                }
                distance += temp;
            }
            minimumChickenDistance = Math.min(minimumChickenDistance, distance);
            return;
        }

        for (int i = index; i < shops.size(); i++) {
//            shops.get(i).survived = true;
//            checkDistance(index + 1, count + 1);
//            shops.get(i).survived = false;
            visited[i] = true;
            checkDistance(index + 1, count + 1);
            visited[i] = false;
        }
    }

    static class Pos{
        int x;
        int y;
        boolean survived = false;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
