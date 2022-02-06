package BaekJoon3;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeBusiness2_16235 {

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] A = new int[N+1][N+1];
        int[][] eat = new int[N + 1][N + 1];
        Deque<Tree> treeList = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                eat[i][j] = 5;
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x, y, age));
        }

        while (K-- > 0) {
            Queue<Tree> deadTreeList = new LinkedList<>();

            for (int i = 0; i < treeList.size();) {
                Tree cur = treeList.poll();
                if (eat[cur.x][cur.y] >= cur.age) {
                    eat[cur.x][cur.y] -= cur.age;
                    cur.age++;
                    i++;
                    treeList.add(cur);
                } else {
                    deadTreeList.add(cur);
                }
            }

            for (Tree tree : deadTreeList) {
                eat[tree.x][tree.y] += tree.age / 2;
            }

            Queue<Tree> temp_list = new LinkedList<>();
            for (Tree tree : treeList) {
                if (tree.age % 5 == 0) {
                    temp_list.add(tree);
                }
            }
            while (!temp_list.isEmpty()) {
                Tree t = temp_list.poll();

                for (int i = 0; i < 8; i++) {
                    int nx = t.x + dx[i];
                    int ny = t.y + dy[i];
                    if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
                        treeList.addFirst(new Tree(nx, ny, 1));
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    eat[i][j] += A[i][j];
                }
            }
        }

        bw.write(treeList.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Tree implements Comparable<Tree> {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

}
