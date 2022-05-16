package programmers;

public class SecretMap {
    static int[][] map;

    public static void main(String[] args) {
        String[] answer = {};

//        int n = 5;
//        int[] arr1 = {9, 20, 28, 18, 11};
//        int[] arr2 = {30, 1, 21, 17, 28};
//        answer = solution(n, arr1, arr2);

        int n = 6;
        int[] arr1 = {46, 33, 33, 22, 31, 50};
        int[] arr2 = {27, 56, 19, 14, 14, 10};
        answer = solution(n, arr1, arr2);

        for (String s : answer) {
            System.out.println(s);
        }
    }

    private static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};

        map = new int[n][n];

        decimalToBinary(n, arr1);
        decimalToBinary(n, arr2);

        answer = changeMapIntoAnswer(n, map);

        return answer;
    }

    private static String[] changeMapIntoAnswer(int n, int[][] map) {
        String[] rtnValue = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j : map[i]) {
                if (j == 0) {
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
            }
            rtnValue[i] = sb.toString();
        }

        return rtnValue;
    }


    private static void decimalToBinary(int n, int[] arr) {
        for (int i = 0; i < n; i++) {
            String bin = Integer.toBinaryString(arr[i]);
            if (bin.length() != n) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n - bin.length(); j++) {
                    sb.append("0");
                }
                sb.append(bin);
                bin = sb.toString();
            }

            for (int j = 0; j < bin.length(); j++) {
                int target = Integer.parseInt(String.valueOf(bin.charAt(j)));
                map[i][j] = map[i][j] | target;
            }
        }
    }
}
