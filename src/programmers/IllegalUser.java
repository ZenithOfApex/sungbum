package programmers;

import java.util.ArrayList;
import java.util.HashSet;

public class IllegalUser {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
//        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        ArrayList<HashSet<String>> answerList = new ArrayList<>();
        ArrayList<ArrayList<String>> combiList = new ArrayList<>();

        for (String banned : banned_id) {//banned는 {"fr*d*", "abc1**} 각 요소
            ArrayList<String> tempList = new ArrayList<>();//같은
            ArrayList<Integer> indexList = new ArrayList<>();
            //별 인덱스 찾기
            for (int i = 0; i < banned.length(); i++) {
                if (banned.charAt(i) == '*') {
                    indexList.add(i);
                }
            }
            //길이가 같은 아이디 추출 및 별 대치
            String[] tempUser = user_id;
            for (String user : tempUser) {
                String originUser = user;
                if (user.length() == banned.length()) {//banned 랑 길이가 같은 아이디 중
                    char[] charUser = user.toCharArray();
                    for (Integer i : indexList) {//별 대치
                        charUser[i] = '*';
                    }
                    StringBuilder sb = new StringBuilder();
                    for (char c : charUser) {
                        sb.append(c);
                    }
                    user = sb.toString();
                    System.out.println("user = " + user);
                    if (user.equals(banned)) {
//                        tempList.add(user);
                        tempList.add(originUser);
                    }
                }
            }
            System.out.println("tempList = " + tempList);

            combiList.add(tempList);
        }

        HashSet<String> hs = new HashSet<>();

        for (int i = 0; i < combiList.get(0).size(); i++) {
            hs.add(combiList.get(0).get(i));
            for (int j = 0; j < combiList.get(1).size(); j++) {
                hs.add(combiList.get(1).get(j));
                for (int k = 0; k < combiList.get(2).size(); k++) {
                    hs.add(combiList.get(2).get(k));
                    System.out.println("hs = " + hs);
                    hs.clear();
                    if (!answerList.contains(hs)) {
                        answerList.add(hs);
//                        System.out.println("hs = " + hs);
                        hs.clear();
                    }else hs.clear();
                }
            }
        }



        System.out.println("answerList = " + answerList);
        for (ArrayList<String> list : combiList) {
            System.out.println(list);
        }

        answer = answerList.size();
        return answer;
    }
}
