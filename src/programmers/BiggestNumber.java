package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BiggestNumber {
    public static void main(String[] args) {
//        int[] numbers = {6, 10, 2};
        int[] numbers = {3, 34, 30, 5, 9};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {
        String answer = "";
        int numLength = numbers.length;
        String[][] oneAndOrigin = new String[numLength][2];

        for (int i = 0; i < numbers.length; i++) {
            oneAndOrigin[i][0] = String.valueOf(String.valueOf(numbers[i]).charAt(0));
            oneAndOrigin[i][1] = String.valueOf(numbers[i]);
        }

        for (String[] strings : oneAndOrigin) {
            System.out.println(strings[0]);
            System.out.println(strings[1]);
            System.out.println();
        }

        //3, 34, 30 중 sort시 우선순위는 34, 3, 30
        Arrays.sort(oneAndOrigin, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (Integer.parseInt(o1[0]) > Integer.parseInt(o2[0])) {
                    return 1;
                } else if (Integer.parseInt(o1[0]) < Integer.parseInt(o2[0])) {
                    return -1;
                }else{
                    if (comparePosition(o1[1], o2[1])) {
                        return 1;
                    }else return -1;
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = oneAndOrigin.length - 1; i >= 0; i--) {
            sb.append(oneAndOrigin[i][1]);
        }
        System.out.println(sb.toString());
//
        System.out.println("midline");
        for (String[] strings : oneAndOrigin) {
            System.out.println(strings[0]);
            System.out.println(strings[1]);
            System.out.println();
        }

        return answer;
    }

    //3. 30 처리
    public static boolean comparePosition(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());

        int i=1;
        while (i<len) {
            if (s1.length() == 1) {
                if (Integer.parseInt(String.valueOf(s1.charAt(i - 1))) > Integer.parseInt(String.valueOf(s2.charAt(i)))) {
                    return true;
                }else return false;
            }else{
                if (s1.charAt(i) == s2.charAt(i)) {
                    i++;
                    continue;
                } else if (Integer.parseInt(String.valueOf(s1.charAt(i))) > Integer.parseInt(String.valueOf(s2.charAt(i)))) {
                    return true;
                }else return false;
            }
        }

        return false;
    }
}
