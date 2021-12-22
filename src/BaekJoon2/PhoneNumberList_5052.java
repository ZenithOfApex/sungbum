package BaekJoon2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PhoneNumberList_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            String[] numbers = new String[n];

            for (int j = 0; j < n; j++) {
                numbers[j] = br.readLine();
            }
            Arrays.sort(numbers);
            if (checkCondition(n, numbers)) {
                bw.write("YES\n");
            }else{
                bw.write("NO\n");
            }

        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean checkCondition(int n, String[] arr) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1].startsWith(arr[i])) {
                return false;
            }
        }
        return true;
    }
}
