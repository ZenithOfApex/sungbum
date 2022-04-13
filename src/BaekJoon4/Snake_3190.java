package BaekJoon4;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Snake_3190 {
    static int N, K, L;
    static int answer = 0;
    static int[][] map;
    static int d = 0;
    static int[] dx = {1, 0, -1, 0};//우 상 하 좌
    static int[] dy = {0, 1, 0, -1};

    static HashMap<Integer, String> moveHash = new HashMap<>();
    static Queue<Integer> snake =new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row-1][col-1] = 1;//사과의 위치를 1로 표시
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();

            moveHash.put(time, dir);
        }

        solution();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution(){
        //1,1에서부터 뱀이 움지기이기 시작: (0,0)으로 저장
        snake.add(0);
        int curTime = 0;
        int x = 0;
        int y = 0;

        while (true) {
            curTime++;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!inRange(nx, ny) || snake.contains(ny * N + nx)) {
                break;
            }

            if (map[ny][nx] == 1) {
                map[ny][nx] = 0;
                snake.add(ny * N + nx);
            } else {
                snake.add(ny * N + nx);
                snake.poll();
            }

            if (moveHash.containsKey(curTime)) {
                String nextDir = moveHash.get(curTime);
                if (nextDir.equals("D")) {
                    d = (++d) % 4;
                } else {
                    d -=1;
                    if (d == -1) {
                        d = 3;
                    }
                }
            }

            x = nx;
            y = ny;
        }
        answer = curTime;
        //초기 이동은 오른쪽으로 이동
        //세가지 분기에 따라 이동 진행
    }

    private static void move(int x, int y, int dir){
        //이동 진행
        //머리가 먼저 다음칸으로 이동
        //머리가 이동한 칸에 사과가 있으면 꼬리는 움직이지 않음
        //머리가 이동한 칸에 사과가 없으면 꼬리는 머리칸으로 이동
        //벽 또는 자기 자신에 부딪히면 종료
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
