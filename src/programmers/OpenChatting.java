package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class OpenChatting {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] answer = solution(record);
        for (String s : answer) {
            System.out.println(s);
        }

    }

    public static String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String, String> hm = new HashMap<>();
        ArrayList<String> answerList = new ArrayList<>();

        for (String s : record) {
            String[] log = s.split(" ");
            String status = log[0];
            if (status.equals("Enter") || status.equals("Change")) {
                String uid = log[1];
                String name = log[2];
                hm.put(uid, name);
            }
        }
        for (String s : record) {
            String[] log = s.split(" ");
            String status = log[0];
            if (status.equals("Enter")) {
                answerList.add(hm.get(log[1]) + "님이 들어왔습니다.");
            } else if (status.equals("Leave")) {
                answerList.add(hm.get(log[1]) + "님이 나갔습니다.");
            }
        }
        answer = answerList.toArray(new String[answerList.size()]);

        return answer;
    }
}
