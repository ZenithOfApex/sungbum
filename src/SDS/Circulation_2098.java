package SDS;

import java.io.*;
import java.util.StringTokenizer;

public class Circulation_2098 {
    /**
     *
     * 외판원 순회
     * DP + 비트마스킹
     *
     * 외판원 순회는 CS 분야에서 중요한 문제 중 하나이다.
     *
     * 도시가 N 개일 꼉우 단순한 전체 탐색을 이용할 경우 시간복잡도가 N!가 나오고 풀이가 불가능하다
     * DP + 비트마스킹 + 일반탐색(DFS) 기법을 사용하면, 시간 복잡도를 N^2 * 2^N 의 풀이가 가능하다
     *
     * DP[Cur][Visit] = Cur 도시까지 Visit에 기록된 도시들을 방문하고 가는 최소 비용
     * 이때, Visit의 경우 N개 도시들의 방문기록을 나타내야하고 일반적으로는 N 크기의 배열을 사용하나, 방문여분는 1 or 0으로 표현될 수 있다는 아이디어에
     * 착안하여 비트마스킹 기법을 사용하여, integer하나로 표현한다.
     * 예를 들어 4개의 도시 1,2,3,4가 있고 이중 2와 3만 방문한 경우 0110 (2) = 6으로 표현한다.
     * 1과 4만 방문한 경우 1001 (2) = 9로 표현한다.
     * 위처럼 Integer를 2진수로 표현해서 기록한다.
     */

    static int n;
    static int[][] dp;
    static int[][] w;
    static int visitAll;
    static final int INF = Integer.MAX_VALUE;
    static int answer;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        visitAll = (1 << n) - 1;
        w = new int[n + 1][n + 1];
        dp = new int[n + 1][visitAll + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = INF;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= visitAll; j++) {
                dp[i][j] = INF;
            }
        }

        dp[1][1] = 0;
        getDP(1, 1);

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void getDP(int now, int visited) {
        //모든 도시를 방문한 경우
        //이제 최초 출발점으로 돌아가는 코드만 있으면 된다
        if (visited == visitAll) {
            if (w[now][1] == 0) {
                return;//now에서 출발점으로 갈 수 없는 경우
            }
            answer = Math.min(answer, dp[now][visited] + w[now][1]);
        }

        //아직 방문할 도시가 남은 경우, 1~N까지 모든 정점을 탐색해본다.
        for (int i = 1; i <= n; i++) {
            int next = (1 << (i - 1));
            int nextVisited = visited | next;//다음 방문할 도시의 비트연산자
            if (nextVisited == visited) {//다음 방문할 도시를 이미 방문한 경우는 continue
                continue;
            }
            if (w[now][i] == 0) {//다음 도시를 갈 수 있느 길이 없어도 continue;
                continue;
            }
            //다음 도시를 계산한다.
            if (dp[i][nextVisited] > dp[now][visited] + w[now][i]) {
                dp[i][nextVisited] = dp[now][visited] + w[now][i];
                getDP(i, nextVisited);
            }
        }
    }
}
