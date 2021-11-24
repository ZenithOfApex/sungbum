package programmers;

import java.util.*;

public class RankSearch {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200","cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        int[] answer = solution(info,query);
//        answer = solution(info, query);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }

    }

    public static int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> infos = new HashMap<>();
        for (String in : info) {
            String[] split = in.split(" ");
            int score = Integer.parseInt(split[4]);

            for (int i = 0; i < (1<<4); i++) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    if((i&(1<<j)) >0) key.append(split[j]);
                }
                infos.computeIfAbsent(key.toString(), s -> new ArrayList<>()).add(score);
            }
        }

        List<Integer> empty = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : infos.entrySet()) {
            entry.getValue().sort(null);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].replaceAll("-", "").split(" and | ");
            String key = String.join("", split[0], split[1], split[2], split[3]);
            int score = Integer.parseInt(split[4]);

            List<Integer> list = infos.getOrDefault(key, empty);

            int s = 0, e = list.size();

            while (s < e) {
                int mid = (s+e)/2;
                if(list.get(mid) < score) s = mid + 1;
                else e = mid;
            }
            answer[i] = list.size() -s;

        }

        return answer;
    }

//    public static int[] solution(String[] info, String[] query) {
//        ArrayList<Appliance> apply = new ArrayList<>();
//        ArrayList<Query> condition = new ArrayList<>();
//        ArrayList<Integer> result = new ArrayList<>();
//
//        for (int i = 0; i < info.length; i++) {//info 입력 받아 arrayList에
//            String input = info[i];
//            StringTokenizer st = new StringTokenizer(input, " ");
//            apply.add(new Appliance(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken())));
//        }
//        for (int i = 0; i < query.length; i++) {//query 입력 받아 arrayList에
//            String input = query[i];
//            String newStr = query[i].replaceAll("and", "");
//            StringTokenizer st = new StringTokenizer(input, " ");
//            StringBuilder sb = new StringBuilder();
//            while (st.hasMoreTokens()) {
//                String temp = st.nextToken();
//                if (!temp.equals("and")) {
//                    sb.append(temp+" ");
//                }
//            }
//            st = new StringTokenizer(String.valueOf(sb), " ");
//            while (st.hasMoreTokens()) {
//                condition.add(new Query(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken())));
//            }
//        }
//
//
//        for (Query question : condition) {
//            String lang = question.lang;
//            String part = question.part;
//            String status = question.status;
//            String food = question.food;
//            int score = question.score;
//
//            int count =0;//해당되는 지원자 수 저장하기 위한 변수
//
//            for (Appliance participant : apply) {
//                if(participant.score>=question.score){
//                    if (lang.equals(participant.lang) || lang.equals("-")) {
//                        if (part.equals(participant.part) || part.equals("-")) {
//                            if (status.equals(participant.status) || status.equals("-")) {
//                                if (food.equals(participant.food) || food.equals("-")) {
//                                    count++;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            result.add(count);
//        }
//
//        int[] answer = new int[result.size()];
//        for (int i = 0; i < result.size(); i++) {
//            answer[i] = result.get(i);
//        }
//
//        return answer;
//    }
}

//class Appliance{
//    String lang;
//    String part;
//    String status;
//    String food;
//    int score;
//
//    public Appliance(String lang, String part, String status, String food, int score) {
//        this.lang = lang;
//        this.part = part;
//        this.status = status;
//        this.food = food;
//        this.score = score;
//    }
//}
//
//class Query{
//    String lang;
//    String part;
//    String status;
//    String food;
//    int score;
//
//    public Query(String lang, String part, String status, String food, int score) {
//        this.lang = lang;
//        this.part = part;
//        this.status = status;
//        this.food = food;
//        this.score = score;
//    }
//}
