package BaekJoon4;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Trip_1976 {
    static int N, M;
    static String answer= "";
    static int[] city, plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());//도시의 수

        city = new int[N + 1];
        plan = new int[M];
        for (int i = 1; i <= N; i++) {
            city[i] = i;
        }

        //여기서 도시 연결 정보 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    if (!isSameGroup(i + 1, j + 1)) {
                        union(i + 1, j + 1);
                    }else continue;
                }
            }
        }

        //마지막 정보 받고 처리 끝
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < M; i++) {
            int found = find(plan[i]);
            hs.add(found);
        }

        if (hs.size() == 1) {
            answer = "YES";
        } else answer = "NO";

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (city[x] == x) {
            return x;
        } else {
            return city[x] = find(city[x]);
        }
    }

    private static boolean isSameGroup(int a, int b) {
        if (find(a) == find(b)) {
            return true;
        } else return false;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            city[a] = b;
        } else {
            city[b] = a;
        }
    }
}
