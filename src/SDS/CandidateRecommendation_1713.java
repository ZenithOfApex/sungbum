package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CandidateRecommendation_1713 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Photo> list = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int student = Integer.parseInt(st.nextToken());
            if (list.size() < N) {
                boolean flag = false;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).num == student) {
                        list.get(j).cnt++;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.add(new Photo(i, student, 1));
                }
            }
            else{
                Collections.sort(list);
                boolean flag = false;
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).num == student) {
                        list.get(j).cnt++;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.remove(0);
                    list.add(new Photo(i, student, 1));
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            answer.add(list.get(i).num);
        }
        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i) + " ");
        }
        System.out.println(sb.toString());

        br.close();

    }

    public static class Photo implements Comparable<Photo>{
        int index, num, cnt;

        public Photo(int index, int num, int cnt) {
            this.index = index;
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Photo o) {
            if (this.cnt == o.cnt) {
                return this.index - o.index;
            }
            return this.cnt - o.cnt;
        }
    }

}
