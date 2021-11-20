
package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class FailRatio {
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] answer = solution(N, stages);
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, int[] stages){//N+1이여서 0인 경우랑, 해당 스테이지에 머무르는 사람 없어서 0인 경우 구분 필요 if there is bigger num in array
        int[] answer = new int[n];
        double[][] result = new double[n][2];//stage-ratio 결과
        int curCnt=stages.length;//ratio 분모

        for (int i = 1; i <= n; i++) {//stage별 측정
            int cnt = 0;//해당 스테이지 개수
            double ratio=0;//실패 ratio
            for (int index = 0; index < stages.length; index++) {
                if (stages[index] == i) {
                    cnt++;
                }
            }
            ratio = (double) cnt/curCnt;
            result[i-1][0]=i;
            result[i-1][1] = ratio;
            curCnt -=cnt;
        }
        //각 stage 별 실패율이 담기게 되는데 이를 순서에 맞게 정렬 - Collections.sort(result, Comparable<>)

        Arrays.sort(result, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                if (o1[1] == o2[1]) {//실패율이 같은 경우에는
                    return Double.compare(o2[0], o1[0]);
//                    return Double.compare(o1[0], o2[0]);
                }
                return Double.compare(o1[1],o2[1]);
            }
        });

//        for (int i = 0; i < n; i++) {
//            System.out.println(result[i][0]);
//            System.out.println(result[i][1]);
//            System.out.println();
//        }

        for (int i = 0; i < n; i++) {
            answer[i] = (int)result[n-i-1][0];
        }

        return answer;
    }

}


