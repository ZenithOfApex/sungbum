package KaKaoCodingTest;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GettingPrimeNumberFromK {
    static int answer = 0;
    static ArrayList<Long> result;

    public static void main(String[] args) {
//        int n=110011;
//        int k=10;
        int n = 437674;
        int k=3;
        int result = solution(n, k);
        System.out.println(result);
    }

    public static int solution(int n, int k) {
        String conversedStr = conversion(n, k);

        StringTokenizer st = new StringTokenizer(conversedStr, "0");

        //test case#1 21.62
//        while (st.hasMoreTokens()) {
//            if (isPrime(Long.valueOf(st.nextToken()))) {
//                answer++;
//            }
//        }

        //test case#1 14.26
        String[] nums = conversedStr.split("0");

        for (String num : nums) {
            if (num.equals("")) continue;
            if (isPrime(Long.valueOf(num))) {
                answer++;
            }
        }

        return answer;

//        StringTokenizer st = new StringTokenizer(conversedStr, "0");
//        while (st.hasMoreTokens()) {
//            String target = st.nextToken();
//            Long targetNum = Long.parseLong(target);
//            if (targetNum>=2 && isPrime(targetNum)) {
//                answer++;
//            }
//            if (targetNum >= 2) {
//                result.add(targetNum);
//            }
        }

    private static boolean isPrime(Long num) {
        if(num==1) return false;
        for (int i = 2; i<= (int)Math.sqrt(num); i++) {
            if(num%i==0) return false;
        }
        return true;
    }

    private static String conversion(int number, int n){
        StringBuilder sb = new StringBuilder();
        while (number >= 1) {
            sb.insert(0, number % n);
            number/=n;
        }
        return sb.toString();
    }
}
