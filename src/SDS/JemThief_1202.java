package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JemThief_1202 {

    static PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
    static ArrayList<Jem> jemList = new ArrayList<>();
    static ArrayList<Integer> bagList = new ArrayList<>();
    static ArrayList<Long> jemTaken = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jemList.add(new Jem(weight, price));
        }
        for (int j = 0; j < k; j++) {
            int bagWeight = Integer.parseInt(br.readLine());
            bagList.add(bagWeight);
        }

        Collections.sort(bagList);

        Collections.sort(jemList, new Comparator<Jem>() {
            @Override
            public int compare(Jem o1, Jem o2) {
                if (o1.weight > o2.weight) {
                    return 1;
                }else return -1;
            }
        });
        long totalPrice =0;
        int j=0;
        for (Integer bag : bagList) {
            while (j < n && (jemList.get(j).weight <= bag)) {
                pq.add(jemList.get(j).price);
                j++;
            }
            if (!pq.isEmpty()) {
                totalPrice += pq.poll();
            }
        }

        System.out.println(totalPrice);
        br.close();

    }

    static class Jem {
        int weight;
        long price;

        public Jem(int weight, long price) {
            this.weight = weight;
            this.price = price;
        }
    }
}
