package BaekJoon;

import java.util.Scanner;

public class sleep_1652 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[][] map = new String[n][n];
        String[][] transposedMap = new String[n][n];
        int garo=0;
        int saero=0;

        for (int i = 0; i < n; i++) {
            String str= sc.nextLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = String.valueOf(str.charAt(j));
            }
        }
        sc.close();
        garo = checkPlace(map);
        transposedMap = transpose(map);
        saero = checkPlace(transposedMap);

        System.out.printf("%d %d", garo, saero);
    }

    public static int checkPlace(String[][] map) {
        int cnt =0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                sb.append(map[i][j]);
            }
            String[] temp = String.valueOf(sb).split("X");
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].contains(".") && temp[j].length() >= 2) {
                    cnt++;
                }else continue;
            }
            sb.setLength(0);
        }


        return cnt;
    }

    public static String[][] transpose(String[][] map){
        String[][] transposed = new String[map.length][map.length];
        for(int i=0;i<map.length;i++){
            for (int j = 0; j < map.length; j++) {
                transposed[i][j] = map[j][i];
            }
        }
        return transposed;
    }
}
