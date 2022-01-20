package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class GCD_2824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BigInteger a = new BigInteger("1");
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a = a.multiply(new BigInteger(st.nextToken()));
        }
        int m = Integer.parseInt(br.readLine());
        BigInteger b = new BigInteger("1");
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            b = b.multiply(new BigInteger(st.nextToken()));
        }

        getGCD(a, b);

        br.close();
    }

    public static void getGCD(BigInteger a, BigInteger b) {
        BigInteger big = a.compareTo(b) > 0 ? a : b;
        BigInteger small = a.compareTo(b) > 0 ? b : a;
        BigInteger zero = new BigInteger("0");
        while (true) {
            BigInteger mod = big.mod(small);
            if (mod.compareTo(zero) ==0) {
                break;
            }
            big=small;
            small = mod;
        }

        String answer = small.toString();

        if (answer.length() > 9) {
            answer = answer.substring(answer.length() - 9, answer.length());
        }
        System.out.println(answer);
    }
}
