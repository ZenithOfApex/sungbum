package KaKaoCodingTest;

import java.util.ArrayList;

public class Q01 {
    public static int[] solution(String[] id_list, String[] report, int k){
        int[] answer = new int[id_list.length];
        ArrayList<UserInfo> users = new ArrayList<>();
        userEnrollment(id_list,users);
        reportCheck(report, users);
        for(int i=0;i<users.size();i++){//신고 다 확인해서 k횟수 초과시 false로 값 치환
            if (users.get(i).reportCount >= k) {
                users.get(i).activeStatus = false;
            }
        }
        //정지됐는지 확인하고 이메일을 보내야지
        checkingStatus(users);
        for(int i=0;i<answer.length;i++){
            answer[i] = users.get(i).emailReceived;
        }
        return answer;
    }
    public static void reportCheck(String[] report, ArrayList<UserInfo> users){
        String[] reported = new String[report.length];
        for(int i=0;i<report.length;i++){
            reported = report[i].split(" ");
            for(int j=0;j<users.size();j++){
                if (users.get(j).userId.equals(reported[0])&& users.get(j).reportTarget.contains(reported[1])) {
                    continue;
                }else if(users.get(j).userId.equals(reported[0]) && !users.get(j).reportTarget.contains(reported[1])){
                    users.get(j).reportTarget.add(reported[1]);
                    for(int k=0;k<users.size();k++){
                        if(users.get(k).userId.equals(reported[1]))
                            users.get(k).reportCount++;
                    }
                }
            }
        }
    }
    public static void checkingStatus(ArrayList<UserInfo> users){
        for(int i=0;i<users.size();i++){
            for(int j=0;j<users.get(i).reportTarget.size();j++){
                for(int k=0;k< users.size();k++){
                    if(users.get(k).userId.equals(users.get(i).reportTarget.get(j))){
                        if(users.get(k).activeStatus == false)
                            users.get(i).emailReceived++;
                    }
                }
            }
        }
    }

    public static void userEnrollment(String[] id_list, ArrayList<UserInfo> users){
        for(int i=0;i<id_list.length;i++){
            users.add(new UserInfo(id_list[i],true));
        }
    }

    public static void main(String[] args) {
        String[] id_list = new String[4];
        id_list = new String[]{"muzi", "frodo", "apeach", "neo"};

        String[] report = new String[5];
        report = new String[]{"muzi frodo","apeach frodo", "frodo neo","muzi neo","apeach muzi"};
        int k=2;
        int[] answer = new int[10];
        answer = solution(id_list, report, k);
        for(int i=0;i<answer.length;i++){
            System.out.println("answer[i] = " + answer[i]);
        }
    }
}
class UserInfo{
    String userId;
    ArrayList<String> reportTarget = new ArrayList<>();
    int reportCount;
    boolean activeStatus;
    int emailReceived;

    public UserInfo(String userId, boolean activeStatus) {
        this.userId = userId;
        this.activeStatus = activeStatus;
    }
}


