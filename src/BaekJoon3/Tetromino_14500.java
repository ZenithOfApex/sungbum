package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino_14500 {

    static int[][] map;
    static boolean[][] visited;
    static int max,n,m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    //ㅗ 모양
    static int[] type1_dx = {0, 1, 1, 1};
    static int[] type1_dy = {0, 0, -1, 1};

    //ㅓ 모양
    static int[] type2_dx = {0, 0, -1, 1};
    static int[] type2_dy = {0, 1, 1, 1};

    //ㅏ 모양
    static int[] type3_dx = {0, 1, 2, 1};
    static int[] type3_dy = {0, 0, 0, 1};

    //ㅜ 모양
    static int[] type4_dx = {0, 0, 1, 0};
    static int[] type4_dy = {0, 1, 1, 2};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        int maxValue = 0;
        max =0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int inputNum = Integer.parseInt(st.nextToken());
                map[i][j] = inputNum;
                maxValue = Math.max(inputNum, maxValue);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j,0,0);
                type1(i, j);
                type2(i, j);
                type3(i, j);
                type4(i, j);
            }
        }

        System.out.println(max);
        br.close();
    }

    //기본적으로 모양들 ㄴ ㄱ ㅣ ㅡ ㄹ 등은 dfs로 해결 가능
    private static int dfs(int i, int j, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(sum, max);
            return 0;
        }

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (inRange(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
        return sum;
    }

    //ㅗ ㅜ ㅓ ㅏ모양은 일일이 해봐야겠다
    private static void type1(int i, int j) {
        int total =0;
        for (int k = 0; k < 4; k++) {
            int nx = i + type1_dx[k];
            int ny = j + type1_dy[k];
            if (!inRange(nx, ny)) {
                break;
            }
            total += map[nx][ny];
        }
        max = Math.max(max, total);
    }
    private static void type2(int i, int j) {
        int total =0;
        for (int k = 0; k < 4; k++) {
            int nx = i + type2_dx[k];
            int ny = j + type2_dy[k];
            if (!inRange(nx, ny)) {
                break;
            }
            total += map[nx][ny];
        }
        max = Math.max(max, total);
    }
    private static void type3(int i, int j) {
        int total =0;
        for (int k = 0; k < 4; k++) {
            int nx = i + type3_dx[k];
            int ny = j + type3_dy[k];
            if (!inRange(nx, ny)) {
                break;
            }
            total += map[nx][ny];
        }
        max = Math.max(max, total);
    }
    private static void type4(int i, int j) {
        int total =0;
        for (int k = 0; k < 4; k++) {
            int nx = i + type4_dx[k];
            int ny = j + type4_dy[k];
            if (!inRange(nx, ny)) {
                break;
            }
            total += map[nx][ny];
        }
        max = Math.max(max, total);
    }



    private static boolean inRange(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

}
