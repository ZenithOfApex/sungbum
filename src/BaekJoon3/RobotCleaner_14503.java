package BaekJoon3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RobotCleaner_14503 {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};//왼후우전
    static int[] dy = {-1, 0, 1, 0};
    static int[] direction = {0, 1, 2, 3};//북동남서 순

    static Queue<Robot> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = 1;

        map = new int[N][M];
        visited = new boolean[N][M];

        //robot 초기화 및 선언(initial state)
        st = new StringTokenizer(br.readLine(), " ");
        int robotX = Integer.parseInt(st.nextToken());
        int robotY = Integer.parseInt(st.nextToken());
        int robotDirection = Integer.parseInt(st.nextToken());
        queue.offer(new Robot(robotX, robotY, robotDirection));
        visited[robotX][robotY] = true;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!queue.isEmpty()) {
            Robot polled = queue.poll();

            checkCleanCondition(polled.x, polled.y, polled.direction);
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void clean(int x, int y) {
        visited[x][y] = true;
        answer++;
    }

    private static int changeDirection(int curDirection) {
        return direction[(curDirection + 1) % 4];
    }

    private static void checkCleanCondition(int x, int y, int InputDirection) {
        int dir = InputDirection;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[(i+InputDirection)%4];
            int ny = y + dy[(i+InputDirection)%4];

            //청소한 적이 없고 벽이 아닌 경우
            if (!inRange(nx, ny)) {
                continue;
            }
            if (!visited[nx][ny] && map[nx][ny] != 1) {
                clean(nx, ny);
                checkCleanCondition(nx, ny, changeDirection(InputDirection));
//                queue.offer(new Robot(nx, ny, changeDirection(InputDirection)));
                break;
            }else continue;
            dir = changeDirection(InputDirection);
        }

        queue.add(new Robot(x + dx[1], y + dy[1], InputDirection));
        //네 방향이 모두 청소하였거나 벽인 경우
        //direction 유지하면서 한칸 뒤로 이동 -> checkCleanCondition()

        //네 방향이 모두 청소하였거나 벽인 경우
        //후진도 할 수 없는 경우에는 -> stop()
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >=0 && y < M;
    }

    static class Robot{
        int x;
        int y;
        int direction;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
