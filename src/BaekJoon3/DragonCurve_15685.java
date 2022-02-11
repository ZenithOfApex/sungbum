package BaekJoon3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DragonCurve_15685 {

    static int N, answer;
    static ArrayList<Dragon> dragon;
    static int[][] map = new int[101][101];//0이면 false 1이면 true
    static int[] directions = {0, 1, 2, 3};//0:동 1:북 2:서 3:남
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

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

        //이동 방향 측으로 하나 더 체크해야 길이가 1인 선분 그려짐
        int nx = x + dx[d];
        int ny = y + dy[d];

        map[nx][ny] = 1;//여기까지 하면 선분 그리기 완료(0세대)


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
