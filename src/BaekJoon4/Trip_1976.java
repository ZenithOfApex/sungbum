package BaekJoon4;

import java.io.*;
import java.util.StringTokenizer;

public class Trip_1976 {
    static int N, M;
    static String answer= "";
    static int[] city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());//도시의 수

        city = new int[N+1];
        for (int i = 1; i <= N; i++) {
            city[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int connected = Integer.parseInt(st.nextToken());
        }

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
