package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DistributedProcess_1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testSize = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ArrayList<Integer> remainder = new ArrayList<>();
            remainder = checkingRemainder(a, remainder);

            int index = (b%remainder.size());
            int answer = 0;

            if (index == 0) {
                answer = remainder.get(remainder.size() - 1);
                if (answer == 0) {
                    System.out.println(10);
                }else System.out.println(answer);
            }else{
                if (remainder.get(index - 1)==0) {
                    System.out.println(10);
                }
                System.out.println(remainder.get(index-1));
            }
        }
    }

    private static ArrayList<Integer> checkingRemainder(int a, ArrayList<Integer> remainder) {
        for (int count = 1; count <= 4; count++) {//4번의 사이클 안에서 1의 자릿수 다 나온다
            //1의 자릿수 계산을 어떻게 할까 : integer -> String -> charAt(temp.length()-1)
            int value = (int) Math.pow(a, count);
            String valueToString = String.valueOf(value);
            int oneValue = Integer.parseInt(String.valueOf(valueToString.charAt(valueToString.length() - 1)));
            if (!remainder.contains(oneValue)) {
                remainder.add(oneValue);
            }
        }
        return remainder;
    }
}
