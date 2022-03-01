package BaekJoon3;

import java.io.*;
import java.util.StringTokenizer;

public class HeightSequence2_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //문제에서 주어진대로 입력을 받고
        boolean[][] arr = new boolean[N + 1][N + 1];
        //문제에서 주어진 반대로 입력을 받는다
        boolean[][] reverseArr = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = true;
            reverseArr[b][a] = true;
        }

        //정상적인 일반 순서에 대한 플로이드 워셜(boolean 타입으로 진행)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][k] && arr[k][j]) {
                        arr[i][j] = true;
                    }
                }
            }
        }
        //반대 방향 순서에 대한 플로이드 워셜(boolean 타입으로 진행)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (reverseArr[i][k] && reverseArr[k][j]) {
                        reverseArr[i][j] = true;
                    }
                }
            }
        }

        //특정 학생에 대하여 키가 큰 사람과 작은 학생 모두를 파악 가능
        //or 연산을 취한 결과가 false라면 그 학생과 키 비교를 할 수 없다는 의미
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] |= reverseArr[i][j];
            }
        }

        int answer = 0;
        outer:
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                //자기 자신에 대한 판단은 제외
                if (i == j) {
                    continue;
                }
                //키 비교를 할 수 없는 학생이 존재한다면 continue
                if (!arr[i][j]) {
                    continue outer;
                }
            }
            answer++;
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
