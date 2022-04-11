package BaekJoon4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Snake_3190 {
    static int N, K, L;
    static int answer = 0;
    static int[][] map;
    static Queue<TimeStamp> timeQueue =new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = 1;//사과의 위치를 1로 표시
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();

            timeQueue.add(new TimeStamp(time, dir));
        }

        solution();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(){
        //1,1에서부터 뱀이 움지기이기 시작
        int head_x = 1;
        int head_y = 1;
        int curTime = 1;

        int targetTime = timeQueue.peek().time;
        String targetDir = timeQueue.poll().dir;
        while(true){
            if (curTime == targetTime) {
                //방향 전환

                //targetTime, targetDir 갱신
                targetTime = timeQueue.peek().time;
                targetDir = timeQueue.poll().dir;
            }
            move();

            if (!inRange(head_x, head_y) || map[head_x][head_y] == 2) {
                 break;
            }
            curTime++;
        }
        //초기 이동은 오른쪽으로 이동
        //세가지 분기에 따라 이동 진행
    }

    private static boolean hasApple(int x, int y){
        if (map[x][y] == 1) {
            return true;
        }else return false;
    }

    private static void move(int x, int y, int dir){
        //이동 진행
        //머리가 먼저 다음칸으로 이동
        //머리가 이동한 칸에 사과가 있으면 꼬리는 움직이지 않음
        //머리가 이동한 칸에 사과가 없으면 꼬리는 머리칸으로 이동

        //벽 또는 자기 자신에 부딪히면 종료
    }

    private static boolean inRange(int x, int y) {
        return x >= 1 && x < N + 1 && y >= 1 && y < N + 1;
    }

    static class TimeStamp {
        int time;
        String dir;

        public TimeStamp(int time, String dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}
