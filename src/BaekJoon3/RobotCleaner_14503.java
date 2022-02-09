package BaekJoon3;

import java.io.*;
import java.util.StringTokenizer;

public class RobotCleaner_14503 {
    static int N, M, answer=0;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};//왼후우전
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        //robot 초기화 및 선언(initial state)
        st = new StringTokenizer(br.readLine(), " ");
        int robotX = Integer.parseInt(st.nextToken());
        int robotY = Integer.parseInt(st.nextToken());
        int robotDirection = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkCleanCondition(robotX, robotY, robotDirection);

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkCleanCondition(int x, int y, int direction) {
        if (map[x][y] == 0) {//방문 처리
            map[x][y] = 2;
            answer++;
        }

        boolean flag = false;
        int inputDirection = direction;

        for (int i = 0; i < 4; i++) {
            int newDirection = (direction + 3) % 4;
            int nx = x + dx[newDirection];
            int ny = y + dy[newDirection];

            if (inRange(nx, ny)) {
                if (map[nx][ny] == 0 ) {
                    checkCleanCondition(nx, ny, newDirection);
                    flag = true;
                    break;
                }
            }
            direction = (direction + 3) % 4;
        }

        if (!flag) {
            int newDirection = (inputDirection + 2) % 4;
            int nx = x + dx[newDirection];
            int ny = y + dy[newDirection];

            if (inRange(nx, ny)) {
                if (map[nx][ny] != 1) {
                    checkCleanCondition(nx, ny, inputDirection);
                }
            }
        }
        //네 방향이 모두 청소하였거나 벽인 경우
        //direction 유지하면서 한칸 뒤로 이동 -> checkCleanCondition()

        //네 방향이 모두 청소하였거나 벽인 경우
        //후진도 할 수 없는 경우에는 -> stop()
    }

    private static boolean inRange(int x, int y) {
        return x > 0 && x < N && y >0 && y < M;
    }
}
