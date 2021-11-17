package programmers;

import java.util.ArrayList;

public class GemShopping {
    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        int[] answer = solution(gems);
//        System.out.println(answer);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i]+" ");
        }
    }

    public static int[] solution(String[] gems) {
        int[] answer = {};
        ArrayList<String> gemList = new ArrayList<>();
        for (String gem : gems) {//입력받은 gem 목록에서 중복없는 gemList 생성
            if (!gemList.contains(gem)) {
                gemList.add(gem);
            }
        }

        int[] check = new int[gems.length];//체크 유무 파악
        int[] dp = new int[gems.length];

        for (int i = 0; i < gems.length-1; i++) {
            for (int j = i + 1; j < gems.length; j++) {
                if (gems[j] != gems[i]) {
                    check[i] = j-i;
                    break;
                }
            }
        }
        return check;








//        return answer;
    }

}
