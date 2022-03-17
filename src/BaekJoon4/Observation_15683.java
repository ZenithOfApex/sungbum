package BaekJoon4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Observation_15683 {

    static int answer;
    static int N, M;
    static final int cameraChecked = Integer.MAX_VALUE;
    static int[] output;
    static int[][] map;
    static int[][] copyMap;
    static ArrayList<Camera> cameraList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;
        map = new int[N][M];

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

        output = new int[cameraList.size()];
        permutation(0, cameraList.size());

        //사각지대 최소가 되는 경우 사각지대의 갯수 반환
        answer = Math.min(answer, getBlindSpot());

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void byDirection(Camera camera, int direction) {
        int cameraNum = camera.cameraNum;
        if (cameraNum == 1) {
            activateCameraNumberOne(camera.x, camera.y, direction);
        } else if (cameraNum == 2) {
            activateCameraNumberTwo(camera.x, camera.y, direction);
        } else if (cameraNum == 3) {
            activateCameraNumberThree(camera.x, camera.y, direction);
        } else if (cameraNum == 4) {
            activateCameraNumberFour(camera.x, camera.y, direction);
        } else if (cameraNum == 5) {
            activateCameraNumberFive(camera.x, camera.y);
        }
    }

    private static void activateCameraNumberOne(int x, int y, int caseNum) {
        if (caseNum == 0) {
            //카메라가 우측 방향을 보는 경우
            checkRight(x, y);
        } else if (caseNum == 1) {
            //카메라가 상단을 보는 경우
            checkTop(x, y);
        } else if (caseNum == 2) {
            //카메라가 좌츨을 보는 경우
            checkLeft(x, y);
        } else {
            //카메라가 하단을 보는 경우
            checkBottom(x, y);
        }
    }

    private static void activateCameraNumberTwo(int x, int y, int caseNum) {
        if (caseNum == 0) {
            //카메라가 좌우측을 보는 경우
            checkLeft(x, y);
            checkRight(x, y);
        } else {
            //카메라가 상하단을 보는 경우
            checkTop(x, y);
            checkBottom(x, y);
        }
    }

    private static void activateCameraNumberThree(int x, int y, int caseNum) {
        if (caseNum == 0) {
            //카메라가 상우 방향을 보는 경우
            checkTop(x, y);
            checkRight(x, y);
        } else if (caseNum == 1) {
            //카메라가 상좌 방향을 보는 경우
            checkTop(x, y);
            checkLeft(x, y);
        } else if (caseNum == 2) {
            //카메라가 좌하 방향을 보는 경우
            checkLeft(x, y);
            checkBottom(x, y);
        } else if (caseNum == 3) {
            //카메라가 우하 방향을 보는 경우
            checkRight(x, y);
            checkBottom(x, y);
        }
    }
    private static void activateCameraNumberFour(int x, int y,int caseNum) {
        if (caseNum == 0) {
            //카메라가 좌우상 방향을 보는 경우
            checkTop(x, y);
            checkLeft(x, y);
            checkRight(x, y);
        } else if (caseNum == 1) {
            //카메라가 좌우하 방향을 보는 경우
            checkLeft(x, y);
            checkRight(x, y);
            checkBottom(x, y);
        } else if (caseNum == 2) {
            //카메라가 좌상하 방향을 보는 경우
            checkTop(x, y);
            checkLeft(x, y);
            checkBottom(x, y);
        } else {
            //카메라가 우상하 방향을 보는 경우
            checkTop(x, y);
            checkRight(x, y);
            checkBottom(x, y);
        }
    }

    private static void permutation(int depth, int r) {
        if (depth == r) {
            copyMap = new int[N][M];
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
            }

            for (int i = 0; i < cameraList.size(); i++) {
                byDirection(cameraList.get(i), output[i]);
            }

            getBlindSpot();

            return;
        }

        for (int i = 0; i < 4; i++) {
            output[depth] = i;
            permutation(depth + 1, r);
        }
    }

    private static void activateCameraNumberFive(int x, int y) {
        //카메라가 상하좌우 보는 경우 하나
        checkTop(x, y);
        checkLeft(x, y);
        checkRight(x, y);
        checkBottom(x, y);
    }

    private static int[][] checkRight(int x, int y) {
        for (int j = y; j < M; j++) {
            if (map[x][j] == 6) {
                break;
            } else {
                copyMap[x][j] = cameraChecked;
            }
        }
        return copyMap;
    }

    private static int[][] checkLeft(int x, int y) {
        for (int j = y; j >=0; j--) {
            if (map[x][j] == 6) {
                break;
            } else {
                copyMap[x][j] = cameraChecked;
            }
        }
        return copyMap;
    }

    private static int[][] checkTop(int x, int y) {
        for (int i = x; i >=0; i--) {
            if (map[i][y] == 6) {
                break;
            } else {
                copyMap[i][y] = cameraChecked;
            }
        }
        return copyMap;
    }

    private static int[][] checkBottom(int x, int y) {
        for (int i = x; i < M; i++) {
            if (map[i][y] == 6) {
                break;
            } else {
                copyMap[i][y] = cameraChecked;
            }
        }
        return copyMap;
    }

    //사각지대의 갯수 구하기
    private static int getBlindSpot() {
        int rtnValue = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
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
                return -1;
            } else if (this.cameraNum == o.cameraNum) {
                return 0;
            }else return 1;
        }
    }
}
