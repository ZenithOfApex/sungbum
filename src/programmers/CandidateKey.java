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

        //나올 수 있는 조합은 아래가 전부
        String[] attributeList = {"sno name", "name", "major", "grade", "sno", "sno major", "sno grade", "name major", "name grade", "major grade", "sno name major", "sno name grade", "name major grade", "sno name major grade"};
        HashSet<String> candidateKey = new HashSet<>();//가능한 후보 키를 담기 위한 hashSet

        ArrayList<StudentInfo> studentList = new ArrayList<>();//학생 정보를 담기 위한 리스트
        int size = relation.length;//전체 학생 수

        for (int i = 0; i < relation.length; i++) {//학생 정보 입력
            String sno = relation[i][0];
            String name = relation[i][1];
            String major = relation[i][2];
            String grade = relation[i][3];
            studentList.add(new StudentInfo(sno, name, major, grade));
        }

        for (String attributeComb : attributeList) {//위의 가능한 조합 중에서 하나씩 체크
            String[] attributes = attributeComb.split(" ");//조합 중 attribute 구분
            StringBuilder sb = new StringBuilder();
            for (StudentInfo student : studentList) {//학생 중에서
                for (String s : attributes) {//attribute 당 해당되는 정보 받아오기
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
                candidateKey.add(sb.toString());//받아온 정보를 hashSet에 저장
                sb.setLength(0);//stringBuilder 초기화
            }
            if (checkCondition(candidateKey, size)) {//hashSet의 사이즈가 전체 학생 수랑 일치하는지 확인
                answerList.add(attributeComb);//일치한다면 answerList에 후보 키 추가
                candidateKey.clear();//hashSet 초기화
            }
            candidateKey.clear();//후보키 hashSet 초기화
        }

        //정답 리스트에서 후보키 사이즈 오름차순으로 정렬
        Collections.sort(answerList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                }else return 1;
            }
        });

        ArrayList<String> tempList = new ArrayList<>();//최소성 확인을 위한 임시 배열 선언 및 초기화
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
        //중복 제거 후 후보 키 만 출력
        for (String s : tempList) {
            if (!s.equals("null")) {
                answerList.add(s);
            }
        }

        answer = answerList.size();
        return answer;
    }

    //hashSet의 사이즈가 전체 학생 수랑 일치하는지 확인
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
