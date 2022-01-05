package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class BiggestNumber2 {
    public static void main(String[] args) {
//        int[] numbers = {6, 10, 2};
        int[] numbers = {3, 34, 30, 5, 9};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {
        String answer = "";

        String[] str = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });

        if(str[0].equals("0")) return "0";

        for (String s : str) {
            answer += s;
        }

        return answer;
    }
}
