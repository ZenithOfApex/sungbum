package BaekJoon;

import java.util.ArrayList;

public class Combination {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int r =3;

        combination comb = new combination(arr.length,r);
        comb.combination(arr, 0, 0, 0);
        ArrayList<ArrayList<Integer>> result = comb.getResult();
        System.out.println(result.size());
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
