package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class noHearSee_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> hs = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String temp_name = br.readLine();
            hs.add(temp_name);
        }
        int cnt =0;
        for (int i = 0; i < m; i++) {
            String temp_name = br.readLine();
            if (hs.contains(temp_name)) {
                cnt++;
                result.add(temp_name);
            }
        }
        br.close();

        Collections.sort(result);
        System.out.println(cnt);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
