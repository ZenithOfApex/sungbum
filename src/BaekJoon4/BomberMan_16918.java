package BaekJoon4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BomberMan_16918 {

    static int R,C, N;
    static int[][] map;

    static Queue<Bomb> q = new LinkedList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        //0이면 폭탄 없음. 1이면 폭탄있음
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                if (str.charAt(j) == '.') {
                    map[i][j] = 0;
                }else map[i][j] = 1;
            }
        }
        //1초동안은 아무런 일이 일어나지 않는다.

        //그 다음 과정 - 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다.
        //그 다음 과정 - 시간이 다 된 폭탄의 해당 칸 및 상하좌우 폭탄이 제거된다.
        sequence();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) {
                    System.out.print(".");
                } else System.out.print("O");
            }
            System.out.println();
        }
        br.close();
    }

    private static void sequence() {
        //1,2 단계는 default이기에 할 필요가 없음
        //따라서 2초때부터 N초때까지
        for (int i = 2; i <= N; i++) {
            if (i % 2 == 0) {
                //폭탄 설치
                installBomb(i);
            } else {
                //설치되어 있는 폭탄들을 큐에 넣고 폭발
                findBomb(i);
                bombExplosion();
            }
        }
    }

    private static void installBomb(int num) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = num;
                }
            }
        }
    }

    private static void findBomb(int num) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] <= num - 2) {
                    q.add(new Bomb(i, j));
                }
            }
        }
    }

    private static void bombExplosion(){
        while (!q.isEmpty()) {
            Bomb bomb = q.poll();
            int x = bomb.x;
            int y = bomb.y;
            map[x][y] = 0;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) {
                    continue;
                } else {
                    //폭탄 폭발 처리
                    map[nx][ny] = 0;
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private static class Bomb{
        int x;
        int y;

        public Bomb(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
