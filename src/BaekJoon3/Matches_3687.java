package BaekJoon3;

import java.io.*;
import java.util.Arrays;

public class Matches_3687 {
    static long minValue;
    static String maxValue;
    static int T;
    static long[] minDp = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2]=1;
        minDp[3]=7;
        minDp[4]=4;
        minDp[5]=2;
        minDp[6]=6;
        minDp[7]=8;
        minDp[8]=10;

        String[] add = {"1", "7", "4", "2", "0", "8"};

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String line = ""+ minDp[i - j] + add[j - 2];
                minDp[i] = Math.min(Long.parseLong(line), minDp[i]);
            }
        }

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            minValue = 0;
            maxValue = "";

            minValue = getMinimumValue(n);
            maxValue = getMaximumValue(n);
            bw.write(minValue+" "+maxValue + "\n");
        }


        bw.flush();
        bw.close();
        br.close();
    }

    private static String getMaximumValue(int n) {
        int mok = 0;
        StringBuilder sb = new StringBuilder();
        if (n % 2 != 0) {
            mok = (n/2)-1;
            sb.append(7);
            for (int i = 0; i < mok; i++) {
                sb.append(1);
            }
            return sb.toString();
        } else {
            mok = n/2;
            for (int i = 0; i < mok; i++) {
                sb.append(1);
            }
            return sb.toString();
        }
    }

    private static long getMinimumValue(int n) {
        return minDp[n];
    }
}
