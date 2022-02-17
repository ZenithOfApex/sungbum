package BaekJoon3;

import java.io.*;
import java.util.StringTokenizer;

public class Gear_14891 {
    static int K, answer;
    static int[][] gear = new int[4][8];//톱니바퀴는 0,1,2,3 번호, 톱니는 12시부터 0,1,2,,,7
    static int[][] changedTempGear = new int[4][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int gearNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            changedTempGear = gear;
            solution(gearNum-1, direction);
            gear = changedTempGear;
        }

        for (int[] ints : gear) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }

        //최종 점수 반환하기
        getFinalScore();
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution(int gearNum, int direction) {
        if (gearNum == 0) {//0회전할거야
            if (direction == -1) {//반시계면
                if (gear[0][2] != gear[1][6]) {//0오와 1왼
                    //반대방향으로 회전
                    rotateClockWise(1);
                    if (gear[1][2] != gear[2][6]) {//1오와 2왼
                        rotateCounterClockWise(2);
                        if (gear[2][2] != gear[3][6]) {//2오와 3왼
                            rotateClockWise(3);
                        }
                    }
                }
                rotateCounterClockWise(0);
            } else {
                if (gear[0][2] != gear[1][6]) {
                    //반대방향으로 회전
                    rotateCounterClockWise(1);
                    if (gear[1][2] != gear[2][6]) {
                        rotateClockWise(2);
                        if (gear[2][2] != gear[3][6]) {
                            rotateCounterClockWise(3);
                        }
                    }
                }
                rotateClockWise(0);
            }
        } else if (gearNum == 1) {//2번 기어가 회전하는 경우 1,3 기어가 회전해야 하고 3번 기어가 회전하는 경우에는 2,4기어가 회전해야 한다.
            //극 확인해서
            if (direction == -1) {
                if (gear[1][6] != gear[0][2]) {
                    rotateClockWise(0);
                }
                if (gear[1][2] != gear[2][6]) {
                    rotateClockWise(2);
                    if (gear[2][2] != gear[3][6]) {
                        rotateCounterClockWise(3);
                    }
                }
                rotateCounterClockWise(1);
            } else {
                if (gear[1][6] != gear[0][2]) {
                    rotateCounterClockWise(0);
                }
                if (gear[1][2] != gear[2][6]) {
                    rotateCounterClockWise(2);
                    if (gear[2][2] != gear[3][6]) {
                        rotateClockWise(3);
                    }
                }
                rotateClockWise(1);
            }

        } else if (gearNum == 2) {
            if (direction == -1) {
                if (gear[2][2] != gear[3][6]) {
                    rotateClockWise(3);
                }
                if (gear[2][6] != gear[1][2]) {
                    rotateClockWise(1);
                    if (gear[1][6] != gear[0][2]) {
                        rotateCounterClockWise(0);
                    }
                }
                rotateCounterClockWise(2);
            } else {
                if (gear[2][2] != gear[3][6]) {
                    rotateCounterClockWise(3);
                }
                if (gear[2][6] != gear[1][2]) {
                    rotateCounterClockWise(1);
                    if (gear[1][6] != gear[0][2]) {
                        rotateClockWise(0);
                    }
                }
                rotateClockWise(2);
            }
        } else if (gearNum == 3) {//4번 기어가 회전하는 경우 3번 기어만 회전하면 된다.
            if (direction == -1) {
                if (gear[2][2] != gear[3][6]) {//1번과 2번 관계 확인
                    //반대방향으로 회전
                    rotateClockWise(2);
                    if (gear[1][2] != gear[2][6]) {
                        rotateCounterClockWise(1);
                        if (gear[1][6] != gear[0][2]) {
                            rotateClockWise(0);
                        }
                    }
                }
                rotateCounterClockWise(3);
            } else {
                if (gear[2][2] != gear[3][6]) {//1번과 2번 관계 확인
                    //반대방향으로 회전
                    rotateCounterClockWise(2);
                    if (gear[1][2] != gear[2][6]) {
                        rotateClockWise(1);
                        if (gear[1][6] != gear[0][2]) {
                            rotateCounterClockWise(0);
                        }
                    }
                }
                rotateClockWise(3);
            }
        }
    }

    private static void rotateClockWise(int gearNum) {
        int temp = changedTempGear[gearNum][7];
        for (int i = 7; i > 0; i--) {
            changedTempGear[gearNum][i] = changedTempGear[gearNum][i-1];
        }
        changedTempGear[gearNum][0] = temp;
    }

    private static void rotateCounterClockWise(int gearNum) {
        int temp = changedTempGear[gearNum][0];
        for (int i = 0; i < 7; i++) {
            changedTempGear[gearNum][i] = changedTempGear[gearNum][i + 1];
        }
        changedTempGear[gearNum][7] = temp;
    }

    private static void getFinalScore() {
        for (int i = 0; i < 4; i++) {
            if (i == 0 && gear[i][0] == 1) {
                answer += 1;
            } else if (i == 1 && gear[i][0] == 1) {
                answer += 2;
            }else if (i == 2 && gear[i][0] == 1) {
                answer += 4;
            }else if (i == 3 && gear[i][0] == 1) {
                answer += 8;
            }
        }
    }
}
