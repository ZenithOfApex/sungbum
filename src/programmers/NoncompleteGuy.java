package programmers;

import java.util.HashMap;
import java.util.HashSet;

public class NoncompleteGuy {
    static HashMap<String, Integer> checkList = new HashMap<>();

    public static void main(String[] args) {
//        String[] participant = {"leo", "kiki", "eden"};
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
//        String[] completion = {"eden", "kiki"};
        String[] completion = {"stanko", "ana", "mislav"};

        System.out.println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        for (String s : participant) {
            if (checkList.get(s) == null) {
                checkList.put(s, 1);
            } else {
                int temp = checkList.get(s);
                checkList.put(s, temp + 1);
            }
        }

        for (String s : completion) {
            int temp = checkList.get(s);
            checkList.put(s, temp - 1);
        }

        for (String s : checkList.keySet()) {
            if (checkList.get(s) != 0) {
                answer = s;
            }
        }

        return answer;
    }
}
