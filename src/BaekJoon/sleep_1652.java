package BaekJoon;

import java.util.Scanner;

public class sleep_1652 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        StringBuilder sb = new StringBuilder();//세로로 저장하기 위함
        String[][] str = new String[n][n];
        int garo, saero;
        for(int i=0;i<n;i++){
            int temp=0;
            String s = sc.nextLine();
            String[] arr = s.split("X");
            for(int j=0;j<arr.length;j++){
                if (arr[i].contains(".") && arr[i].length() >= 2) {
                    temp++;
                }
            }
            System.out.println(temp);
            System.out.println(arr[i].length());
            temp=0;
        }

    }
}
