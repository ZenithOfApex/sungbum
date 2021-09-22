package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class dwarf_2309 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[9];
        ArrayList<Integer> dwarf = new ArrayList<>();
        int sum=0;
        for(int i=0;i<9;i++){
            dwarf.add(sc.nextInt());
        }
        for (int i = 0; i<dwarf.size(); i++) {
            sum += dwarf.get(i);
        }

        for(int i=0;i< dwarf.size();i++){
            for (int j = i + 1; j < dwarf.size(); j++) {
                if (sum - dwarf.get(i) - dwarf.get(j) == 100) {
                    dwarf.remove(i);
                    dwarf.remove(j);
                    Collections.sort(dwarf);
                    for (int k = 0; k < dwarf.size(); k++) {
                        System.out.println(dwarf.get(k));
                    }
                }else continue;
            }
        }
    }
}
