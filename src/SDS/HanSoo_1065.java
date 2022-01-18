package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HanSoo_1065 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        System.out.println(checkHanSoo(N));
        br.close();
    }

    public static int checkHanSoo(int n) {
        int count =0;
        for (int i = 1; i <= n; i++) {
            if (i < 100) {
                count++;
            }else{
                String strNum = String.valueOf(i);
                int diff = Integer.parseInt(String.valueOf(strNum.charAt(0))) - Integer.parseInt(String.valueOf(strNum.charAt(1)));
                boolean flag = true;
                for (int j = 1; j < strNum.length()-1; j++) {
                    if (Integer.parseInt(String.valueOf(strNum.charAt(j))) - Integer.parseInt(String.valueOf(strNum.charAt(j + 1)))==diff) {
                        continue;
                    }else flag = false;
                }
                if (flag) {
                    count++;
                }
            }
        }
        return count;
    }
}
