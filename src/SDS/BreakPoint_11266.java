package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BreakPoint_11266 {

    static int v, e;
    static ArrayList<Integer>[] map;
    static int[] searchOrder;
    static boolean[] isCutVertex;
    static int order, answer;
    static StringBuffer answerList;

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine(), " ");
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        searchOrder = new int[v + 1];
        isCutVertex = new boolean[v + 1];
        map = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            map[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        order = 0;
        for (int i = 1; i <= v; i++) {
            if (searchOrder[i] == 0) {
                dfs(i, true);
            }
        }

        answer =0;
        answerList = new StringBuffer();

        for (int i = 1; i <= v; i++) {
            if (isCutVertex[i] == true) {
                answer++;
                answerList.append(i + " ");
            }
        }

        bw.write(answer + "\n");
        if (answer > 0) {
            bw.write(answerList + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int now, boolean isRoot) {
        order++;//방문 순서
        searchOrder[now] = order;
        int rtn = order;//지금 정점 이후에 도달할 수 있는 모든 정점들의 탐색순서 중 가장 작은 값
        int child = 0;//자식의 숫자 root일 경우 단절점 판단을 위함

        for (Integer next : map[now]) {
            if (searchOrder[next] == 0) {
                child++;

                //자식 정점 중 방문 순서가 가장 빠른 값.
                //이때, 특정 자식 정점이 여러 개의 정점을 타고타고 올라가서 1번 정점까지 갈 수도 있다는 점에 유의
                int low = dfs(next, false);//현재 정점의 다음에 방문할 모든 정점에 대해서 도달할 수 있는 최소의 order 순서(우회로가 있나 확인)

                //root가 아니고, 내 다음에 방문할 정점의 순서가 모두 나보다 클 경우에 지금 위치는 단절점이다.
                if (isRoot == false && low >= searchOrder[now]) {
                    isCutVertex[now] = true;
                }
                rtn = Math.min(rtn, low);
            } else {//자식 정점이 이미 방문한 경우
                rtn = Math.min(rtn, searchOrder[next]);
            }
        }
        //root의 경우 내 order보다 작은게 낭로 수 없기 때문에 자식 노드의 숫자로 판별한다.
        if (isRoot == true && child >= 2) {
            isCutVertex[now] = true;
        }

        return rtn;
    }
}
