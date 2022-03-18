package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class FlowerRoad_14620 {
    static int n;
    static int answer = Integer.MAX_VALUE;
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
                if (i >= 1 && j < n-1 && checkCondition(i,j)) {
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
            visited = new boolean[n][n];

            int tempCnt = 0;
            for (int i = 0; i < check.length; i++) {
                if (check[i]) {
                    if (checkFlower(flowerList.get(i).x, flowerList.get(i).y)) {
                        tempCnt += flowerList.get(i).price;
                    }
                    else if (!checkFlower(flowerList.get(i).x, flowerList.get(i).y)) {
//                        System.out.println("tempCnt1 = " + tempCnt);
                        return;
                    }
                }
            }
            System.out.println("tempCnt2 = " + tempCnt);
            answer = Math.min(answer,tempCnt);//세개 더한 값
            return;
        }
        //n==1부터 n-2까지만 검사하면 된다 - 테두리는 꽃이 필 수 없기 때문

        for (int i = index; i < flowerList.size(); i++) {
            check[index] = true;
            solution(index + 1, count + 1);
            check[index] = false;

        }

    }

    private static boolean checkFlower(int x, int y) {
        //하나라도 이미 꽃이 피어있는 경우에는 불가한 경우이기에 false 반환
        if (visited[x][y] || visited[x - 1][y] || visited[x + 1][y] | visited[x][y - 1] | visited[x][y + 1]) {
            return false;
        }else{//꽃이 필 수있는 경우에는 꽃 피우고 true 반환
            visited[x][y] = true;
            visited[x-1][y] = true;
            visited[x+1][y] = true;
            visited[x][y-1] = true;
            visited[x][y+1] = true;
            return true;
        }
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
