package BaekJoon3;

import java.io.*;
import java.util.*;

public class TreeBusiness_16235 {

    static int[][] nutrition;
    static int[][] injection;
    static Deque<Tree> treeList = new LinkedList<>();
    static Queue<Tree> deadTree = new LinkedList<>();
    static Queue<Tree> autumnTree = new LinkedList<>();

    static int N, M, K;

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nutrition = new int[N + 1][N + 1];
        injection = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                injection[i][j] = Integer.parseInt(st.nextToken());
                nutrition[i][j] = 5;
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
            spring();
            summer();
            autumn();
            winter();
        }

        bw.write(treeList.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    //양분 흡수(어린나무부터), 흡수못한 나무는 즉사
    private static void spring() {
        for (int i = 0; i < treeList.size();) {
            Tree polled = treeList.poll();
            if (nutrition[polled.x][polled.y] >= polled.age) {
                nutrition[polled.x][polled.y] -= polled.age;
                polled.age++;
                i++;
                treeList.add(polled);
            } else {
                deadTree.add(polled);
            }
        }
    }
    //죽은 나무의 나이/2 값을 양분에 추가
    private static void summer() {
        for (Tree tree : deadTree) {
            nutrition[tree.x][tree.y] += tree.age / 2;
        }
    }

    //나이가 5의 배수인 나무 8방에 새로운 묘목(1살) 추가
    private static void autumn() {

        for (Tree tree : treeList) {
            if (tree.age % 5 == 0) {
                autumnTree.add(tree);
            }
        }
        while (!autumnTree.isEmpty()) {
            Tree t = autumnTree.poll();

            for (int i = 0; i < 8; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                if (inRange(nx, ny)) {
                    treeList.addFirst(new Tree(nx, ny, 1));
                }
            }

        }
    }
    //영양분 추가
    private static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                nutrition[i][j] += injection[i][j];
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }

    static class Tree implements Comparable<Tree>{
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            super();
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
