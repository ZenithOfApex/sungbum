package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class NetworkConnection2_1922 {

    static int N, M;
    static int answer = 0;
    static ArrayList<Node> nodeList = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for (int i = 1; i <=N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            nodeList.add(new Node(start, end, weight));
        }
        Collections.sort(nodeList);

        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if (!isSameParent(node.start, node.end)) {
                answer += node.weight;
                union(node.start, node.end);
            }
        }

        bw.write(answer + " ");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    private static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return true;
        } else return false;
    }

    private static class Node implements Comparable<Node>{
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if (this.weight > o.weight) {
                return 1;
            } else if (this.weight == o.weight) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
