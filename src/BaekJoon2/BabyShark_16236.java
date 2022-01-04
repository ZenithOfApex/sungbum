package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark_16236 {

    static int N, time, x, y, size, cnt;//map size, 시간, 좌표, 상어 크기, 카운트
    static int[][] map;//모든 정보를 담고 있는 지도
    static ArrayList<Info> fishes;//물고기에 대한 정보를 담기 위한 배열
    //상하좌우 움직이기 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        size = 2;//아기 상어 크기 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 9) {//아기 상어의 위치 확ㄹ인
                    x = i;
                    y = j;
                    map[i][j] = 0;//상어가 있는 위치를 0으로 취급
                }
            }
        }
        br.close();

        while (true) {
            fishes = new ArrayList<>();//물고기 위치를 저장하기 위한 배열 선언
            Queue<Info> q = new LinkedList<>();//bfs 사용하기 위한 큐
            boolean visited[][] = new boolean[N][N];//방문 확인하기 위한 visited

            q.offer(new Info(x, y, 0));//아기 상어의 현재 위치 큐에 넣기
            visited[x][y] = true;//시작 위치 방문 처리

            while (!q.isEmpty()) {//더 이상 큐에 들어올게 없을 때까지
                Info shark = q.poll();//상어의 위치 정보 받아옴
                for (int i = 0; i < 4; i++) {//받아온 위치 기준으로 상하좌우 범위, 방문 체크하고 해당되면 큐에 넣기
                    int nx = shark.x + dx[i];
                    int ny = shark.y + dy[i];
                    if (inRange(nx, ny)) {//범위 벗어난다면
                        continue;
                    }
                    if (visited[nx][ny]) {//방문한적이 있다면
                        continue;
                    }

                    if (1 <= map[nx][ny] && map[nx][ny] < size) {//먹을수 있고 지나갈 수도 있는 경우
                        q.offer(new Info(nx, ny, shark.dist + 1));//bfs를 위해 큐에 넣기
                        fishes.add(new Info(nx, ny, shark.dist + 1));//먹을 수 있는 물고기 처리
                        visited[nx][ny] = true;//해당 노드 방문 처리
                    } else if (map[nx][ny] == size || map[nx][ny] == 0) { // 먹을수는 없지만 지나갈 수 있는 경우
                        q.offer(new Info(nx, ny, shark.dist + 1));//지나갈 수 있음을 위해 bfs를 위해 큐에 넣기
                        visited[nx][ny] = true;
                    }
                }
            }
            if (fishes.size() == 0) {//먹을 수 있는 물고기가 없는 경우
                System.out.println(time);
                return;
            }

            Info eatingFish = fishes.get(0);//먹을수 있는 첫번째 물고기 정보 얻어오기
            for (int i = 1; i < fishes.size(); i++) {
                if (fishes.get(i).dist < eatingFish.dist) {//다음꺼가 더 가깝다? 바꿔
                    eatingFish = fishes.get(i);
                }
                if (eatingFish.dist == fishes.get(i).dist) {//거리가 같은 경우에는 x값 기준으로 처리
                    if (eatingFish.x > fishes.get(i).x) {
                        eatingFish = fishes.get(i);
                    }
                }
            }

            time += eatingFish.dist;//거리가 즉 시간이므로 시간+=
            cnt++;//먹었으면 cnt증가
            map[eatingFish.x][eatingFish.y] = 0;//먹은 거 처리

            if (cnt == size) {//자기 크기만큼 먹었으면 몸집 키우기, 다시 cnt 초기화
                size++;
                cnt=0;
            }
            //먹은 물고기 위치로 아기 상어 위치 변경
            x = eatingFish.x;
            y = eatingFish.y;
        }
    }

    public static boolean inRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    static class Info {
        int x;
        int y;
        int dist;

        public Info(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

}
