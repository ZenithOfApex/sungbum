package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Encryption_1759 {

    private static int L,C;
    private static String[] trimmedStr;
    private static boolean[] visited;
    private static ArrayList<String> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        String alphabets = br.readLine();
        System.out.println(alphabets.charAt(0));

        trimmedStr = alphabets.split(" ");

        Arrays.sort(trimmedStr);

        System.out.println();
        visited = new boolean[C];
        DFS(0, 0);

        br.close();
    }

    public static void DFS(int start, int depth) {
        if (depth == L) {
            int vow = 0, cons =0;
            String answer = "";
            for (int i = 0; i < C; i++) {
                if (visited[i]) {
                    answer += trimmedStr[i];
                    if (trimmedStr[i].equals("a") || trimmedStr[i].equals("e") || trimmedStr[i].equals("i") || trimmedStr[i].equals("o") || trimmedStr[i].equals("u")) {
                        vow++;
                    }else cons++;
                }
            }
            if(vow>=1 && cons>=2) System.out.println(answer);
            return;
        }
        for (int i = start; i < C; i++) {
            visited[i] = true;
            DFS(i + 1, depth + 1);
            visited[i] = false;
        }
    }
}
