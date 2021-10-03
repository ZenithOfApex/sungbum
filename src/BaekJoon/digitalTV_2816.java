package BaekJoon;

import java.util.Scanner;

public class digitalTV_2816 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] channel = new String[num];
        for (int i = 0; i < num; i++) {
            String temp = sc.nextLine();
            channel[i] = temp;
        }
        for (int i = 0; i < channel.length; i++) {
            System.out.println(channel[i]);
        }

        while (!checkFinished(channel)) {

        }
    }

    public static boolean checkFinished(String[] arr){
        boolean status =false;
        if (arr[0].equals("KBS1") && arr[1].equals("KBS2")) {
            status = true;
        }
        return status;
    }
}
