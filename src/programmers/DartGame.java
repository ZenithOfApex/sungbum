package programmers;

import java.util.ArrayList;

public class DartGame {
    public static int solution(String dartResult) {
        int answer = 0;
        int[] score = new int[3];
        String tempString = "";
        int tempNum = 0;
        int idx = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);

            if (c >= '0' && c <= '9') {
                tempString += String.valueOf(c);
            } else if (c == 'S' || c == 'D' || c == 'T') {
                tempNum = Integer.parseInt(tempString);
                if (c == 'S') {
                    tempNum = (int) Math.pow(tempNum, 1);
                } else if (c == 'D') {
                    tempNum = (int) Math.pow(tempNum, 2);
                } else {
                    tempNum = (int) Math.pow(tempNum, 3);
                }
                score[idx++] = tempNum;
                tempString = "";
            } else {
                if (c == '#') {
                    score[idx - 1] *= -1;
                } else {
                    score[idx - 1] *= 2;
                    if (idx - 2 >= 0) {
                        score[idx - 2] *= 2;
                    }
                }
            }
        }

        for (int i = 0; i < score.length; i++) {
            answer += score[i];
        }
        return answer;
    }
}
