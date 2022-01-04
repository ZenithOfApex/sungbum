package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoodSequence_2661 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        backTracking("");//처음에는 빈 공백으로 시작
    }

    private static void backTracking(String result) {
        if (result.length() == N) {//입력받은 크기만큼 완성되었다면 출력 및 종료
            System.out.println(result);
            System.exit(0);
        }else{//종료 조건 미충족시 수열 만들어나가야 함
            for (int i = 1; i <= 3; i++) {//작은 정수를 위해 1부터 넣는 형식으로
                if (isGoodSequence(result + i)) {//좋은 수열인지 체크
                    backTracking(result + i);
                }
            }
        }
    }

    private static boolean isGoodSequence(String s) {//길이의 절반까지만 보고 좋은 수열인지 체크
        int length = s.length() / 2;

        for (int i = 1; i <= length; i++) {
            if (s.substring(s.length() - i).equals(s.substring(s.length() - 2 * i, s.length() - i))) {//반 토막내서 반복되는지 확인
                return false;
            }
        }
        return true;
    }
}
