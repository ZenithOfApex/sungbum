package programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class CandidateKey2 {

    static ArrayList<HashSet<Integer>> candidateKey;
    static String Table[][];
    static int length;
    static int answer;

    public static void main(String[] args) {
        String[][] input = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        System.out.println(solution(input));
    }

    public static int solution(String[][] relation) {
        answer = 0;
        candidateKey = new ArrayList<HashSet<Integer>>();
        Table = relation;
        length = relation[0].length;
        for (int i = 1; i <= length; i++) {
            makeSet(-1, i, 0, new HashSet<Integer>());
        }

        return answer;
    }

    public static void makeSet(int index, int target, int count, HashSet<Integer> set) {
        if (count == target) {
            if (!isUnique(set)) {//유일성이 보장되지 않았다면 끝내기
                return;
            }
            for (HashSet<Integer> key : candidateKey) {
                if (set.containsAll(key)) {
                    return;
                }
            }
            candidateKey.add(set);
            answer++;
            return;
        }
        for (int i = index + 1; i < length; i++) {
            HashSet<Integer> newSet = new HashSet<Integer>(set);
            newSet.add(i);
            makeSet(i, target, count + 1, newSet);
        }
    }

    public static boolean isUnique(HashSet<Integer> set) {//유일성 체크
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Table.length; i++) {
            String temp = "";
            for (Integer index : set) {//attribute 인덱스 번호로 따와서 문자열로 생성
                temp += Table[i][index];
            }
            if (!list.contains(temp)) {//중복되지 않게 추가
                list.add(temp);
            } else {//이미 있다면 false 반환
                return false;
            }
        }
        return true;
    }
}
