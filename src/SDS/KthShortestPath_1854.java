package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KthShortestPath_1854 {
    static int n,m, k;
    static ArrayList<Info>[] map;
    static PriorityQueue<Integer>[] distance;

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new ArrayList[n + 1];
        distance = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
            distance[i] = new PriorityQueue<>(Collections.reverseOrder());//Peek시 가장 큰 숫자가 나올 수 있게 반대로 정렬
        }

        int a,b,c;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map[a].add(new Info(b, c));
        }

        findShortestPath(1);

        for (int i = 1; i <= n; i++) {
            if (distance[i].size() == k) {
                bw.write(distance[i].peek() + "\n");
            } else {
                bw.write("-1" + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void findShortestPath(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        distance[start].add(0);
        pq.add(new Info(start, 0));

        while (!pq.isEmpty()) {
            Info now = pq.poll();

            if (now.distance > distance[now.node].peek()) {
                continue;
            }

            for (Info next : map[now.node]) {
                if (distance[next.node].size() < k) {
                    distance[next.node].add(now.distance + next.distance);
                    pq.add(new Info(next.node, now.distance + next.distance));
                } else if (distance[next.node].peek() > (now.distance + next.distance)) {
                    distance[next.node].poll();
                    distance[next.node].add(now.distance + next.distance);
                    pq.add(new Info(next.node, now.distance + next.distance));
                }
            }
        }
    }

    static class Info implements Comparable<Info> {
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
