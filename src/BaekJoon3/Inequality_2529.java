package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Inequality_2529 {

    static ArrayList<String> permutationList = new ArrayList<>();
    static ArrayList<String> answerList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] number = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        String[] signs = new String[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            signs[i] = st.nextToken();
        }

        System.out.println("entered");
        permutation(number, 0, 10, n+1);

        for (String perm : permutationList) {
            int cnt =0;
            for (int i = 0; i < perm.length()-1; i++) {
                if (checkInequality(perm.charAt(i), perm.charAt(i + 1), signs[i])) {
                    if (!answerList.contains(perm)) {
                        cnt++;
                    }else break;
                }else break;
            }
            if (cnt == perm.length()-1) {
                answerList.add(perm);
            }
        }



        System.out.println(permutationList);
        Collections.sort(answerList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Integer.parseInt(o1) > Integer.parseInt(o2)) {
                    return 1;
                }else return -1;
            }
        });
        System.out.println(answerList);
        System.out.println("answerLIst.get(answerList.size()-1) = " + answerList.get(answerList.size() - 1));
        System.out.println("answerList = " + answerList.get(0));

    }

    public static boolean checkInequality(char a, char b, String sign) {
        if (sign.equals(">")) {
            return Integer.parseInt(String.valueOf(a)) > Integer.parseInt(String.valueOf(b)) ? true : false;
        } else return Integer.parseInt(String.valueOf(a)) < Integer.parseInt(String.valueOf(b)) ? true : false;
    }

    public static void permutation(int[] arr, int depth, int n, int r) {
        if (depth == r) {
            permutationList.add(save(arr, r));
            return;
        }
        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    public static String save(int[] arr, int r) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
}
