package SDS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CardPlay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testcase = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int turn = 0;
        while (turn < testcase) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            Deque<Integer> oddArr = new ArrayDeque<>();
            Deque<Integer> evenArr = new ArrayDeque<>();
            ArrayList<Integer> arr = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0;i<length;i++){
                int inputNum = Integer.parseInt(st.nextToken());
                arr.add(inputNum);
            }
            Collections.sort(arr);
            Collections.reverse(arr);//내림차순으로 변환
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) % 2 == 0) {
                    evenArr.add(arr.get(i));
                } else oddArr.add(arr.get(i));
            }
            int answer = solution(arr, oddArr, evenArr);
            hm.put(turn + 1, answer);
            turn++;
        }
        br.close();

        for (Integer testcaseNum : hm.keySet()) {
            System.out.println("#" + testcaseNum + " " + hm.get(testcaseNum));
        }
    }

    public static int solution(ArrayList<Integer> arr, Deque<Integer> oddArr, Deque<Integer> evenArr) {
        int answer =0;
        while (!evenArr.isEmpty()) {
            if (evenArr.size() != 1) {
                answer += evenArr.poll();
                evenArr.pollLast();
            }else{
                answer += Math.max(evenArr.poll(), oddArr.poll());

            }
        }
        while (!oddArr.isEmpty()) {
            if (oddArr.size() != 1) {
                answer += oddArr.poll();
                oddArr.pollLast();
            }else{
                answer += Math.max(evenArr.poll(), oddArr.poll());
            }
        }

        return answer;
    }
}
