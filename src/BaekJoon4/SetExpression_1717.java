package BaekJoon4;

import java.io.*;
import java.util.StringTokenizer;

public class SetExpression_1717 {
    static int N, M;
    static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        set = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            set[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            operation(command, a, b);
        }
        br.close();
    }

    private static void operation(int command, int a, int b) {
        if (command == 0) {
            union(a, b);
        } else if (command == 1) {
            if (find(a) == find(b)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static int find(int a) {
        if (set[a] == a) {
            return a;
        } else {
            return set[a] = find(set[a]);
        }
    }

    private static void union(int a, int b) {
        int aSet = find(a);
        int bSet = find(b);

        set[aSet] = bSet;
    }
}
