package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Observation2_15683 {

    static int N, M;
    static int[][] map;
    static int[][] copyMap;
    static int[] output;
    static ArrayList<CCTV> cctvList;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int inputValue = Integer.parseInt(st.nextToken());
                if (inputValue > 0 && inputValue < 6) {
                    cctvList.add(new CCTV(inputValue, i, j));
                }
                map[i][j] = inputValue;
            }
        }

        output = new int[cctvList.size()];
        permutation(0, cctvList.size());

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void permutation(int depth, int r) {
        if (depth == r) {
            copyMap = new int[N][M];
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
            }

            for (int i = 0; i < cctvList.size(); i++) {
                direction(cctvList.get(i), output[i]);
            }

            getBlindSpot();

            return;
        }

        for (int i = 0; i < 4; i++) {
            output[depth] = i;
            permutation(depth + 1, r);
        }
    }

    private static void direction(CCTV cctv, int d) {
        int cctvNum = cctv.num;
        if (cctvNum == 1) {
            if(d==0) watch(cctv, 0);
            else if(d==1) watch(cctv, 1);
            else if(d==2) watch(cctv, 2);
            else if(d==3) watch(cctv, 3);
        } else if (cctvNum == 2) {
            if (d == 0 || d == 2) {
                watch(cctv, 0);
                watch(cctv, 2);
            } else {
                watch(cctv, 1);
                watch(cctv, 3);
            }
        } else if (cctvNum == 3) {
            if (d == 0) {
                watch(cctv, 0);
                watch(cctv, 1);
            } else if (d == 1) {
                watch(cctv, 1);
                watch(cctv, 2);
            } else if (d == 2) {
                watch(cctv, 2);
                watch(cctv, 3);
            } else if (d == 3) {
                watch(cctv, 0);
                watch(cctv, 3);
            }
        } else if (cctvNum == 4) {
            if (d == 0) {
                watch(cctv, 0);
                watch(cctv, 1);
                watch(cctv, 3);
            } else if (d == 1) {
                watch(cctv, 0);
                watch(cctv, 1);
                watch(cctv, 2);
            } else if (d == 2) {
                watch(cctv, 1);
                watch(cctv, 2);
                watch(cctv, 3);
            } else if (d == 3) {
                watch(cctv, 0);
                watch(cctv, 2);
                watch(cctv, 3);
            }
        } else if (cctvNum == 5) {
            watch(cctv, 0);
            watch(cctv, 1);
            watch(cctv, 2);
            watch(cctv, 3);
        }

    }

    private static void watch(CCTV cctv, int d) {
        Queue<CCTV> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(cctv);
        visited[cctv.x][cctv.y] = true;

        while (!queue.isEmpty()) {
            CCTV polledCCTV = queue.poll();
            int x = polledCCTV.x;
            int y = polledCCTV.y;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) {
                break;
            }
            if (copyMap[nx][ny] == 0) {
                copyMap[nx][ny] = -1;
                queue.add(new CCTV(cctv.num, nx, ny));
            } else {
                queue.add(new CCTV(cctv.num, nx, ny));
            }
        }
    }

    private static void getBlindSpot() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        answer = Math.min(answer, cnt);
    }

    private static class CCTV {
        int num;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}
