package BaekJoon2;

import java.io.*;
import java.util.*;

public class Newbie_1946 {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Applicant> applicants = new ArrayList<>();
            int count =0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                applicants.add(new Applicant(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            Collections.sort(applicants, new Comparator<Applicant>() {
                @Override
                public int compare(Applicant o1, Applicant o2) {
                    if (o1.paper > o2.paper) {
                        return 1;
                    } else return -1;
                }
            });


            count = checkCondition(applicants);
            System.out.println(n - count);
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public static int checkCondition(ArrayList<Applicant> applicants) {
        int cnt = 0;
        int min = applicants.get(0).face;
        for (int i = 1; i < applicants.size(); i++) {
            if (applicants.get(i).face >= min) {
                cnt++;
            }
            min = Math.min(applicants.get(i).face, min);
        }
        return cnt;
    }

    static class Applicant{
        int paper;
        int face;

        public Applicant(int paper, int face) {
            this.paper = paper;
            this.face = face;
        }
    }
}
