package BaekJoon3;

import java.io.*;
import java.util.Arrays;

public class RobotProject_3649 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int toMake = 0;
        String input;

        while ((input = br.readLine()) != null) {
            toMake = Integer.parseInt(input)*10000000;
            int legos = Integer.parseInt(br.readLine());
            int[] numbers = new int[legos];

            for (int i = 0; i < legos; i++) {
                numbers[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(numbers);

            int sp =0;
            int ep = numbers.length - 1;
            boolean flag = false;
            while (sp < ep) {
                if (numbers[sp] + numbers[ep] > toMake) {
                    ep--;
                } else if (numbers[sp] + numbers[ep] < toMake) {
                    sp++;
                } else if (numbers[sp] + numbers[ep] == toMake) {
                    flag = true;
                    System.out.println("yes " + numbers[sp] + " " + numbers[ep]);
                    break;
                }
            }
            if(!flag) System.out.println("danger");
        }

        br.close();
    }
}
