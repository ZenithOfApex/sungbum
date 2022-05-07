package KaKaoCodingTest;

public class SummerInternship_Problem05 {
    static int[][] answer;
    static int startIndex, endIndex;
    static int rowLength, colLength;


    public static void main(String[] args) {
//        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] rc = {{8, 6, 3}, {3, 3, 7}, {8, 4, 9}};
        int[][] rc = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
//        String[] operations = {"Rotate", "ShiftRow"};
//        String[] operations = {"Rotate", "ShiftRow", "ShiftRow"};
        String[] operations = {"ShiftRow", "Rotate", "ShiftRow","Rotate"};
        answer = new int[rc.length][rc[0].length];
        answer = solution(rc, operations);

        for (int[] ints : answer) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }

    private static int[][] solution(int[][] rc, String[] operations){
        startIndex = 0;
        endIndex = rc.length-1;
        rowLength = rc.length;
        colLength = rc[0].length;


        for (String operation : operations) {
            if (operation.equals("Rotate")) {
//                int[][] tmp = fixMap(rc);
//                rc = tmp.clone();
                rc = fixMap(rc);
                rc = rotate(rc);
            } else if (operation.equals("ShiftRow")) {
                shift(rc);
            }

            System.out.println("Operation ended");
            for (int[] ints : rc) {
                for (int anInt : ints) {
                    System.out.print(anInt+" ");
                }
                System.out.println();
            }
            System.out.println("startIndex = " + startIndex);
            System.out.println("endIndex = " + endIndex);
            System.out.println();

        }
//        int[][] tmp = fixMap(rc);
//        rc = tmp.clone();
        return rc;
    }

    private static int[][] fixMap(int[][] rc) {
        int[][] rtnMap = new int[rowLength][colLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                rtnMap[i][j] = rc[(startIndex+i)%rowLength][j];
            }
        }

        return rtnMap;
    }

    private static int[][] rotate(int[][] map) {

        int[][] rtnMap = map;

        int swap = map[1][0];
        for (int i = 1; i < rowLength - 1; i++) {
            rtnMap[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < colLength - 1; i++) {
            rtnMap[rowLength - 1][i] = map[rowLength - 1][i + 1];
        }
        for (int i = rowLength - 1; i > 0; i--) {
            rtnMap[i][colLength - 1] = map[i - 1][colLength - 1];
        }
        for (int i = colLength - 1; i > 0; i--) {
            rtnMap[0][i] = map[0][i - 1];
            if (i == 2) {
                rtnMap[0][i - 1] = 0;
            }
        }
        map[0][0] = swap;

        System.out.println("rotation test");
        for (int[] ints : rtnMap) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
        System.out.println();

        return rtnMap;
    }

    private static void shift(int[][] map) {
        if (endIndex > 0) {
            endIndex--;
        } else if (endIndex == 0) {
            endIndex = rowLength-1;
        }

        if (startIndex == 0) {
            startIndex = rowLength-1;
        } else if (startIndex > 0) {
            startIndex--;
        }
    }
}
