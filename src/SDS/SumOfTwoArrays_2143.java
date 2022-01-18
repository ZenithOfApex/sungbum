package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SumOfTwoArrays_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        long T = Long.parseLong(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr1 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr2 = new int[M];
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> subA = makingSubArray(arr1);
        ArrayList<Integer> subB = makingSubArray(arr2);

        int count =0;

        HashMap<Integer, Integer> numberReduntantA = new HashMap<>();
        HashMap<Integer, Integer> numberReduntantB = new HashMap<>();
        for (Integer integer : subA) {
            if (numberReduntantA.keySet().contains(integer)) {
                int cnt = numberReduntantA.get(integer);
                cnt++;
                numberReduntantA.replace(integer, cnt);
            } else numberReduntantA.put(integer, 1);
        }
        for (Integer integer : subB) {
            if (numberReduntantB.keySet().contains(integer)) {
                int cnt = numberReduntantB.get(integer);
                cnt++;
                numberReduntantB.replace(integer, cnt);
            } else numberReduntantB.put(integer, 1);
        }

        for (Integer a : numberReduntantA.keySet()) {
            for (Integer b : numberReduntantB.keySet()) {
                if ((long)(a + b) == T) {
                    count += numberReduntantA.get(a) * numberReduntantB.get(b);
                }else continue;
            }
        }

//        while (start <= subN && end >= 0) {
//            if ((long)(subA.get(start) + subB.get(end)) == T) {
//                int repA = numberReduntantA.get(subA.get(start));
//                int repB = numberReduntantB.get(subB.get(end));
////                int repA = Collections.frequency(subA, subA.get(start));
////                int repB = Collections.frequency(subB, subB.get(end));
//                count += repA * repB;
//                while (start<=subN && end>=0) {
//                    start++;
//                    if (subA.get(curA) != subA.get(start)) {
//                        break;
//                    }
//                }curA = start;
//
//            } else if ((long)(subA.get(start) + subB.get(end)) < T) {
//                while (start<=subN) {
//                    start++;
//                    if (subA.get(curA) != subA.get(start)) {
//                        break;
//                    }
//                }curA = start;
//            } else {
//                while (end>=0) {
//                    if (subB.get(curB) != subB.get(end)) {
//                        break;
//                    }
//                    end--;
//                }curB = end;
//            }
//        }

        System.out.println(count);
        br.close();
    }

    public static ArrayList<Integer> makingSubArray(int[] arr) {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i : arr) {
            tempList.add(i);
        }
        for (int i = 0; i < arr.length-1; i++) {
            int sum =0;
            sum += arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                tempList.add(sum);
            }
        }
        return tempList;
    }
}
