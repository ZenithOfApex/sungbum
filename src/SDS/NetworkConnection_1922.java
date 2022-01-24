package SDS;

import java.io.*;
import java.util.*;

public class NetworkConnection_1922 {

    static int n, m;
    static Info[] list;
    static int[] group;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st;

        list = new Info[m + 1];
        group = new int[n + 1];
        answer =0;
        int n1, n2, cost;

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            list[i] = new Info(n1, n2, cost);
        }

        for (int i = 1; i <= n; i++) {
            group[i] = i;
        }

        Arrays.sort(list, 1, m + 1);

        int connectCount = 0;
        for (int i = 1; i <= m; i++) {
            if (find(list[i].node1) != find(list[i].node2)) {
                union(list[i].node1, list[i].node2);
                answer += list[i].weight;
                connectCount++;
            }

            if (connectCount == n - 1) {
                break;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    public static void union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);

        group[aGroup] = bGroup;
    }

    public static int find(int i) {
        if (group[i] == i) {
            return i;
        } else {
            return group[i] = find(group[i]);
        }
    }

    static class Info implements Comparable<Info> {
        int node1;
        int node2;
        int weight;

        public Info(int node1, int node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(weight, o.weight);
        }
    }

}
