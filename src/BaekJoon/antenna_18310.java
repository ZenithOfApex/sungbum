package BaekJoon;

import java.util.*;

public class antenna_18310 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> house = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int index = sc.nextInt();
            int distance =0;
            house.put(index, distance);
        }

//        for (Integer key : house.keySet()) {
//            int start = key;
//
//            System.out.println(house.keySet());
//            System.out.println("key = " + key);
//            System.out.println("house.get(key) = " + house.get(key));
//        }
//        for (int i = 0; i < house.keySet().size(); i++) {
//            System.out.println(house.keySet());
//        }
        Iterator<Integer> keys = house.keySet().iterator();
        while (keys.hasNext()) {
            Integer key = keys.next();
            System.out.println(key);
        }

//        int answer = 0;
//        for (int i = 0; i < n; i++) {
//            int num = sc.nextInt();
//            house[i] = num;
//            answer+=num;
//        }
//        sc.close();
//        Arrays.sort(house);
//        int result=0;
//        for (int i = 0; i < n; i++) {
//            int start = house[i];
//            int sum=0;
//            for (int j = 0; j < house.length; j++) {
//                sum += Math.abs(house[j] - start);
//            }
//            if (answer > sum) {
//                answer = sum;
//                result = start;
//            }
//            answer = Math.min(answer, sum);
//        }
//        System.out.println(result);
    }
}
