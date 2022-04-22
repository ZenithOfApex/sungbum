package BaekJoon4;

import java.io.*;
import java.util.*;

public class SharkElementarySchool_21608 {
    static int N;
    static int answer = 0;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] inputOrder;
    static HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        inputOrder = new int[N * N];

        //한줄당 하나씩 그냥 hashMap에 저장
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int main_student = Integer.parseInt(st.nextToken());
            ArrayList<Integer> like = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                like.add(Integer.parseInt(st.nextToken()));
            }
            hm.put(main_student, like);
            inputOrder[i] = main_student;
        }

        solution();
        getSatisfaction();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        /**
         * 1번부터 N*N 학생까지 Input 순서대로 진행될건데
         * 다음의 조건에 맞게 구현되어야 한다.
         * 1. 비어있는 칸 중 좋아하는 학생의 수가 가장 많은 칸에 우선 배치되어야 한다.
         * 2. 1번 조건을 만족하는 칸이 여러 개라면 인접한 칸 중 비어있는 칸이 가장 많은 칸으로 배치한다.
         * 3. 2번 조건을 만족하는 칸이 여러 개라면 행의 번호가 작은 순으로, 그 다음은 열의 번호가 작은 순으로 배치한다.
         */
        for (int i = 0; i < inputOrder.length; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] == 0) {
                        continue;
                    } else {

                    }
                }
            }
        }
    }

    //인접한 4칸 중 좋아하는 학생의 수 구하는 func
    private static int getNearBestFriendCount(int main_student, int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny)) {
                continue;
            } else {
                if (hm.get(main_student).contains(map[nx][ny])) {
                    count++;
                }
            }
        }
        return count;
    }

    //인접한 4칸 중 비어있는 칸의 수 구하는 func
    private static int getNearEmptyCnt(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!inRange(nx, ny)) {
                continue;
            } else {
                if (map[nx][ny] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    //모든 칸에 대하여 만족도 조사하는 func
    private static void getSatisfaction() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (!inRange(nx, ny)) {
                        continue;
                    }
                    if (hm.get(map[i][j]).contains(map[nx][ny])) {
                        count++;
                    }
                }
                if (count == 0) {
                    answer += 0;
                } else {
                    answer += (int)(Math.pow(10, count));
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
