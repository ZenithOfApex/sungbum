package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class hideAndSeek_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> hm = new HashMap<>();
        int level = 0;
        while (true) {
            if (hm.containsKey(m)) {
                System.out.println(hm.get(m));
                break;
            }
            hm.put(n, level);
            hm.put(n+1, level+1);
            hm.put(n - 1, level + 1);
            hm.put(n * 2, level + 1);
            level++;
        }
    }
}
