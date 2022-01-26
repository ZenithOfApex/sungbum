package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AandB2_12919 {
    static StringBuilder sb;
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        if (operation(T)) {
            System.out.println(1);
        }else System.out.println(0);


        br.close();
    }

    static boolean operation(String str) {
        if (str.length() == S.length()) {
            if(str.equals(S)){
                return true;
            }
            return false;
        }
        if (str.charAt(str.length() - 1) == 'A') {
            if (operation(str.substring(0, str.length() - 1))) {
                return true;
            }
        }
        if (str.charAt(0) == 'B') {
            sb = new StringBuilder();
            sb.append(str.substring(1, str.length()));
            if (operation(sb.reverse().toString())) {
                return true;
            }
        }
        return false;
    }
}
