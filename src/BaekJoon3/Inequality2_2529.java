package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Inequality2_2529 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static char[] arr = new char[10];
    private static boolean[] visited = new boolean[10];
    private static List<String> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        dfs("", 0);
        Collections.sort(answer);

        System.out.println(answer.get(answer.size() - 1));
        System.out.println(answer.get(0));
        br.close();
    }

    private static void dfs(String num, int index) {
        if (index == N + 1) {
            answer.add(num);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            if (index == 0 || check(Character.getNumericValue(num.charAt(index - 1)), i, arr[index - 1])) {
                visited[i] = true;
                dfs(num + i, index + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean check(int a, int b, char c) {
        if (c == '<') {
            if (a > b) return false;
        } else if (c == '>') {
            if(a<b) return false;
        }
        return true;
    }
}
