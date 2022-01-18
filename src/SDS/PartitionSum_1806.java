package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PartitionSum_1806 {

    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        numbers = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }


        int start =0, end =0;
        int min = Integer.MAX_VALUE;
        int total =0;

        while (start <= n && end <= n) {
            if (total >= s && min > end - start) {
                min = end - start;
            }
            if (total < s) {
                total += numbers[end++];
            } else total -= numbers[start++];
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        }else System.out.println(min);
        br.close();
    }
}
