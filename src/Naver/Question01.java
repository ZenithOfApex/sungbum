package Naver;

import java.util.ArrayList;

public class Question01 {
    static String[][] keyboards = {};
    public static void main(String[] args) {

    }

    private static int[] solution(String word) {
        int[] answer = {};

        ArrayList<Pos> tempPos = new ArrayList<>();

        int totalDistance = 0;
        int countDistance = 0;

        for (int k = 0; k < word.length()-1; k++) {
            String targetStr = String.valueOf(word.charAt(k));
            String nextTargetStr = String.valueOf(word.charAt(k + 1));

            int curX = 0;
            int curY = 0;

            int nextX = 0;
            int nextY = 0;

            boolean firstCheck = false;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (keyboards[i][j].equals(targetStr)) {
                        curX = i;
                        curY = j;
                        firstCheck = true;
                    } else continue;
                }
            }

            boolean secondCheck = false;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (keyboards[i][j].equals(nextTargetStr)) {
                        nextX = i;
                        nextY = j;
                        tempPos.add(new Pos(nextX, nextY));
                        secondCheck = true;
                    } else continue;
                }
            }

            //뒤에 보는 글자가 없는 경우
            if (!(firstCheck || secondCheck)) {

            } else {
                countDistance++;
            }

            int minDistance = Integer.MAX_VALUE;

            for (Pos pos : tempPos) {
                int tempDistance = getDistance(pos.x, pos.y, curX, curY);
                minDistance = Math.min(minDistance, tempDistance);
            }

            totalDistance += minDistance;

        }



        return answer;
    }

    private static int getDistance(int i, int j, int a, int b) {
        return Math.abs(i - a) + Math.abs(j - b);
    }

    private static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
