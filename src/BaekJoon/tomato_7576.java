package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class tomato_7576 {

    static int[][] box;
    static boolean[][] checked;
    static ArrayList<Position> subject;
    static int n;
    static int m;

    public static void main(String[] args) {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();//width
        n = sc.nextInt();//height
        sc.nextLine();
        box = new int[n][m];
        checked = new boolean[n][m];
        subject = new ArrayList<Position>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                box[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
        sc.close();
        //만약 checkFlow의 결과값이 true로 반환시 contaminate 계속 진행

        while (true) {
            boolean stop = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    boolean contaminated = false;
                    contaminate(i, j,contaminated,stop);
                    if (contaminated) {
                        answer++;
                    }
                }
            }
            if (stop) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {//전파 완료시에도 0이 존재하면 불가능한 경우임을 반환
            int[] temp = box[i];
            if (Arrays.stream(temp).anyMatch(s -> s == 0)) {
                answer = -1;
            }
        }


        System.out.println(answer);
    }

    public static void contaminate(int i, int j, boolean contaminated,boolean stop) {
        if (box[i][j] == 1) {
            System.out.print("i = " + i);
            System.out.println("j = " + j);
            if (i + 1 <box.length) {
                if (box[i + 1][j] == 0 && !checked[i + 1][j]) {
                    box[i + 1][j] = 1;
                    checked[i][j] = true;
                    contaminated = true;
                }//우 체크
            }
            if (i - 1 >= 0) {
                if (box[i - 1][j] == 0 && !checked[i - 1][j]) {
                    box[i - 1][j] = 1;
                    checked[i][j] = true;
                    contaminated = true;
                }
            }
            if (j + 1 < box[0].length) {
                if (box[i][j + 1] == 0 && !checked[i][j + 1]) {
                    box[i][j + 1] = 1;
                    checked[i][j] = true;
                    contaminated = true;
                }
            }
            if (j - 1 >= 0) {
                if (box[i][j - 1] == 0 && !checked[i][j - 1]) {
                    box[i][j - 1] = 1;
                    checked[i][j] = true;
                    contaminated = true;
                }
            }
            else{
                stop = true;
            }
//            if (i - 1 >= 0 && i + 1 < box.length && j - 1 >= 0 && j + 1 < box[0].length) {//범위 내에 존재할 시에만 진행
//                if (box[i + 1][j] == 0 && !checked[i + 1][j]) {
//                    box[i + 1][j] = 1;
//                    checked[i][j] = true;
//                    contaminated = true;
//                }//우 체크
//                else if (box[i - 1][j] == 0 && !checked[i - 1][j]) {
//                    box[i - 1][j] = 1;
//                    checked[i][j] = true;
//                    contaminated = true;
//                } else if (box[i][j + 1] == 0 && !checked[i][j + 1]) {
//                    box[i][j + 1] = 1;
//                    checked[i][j] = true;
//                    contaminated = true;
//                } else if (box[i][j - 1] == 0 && !checked[i][j - 1]) {
//                    box[i][j - 1] = 1;
//                    checked[i][j] = true;
//                    contaminated = true;
//                } else {
//                    stop = true;
//                }
//            }
        } else {
            System.out.println("passed");
        }
    }
}

class Position{
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}