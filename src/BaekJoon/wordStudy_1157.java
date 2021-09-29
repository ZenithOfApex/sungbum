package BaekJoon;

import java.util.ArrayList;
import java.util.Scanner;

public class wordStudy_1157 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        String str = temp.toUpperCase();
        ArrayList<Character> alphabet = new ArrayList<>();
        ArrayList<word> dic = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (alphabet.contains(str.charAt(i))) {
                continue;
            }else{
                alphabet.add(str.charAt(i));
            }
        }//사용된 알파벳만 추가
        int maximum =0;
        for (int i = 0; i < alphabet.size(); i++) {
            int count =0;
            for (int j = 0; j < str.length(); j++) {
                if (alphabet.get(i).equals(str.charAt(j))) {
                    count++;
                }
            }
            maximum = Math.max(maximum, count);
            dic.add(new word(alphabet.get(i),count));
        }
        int maximum_count=0;
        for (word target : dic) {
            if (target.count == maximum) {
                maximum_count++;
            }
        }
        if (maximum_count > 1) {
            System.out.println("?");
        } else if (maximum_count == 1) {
            for (int i = 0; i < dic.size(); i++) {
                if (dic.get(i).count == maximum) {
                    System.out.println(dic.get(i).alphabet);
                }
            }
        }
    }
}

class word{
    char alphabet;
    int count;

    public word(char alphabet, int count) {
        this.alphabet = alphabet;
        this.count = count;
    }
}

