package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimumHeap_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int inputNumber = Integer.parseInt(st.nextToken());
            if (inputNumber == 0 && !pq.isEmpty()) {
                System.out.println(pq.poll());
            } else if (inputNumber == 0 && pq.isEmpty()) {
                System.out.println(0);
            } else {
                pq.add(inputNumber);
            }
        }
        br.close();
    }
}
