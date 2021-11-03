package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class addCycle_1110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int breakNum = n;
        int cycleCnt =0;
        br.close();

        while (true) {

            int addNum = n;
            int ten = addNum / 10;
            int one = addNum % 10;
            int sum = (ten + one) % 10;

            n = ((n%10)*10)+sum;
            cycleCnt++;
            if (n == breakNum) {
                break;
            }
        }
        System.out.println(cycleCnt);
    }
}
