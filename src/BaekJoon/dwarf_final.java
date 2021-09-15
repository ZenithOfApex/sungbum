package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class dwarf_final {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[9];
        for(int i=0;i<9;i++){
            arr[i] = sc.nextInt();
        }
        sc.close();

        ArrayList<Integer> answer = new ArrayList<>();

        combination comb = new combination(arr.length, 7);
        comb.combination(arr, 0, 0, 0);
        ArrayList<ArrayList<Integer>> result = comb.getResult();
        for(int i=0;i<result.size();i++){
            int sum=0;
            for(int j=0;j<result.get(i).size();j++){
                sum+=result.get(i).get(j);
            }
            if(sum==100){
                for(int j=0;j<result.get(i).size();j++){
                    answer.add(result.get(i).get(j));
                }
            }
        }
        Collections.sort(answer);
        for(int i=0;i<answer.size();i++){
            System.out.println(answer.get(i));
        }
    }

    static class combination{
        private int n;
        private int r;
        private int[] now;
        private ArrayList<ArrayList<Integer>> result;

        public ArrayList<ArrayList<Integer>> getResult(){
            return result;
        }

        public combination(int n, int r) {
            this.n = n;
            this.r = r;
            this.now = new int[r];
            this.result = new ArrayList<ArrayList<Integer>>();
        }

        public void combination(int[] arr, int depth, int index, int target){
            if(depth == r){
                ArrayList<Integer> temp = new ArrayList<>();
                for(int i=0;i<now.length;i++){
                    temp.add(arr[now[i]]);
                }
                result.add(temp);
                return;
            }
            if(target==n) return;
            now[index] = target;
            combination(arr, depth + 1, index + 1, target + 1);
            combination(arr, depth, index, target + 1);
        }
    }
}
