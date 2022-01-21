package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dictionary_1256 {

    static double[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n, m;
        double k;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new double[n+1][m+1];
        getResult(n, m, k);

        if (check(n, m) < k) {
            System.out.println(-1);
        }else System.out.println(sb.toString());
    }

    private static double check(int a, int z) {
        if (a == 0 || z == 0) return 1;
        if (dp[a][z] != 0) {
            return dp[a][z];
        }
        return dp[a][z] = Double.min(check(a - 1, z) + check(a, z - 1), 1000000001);
    }

    private static void getResult(int a, int z, double k) {
        if (a == 0) {
            for (int i = 0; i < z; i++) {
                sb.append("z");
            }
            return;
        }
        if (z == 0) {
            for (int i = 0; i < a; i++) {
                sb.append("a");
            }
            return;
        }

        double check = check(a - 1, z);
        if (k > check) {
            sb.append("z");
            getResult(a, z - 1, k - check);
        } else {
            sb.append("a");
            getResult(a - 1, z, k);
        }
    }
}
