package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {
    //무한을 의미하는 값이므로 Integer.MAX_VALUE
    static final int INF = Integer.MAX_VALUE;
    //노드의 개수(N) 간선의 개수(M) 시작 노드 번호(Start)
    static int N, M , start;
    //각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    //최단 거리 테이블 만들기
    static int[] d = new int[100001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
        }

        //최단 거리 테이블을 모두 최대값으로 초기화
        Arrays.fill(d, INF);

        //다익스트라 알고리즘 수행
        dijkstra(1);

        st = new StringTokenizer(br.readLine(), " ");
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            if (d[i] == INF) {
                System.out.println("INFINITY");
            } else {
                System.out.println(d[i]);
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }


    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        //시작 노드로 가기 위한 최단 경로는 0으로 설정하여 큐에 삽입
        pq.offer(new Node(start, 0));
        d[start] = 0;
        //큐가 비어있지 않을 때까지 반복
        while (!pq.isEmpty()) {
            //가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node node = pq.poll();
            //현재 노드까지의 비용
            int dist = node.getDistance();
            //현재 노드
            int now = node.getIndex();
            //현재 노드가 이미 처리된적이 있는 노드라면 무시
            if(d[now] < dist) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();
                //현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(now).get(i).getDistance()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }


    private static class Node implements Comparable<Node> {
        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return this.index;
        }

        public int getDistance() {
            return this.distance;
        }

        //거리(비용)가 짧은 것이 높은 우선 순위를 가지도록 설정
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
