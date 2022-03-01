package programmers;

import java.io.IOException;

public class ArcheryContest {
    static int[] rtnArr = {-1};
    static int answerDiff = Integer.MIN_VALUE;
    static int apeachScore = Integer.MIN_VALUE;
    static int[] lionScore = new int[11];

    public static void main(String[] args) throws IOException {
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] answer = solution(5, info);
        apeachScore = Integer.MIN_VALUE;
    }

    private static int[] solution(int n, int[] info) {
        lionScore = new int[11];
        getMaximumValue(info, 1, n);
        return rtnArr;
    }

    //라이언의 가능한 모든 경우의 수 구한다.
    private static void getMaximumValue(int[] info, int index, int count) {
        if (index == count+1) {
            //만들어진 조합에 대하여 점수 반환
            int apeach_score = 0;
            int lion_score = 0;
            for (int i = 0; i <= 10; i++) {
                if (info[i] != 0 || lionScore[i] != 0) {
                    if (info[i] < lionScore[i]) {
                        lion_score += 10 - i;
                    } else {
                        apeach_score += 10 - i;
                    }
                }
            }
            if (lion_score > apeach_score) {
                if (lion_score - apeach_score >= answerDiff) {
                    rtnArr = lionScore.clone();
                    answerDiff = lion_score - apeach_score;
                }
            }
            return;
        }

        for (int i = 0; i < 11 && info[i] >= lionScore[i]; i++) {
            lionScore[i]++;
            getMaximumValue(info, index + 1, count);
            lionScore[i]--;
        }
    }
}
