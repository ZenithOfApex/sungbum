package BaekJoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class treasure_2589_answer {
    static int R, C;
    static int moveY[] = {-1, 0, 1, 0};
    static int moveX[] = {0, 1, 0, -1};
    static char arr[][];
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visited = new boolean[R][C];
        for(int i=0;i<R;i++){
            String str= br.readLine();
            for(int j=0;j<C;j++){
                arr[i][j] = str.charAt(j);
            }
        }
        int result =0;

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if (arr[i][j] == 'L') {
                    visited = new boolean[R][C];
                    int val = bfs(i, j);
                    result = Math.max(result, val);
                }
            }
        }

        bw.write(result);
    }

    private static int bfs(int i, int j) {
        Queue<Node> queue = new LinkedList<>();
        int val=0;
        visited[i][j] = true;
        queue.add(new Node(j, i, 0));
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (int d = 0; d < 4; d++) {
                int newX = n.x + moveX[d];
                int newY = n.y + moveY[d];
                if (0 <= newX && newX < C && 0 <= newY && newY < R && !visited[newX][newY] && arr[newX][newY] == 'L') {
                    visited[newY][newX] = true;
                    queue.add(new Node(newX, newY, n.cnt + 1));
                    val = Math.max(val, n.cnt + 1);
                }
            }
        }
        return val;
    }

    public static class Node{
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
