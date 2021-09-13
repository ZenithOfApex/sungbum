package BaekJoon;

import java.util.ArrayList;
import java.util.Scanner;

public class bridge_1010_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        ArrayList<test> testCase = new ArrayList<>();
        for(int i=0;i<size;i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            testCase.add(new test(n,m));
        }

        for(int i=0;i<testCase.size();i++){
            int left = testCase.get(i).left;
            int right = testCase.get(i).right;
            int[] arr = new int[right];
            for (int j = 0; j < right; j++) {
                arr[j] = j;
            }

            combination comb = new combination(arr.length,left);
            comb.combination(arr, 0, 0, 0);
            ArrayList<ArrayList<Integer>> result = comb.getResult();
            System.out.println(result.size());
        }
    }

    static class test{
        private int left;
        private int right;

        public test(int left, int right) {
            this.left = left;
            this.right = right;
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
