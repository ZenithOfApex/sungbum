package BaekJoon3;

import java.io.*;
import java.util.StringTokenizer;

public class ColorPaper_2563 {

    static int[][] map = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int squareNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < squareNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int xDistance = Integer.parseInt(st.nextToken());
            int yDistance = Integer.parseInt(st.nextToken());
            checkingArea(xDistance, yDistance);
        }

        int answer = getArea();

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getArea() {
        int count = 0;
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                if (map[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void checkingArea(int xDistance, int yDistance) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[xDistance + i][yDistance + j] = 1;
            }
        }
    }
}
