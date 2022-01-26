package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * union-find의 응용
 * A와 B가 연결관계가 있고, B와 C의 연결관계가 있고, A와 D의 연결관계가 있으면 A B C D 끼리는 모두 비교할 수 있음
 *
 * weightDiff[i] = 내 root (parent) 보다 무거운 정도
 * 사용 예) weightDiff[2] =3 이고 parent[2] = 4라면 2번 노드는 4번 노드보다 상대적으로 3만큼 더 무겁다
 *
 * 즉, 최상위 parent의 weightDiff 값은 0이 된다.
 *
 * 단순히 탐색(dfs/ bfs로 풀이하려면 풀이가 쉽지 않음)
 */

public class ProfessorDoesntWait_3830 {
    static int n, m;
    static long[] weightDiff;
    static int[] parent;
    static long answer;

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            weightDiff = new long[n + 1];
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            String q;
            int a, b, diff;
            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                q = st.nextToken();
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                if (q.equals("!")) {
                    diff = Integer.parseInt(st.nextToken());
                    union(a, b, diff);
                } else {
                    if (find(a) == find(b)) {
                        answer = weightDiff[b] - weightDiff[a];
                        bw.write(answer + "\n");
                    } else {
                        bw.write("UNKNOWN" + "\n");
                    }
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b, int diff) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) {
            return;
        }

        weightDiff[parentB] = weightDiff[a] - weightDiff[b] + diff;
        parent[parentB] = parentA;
    }

    private static int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            int parentIndex = find(parent[i]);
            weightDiff[i] += weightDiff[parent[i]];
            return parent[i] = parentIndex;
        }
    }
}
