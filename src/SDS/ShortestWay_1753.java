package SDS;

import java.io.*;
import java.util.*;

public class ShortestWay_1753 {

    static int V, E;
    static ArrayList<Info>[] Map;
    static int[] distance;
    static int start;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Map = new ArrayList[V + 1];
        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            distance[i] = INF;
            Map[i] = new ArrayList<>();
        }

        start = Integer.parseInt(br.readLine());

        int u,v,w;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            Map[u].add(new Info(v, w));
        }

        findShortestPath(start);

        for (int i = 1; i <= V; i++) {
            if (distance[i] != INF) {
                bw.write(distance[i] + "\n");
            }else{
                bw.write("INF" + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findShortestPath(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.add(new Info(start, 0));

        while (!pq.isEmpty()) {
            Info curPos = pq.poll();

            if (curPos.distance > distance[curPos.node]) {
                continue;
            }

            for (Info next : Map[curPos.node]) {
                if (distance[next.node] > distance[curPos.node] + next.distance) {
                    distance[next.node] = distance[curPos.node] + next.distance;
                    pq.add(new Info(next.node, distance[next.node]));
                }
            }
        }
    }

    public static class Info implements Comparable<Info> {
        int node;
        int distance;

        public Info(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(distance, o.distance);
        }
    }
}
