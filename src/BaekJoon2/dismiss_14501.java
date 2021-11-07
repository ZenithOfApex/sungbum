package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dismiss_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<tiPi> sched = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int ti = Integer.parseInt(st.nextToken());
            int pi = Integer.parseInt(st.nextToken());

            sched.add(new tiPi(ti, pi));
        }
        br.close();

        for (tiPi tiPi : sched) {
            System.out.println("tiPi.ti = " + tiPi.ti);
            System.out.println("tiPi.pi = " + tiPi.pi);
            System.out.println();
        }

    }
}

class tiPi{
    int ti;
    int pi;

    public tiPi(int ti, int pi) {
        this.ti = ti;
        this.pi = pi;
    }
}
