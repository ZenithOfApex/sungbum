package BaekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class numberSorting_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> numbers = new ArrayList<>();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            numbers.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(numbers);
        for (int comp : numbers) {
            sb.append(comp).append('\n');
        }
        System.out.println(sb);
    }
}
