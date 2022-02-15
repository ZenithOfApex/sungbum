package BaekJoon3;

import java.io.*;
import java.util.*;

public class DragonCurve_15685 {

    static int N, answer;
    static ArrayList<Dragon> dragon;
    static int nx, ny;
    static int[][] map = new int[101][101];//0이면 false 1이면 true

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dragon = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dragon.add(new Dragon(x, y, d, g));
        }

        //입력받은 드래곤 커브에 대하여 map에 visited 처리
        for (Dragon drag : dragon) {
            int x = drag.x;
            int y = drag.y;
            int d = drag.direction;
            int g = drag.generation;

            draw(x, y, getDirection(d, g));
        }
        //map에서 전체 사각형 갯수 세기
        countingSquares();


        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static List<Integer> getDirection(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        while (g-- > 0) {
            for (int i = directions.size() - 1; i >= 0; i--) {
                int direction = (directions.get(i) + 1) % 4;
                directions.add(direction);
            }
        }
        return directions;
    }

//    private static void solution(int x, int y, Deque<Integer> directionQueue, int curGeneration, int targetGeneration) {
//        if (curGeneration == targetGeneration) {
//            return;
//        }
//
//        Deque<Integer> tempDeque = new LinkedList<>(directionQueue);
//        while (!directionQueue.isEmpty()) {
//            int polledDirection = directionQueue.pollFirst();
//            draw(x, y, polledDirection);
//            tempDeque.offerLast((polledDirection + 1) % 4);
//        }
//
//
//        //다음 generation 그리기
//        solution(nx, ny, tempDeque, curGeneration + 1,targetGeneration);
//        tempDeque = null;
//    }

//    private static void solution(int x, int y, int d, int g) {
//        int curGeneration = 0;
//
//        workQueue.offer(d);
//
//        nx = x;
//        ny = y;
//
//        while (curGeneration <= g) {
////            draw(x, y, d);
//            //현 작업리스트에 대하여 체크 실행
//            Deque<Integer> tempDeque = new LinkedList<>(workQueue);
//            while (!workQueue.isEmpty()) {
//                int polledDirection = workQueue.pollFirst();
//                nx = nx + dx[polledDirection];
//                ny = ny + dy[polledDirection];
//                draw(nx, ny, polledDirection);
//            }
////            System.out.println("checked");
//            //다음세대를 위한 작업 리스트 갱신
//            ArrayList<Integer> prevWork = new ArrayList<>();
//            ArrayList<Integer> curWork = new ArrayList<>();
//            while (!tempDeque.isEmpty()) {
//                int polledDirection = tempDeque.pollLast();
//
//                int newDirection = (polledDirection + 1) % 4;
//                prevWork.add(polledDirection);
//                curWork.add(newDirection);
//            }
//            for (Integer integer : prevWork) {
//                tempDeque.offerFirst(integer);
//            }
//            for (Integer integer : curWork) {
//                tempDeque.offerLast(integer);
//            }
////            System.out.println("done");
//
//            curGeneration++;
//        }
//    }

    private static void draw(int x, int y, List<Integer> directions) {
        map[x][y] = 1;

        for (int direction : directions) {
            if (direction == 0) {
                map[++x][y] = 1;
            } else if (direction == 1) {
                map[x][--y] = 1;
            } else if (direction == 2) {
                map[--x][y] = 1;
            } else {
                map[x][++y] = 1;
            }
        }

        map[nx][ny] = 1;
    }

    private static void countingSquares() {
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (map[i][j]==1 && map[i + 1][j]==1 && map[i][j + 1]==1 && map[i + 1][j + 1]==1) {
                    answer++;
                }
            }
        }
    }

    static class Dragon {
        int x;
        int y;
        int direction;
        int generation;

        public Dragon(int x, int y, int direction, int generation) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.generation = generation;
        }
    }

}
