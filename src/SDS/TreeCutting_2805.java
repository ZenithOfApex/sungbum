package SDS;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TreeCutting_2805 {

    static int[] wood;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        wood = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        int max = 0;
        int min = 0;
        for (int i = 0; i < n; i++) {
            int inputNum = Integer.parseInt(st.nextToken());
            max = Math.max(inputNum, max);
            wood[i] = inputNum;
        }
        Arrays.sort(wood);

        while (min < max) {
            int mid = (min + max) / 2;
            long sum =0;

            for (int i : wood) {
                if (i - mid > 0) {
                    sum += i - mid;
                }
            }

            if (sum < m) {
                max = mid;
            } else {
                min = mid+1;
            }
        }
        System.out.println(min-1);

        br.close();
    }
}
