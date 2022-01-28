package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Treasure_1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> arrA = new ArrayList<>();
        ArrayList<Integer> arrB = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arrA.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arrB.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arrA);
        Collections.sort(arrB, Comparator.reverseOrder());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arrA.get(i) * arrB.get(i);
        }
        System.out.println(sum);
        br.close();
    }
}
