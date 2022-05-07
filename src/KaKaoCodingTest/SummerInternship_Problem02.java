package KaKaoCodingTest;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class SummerInternship_Problem02 {
    static BigInteger answer = new BigInteger("0");

    public static void main(String[] args) {
//        int[] queue1 = {3, 2, 7, 2};
//        int[] queue2 = {4, 6, 5, 1};

        int[] queue1 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] queue2 = {10, 1};

//        int[] queue1 = {1, 2, 1, 2};
//        int[] queue2 = {1, 10, 1, 2};
//
//        int[] queue1 = {1,1};
//        int[] queue2 = {1,5};

//        int[] queue1 = {2,2,2,2,2};
//        int[] queue2 = {8};
        System.out.println(solution(queue1, queue2));
    }

    private static BigInteger solution(int[] queue1, int[] queue2){
        int totalSum = 0;
        int sum1 = 0;
        int sum2 = 0;

        for (int i : queue1) {
            totalSum += i;
            sum1 += i;
        }
        for (int i : queue2) {
            totalSum += i;
            sum2 += i;
        }

        if (totalSum % 2 != 0) {
            return BigInteger.valueOf(-1);
        }
        int half = totalSum / 2;


        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2= new LinkedList<>();

        for (int i : queue1) {
            q1.add(i);
        }
        for (int i : queue2) {
            q2.add(i);
        }

        int cnt = 0;
        while(sum1!=sum2){
            if (sum1 > half) {
                if (q1.isEmpty()) {
                    return BigInteger.valueOf(-1);
                }
                int temp = q1.poll();
                sum1 -= temp;
                q2.add(temp);
                sum2 += temp;
                cnt++;
            } else if(sum2 > half){
                if (q2.isEmpty()) {
                    return BigInteger.valueOf(-1);
                }
                int temp = q2.poll();
                sum2-=temp;
                q1.add(temp);
                sum1 += temp;
                cnt++;
            }
            if (cnt == queue1.length + queue2.length) {
                break;
            }
        }
        if (cnt == queue1.length + queue2.length) {
            answer = BigInteger.valueOf(-1);
        } else {
            answer = BigInteger.valueOf(cnt);
        }
        return answer;
    }
}
