package BaekJoon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class bridge_1010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        ArrayList<test> testCase = new ArrayList<>();
        HashMap<Integer, Boolean> leftSite = new HashMap<>();
        HashMap<Integer, Boolean> rightSite = new HashMap<>();
        for(int i=0;i<size;i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            testCase.add(new test(n,m));
        }

        for(int caseNum =0; caseNum<testCase.size();caseNum++){
            int n = testCase.get(caseNum).left;
            int m = testCase.get(caseNum).right;

            //입력받은 testCase에 따라 leftSite, rightSite hashmap에 false로 put
            for(int i=0;i<n;i++){
                leftSite.put(i, false);
            }
            for(int i=0;i<m;i++){
                rightSite.put(i, false);
            }//
            int cnt =0;
            int result = 0;
            int tempM=0;
            for(int i=0;i<n;i++){
                if (leftSite.get(i)== true) {//leftSite의 site가 이미 다리가 설치되어 있는 경우 continue
                    continue;
                }else{//아닌 경우에 다리 설치 시작
                    for(int j=tempM ;j<m-1;j++){//
                        if (rightSite.get(j) == false) {
                            rightSite.put(j,true);
                            leftSite.put(i,true);
                            tempM = j+1;
                            break;
                        }else continue;
                    }
                }
                if (i == n - 1) {
                    cnt+=(m-tempM);
                }
                result +=cnt;
            }
            System.out.println("result = " + result);
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
}
