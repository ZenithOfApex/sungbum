package pathFinder;

import java.util.ArrayList;

public class ExtraOrdinaryString {
    public static void main(String[] args) {
//        String quest = "nrqqigtqph";
        String quest = "abcd";
        int answer = countSubStrings(quest);
        System.out.println(answer);

    }

    public static int countSubStrings(String input_str){
        int answer=0;//전체 결과 수 반환
        int[] charWeight = new int[input_str.length()];
        for (int i = 0; i < charWeight.length; i++) {
            charWeight[i] = checkMap(input_str.charAt(i));
        }
        for (int i : charWeight) {
            System.out.println("i = " + i);
        }

        for (int size = 1; size <= input_str.length(); size++) {
            for (int index = 0; index + size <= input_str.length(); index++) {
                int sum =0;
                for (int i = index; i < index + size; i++) {
                    sum+=charWeight[i];
                }
                System.out.println("sum = " + sum);
                if (sum % size == 0) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static int checkMap(char c){
        int checkTarget = (int)c;

        if (c >= 97 && c <= 98) {
            return 1;
        } else if (c >= 99 && c <= 101) {
            return 2;
        } else if (c >= 102 && c <= 104) {
            return 3;
        } else if (c >= 105 && c <= 107) {
            return 4;
        } else if (c >= 108 && c <= 110) {
            return 5;
        } else if (c >= 111 && c <= 113) {
            return 6;
        } else if (c >= 114 && c <= 116) {
            return 7;
        } else if (c >= 117 && c <= 119) {
            return 8;
        } else {
            return 9;
        }
    }
}
