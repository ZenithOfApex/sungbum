package SDS;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SumOfTwoArrays2_2143 {

    static ArrayList<Integer> subA, subB;
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr1 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr2 = new int[M];
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }



        subA = makingSubArray(arr1);
        subB = makingSubArray(arr2);

        Collections.sort(subA);
        Collections.sort(subB);

        bw.write(getCount() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static long getCount(){
        int pa =0;
        int pb = subB.size()-1;
        long cnt = 0;

        while (pa < subA.size() && pb >= 0) {
            long sum = subA.get(pa) + subB.get(pb);

            if (sum == T) {
                int a = subA.get(pa);
                int b = subB.get(pb);
                long aCnt = 0;
                long bCnt = 0;

                while (pa < subA.size() && subA.get(pa) == a) {
                    aCnt++;
                    pa++;
                }
                while (pb >= 0 && subB.get(pb) == b) {
                    bCnt++;
                    pb--;
                }

                cnt+=aCnt * bCnt;
            } else if (sum < T) {
                pa++;
            } else if (sum > T) {
                pb--;
            }
        }
        return cnt;
    }

    public static ArrayList<Integer> makingSubArray(int[] arr) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int sum =0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                tempList.add(sum);
            }
        }
        return tempList;
    }
}
