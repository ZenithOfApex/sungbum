package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class virus_2606 {

    static int node[][];
    static boolean visited[];
    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();

        visited[start] =true;
        queue.offer(start);
        int cnt =0;
        while (!queue.isEmpty()) {
            int x= queue.poll();

            for(int i=1;i<node.length;i++){
                if (node[x][i] == 1 && visited[i] != true) {
                    queue.offer(i);
                    visited[i]=true;
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        node = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b= sc.nextInt();
            node[a][b]=1;
            node[b][a]=1;
        }
        bfs(1);
    }
}
