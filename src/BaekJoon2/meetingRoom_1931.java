package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class meetingRoom_1931 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        ArrayList<timeTable> list = new ArrayList<>();
//
//        int n = Integer.parseInt(st.nextToken());
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            list.add(new timeTable(a,b));
//        }
//        br.close();
//
//        int cnt=0;
//        int maxTime=0;
//        for (timeTable t : list) {
//            maxTime = Math.max(maxTime, t.endTime);
//        }//입력받은 수 중 가장 큰 값 추출
//
//        int[] timeArr = new int[maxTime+1];
//        for (timeTable t : list) {
//            int startIndex = t.startTime;
//            int endIndex = t.endTime;
//            int[] tempArr = Arrays.copyOfRange(timeArr, startIndex, endIndex);
//            if(Arrays.stream(tempArr).anyMatch(s->s!=0)){
//                continue;
//            }
//            else{
//                for (int i = startIndex; i <= endIndex; i++) {
//                    timeArr[i]=1;
//                }
//                cnt++;
//            }
//        }
//        System.out.println(cnt);
//    }
//
//    static class timeTable{
//        int startTime;
//        int endTime;
//
//        public timeTable(int startTime, int endTime) {
//            this.startTime = startTime;
//            this.endTime = endTime;
//        }
//    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] start, int[] end) {
                if (start[1] == end[1]) {
                    return Integer.compare(start[0], end[0]);
                }
                return Integer.compare(start[1], end[1]);
            }
        });

        int count=0;
        int end=-1;
        for (int i = 0; i < n; i++) {
            if (arr[i][0] >= end) {
                end=arr[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}
