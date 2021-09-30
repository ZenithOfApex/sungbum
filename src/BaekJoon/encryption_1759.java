package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class encryption_1759 {
    static ArrayList<String> combinationResult;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        sc.nextLine();
        //nCr구해야 한다.
        String inputStr = sc.nextLine();
        sc.close();
        String[] str = inputStr.split(" ");
        boolean[] visited = new boolean[30];
        combinationResult = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        Arrays.sort(str);
        combination(str, visited, 0, r, n);
        for (int i = 0; i < combinationResult.size(); i++) {
            int vowelCount =0;
            int consonantCount =0;

            String answerSample = combinationResult.get(i);
            for (int j = 0; j < answerSample.length(); j++) {
                if (answerSample.charAt(j) == 'a' || answerSample.charAt(j) == 'e' || answerSample.charAt(j) == 'i' || answerSample.charAt(j) == 'o' || answerSample.charAt(j) == 'u') {
                    vowelCount++;
                }else{
                    consonantCount++;
                }
            }
            if (vowelCount >= 1 && consonantCount >= 2) {
                System.out.println(answerSample);
            }
        }
    }
    static void combination(String[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            combinationResult.add(save(arr, visited, n));
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    static String save(String[] arr, boolean[] visited, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(arr[i]);
            }
        }
        return String.valueOf(sb);
    }

}
