package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LineUp_2631 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        int[] lis = new int[n];
        int max=0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        //최장 증가 수열
        lis[0] = 1;
        for (int i = 1; i < n; i++) {
            lis[i]=1;
            for (int j = 0; j < i; j++) {
                if (nums[i] >= nums[j] && lis[j] + 1 > lis[i]) {//i번째 원소까지의 최장 길이 lis[i]는 이전까지 원소의 최장길이 +1
                    lis[i] = lis[j] + 1;
                }
                max = Math.max(max, lis[i]);
            }
        }

        System.out.println(n - max);
    }
}
