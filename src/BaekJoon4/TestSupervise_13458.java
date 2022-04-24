package BaekJoon4;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class TestSupervise_13458 {
    static int N, B, C;
    static long answer = 0;
    static int[] tester;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tester = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            tester[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solution();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        answer+=N;

        for (int i = 0; i < N; i++) {
            int testNum = tester[i];
            testNum = testNum - B;
            if(testNum<=0) continue;
            if (testNum % C == 0) {
                answer+=testNum/C;
            } else answer += (testNum / C) + 1;
        }
    }
}
