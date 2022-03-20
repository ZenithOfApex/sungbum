package BaekJoon4;

import java.io.*;
import java.util.StringTokenizer;

public class FlowerRoad_14620 {
    static int n;
    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0,0);

        bw.write(answer + " ");
        bw.close();
        br.close();
    }

    private static void solution(int depth, int sum){
        if(depth==3){
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!checkCondition(i, j)) {
                    continue;
                }

                int temp = getSum(i, j);
                checkFlower(i, j, true);
                solution(depth + 1, sum + temp);
                checkFlower(i, j, false);
            }
        }
    }

    private static int getSum(int x, int y) {
        int sum = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            sum += map[nx][ny];
        }
        return sum;
    }

    private static void checkFlower(int x, int y, boolean type) {
        visited[x][y] = type;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[nx][ny] = type;
        }
    }

    private static boolean checkCondition(int x, int y) {
        if(visited[x][y]) return false;

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny) || visited[nx][ny]) return false;
        }
        //범위내 존재하는가
        return true;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
