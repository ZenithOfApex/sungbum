package line;

public class SummerInternship22_Problem02 {
    static int answer;
    static int[] dp;

    public static void main(String[] args) {
//        int n = 4;
//        int[] times = {2, 3};
        int n = 5;
        int[] times = {2, 4, 5};
        answer = solution(n, times);
        System.out.println(answer);

    }

    public static int solution(int n, int[] times) {
        dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            cuttingLine(i, times);
        }

        answer = dp[n];
        return answer;
    }

    private static void cuttingLine(int index, int[] times){
        if (index % 2 == 0) {
            dp[index] = Math.min(dp[index - 1] + times[0], dp[index/2] + times[(index / 2) -1]);
        } else {
            dp[index] = dp[index - 1] + times[0];
        }
    }
}
