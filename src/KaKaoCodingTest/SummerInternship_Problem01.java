package KaKaoCodingTest;

import java.util.HashMap;

public class SummerInternship_Problem01 {
    static String answer="";

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5}));
    }

    private static String solution(String[] survey, int[] choices){
        HashMap<String, Integer> hm = new HashMap<>();

        hm.put("A", 0);
        hm.put("N", 0);
        hm.put("J", 0);
        hm.put("M", 0);
        hm.put("C", 0);
        hm.put("F", 0);
        hm.put("R", 0);
        hm.put("T", 0);

        for (int i = 0; i < survey.length; i++) {
            String testType = survey[i];
            String negativeType = String.valueOf(testType.charAt(0));//A
            String positiveType = String.valueOf(testType.charAt(1));//N

            int choice = choices[i];//5
            if (choice >= 1 && choice <= 3) {//negative 번호라면
                int score = 4-choice;
                int curScore = hm.get(negativeType);
                hm.put(negativeType, curScore + score);
            } else if (choice == 4) {
                continue;
            } else {
                int score = choice - 4;
                int curScore = hm.get(positiveType);
                hm.put(positiveType, curScore + score);
            }
        }
        answer = testResult(hm);
        return answer;
    }

    private static String testResult(HashMap<String, Integer> test) {
        String type1 = "";
        String type2 = "";
        String type3 = "";
        String type4 = "";

        //type 1 산출
        int type_R = test.get("R");
        int type_T = test.get("T");
        if (type_R > type_T) {
            type1 = "R";
        } else if (type_R < type_T) {
            type1 = "T";
        } else if (type_R == type_T) {
            //사전순 리턴
            type1 = "R";
       }

        //type 2 산출
        int type_C = test.get("C");
        int type_F = test.get("F");
        if (type_C > type_F) {
            type2 = "C";
        } else if (type_C < type_F) {
            type2 = "F";
        } else if (type_C == type_F) {
            //사전순 리턴
            type2 = "C";
        }

        //type 3 산출
        int type_J = test.get("J");
        int type_M = test.get("M");
        if (type_J > type_M) {
            type3 = "J";
        } else if (type_J < type_M) {
            type3 = "M";
        } else if (type_J == type_M) {
            //사전순 리턴
            type3 = "J";
        }

        //type 4 산출
        int type_A = test.get("A");
        int type_N = test.get("N");
        if (type_A > type_N) {
            type4 = "A";
        } else if (type_A < type_N) {
            type4 = "N";
        } else if (type_A == type_N) {
            //사전순 리턴
            type4 = "A";
        }

        StringBuilder result = new StringBuilder();
        result.append(type1);
        result.append(type2);
        result.append(type3);
        result.append(type4);

        return result.toString();
    }
}
