package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class TimeMachine_11657 {

    static int N, M;
    static Info[] list;
    static long[] cost;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException, NumberFormatException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new long[N + 1];
        list = new Info[M + 1];
        for (int i = 1; i <= N; i++) {
            cost[i] = INF;
        }

        int A, B, C;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            list[i] = new Info(A, B, C);
        }

        findShortestPath(1);
        boolean isNegativeCycle = findNegativeCycle();

        if (isNegativeCycle == true) {
            bw.write("-1" + "\n");
            bw.flush();
            bw.close();
            return;
        }

        for (int i = 2; i <= N; i++) {
            if (cost[i] == INF) {
                bw.write("-1" + "\n");
            } else {
                bw.write(cost[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean findNegativeCycle() {
        for (int j = 1; j <= M; j++) {
            Info nowEdge = list[j];
            if (cost[nowEdge.from] != INF) {
                if (cost[nowEdge.to] > cost[nowEdge.from] + nowEdge.time) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void findShortestPath(int start) {
        cost[start] =0;

        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M; j++) {
                Info nowEdge = list[j];
                if (cost[nowEdge.from] != INF) {
                    if (cost[nowEdge.to] > cost[nowEdge.from] + nowEdge.time) {
                        cost[nowEdge.to] = cost[nowEdge.from] + nowEdge.time;
                    }
                }
            }
        }
    }

    static class Info{
        int from;
        int to;
        int time;

        public Info(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }
}
