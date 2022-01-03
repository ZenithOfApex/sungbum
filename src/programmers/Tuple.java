package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Tuple {
    public static void main(String[] args) {
        Integer[] answer = solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");
        for (int i : answer) {
            System.out.println(i);
        }
    }

    public static Integer[] solution(String s) {
        ArrayList<Integer> answerList = new ArrayList<>();

        String trimmedStr = s.substring(2, s.length() - 2);
        trimmedStr = trimmedStr.replace("},{", "-");
        System.out.println(trimmedStr);
        String[] collectionList = trimmedStr.split("-");
        for (String s1 : collectionList) {
            System.out.println("s1 = " + s1);
        }
        Arrays.sort(collectionList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else return -1;
            }
        });
        System.out.println();
        for (String s1 : collectionList) {
            String[] tempStr = s1.split(",");
            for (String target : tempStr) {
                if (!answerList.contains(Integer.parseInt(target))) {
                    answerList.add(Integer.parseInt(target));
                } else continue;
            }
            System.out.println("s1 = " + s1);
        }
        System.out.println(answerList);
        Integer[] answer = answerList.toArray(new Integer[answerList.size()]);
        return answer;
    }
}
