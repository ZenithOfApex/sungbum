package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class CandidateKey {
    public static void main(String[] args) {
        String[][] input = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        System.out.println(solution(input));
    }

    public static int solution(String[][] relation){
        int answer =0;
        ArrayList<String> answerList = new ArrayList<>();

        String[] attributeList = {"sno name", "name", "major", "grade", "sno", "sno major", "sno grade", "name major", "name grade", "major grade", "sno name major", "sno name grade", "name major grade", "sno name major grade"};
        HashSet<String> candidateKey = new HashSet<>();

        ArrayList<StudentInfo> studentList = new ArrayList<>();
        int size = relation.length;


        for (int i = 0; i < relation.length; i++) {
            String sno = relation[i][0];
            String name = relation[i][1];
            String major = relation[i][2];
            String grade = relation[i][3];
            studentList.add(new StudentInfo(sno, name, major, grade));
        }

        for (String attributeComb : attributeList) {
            String[] attributes = attributeComb.split(" ");
            StringBuilder sb = new StringBuilder();
            for (StudentInfo student : studentList) {
                for (String s : attributes) {
                    if (s.equals("sno")) {
                        sb.append(student.sno);
                    } else if (s.equals("name")) {
                        sb.append(student.name);
                    } else if (s.equals("major")) {
                        sb.append(student.major);
                    } else {
                        sb.append(student.grade);
                    }
                    sb.append(" ");
                }
                candidateKey.add(sb.toString());
                sb.setLength(0);

            }
            if (checkCondition(candidateKey, size)) {
                answerList.add(attributeComb);
                candidateKey.clear();
            }
            candidateKey.clear();
        }

        Collections.sort(answerList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                }else return 1;
            }
        });

        ArrayList<String> tempList = new ArrayList<>();
        for (String s : answerList) {
            tempList.add(s);
        }

        for (int i = 0; i < answerList.size()-1; i++) {
            int len = answerList.get(i).length();
            for (int j = i+1; j < answerList.size(); j++) {
                if (answerList.get(j).substring(0, len).equals(answerList.get(i))) {
                    tempList.set(j, "null");
                }
            }
        }
        answerList.clear();
        for (String s : tempList) {
            if (!s.equals("null")) {
                answerList.add(s);
            }
        }

        answer = answerList.size();
        return answer;
    }

    public static boolean checkCondition(HashSet<String> attribute, int n) {
        if (attribute.size() == n) {
            return true;
        } else return false;
    }

    static class StudentInfo{
        String sno;
        String name;
        String major;
        String grade;

        public StudentInfo(String sno, String name, String major, String grade) {
            this.sno = sno;
            this.name = name;
            this.major = major;
            this.grade = grade;
        }
    }
}
