package BaekJoon4;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Observation_15683 {

    static int answer;
    static int N, M;
    static final int cameraChecked = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<Camera> cameraList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int inputValue = Integer.parseInt(st.nextToken());
                if (inputValue > 0 && inputValue < 6) {
                    cameraList.add(new Camera(i, j, inputValue));
                }
                map[i][j] = inputValue;
            }
        }
        Collections.sort(cameraList);

        for (Camera camera : cameraList) {
            if (camera.cameraNum == 1) {
                for (int i = 0; i < 4; i++) {
                    activateCameraNumberOne();
                }
            }
        }

        //사각지대 최소가 되는 경우 사각지대의 갯수 반환
        answer = Math.min(answer, getBlindSpot());

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void activateCameraNumberOne(int x, int y,int[][] checkMap, int caseNum) {
        if (caseNum == 0) {
            //카메라가 우측 방향을 보는 경우
            checkRight(x, y, checkMap);
        } else if (caseNum == 1) {
            //카메라가 상단을 보는 경우
            checkTop(x, y, checkMap);
        } else if (caseNum == 2) {
            //카메라가 좌츨을 보는 경우
            checkLeft(x, y, checkMap);
        } else {
            //카메라가 하단을 보는 경우
            checkBottom(x, y, checkMap);
        }
    }

    private static void activateCameraNumberTwo(int x, int y, int[][] checkMap,int caseNum) {
        if (caseNum == 0) {
            //카메라가 좌우측을 보는 경우
            checkLeft(x, y, checkMap);
            checkRight(x, y, checkMap);
        } else {
            //카메라가 상하단을 보는 경우
            checkTop(x, y, checkMap);
            checkBottom(x, y, checkMap);
        }
    }

    private static void activateCameraNumberThree(int x, int y, int[][] checkMap, int caseNum) {
        if (caseNum == 0) {
            //카메라가 상우 방향을 보는 경우
            checkTop(x, y, checkMap);
            checkRight(x, y, checkMap);
        } else if (caseNum == 1) {
            //카메라가 상좌 방향을 보는 경우
            checkTop(x, y, checkMap);
            checkLeft(x, y, checkMap);
        } else if (caseNum == 2) {
            //카메라가 좌하 방향을 보는 경우
            checkLeft(x, y, checkMap);
            checkBottom(x, y, checkMap);
        } else if (caseNum == 3) {
            //카메라가 우하 방향을 보는 경우
            checkRight(x, y, checkMap);
            checkBottom(x, y, checkMap);
        }
    }
    private static void activateCameraNumberFour(int x, int y, int[][] checkMap,int caseNum) {
        if (caseNum == 0) {
            //카메라가 좌우상 방향을 보는 경우
            checkTop(x, y, checkMap);
            checkLeft(x, y, checkMap);
            checkRight(x, y, checkMap);
        } else if (caseNum == 1) {
            //카메라가 좌우하 방향을 보는 경우
            checkLeft(x, y, checkMap);
            checkRight(x, y, checkMap);
            checkBottom(x, y, checkMap);
        } else if (caseNum == 2) {
            //카메라가 좌상하 방향을 보는 경우
            checkTop(x, y, checkMap);
            checkLeft(x, y, checkMap);
            checkBottom(x, y, checkMap);
        } else {
            //카메라가 우상하 방향을 보는 경우
            checkTop(x, y, checkMap);
            checkRight(x, y, checkMap);
            checkBottom(x, y, checkMap);
        }
    }

    private static void activateCameraNumberFive(int x, int y, int[][] checkMap) {
        //카메라가 상하좌우 보는 경우 하나
        checkTop(x, y, checkMap);
        checkLeft(x, y, checkMap);
        checkRight(x, y, checkMap);
        checkBottom(x, y, checkMap);
    }

    private static int[][] checkRight(int x, int y, int[][] checkMap) {
        for (int j = y; j < M; j++) {
            if (map[x][j] == 6) {
                break;
            } else {
                checkMap[x][j] = cameraChecked;
            }
        }
        return checkMap;
    }

    private static int[][] checkLeft(int x, int y, int[][] checkMap) {
        for (int j = y; j >=0; j--) {
            if (map[x][j] == 6) {
                break;
            } else {
                checkMap[x][j] = cameraChecked;
            }
        }
        return checkMap;
    }

    private static int[][] checkTop(int x, int y, int[][] checkMap) {
        for (int i = x; i >=0; i--) {
            if (map[i][y] == 6) {
                break;
            } else {
                checkMap[i][y] = cameraChecked;
            }
        }
        return checkMap;
    }

    private static int[][] checkBottom(int x, int y, int[][] checkMap) {
        for (int i = x; i < M; i++) {
            if (map[i][y] == 6) {
                break;
            } else {
                checkMap[i][y] = cameraChecked;
            }
        }
        return checkMap;
    }

    //사각지대의 갯수 구하기
    private static int getBlindSpot() {
        int rtnValue = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    rtnValue++;
                }
            }
        }
        return rtnValue;
    }

    static class Camera implements Comparable<Camera>{
        int x;
        int y;
        int cameraNum;

        public Camera(int x, int y, int cameraNum) {
            this.x = x;
            this.y = y;
            this.cameraNum = cameraNum;
        }

        @Override
        public int compareTo(Camera o) {
            if (this.cameraNum > o.cameraNum) {
                return 1;
            } else if (this.cameraNum == o.cameraNum) {
                return 0;
            }else return -1;
        }
    }
}
