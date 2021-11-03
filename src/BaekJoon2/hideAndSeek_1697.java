package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class hideAndSeek_1697 {
//    static int INF = 100;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine()," ");
//        int n = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//        int[] numbers = new int[100001];
//        int level = 0;
//
//        for (int i = 0; i < numbers.length; i++) {
//            numbers[i] = INF;
//        }
//        numbers[n] = level;
//        while (true) {
//            for (int i = 0; i < numbers.length; i++) {
//                if (numbers[i] == level) {
//                    if (i - 1 >= 0 && i + 1 < numbers.length && 2 * i < numbers.length) {
//                        if (numbers[i - 1] == INF) {
//                            numbers[i - 1] = level + 1;
//                        }
//                        if (numbers[i + 1] == INF) {
//                            numbers[i + 1] = level + 1;
//                        }
//                        if (numbers[2 * i] == INF) {
//                            numbers[i * 2] = level+1;
//                        }
//                    }
//                }
//            }
//            if (numbers[k] != INF) {
//                System.out.println(numbers[k]);
//                break;
//            }
//            level++;
//        }
//    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Integer> ar = new ArrayList<Integer>();
        br.close();

        int level = 0;
        ar.add(n);
        while(true){
            System.out.println(ar);
            if (ar.contains(k)) {
                System.out.println(level);
                break;
            }

            for (int i = 0; i < ar.size(); i++) {
                int number = ar.get(i);
                int addNum = number+1;
                int subNum = number-1;
                int mulNum = number*2;
                if (!ar.contains(addNum) && addNum >= 0) {
                    ar.add(addNum);
                }
                if (!ar.contains(subNum) && subNum >= 0) {
                    ar.add(subNum);
                }
                if (!ar.contains(mulNum) && mulNum >= 0) {
                    ar.add(mulNum);
                }
            }
            level++;
        }
        System.out.println();
    }
}
