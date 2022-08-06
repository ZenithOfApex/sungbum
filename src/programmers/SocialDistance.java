package programmers;

import java.util.ArrayList;

public class SocialDistance {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Pos> personLocation = new ArrayList<>();

    public static void main(String[] args) {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] output = solution(places);
        for (int i : output) {
            System.out.println("i = " + i);
        }
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int t = 0; t < places.length; t++) {
            String[] map = places[t];
            boolean flag = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i].charAt(j) == 'P') {
                        if(isAroundExistPerson(i,j,map)){
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    answer[t] = 0;
                    break;
                }
            }
            if (!flag) {
                answer[t] = 1;
            }
        }
        return answer;
    }

    private static boolean isAroundExistPerson(int i, int j, String[] map) {
        int[] mi = {0, 0, 1, -1};
        int[] mj = {1, -1, 0, 0};
        for (int d = 0; d < 4; d++) {
            int ni = i + mi[d];
            int nj = j + mj[d];
            if(ni<0 || ni>=5 || nj<0 || nj>=5) continue;
            if(map[ni].charAt(nj)=='P') return true;
        }

        // 상하좌우 맨하탄 거리 2 확인
        int[] mi2 = {0, 0, 2, -2};
        int[] mj2 = {2, -2, 0, 0};
        for (int d = 0; d < 4; d++) {
            int ni = i + mi2[d];
            int nj = j + mj2[d];
            if(ni<0 || ni>=5 || nj<0 || nj>=5) continue;
            if(map[ni].charAt(nj)=='P') {
                if(map[(i+ni)/2].charAt((j+nj)/2)!='X'){
                    return true;
                }
            }
        }

        // 대각선 확인
        int[] mi3 = {1, 1, -1, -1};
        int[] mj3 = {1, -1, 1, -1};

        for (int d = 0; d < 4; d++) {
            int ni = i + mi3[d];
            int nj = j + mj3[d];
            if(ni<0 || ni>=5 || nj<0 || nj>=5) continue;
            if(map[ni].charAt(nj)=='P') {
                if(!(map[i].charAt(nj)=='X' && map[ni].charAt(j)=='X')){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 5 && y < 5;
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
