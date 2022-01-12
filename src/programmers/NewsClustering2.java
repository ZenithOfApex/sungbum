package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class NewsClustering2 {
    public static void main(String[] args) {
//        System.out.println(solution("FRANCE", "french"));
//        System.out.println(solution("handshake","shake hands"));
//        System.out.println(solution("aa1+aa2","AAAA12"));
        System.out.println(solution("E=M*C^2","e=m*c^2"));
    }

    public static int solution(String str1, String str2) {
        double answer = 0;

        String lowerStr1 = toLowerCase(str1);
        String lowerStr2 = toLowerCase(str2);

        ArrayList<String> splitStr1 = splitIntoSubString(lowerStr1);
        ArrayList<String> splitStr2 = splitIntoSubString(lowerStr2);

        int intersectSize = getIntersectSize(splitStr1, splitStr2);
        int unionSize = getUnionSize(splitStr1, splitStr2);
        if (((double) unionSize - (double) intersectSize) == 0) {
            answer = 1;
            answer = answer *65536;

        } else {
            double tempAnswer = (double) ((double) intersectSize / ((double) unionSize));
            answer = (tempAnswer * 65536);
        }


        return (int)answer;
    }

    public static String toLowerCase(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        return lowerCase;
    }

    public static ArrayList<String> splitIntoSubString(String str) {
        int n = str.length();
        ArrayList<String> subList = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            String subStr = str.substring(i, i + 2);
            boolean flag = true;
            /**
             * 아스키 코드 97~122가 a~z
             */
            for (int j = 0; j < subStr.length(); j++) {
                if ((int) subStr.charAt(j) < 97 || (int) subStr.charAt(j) > 122) {//소문자를 제외한 어떠한 문자라도 포함하고 있다면
                    flag = false;
                }
            }
            if (!flag) {
                continue;
            }
            subList.add(str.substring(i, i + 2));
        }

        return subList;
    }

    public static int getUnionSize(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> finalList = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();
        for (String s : list1) {
            hs.add(s);
        }
        for (String s : list2) {
            hs.add(s);
        }
        for (String h : hs) {
            int num1 = (int) list1.stream().filter(s -> s.equals(h)).count();
            int num2 = (int) list2.stream().filter(s -> s.equals(h)).count();
            int maxCnt = Math.max(num1, num2);

            for (int i = 0; i < maxCnt; i++) {
                finalList.add(h);
            }
        }
        return finalList.size();
    }

    public static int getIntersectSize(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> finalList = new ArrayList<>();
        HashSet<String> hs = new HashSet<>();
        for (String s : list1) {
            hs.add(s);
        }
        for (String s : list2) {
            hs.add(s);
        }
        for (String h : hs) {
            int num1 = (int) list1.stream().filter(s -> s.equals(h)).count();
            int num2 = (int) list2.stream().filter(s -> s.equals(h)).count();
            int minCnt = Math.min(num1, num2);

            for (int i = 0; i < minCnt; i++) {
                finalList.add(h);
            }
        }
        return finalList.size();
    }
}
