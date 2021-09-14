package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class dwarf_2309 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[9];
        for(int i=0;i<9;i++){
            arr[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);//오름차순 정렬
        }
        System.out.println();
        System.out.println();
        ArrayList<Integer> answer = new ArrayList<>();

        Combination.combination comb = new Combination.combination(arr.length, 7);
        comb.combination(arr, 0, 0, 0);
        ArrayList<ArrayList<Integer>> result = comb.getResult();
        for(int i=0;i<result.size();i++){
            int sum=0;
            for(int j=0;j<result.get(i).size();j++){
                sum+=result.get(i).get(j);
            }
            if(sum==100){
                for(int j=0;j<result.get(i).size();j++){
                    answer = result.get(i);
//                    System.out.println(result.get(i).get(j));
                }
                System.exit(0);
            }
        }
    }
}
