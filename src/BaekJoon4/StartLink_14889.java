package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StartLink_14889 {
    static int N, answer = Integer.MAX_VALUE;
    static int startScore;
    static int linkScore;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];//사람 번호도 1번부터 N번 행렬도 1부터 N이라고 가정하고 풀자
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 0);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    //index도 1부터 시작해야된다
    private static void dfs(int index, int depth) {
        if (depth == N / 2) {
            //두개의 group으로 나누어진다 visited_true & visited_false
            //visited_true를 start team으로
            //visited_false를 link team으로
            ArrayList<Integer> linkTeam = new ArrayList<>();
            ArrayList<Integer> startTeam = new ArrayList<>();
            for (int i = 1; i < N + 1; i++) {
                if (visited[i]) {
                    startTeam.add(i);
                } else {
                    linkTeam.add(i);
                }
            }

            //점수 계산
            getMinimumScoreGap(startTeam, linkTeam);
            return;
        }

        for (int i = index; i < N; i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static void getMinimumScoreGap(ArrayList<Integer> teamStart, ArrayList<Integer> teamLink) {
        //teamLink 모든 합 구하기

        for (int i = 0; i < teamLink.size() - 1; i++) {
            for (int j = i + 1; j < teamLink.size(); j++) {
                int num1 = teamLink.get(i);
                int num2 = teamLink.get(j);
                linkScore += map[num1][num2];
                linkScore += map[num2][num1];
            }
        }
        //teamStart 모든 합 구하기
        for (int i = 0; i < teamStart.size() - 1; i++) {
            for (int j = i + 1; j < teamStart.size(); j++) {
                int num1 = teamStart.get(i);
                int num2 = teamStart.get(j);
                startScore += map[num1][num2];
                startScore += map[num2][num1];
            }
        }

        answer = Math.min(answer, Math.abs(startScore-linkScore));
        linkScore = 0;
        startScore = 0;
    }
}
