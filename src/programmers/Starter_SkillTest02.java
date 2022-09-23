package programmers;

public class Starter_SkillTest02 {

    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        if (n == 1) return sb.append("수").toString();
        else if (n == 2) return sb.append("수박").toString();
        else{
            if (n % 2 == 1) {//홀수이면
                int cnt = n / 2;
                for (int i = 0; i < cnt; i++) {
                    sb.append("수박");
                }
                return sb.append("수").toString();

            } else {//짝수이면
                int cnt = n / 2;
                for (int i = 0; i < cnt; i++) {
                    sb.append("수박");
                }
                return sb.toString();
            }
        }
    }
}
