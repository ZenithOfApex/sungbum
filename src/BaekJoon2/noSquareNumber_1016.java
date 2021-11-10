package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class noSquareNumber_1016 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        ArrayList<Integer> square = new ArrayList<>();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int totalLength = b-(a-1);

        br.close();

        //a와 b 사이의 제곱수 list만들고
        int subLength =0;//여따가 list.length넣고

        int mid = (int)Math.sqrt(b);
        for (int i = 1; i <= mid; i++) {
            int ssNum = i*i;
            if (ssNum >= a) {
                square.add(ssNum);
            }
        }
        subLength=square.size();
        int answer = totalLength-subLength;
        System.out.println(answer);
        System.out.println((int)Math.sqrt(1000));
    }
}
