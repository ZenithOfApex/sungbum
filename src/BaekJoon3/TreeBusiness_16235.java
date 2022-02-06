package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeBusiness_16235 {

    static int[][] nutrition;
    static int[][] injection;
    static Queue<Tree> treeList = new LinkedList<>();
    static Queue<Tree> deadTree = new LinkedList<>();
    static Queue<Tree> autumnTree = new LinkedList<>();

    static int N, M, K;

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nutrition = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrition[i][j] = 5;
            }
        }

        injection = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                injection[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x-1, y-1, age));
        }

        while (K-- > 0) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(treeList.size());

        br.close();
    }
    //양분 흡수(어린나무부터), 흡수못한 나무는 즉사
    private static void spring() {
        while (!treeList.isEmpty()) {
            Tree polled = treeList.poll();
            int polledX = polled.x;
            int polledY = polled.y;
            int polledAge = polled.age;

            if (polledAge > nutrition[polledX][polledY]) {
                deadTree.offer(polled);
            } else {
                nutrition[polledX][polledY] -= polledAge;
                autumnTree.offer(new Tree(polledX, polledY, polledAge + 1));
            }
        }
    }
    //죽은 나무의 나이/2 값을 양분에 추가
    private static void summer() {
        while (!deadTree.isEmpty()) {
            Tree dead = deadTree.poll();

            int deadNut = dead.age / 2;
            nutrition[dead.x][dead.y] += deadNut;

        }
    }

    //나이가 5의 배수인 나무 8방에 새로운 묘목(1살) 추가
    private static void autumn() {
        while (!autumnTree.isEmpty()) {
            Tree autumn = autumnTree.poll();
            if (autumn.age % 5 == 0) {//5의 배수라면
                for (int i = 0; i < 8; i++) {
                    int nx = autumn.x + dx[i];
                    int ny = autumn.y + dy[i];
                    if (inRange(nx, ny)) {
                        treeList.add(new Tree(nx, ny, 1));
                    }
                }
            }
            treeList.offer(autumn);
        }
    }
    //영양분 추가
    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nutrition[i][j] += injection[i][j];
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Tree implements Comparable<Tree>{
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return age - o.age;
        }
    }
}
