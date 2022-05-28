package line;

import java.util.*;

public class SummerInternship22_Problem01 {
    static String[] answer = {};
    static HashMap<String, Integer> wellKnown = new HashMap<>();
    static HashMap<String, ArrayList<String>> history = new HashMap<>();
    static HashSet<String> names = new HashSet<>();

    public static void main(String[] args) {
        String[] logs = {"morgan string_compare", "felix string_compare", "morgan reverse", "rohan sort", "andy reverse", "morgan sqrt"};
//        String[] logs = {"morgan sort", "felix sort", "morgan sqrt", "morgan sqrt", "rohan reverse", "rohan reverse"};
        answer = solution(logs);
    }

    private static String[] solution(String[] logs){
        ArrayList<String> tempList = new ArrayList<>();

        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, " ");
            String name = st.nextToken();
            names.add(name);
        }

        for (String name : names) {
            history.put(name, new ArrayList<>());
        }


        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, " ");
            String name = st.nextToken();
            String problem = st.nextToken();

            names.add(name);
            //중복 풀이 체크
            if (!history.get(name).contains(problem)) {
                history.get(name).add(problem);
            }else continue;
        }

        for (String s : history.keySet()) {
            for (String problem : history.get(s)) {
                if (wellKnown.keySet().contains(problem)) {
                    int tempCnt = wellKnown.get(problem);
                    wellKnown.put(problem, tempCnt + 1);
                }
                else {
                    wellKnown.put(problem, 1);
                }
            }
        }
        int half ;
        if (names.size() % 2 == 0) {
            half = names.size() / 2;
        } else half = names.size() / 2 + 1;

        for (String key : wellKnown.keySet()) {
            if (wellKnown.get(key) >= half) {
                tempList.add(key);
            }else continue;
        }

        Collections.sort(tempList);

        answer = tempList.toArray(new String[tempList.size()]);

        return answer;
    }
}
