package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class dwarf_2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dwarf = new int[9];

        int sum=0;

        for(int i=0;i<9;i++){
            int inputNum = Integer.parseInt(br.readLine());
            dwarf[i] = inputNum;
            sum += inputNum;
        }
        int checkPoint = sum - 100;
        Arrays.sort(dwarf);
        for (int i = 0; i < 8; i++) {
            boolean flag = true;
            for (int j = i+1; j < 9; j++) {
                if (dwarf[i] + dwarf[j] == checkPoint) {
                    dwarf[i]=0;
                    dwarf[j]=0;
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        Arrays.sort(dwarf);
        for (int i : dwarf) {
            if (i != 0) {
            System.out.println(i);
            }
        }

        br.close();
    }
}
