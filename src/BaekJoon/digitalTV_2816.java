package BaekJoon;

import java.util.Scanner;

public class digitalTV_2816 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        String[] channel = new String[num];

        for (int i = 0; i < num; i++) {
            channel[i] = sc.nextLine();
        }
        sc.close();
        StringBuilder sb = new StringBuilder();

        int targetOneIndex = 0;
        int targetTwoIndex = 0;

        for (int i = 0; i < channel.length; i++) {
            if (channel[i].equals("KBS1")) {
                targetOneIndex = i;
            } else if (channel[i].equals("KBS2")) {
                targetTwoIndex = i;
            }
        }
        for (int i = 0; i < targetOneIndex; i++) {
            sb.append("1");
        }
        for (int i = 0; i < targetOneIndex; i++) {
            sb.append("4");
        }
        if (targetOneIndex > targetTwoIndex) {
            targetTwoIndex++;
        }
        for (int i = 0; i < targetTwoIndex; i++) {
            sb.append("1");
        }
        for (int i = 0; i < targetTwoIndex - 1; i++) {
            sb.append("4");
        }
        System.out.println(String.valueOf(sb));
    }

}
