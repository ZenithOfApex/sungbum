package BaekJoon3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CastleDefense3_17135 {

    static int N, M, D;
    static int[][] map;
    static int[][] copyMap;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        copyMap = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }

        ArrayList<Integer> archer = new ArrayList<>();
        answer = 0;
        combination(1, M, 3, archer);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int start, int n, int r, ArrayList<Integer> archer) {
        if (r == 0) {
            init();
            attack(archer);
            return;
        }

        for (int i = start; i <= n; i++) {
            archer.add(i);
            combination(i + 1, n, r - 1, archer);
            archer.remove(archer.size() - 1);
        }
    }

    private static void attack(ArrayList<Integer> archer) {
        int res = 0;

        for (int n = 1; n <= N; n++) {
            boolean[][] visited = new boolean[N + 1][M + 1];
            for (int k = 0; k < archer.size(); k++) {
                int temp = archer.get(k);
                int minD = Integer.MAX_VALUE;
                int minR = Integer.MAX_VALUE;
                int minC = Integer.MAX_VALUE;

                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= M; j++) {
                        if (map[i][j] == 1) {
                            if (minD >= distance(i, N + 1, j, temp)) {
                                if (minD > distance(i, N + 1, j, temp)) {
                                    minD = distance(i, N + 1, j, temp);
                                    minR = i;
                                    minC = j;
                                } else {
                                    if (minC > j) {
                                        minR = i;
                                        minC = j;
                                    }
                                }
                            }
                        }
                    }
                }
                if (minD <= D) {
                    visited[minR][minC] = true;
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (visited[i][j]) {
                        map[i][j] = 0;
                        res++;
                    }
                }
            }

            for (int i = 1; i <= M; i++) {
                map[N][i] = 0;
            }

            for (int i = N; i >= 1; i--) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = map[i - 1][j];
                }
            }
        }

        answer = Math.max(answer, res);
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    private static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
