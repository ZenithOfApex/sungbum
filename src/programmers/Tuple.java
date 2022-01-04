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

        String trimmedStr = s.substring(2, s.length() - 2);//맨 앞 뒤, 중괄호 두개씩 제거
        trimmedStr = trimmedStr.replace("},{", "-");//그 다음 },{ -로 대체
        String[] collectionList = trimmedStr.split("-");//대체한 - 기준으로 분리

        //작은 집합 순으로 오름차순 정렬
        Arrays.sort(collectionList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else return -1;
            }
        });

        //집합 당 , 기준으로 수 분리 및 정답에 추가
        for (String s1 : collectionList) {
            String[] tempStr = s1.split(",");
            for (String target : tempStr) {
                if (!answerList.contains(Integer.parseInt(target))) {//이미 접답 리스트에 있다면 추가하지 않음
                    answerList.add(Integer.parseInt(target));
                } else continue;
            }
        }

        //리스트를 배열로
        Integer[] answer = answerList.toArray(new Integer[answerList.size()]);
        return answer;
    }
}
