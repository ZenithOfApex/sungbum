package KaKaoCodingTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecoTeam_Q1 {
    public static void main(String[] args) {
        List<Integer> stockprices = new ArrayList<>();
        stockprices.add(7);
        stockprices.add(1);
        stockprices.add(2);
        stockprices.add(3);
        stockprices.add(6);
        stockprices.add(9);
        stockprices.add(12);
        stockprices.add(3);
//        stockprices.add(7);
//        stockprices.add(3);
//        stockprices.add(8);
//        stockprices.add(7);
//        stockprices.add(1);
//        stockprices.add(2);
//        stockprices.add(3);
//        stockprices.add(6);
//        stockprices.add(1);
//        stockprices.add(2);
//        stockprices.add(3);


        int k = 2;
//        int k =6;
        System.out.println(solution(stockprices, k));
    }

    public static int solution(List<Integer> stockPrices, int k) {
        int answer = 0;
        int[] numbers = stockPrices.stream().mapToInt(i -> i).toArray();
        int[] dp = new int[numbers.length];
        dp[0] = 0;


        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else dp[i] = 0;
        }

        int targetCount = (int) Arrays.stream(dp).filter(s -> s == k-1).count();
        int maxNumber = Arrays.stream(dp).max().getAsInt();
        int temp = maxNumber;
        int maxNumberCount = (int) Arrays.stream(dp).filter(s -> s == temp).count();
        if (maxNumberCount > 1) {
            if (maxNumber > k - 1) {
                maxNumber -= (k - 2);
            }else{
                maxNumber=0;
            }
        }else{
            if (maxNumber > k - 1) {
                targetCount--;
                maxNumber -= (k - 2);
            }else{
                maxNumber=0;
            }
        }
        answer = targetCount +maxNumber;
        return answer;
    }
}
