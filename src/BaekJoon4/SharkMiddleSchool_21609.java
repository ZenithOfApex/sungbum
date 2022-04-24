package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SharkMiddleSchool_21609 {
    static int INF = Integer.MAX_VALUE;
    static int N, M;
    static int answer;
    static int maximumSize = Integer.MIN_VALUE;
    static int[][] map;
    static int[][] blockCnt;
    static ArrayList<Block> bigBlock = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        blockCnt = new int[N][N];

        for (int i = 0; i < N; i++) {
            //검은색 블록은 -1, 무지개 블록은 0, 일반 블록은 M이하의 자연수
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (bigBlock.size() != 1) {
            findBiggestBlock();
            deleteBiggestBlock(bigBlock);
            getScore(bigBlock.size());
            applyGravity();
            rotateClockwise();
            applyGravity();
            print();
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    //map 내 가장 큰 블럭을 구한다(bfs로 구현)
    //i, j 로 bfs 돌려서 Math.max(count, curCnt) 이런식으로 출발 좌표를 기준으로 식별
    private static void findBiggestBlock(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int fixedNormalBlock = map[i][j];
                //시작 블럭 개수 count
                int cnt = 1;
                //blockList에 포함되는 block들
                ArrayList<Block> blockList = new ArrayList<>();
                blockList.add(new Block(i,j,fixedNormalBlock));

                //bfs에서 사용할 큐
                Queue<Block> blockQueue = new LinkedList<>();
                blockQueue.add(new Block(i, j, map[i][j]));

                while (!blockQueue.isEmpty()) {
                    Block block = blockQueue.poll();
                    int x = block.x;
                    int y = block.y;

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        //map의 범위를 벗어나거나 검은 block인 경우
                        if (!inRange(nx, ny) || map[nx][ny]== -1) {
                            continue;
                        }
                        //일반 블럭에 다른 종류가 나오는 경우도 패스
                        if (map[nx][ny] == fixedNormalBlock) {
                            continue;
                        }

                        blockQueue.add(new Block(nx, ny,map[nx][ny]));
                        blockList.add(new Block(nx, ny, map[nx][ny]));
                        cnt++;
                    }
                }
                //블럭의 크기 저장
                maximumSize = Math.max(maximumSize, blockList.size());
                if (blockList.size() == maximumSize) {
                    bigBlock = new ArrayList<>(blockList);
                }
                if (checkBlockCondition(blockList)) {
                    blockCnt[i][j] = blockList.size();
                }else blockCnt[i][j] = 1;
            }
        }
    }

    private static boolean checkBlockCondition(ArrayList<Block> blockList) {
        int cnt = 0;
        for (Block block : blockList) {
            if (block.value != 0) {
                cnt++;
            }
        }
        if (cnt == 1) {
            return false;
        }else return true;
    }

    //가장 큰 블럭 삭제
    private static void deleteBiggestBlock(ArrayList<Block> blockList){
        for (Block block : blockList) {
            map[block.x][block.y] = INF;
        }
    }

    //2차원 배열 반시계로 90도 회전
    private static int[][] rotateClockwise(){
        int[][] rotate = new int[N][N];

        for(int i=0;i<rotate.length;i++){
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = map[N - 1 - j][i];
            }
        }

        return rotate;
    }

    //이게 어려울듯
    private static void applyGravity(){
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 0) {
                    //아래칸이 범위안에 있고 비어있는 칸일때만 하나 이동이 아니라 쭉
                    while (!inRange(i + 1, j) && map[i + 1][j] == INF) {
                        map[i+1][j] = map[i][j];
                        map[i][j] = INF;
                    }
                }
            }
        }
    }

    //블럭의 수 필요
    private static void getScore(int blockCnt){
        answer+= (int)Math.pow(blockCnt, 2);
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static void print() {
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Block{
        int x;
        int y;
        int value;

        public Block(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
