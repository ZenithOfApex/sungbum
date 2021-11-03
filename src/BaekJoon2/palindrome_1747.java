package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class palindrome_1747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        br.close();
        for (int i = n; i < 1004000; i++) {
            if (checkPalindrome(i) && checkPrimeNum(i)) {
                System.out.println(i);
                break;
            }
        }

    }

    private static boolean checkPalindrome(int num) {
        boolean palindrome = true;
        String checkString = String.valueOf(num);
        if (num % 2 == 0) {
            for (int i = 0; i <= checkString.length() / 2; i++) {
                if (checkString.charAt(i) != checkString.charAt(checkString.length() - 1- i)) {
                    palindrome = false;
                    break;
                }
            }
        }else{
            for (int i = 0; i < checkString.length() / 2; i++) {
                if (checkString.charAt(i) != checkString.charAt(checkString.length() - 1- i)) {
                    palindrome = false;
                    break;
                }
            }
        }
        return palindrome;
    }

    public static boolean checkPrimeNum(int n) {
        boolean result = true;
        int end = (int)Math.sqrt(n);
        if (n == 1) {
            result = false;
        }
        for(int i = 2; i <= end; i++) {
            if(n%i == 0) {
                result = false;
                break;
            } else {
                result = true;
            }
        }
        return result;

    }
}
