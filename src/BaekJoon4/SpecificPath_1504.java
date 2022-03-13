package BaekJoon4;

import java.io.*;
import java.util.*;

public class SpecificPath_1504 {
    static List<Node> list[];
    static int dist[];
    static boolean check[];
    static int n, v;
    static final int INF = 200_000_000;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        dist = new int[n + 1];
        check = new boolean[n + 1];

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int require1 = Integer.parseInt(st.nextToken());
        int require2 = Integer.parseInt(st.nextToken());

        answer = solution(require1, require2);

        bw.write(answer + " ");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(int required1, int required2) {
        //from에서 to까지의 weight 반환
        int result1 = 0;
        int result2 = 0;

        result1 += dijkstra(1, required1);
        result1 += dijkstra(required1, required2);
        result1 += dijkstra(required2, n);


        result2 += dijkstra(1, required2);
        result2 += dijkstra(required2, required1);
        result2 += dijkstra(required1, n);

        if(result1>=INF &&result2>=INF) return -1;
        else return Math.min(result1, result2);
    }

    private static int dijkstra(int start,int end){
        Arrays.fill(dist, INF);
        Arrays.fill(check, false);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int node = curNode.end;
            int weight = curNode.weight;

            if(check[node] == true) continue;
            check[node] = true;

            for (int i = 0; i < list[node].size(); i++) {
                int nextNode = list[node].get(i).end;
                int nextWeight = list[node].get(i).weight;

                if (check[nextNode] == false && dist[nextNode] > weight + nextWeight) {
                    dist[nextNode] = weight + nextWeight;
                    queue.add(new Node(nextNode, dist[nextNode]));
                }
            }
        }
        return dist[end];
    }

    private static class Node implements Comparable<Node>{
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
