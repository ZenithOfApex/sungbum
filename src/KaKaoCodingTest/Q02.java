package KaKaoCodingTest;

import java.util.ArrayList;

public class Q02 {
    public static void main(String[] args) {
        int n=110011;
        int k=10;
//        int n = 437674;
//        int k=3;
        int result = solution(n, k);
        System.out.println(result);
    }
    public static int solution(int n, int k) {
        int answer = -1;
        String str = conversion(n, k);
        System.out.println(str);
        Long strNum = Long.parseLong(str);
        System.out.println("strNum = " + strNum);
        String[] result = str.split("0");
        ArrayList<String> result2 = new ArrayList<String>();
//        String[] result2 = new String[result.length * 2];
        for(int i=0;i<result.length;i++){
            System.out.println("result = " + result[i]);
            if (Integer.parseInt(result[i]) >= 2) {
                result2.add(result[i]);
            }
        }
        for(int i=0;i<result2.size();i++){
            System.out.println("result2.get(i) = " + result2.get(i));
        }
        int count=0;
        for(int i=0;i<result2.size();i++){
            System.out.println(Integer.parseInt(result2.get(i))+" a");
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
