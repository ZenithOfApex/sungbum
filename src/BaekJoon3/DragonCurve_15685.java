package BaekJoon3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DragonCurve_15685 {

    static int N, answer;
    static ArrayList<Dragon> dragon;
    static int[][] map = new int[101][101];//0이면 false 1이면 true

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dragon = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dragon.add(new Dragon(x, y, d, g));
        }

        //입력받은 드래곤 커브에 대하여 map에 visited 처리
        for (Dragon drag : dragon) {
            int x = drag.x;
            int y = drag.y;
            int d = drag.direction;
            int g = drag.generation;

            solution(x, y, d, g);
        }
        //map에서 전체 사각형 갯수 세기
        countingSquares();

        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution(int x, int y, int d, int g) {
        map[x][y] = 1;
        if (d == 0) {
            map[x][y+1] = 1;
            if(g==0) return;
            rotate(0, g);
        } else if (d == 1) {
            map[x-1][y] = 1;
            if(g==0) return;
            rotate(0, g);
        } else if (d == 2) {
            map[x][y-1] = 1;
            if(g==0) return;
            rotate(0, g);
        } else if (d == 3) {
            map[x+1][y] = 1;
            if(g==0) return;
            rotate(0, g);
        }

    }

    private static void rotate(int generation,int targetGen) {
        if (generation == targetGen) {
            return;
        }
        int[][] rotated = new int[101][101];
        //회전
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                rotated[i][j] = map[99 - j][i];
            }
        }
        //회전 후 붙이기
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (rotated[i][j]==1) {
                    map[i][j] = rotated[i][j];
//                    if (!map[i][j]) {
//                    }
                }
            }
        }

        rotate(generation + 1, targetGen);
    }

    private static void countingSquares() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j]==1 && map[i + 1][j]==1 && map[i][j + 1]==1 && map[i + 1][j + 1]==1) {
                    answer++;
                }
            }
        }
    }

    static class Dragon {
        int x;
        int y;
        int direction;
        int generation;

        public Dragon(int x, int y, int direction, int generation) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.generation = generation;
        }
    }

}
