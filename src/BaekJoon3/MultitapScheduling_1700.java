package BaekJoon3;

import java.io.*;
import java.util.*;

public class MultitapScheduling_1700 {
    static int answer =0;
    static int N, K;
    static int[] record;

    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        record = new int[K];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            record[i] = Integer.parseInt(st.nextToken());
        }

        set = new HashSet<>();

        for (int i = 0; i < K; i++) {
            if (!set.contains(record[i])) {//새로 들어오는 숫자가 멀티탭에 꽂혀있지 않는 경우
                if (N <= set.size()) {//멀티탭에 여유가 없는 경우 여유 만들어주기
                    ArrayList<Integer> window = new ArrayList<>();//똑같은 크기만큼 보자
                    Set<Integer> check = new HashSet<>(set);//set와 window 비교하기 위함

                    for (int j = i; j < K; j++) {//sliding window(N 사이즈만큼)
                        if (set.contains(record[j]) && !window.contains(record[j])) {//set에는 수가 들어있지만 앞으로의 숫자에는 포함되어 있지않은 경우
                            window.add(record[j]);
                            check.remove(record[j]);
                        }
                    }

                    if (window.size() == N) {//window가 다 만들어지면 기존의 set에서 하나 제거해주기(여유 만들어주기)
                        set.remove(window.get(window.size() - 1));
                    } else {
                        ArrayList<Integer> temp = new ArrayList<>(check);//window가 다 만들어지지 않아도 진행은 해야되니깐 하나 뺴주기(여유 만들어주기)
                        set.remove(temp.get(0));
                    }
                    answer++;//여유 만들어줘서 장비 끼울 수 있으니깐 횟수 추가
                }
                //만들어진 여유 공간에 값 추가
                set.add(record[i]);
            }
        }

        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
