package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaximumHeap_11279 {

    static PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int inputNumber = Integer.parseInt(br.readLine());
            if (inputNumber != 0) {
                q.add(inputNumber);
            } else {
                if (!q.isEmpty()) {
                    System.out.println(q.poll());
                }else System.out.println(0);
            }
        }
        br.close();
    }
}
