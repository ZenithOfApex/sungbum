package Naver;

import java.util.ArrayList;

public class Question01_1 {
    static String[][] keyboards = {};

    public int[] solution(String word){
        int[] answer = new int[2];

        int totalDistance = 0;
        int countDistance = 0;

        int nextPosX = 0;
        int nextPosY = 0;

        for(int k = 0; k< word.length()-1;k++){
            String firstChar = String.valueOf(word.charAt(k));
            String secondChar = String.valueOf(word.charAt(k + 1));

            ArrayList<Pos> firstTempPos = new ArrayList<>();
            ArrayList<Pos> tempPos = new ArrayList<>();

            int firstX = 0;
            int firstY = 0;
            boolean firstCheck = false;

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (keyboards[i][j].equals(secondChar)) {
                        firstTempPos.add(new Pos(i, j));
                        firstCheck = true;
                    } else continue;
                }
            }

//            if (nextPosX != 0 && nextPosY != 0) {
//                firstX = nextPosX;
//                firstY = nextPosY;
//            } else {
//                for (int i = 0; i < 10; i++) {
//                    for (int j = 0; j < 10; j++) {
//                        if (keyboards[i][j].equals(firstChar)) {
//                            firstX = i;
//                            firstY = j;
//                            firstCheck = true;
//                            break;
//                        }else continue;
//                    }
//                    if (firstCheck) {
//                        break;
//                    }
//                }
//            }

//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    if (keyboards[i][j].equals(firstChar)) {
//                        firstX = i;
//                        firstY = j;
//                        firstCheck = true;
//                        break;
//                    }else continue;
//                }
//                if (firstCheck) {
//                    break;
//                }
//            }

            //첫번째 문자 키보드에서 찾기


            //만약 첫번째 단어가 키보드에 없으면
            //거리값20으로 처리하고 전체 횟수 카운트 +1
            //그 다음에는 다시 처음처럼
            if (!firstCheck) {
                totalDistance += 20;
                countDistance++;
                continue;
            }

            boolean secondCheck = false;
            //두번째 문자 키보드에서 찾기(얘는 여러개일수 있음)
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (keyboards[i][j].equals(secondChar)) {
                        tempPos.add(new Pos(i, j));
                        secondCheck = true;
                    } else continue;
                }
            }

            if (!secondCheck) {
                totalDistance+=20;
                countDistance++;
                k = k+2;
            }

            int minDistance = Integer.MAX_VALUE;
            if (!tempPos.isEmpty() && !firstTempPos.isEmpty()) {
                for (Pos firstPos : firstTempPos) {
                    for (Pos pos : tempPos) {
                        int tempDistance = getDistance(pos.x, pos.y, firstPos.x, firstPos.y);
                        minDistance = Math.min(minDistance, tempDistance);
                    }
                    totalDistance += minDistance;
                }
            }
            countDistance++;
        }


        answer[0] = totalDistance;
        answer[1] = countDistance;

        return answer;
    }

    private static int getDistance(int i, int j, int a, int b) {
        return (Math.abs(i - a) + Math.abs(j - b));
    }

    private static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
