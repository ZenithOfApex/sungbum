package BaekJoon3;

import java.io.*;
import java.util.*;

public class ChickenDelivery_15686 {

    static int N, M;
    static int[][] map;
    static int minimumChickenDistance;
    static ArrayList<Pos> shopCombination = new ArrayList<>();
    static ArrayList<Pos> houses = new ArrayList<>();
    static ArrayList<Pos> shops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
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
        checkDistance(0,0);

        bw.write(minimumChickenDistance + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int getDistance(Pos p1, Pos p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static void checkDistance(int index, int count) {
        if (count == M) {
            //거리 계산해서 add
            int distance = 0;
            for (Pos house : houses) {
                int temp = Integer.MAX_VALUE;
                for (Pos shop : shopCombination) {
                    temp = Math.min(temp, getDistance(house, shop));
                }
                distance+=temp;
            }
            minimumChickenDistance = Math.min(minimumChickenDistance, distance);
            return;
        }
        //여기서 시간초과 나는듯....arrayList -> array로 전환해서 도전해보자
        for (int i = index; i < shops.size(); i++) {
            shops.get(i).survived = true;
            shopCombination.add(shops.get(i));
            checkDistance(index + 1, count + 1);
            shops.get(i).survived = false;
            shopCombination.remove(shops.get(i));
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
