package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class repairMan_1449 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int tapeLength = sc.nextInt();
        int[] spot = new int[size];

        for(int i=0;i<size;i++){
            spot[i] = sc.nextInt();
        }

        Arrays.sort(spot);
        int cnt = 0;

        for(int i=0;i<size;i++){
            if(i==size-1){
                cnt++;
                continue;
            }
            int curr = spot[i++];
            while(i<size) {
                if(spot[i] - curr <= tapeLength - 1) {
                    i++;
                }
                else {
                    break;
                }
            }
            cnt++;
            i--;
        }

        System.out.println(cnt);
    }
}
