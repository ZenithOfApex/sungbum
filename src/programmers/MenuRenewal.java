package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MenuRenewal {
    static ArrayList<String> combinationList;
    static ArrayList<String> answerList;

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        String[] answer = solution(orders, course);
//        for (String s : answer) {
//            System.out.println("s = " + s);
//        }
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> answerList = new ArrayList<>();
        answerList = new ArrayList<>();

        //orders에서 하나씩 받아와서 배열로 만들기
        for (int courseNum : course) {//[2,3,5]
            ArrayList<String> courseList = new ArrayList<>();
            for (String order : orders) {//orders = {abcfg, ac, cde, acde}, order는 각 요소
                char[] item = order.toCharArray();//item은 하나 하나
                Arrays.sort(item);//xy yx 동일 처리하기 위해 정렬 처리
                boolean[] visited = new boolean[item.length];

                combinationList = new ArrayList<>();
                ArrayList<String> itemList = combination(item, visited, 0, item.length, courseNum);//조합 반환하기 위한 arrayList
                for (String s : itemList) {
                    courseList.add(s);
                }
            }
            int max = 0;
            for (String s : courseList) {
                int count = Collections.frequency(courseList, s);
                max = Math.max(count, max);
            }
            for (String s : courseList) {
                if (Collections.frequency(courseList, s) == max && max>=2) {
                    if (answerList.contains(s)) {
                        continue;
                    } else answerList.add(s);
                }
            }
        }
        Collections.sort(answerList);
        answer = answerList.toArray(new String[answerList.size()]);

        return answer;
    }

    static ArrayList<String> combination(char[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            String temp = print(arr, visited, n);
            combinationList.add(temp);
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
        return combinationList;
    }

    static String print(char[] arr, boolean[] visited, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
