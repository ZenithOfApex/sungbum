package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Palindrome2_10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] palindrome = new int[N];


        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            palindrome[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for (int index = s-1; index <e; index++) {
                sb.append(palindrome[index]);
            }
            String target = sb.toString();
            String revered = sb.reverse().toString();
            if (target.equals(revered)) {
                System.out.println(1);
            }else System.out.println(0);
        }
        br.close();
    }
}
