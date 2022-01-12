package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CardFusion_15903 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Long> numList = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        while (st.hasMoreTokens()) {
            numList.add(Long.parseLong(st.nextToken()));
        }

        long sum = 0;
        for (int i = 0; i < m; i++) {
            Collections.sort(numList);
            long cardSum = numList.get(0)+numList.get(1);
            numList.set(0, cardSum);
            numList.set(1, cardSum);
        }
        for (Long num : numList) {
            sum += num;
        }

        System.out.println(sum);
    }
}
