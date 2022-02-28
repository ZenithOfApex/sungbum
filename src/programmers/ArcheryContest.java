package programmers;

import java.io.IOException;

public class ArcheryContest {

    static int answerDiff = Integer.MIN_VALUE;
    static int apeachScore = Integer.MIN_VALUE;
    static boolean[] visited = new boolean[11];
    static int[] lionScore = new int[11];

    public static void main(String[] args) throws IOException {
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] answer = solution(5, info);
        apeachScore = Integer.MIN_VALUE;
        for (int i : lionScore) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int[] solution(int n, int[] info) {
        apeachScore = getScore(info);
        int[] tempCheck = new int[11];
        getMaximumValue(info, tempCheck, 0, 0, n);

        return lionScore;
    }

    //라이언의 가능한 모든 경우의 수 구한다.
    private static void getMaximumValue(int[] info, int[] checkArr, int index, int count, int targetCount) {
        if (count == targetCount) {
            //만들어진 조합에 대하여 점수 반환
            int tempScore = getScore(checkArr);
            int tempDiff = getScoreDiff(tempScore);
            if (tempDiff >= answerDiff) {
                answerDiff = tempDiff;
                lionScore = checkArr;
                System.arraycopy(checkArr, 0, lionScore, 0, 11);
            }
            for (int i : checkArr) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = index; i < 11; i++) {
            visited[i] = true;
            checkArr[i] = info[i] + 1;
            getMaximumValue(info, checkArr, index + 1, count + (checkArr[i]), targetCount);//여기서 count 차감수만큼 증가시켜서 넘겨야되고, visited도 처리해야한다.
            checkArr[i] = 0;
            visited[i] = false;
        }
    }

    //두 점수간의 차이를 구하는 함수
    private static int getScoreDiff(int x) {
        return Math.abs(apeachScore - x);
    }

    //주어진 배열에 대한 점수 구함
    private static int getScore(int[] score) {
        int totalScore = 0;
        for (int i = 0; i <= 10; i++) {
            if (score[i] != 0) {
                totalScore += (10 - i);
            }
        }
        return totalScore;
    }
}
