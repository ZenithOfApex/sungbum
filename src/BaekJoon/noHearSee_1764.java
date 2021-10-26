package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class noHearSee_1764 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        ArrayList<String> noSee = new ArrayList<>();
        ArrayList<String> noHear = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String temp_name = sc.nextLine();
            noHear.add(temp_name);
        }
        for (int i = 0; i < m; i++) {
            String temp_name = sc.nextLine();
            noSee.add(temp_name);
        }
        int cnt =0;
        for (int i = 0; i < noHear.size(); i++) {
            for (int j = 0; j < noSee.size(); j++) {
                if (noHear.get(i).equals(noSee.get(j))) {
                    cnt++;
                    result.add(noHear.get(i));
                }
            }
        }
        Collections.sort(result);
        System.out.println(cnt);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
