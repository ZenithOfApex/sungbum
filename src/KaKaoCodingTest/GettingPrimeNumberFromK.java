package KaKaoCodingTest;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GettingPrimeNumberFromK {
    public static void main(String[] args) {
//        int n=110011;
//        int k=10;
        int n = 437674;
        int k=3;
        int result = solution(n, k);
        System.out.println(result);
    }

    public static int solution(int n, int k) {
        int answer =-1;
        String str = conversion(n, k);
//        Long strNum = Long.parseLong(str);
        ArrayList<String> result2 = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str, "0");
        while (st.hasMoreTokens()) {
            String target = st.nextToken();
            int targetNum = Integer.parseInt(target);
            if (targetNum >= 2) {
                result2.add(target);
            }
        }
        int count=0;
        for(int i=0;i<result2.size();i++){
            int checkNum = Integer.parseInt(result2.get(i));
            if(checkNum==1) break;
            if(checkNum==2){
                count++;
            }else{
                boolean prime = false;
                for(int j=2;j<checkNum;j++){
                    if(checkNum%j==0){
                        prime = true;
                        System.out.println(checkNum);
                        break;
                    }
                }
                if(prime==false) count++;
            }
        }
        answer = count;
        return answer;
    }

    public static String conversion(int number, int n){
        StringBuilder sb = new StringBuilder();
        while (number >= 1) {
            sb.insert(0, number % n);
            number/=n;
        }
        return sb.toString();
    }
}
