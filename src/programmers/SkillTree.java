package programmers;

public class SkillTree {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_tree = {"BACDE", "CBADF", "AECB", "BDA","FFF","CD"};//cbadf, aecb, fff
        int answer = solution(skill, skill_tree);
        System.out.println(answer);
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String st : skill_trees) {//선행 스킬과 관련 없이 등장하는 스킬들
            int noSkillCnt=0;
            int skillIndex=0;
            int checkCnt=0;
            for (int i = 0; i < st.length(); i++) {
                if (skill.contains(String.valueOf(st.charAt(i)))) {//읽어온 스킬이 skill안에 있고
                    if(skill.charAt(skillIndex)!=st.charAt(i)){//skill안에서 순서대로 증가함을 확인하기 위함
                        break;
                    }else{
                        skillIndex++;
                        checkCnt++;
                    }
                }else{//입력받은 스킬 관계와 상관없는 스킬들만 등록된 경우
                    noSkillCnt++;
                }
            }
            if (checkCnt + noSkillCnt == st.length()) {
                answer++;
            }

        }
        return answer;
    }
}

