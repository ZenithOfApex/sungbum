
package programmers;

import java.util.Arrays;

public class FailRatio {
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] answer = solution(N, stages);
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, int[] stages){//N+1이여서 0인 경우랑, 해당 스테이지에 머무르는 사람 없어서 0인 경우 구분 필요
        int[] answer = {};
        double[] result = new double[n];//stage-ratio 결과
        int curCnt=stages.length;//ratio 분모

        for (int i = 1; i <= n; i++) {//stage별 측정
            int cnt = 0;//해당 스테이지 개수
            double ratio=0;//실패 ratio
            for (int index = 0; index < stages.length; index++) {
                if (stages[index] == i) {
                    cnt++;
                }
            }
            ratio = (double) i/curCnt;
            result[i-1] = ratio;
            curCnt -=cnt;
        }

        Arrays.sort(result);


        return answer;
    }

}


