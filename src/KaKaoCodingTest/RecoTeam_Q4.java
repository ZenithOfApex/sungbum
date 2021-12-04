package KaKaoCodingTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecoTeam_Q4 {
    static int INF = 10000000;
    static ArrayList<Integer> answerList;

    public static void main(String[] args) {
        List arr = new ArrayList();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(8);
        int m =3;
        System.out.println(solution(arr, m));
    }

    public static int solution(List<Integer> arr, int m) {
        int answer =0;
        answerList = new ArrayList<>();
        int[] numbers = arr.stream().mapToInt(i -> i).toArray();
        boolean[] visited = new boolean[numbers.length];

        combination(numbers, visited, 0, numbers.length, m);
        Collections.sort(answerList);
        answer = answerList.get(answerList.size() - 1);

        return answer;
    }

    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    static void print(int[] arr, boolean[] visited, int n) {
        ArrayList<Integer> tempList = new ArrayList<>();
        int minValue = INF;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                tempList.add(arr[i]);
            }
        }
        int[] tempNum = tempList.stream().mapToInt(i -> i).toArray();
        for (int i = 0; i < tempNum.length-1; i++) {
            int a = tempNum[i];
            int b = tempNum[i+1];
            minValue = Math.min(minValue, Math.abs(a - b));
        }
        answerList.add(minValue);
    }
}
