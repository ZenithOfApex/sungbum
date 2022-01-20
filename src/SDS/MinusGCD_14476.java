package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinusGCD_14476 {
    static int[] ltr;
    static int[] rtl;
    static int[] originalArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        originalArr = new int[n];
        for (int i = 0; i < n; i++) {
            originalArr[i] = Integer.parseInt(st.nextToken());
        }
        ltr = new int[n];
        rtl = new int[n];
        ltr[0] = originalArr[0];
        rtl[n - 1] = originalArr[n - 1];
        for (int i = 1; i < n; i++) {
            ltr[i] = getGCD(ltr[i - 1], originalArr[i]);
            rtl[n - 1 - i] = getGCD(rtl[n - i], originalArr[n - 1 - i]);
        }


        int max =0;
        int maxIndex =0;
        for (int i = 0; i < n; i++) {
            int gcd = 0;
            if (i == 0) {
                gcd = rtl[1];
            } else if (i == n - 1) {
                gcd = ltr[n - 2];
            } else gcd = getGCD(ltr[i - 1], rtl[i + 1]);

            if (originalArr[i] % gcd != 0 && gcd > max) {
                max = gcd;
                maxIndex = i;
            }
        }
        if (max == 0) {
            System.out.println(-1);
        } else System.out.println(max + " " + originalArr[maxIndex]);
        br.close();
    }


    public static int getGCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a=b;
            b=r;
        }
        return a;
    }
}
