package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InsertInstructions_14888 {
    static int N;
    static int[] numbers;
    static int maxValue=Integer.MIN_VALUE, minValue=Integer.MAX_VALUE;
    static int[] instructionsCnt;
    static int[] instructions;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        instructions = new int[N-1];
        instructionsCnt = new int[4];

        visited = new boolean[N - 1];

        //숫자 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 입력(+,-,*,/)순
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            instructionsCnt[i] = Integer.parseInt(st.nextToken());
        }

        instructions = transferInstructionsToCount(instructionsCnt);

        permutation(0, 1);

//        int maxValue = getMaximumValue(numbers, instructions);
//        int minValue = getMinimumValue(numbers, instructions);

        bw.write(maxValue + "\n");
        bw.write(minValue + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] transferInstructionsToCount(int[] instructionsCnt) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < instructionsCnt[i]; j++) {
                tempList.add(i);
            }
        }

        int[] rtnCnt = new int[N - 1];

        for (int i = 0; i < N - 1; i++) {
            rtnCnt[i] = tempList.get(i);
        }

        return rtnCnt;
    }

    private static void permutation(int index, int depth) {
        if (depth == N - 1) {
            //최대값 최소값 체크
            for (int instruction : instructions) {
                System.out.print(instruction);
            }
            System.out.println();
            getMaxMinValue();
            return;
        }

        for (int i = index; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation(index + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void getMaxMinValue() {
        int temp = numbers[0];
        int number_index = 1;

        for (int i = 0; i < N - 1; i++) {
            if (visited[i]) {
                if (instructions[i] == 0) {//더하기 연산
                    temp = temp + numbers[number_index++];
                } else if (instructions[i] == 1) {
                    temp = temp - numbers[number_index++];
                } else if (instructions[i] == 2) {
                    temp = temp * numbers[number_index++];
                } else {
                    if (temp < 0) {
                        temp = temp * (-1);
                        temp = (temp / numbers[number_index++]) * (-1);
                    } else {
                        temp = temp / numbers[number_index++];
                    }
                }
            }
        }
        System.out.println(temp);
        maxValue = Math.max(maxValue, temp);
        minValue = Math.min(minValue, temp);
    }
}
