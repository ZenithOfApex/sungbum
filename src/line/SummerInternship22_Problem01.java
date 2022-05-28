package line;

import java.util.*;

public class SummerInternship22_Problem01 {
    static String[] answer = {};
    static HashMap<String, Integer> wellKnown = new HashMap<>();
    static HashMap<String, ArrayList<String>> history = new HashMap<>();
    static HashSet<String> names = new HashSet<>();

    public static void main(String[] args) {
//        String[] logs = {"morgan string_compare", "felix string_compare", "morgan reverse", "rohan sort", "andy reverse", "morgan sqrt"};
        String[] logs = {"morgan sort", "felix sort", "morgan sqrt", "morgan sqrt", "rohan reverse", "rohan reverse"};
        answer = solution(logs);
        for (String s : answer) {
            System.out.println("s = " + s);
        }
    }

    private static String[] solution(String[] logs){

        makeNameSet(logs);
        initHistory();
        makeHistory(logs);
        makeWellKnownSet();
        trimWellKnowSet();

        ArrayList<String> list = trimWellKnowSet();
        Collections.sort(list);

        return list.toArray(new String[list.size()]);
    }


    private static void makeNameSet(String[] logs) {
        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, " ");
            String name = st.nextToken();
            names.add(name);
        }
    }

    private static void initHistory() {
        for (String name : names) {
            history.put(name, new ArrayList<>());
        }
    }

    private static void makeHistory(String[] logs) {
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
    }

    private static void makeWellKnownSet() {
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
    }

    private static ArrayList<String> trimWellKnowSet() {
        ArrayList<String> tempList = new ArrayList<>();

        int half;
        if (names.size() % 2 == 0) {
            half = names.size() / 2;
        } else half = names.size() / 2 + 1;

        for (String key : wellKnown.keySet()) {
            if (wellKnown.get(key) >= half) {
                tempList.add(key);
            }else continue;
        }

        return tempList;
    }
}
