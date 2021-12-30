package programmers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

public class IllegalUser2 {

    static HashSet<String> set;
    static String[] regex;
    static String[] user;
    static boolean[] visited;

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
//        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        user = user_id;

        regex = new String[banned_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            regex[i] = banned_id[i].replace("*", ".");
        }

        visited = new boolean[user.length];
        backtracking(0, "");
        return set.size();
    }

    public static void backtracking(int idx, String result) {
        if (idx == regex.length) {
            String[] str = result.split(" ");
            Arrays.sort(str);

            String newStr = "";
            for (int i = 0; i < str.length; i++) {
                newStr += str[i];
            }
            set.add(newStr);
            return;
        }

        for (int i = 0; i < user.length; i++) {
            if (visited[i] == false && user[i].matches(regex[idx])) {
                visited[i] = true;
                backtracking(idx + 1, result + " " + user[i]);
                visited[i] = false;
            }
        }
    }
}