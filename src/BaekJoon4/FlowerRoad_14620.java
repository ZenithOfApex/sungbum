package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class FlowerRoad_14620 {
    static int n, answer;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] check;
    static ArrayList<Pos> flowerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
//        visited = new boolean[n][n];


        flowerList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i >= 1 && j < n-1) {
                    flowerList.add(new Pos(i, j, map[i][j]));
                }
            }
        }
        Collections.sort(flowerList);
        check = new boolean[flowerList.size()];

        solution(0,0);

        bw.write(answer + " ");
        bw.close();
        br.close();
    }

    private static void solution(int index, int count) {
        if (count == 3) {
            answer = Math.min(answer,//세개 더한 값)
        }
        //n==1부터 n-2까지만 검사하면 된다 - 테두리는 꽃이 필 수 없기 때문

        check[index] = true;
        solution(index + 1, count + 1);
        check[index] = false;

    }

    private static boolean checkCondition(int x, int y) {
        boolean north = inRange(x - 1, y);
        boolean west = inRange(x, y - 1);
        boolean south = inRange(x + 1, y);
        boolean east = inRange(x, y + 1);

        if (north && west && south && east) {
            return true;
        } else return false;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    private static class Pos implements Comparable<Pos>{
        int x;
        int y;
        int price;

        public Pos(int x, int y, int price) {
            this.x = x;
            this.y = y;
            this.price = price;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.price, o.price);
        }
    }
}
