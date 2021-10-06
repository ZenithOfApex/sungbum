package BaekJoon;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class alphabet_1987 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visit;
    static String[][] map;
    static int r,c;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        sc.nextLine();

        map = new String[r][c];

        for (int i = 0; i < r; i++) {
            String temp = sc.nextLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = String.valueOf(temp.charAt(j));
            }
        }
        count=0;
        //상하좌우 시작이 매번 다른 결과
        for (int i = 0; i < 4; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(map[0][0]);//처음 시작 지점 추가
            visit = new boolean[r][c];
            int newX = 0+dx[i];
            int newY = 0+dy[i];
            if (newX >= 0 && newY >= 0 && newX < r && newY < c) {
                dfs(newX, newY, sb);
            }
            System.out.println(sb);
        }
    }

    private static void dfs(int x, int y,StringBuilder sb) {
        sb.append(map[x][y]);
        visit[x][y] = true;

        String str = String.valueOf(sb);

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newY >= 0 && newX < r && newY < c) {
                if (!str.contains(map[newX][newY]) && !visit[newX][newY]) {
                    dfs(newX,newY,sb);
                }
            }
        }
    }

}
