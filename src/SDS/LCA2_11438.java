package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA2_11438 {
    static int n, m;
    static int logN;
    static int[] depth;
    static int[][] parents;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new ArrayList[n + 1];
        depth = new int[n + 1];

        getLogN();

        parents = new int[logN + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 1; i <= n-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }
        //dfs로 구현할 경우 재귀 방식으로 구현하면, 재귀 호출을 최대 10만번(n)함으로써 stack overflow 발생 가능
        //재귀 방식의 dfs가 아닌 stack 자료구로를 이용한 dfs로 설계해야함
        bfs(1);
        makeSparseTable();

        m = Integer.parseInt(br.readLine());
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(getLCA(a, b) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void getLogN() {
        logN = 0;
        for (int k = 1; k < n; k *= 2) {
            logN++;
        }
    }

    private static void makeSparseTable(){
        for (int i = 1; i <= logN; i++) {
            for (int j = 1; j <= n; j++) {
                parents[i][j] = parents[i - 1][parents[i - 1][j]];
            }
        }
    }

    private static void bfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        depth[start] = 1;
        dq.add(start);

        while (!dq.isEmpty()) {
            int now = dq.poll();
            for (Integer next : map[now]) {
                if (depth[next] == 0) {
                    depth[next] = depth[now] + 1;
                    parents[0][next] = now;
                    dq.add(next);
                }
            }
        }
    }

    private static int getLCA(int a, int b) {
        if (depth[a] < depth[b]) {//a가 더 깊이 있을 때
            return getLCA(b, a);
        }

        for (int i = 0; i <= logN; i++) {//높이 맞추기
            if (((depth[a] - depth[b]) & (1 << i)) >= 1) {
                a = parents[i][a];
            }
        }

        if (a == b) {//높이 맞췄는지 검사
            return a;
        }

        //공통조상이 아닐때까지 부모를 따라 올라간다
        //최종적으로는 LCA 바로 밑칸까지만 올라간다.
        for (int i = logN; i >= 0; i--) {
            if (parents[i][a] != parents[i][b]) {
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        return parents[0][a];
    }

}
