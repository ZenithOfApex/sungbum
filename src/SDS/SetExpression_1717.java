package SDS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SetExpression_1717 {

    static StringTokenizer st;
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        group = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            group[i] = i;
        }

        for (int i = 0; i < m; i++) {
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
        if (group[a] == a) {
            return a;
        } else {
            return group[a] = find(group[a]);
        }
    }

    private static void union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);

        group[aGroup] = bGroup;
    }
}
