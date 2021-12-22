package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BeerWalking_9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());

        while (testCase > 0) {
            String answer = "sad";
            ArrayList<Pos> shop = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int shopNum = Integer.parseInt(st.nextToken());
            Pos curPos = new Pos(0, 0);
            st = new StringTokenizer(br.readLine(), " ");
            Pos home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            curPos.x = home.x;
            curPos.y = home.y;

            for (int i = 0; i < shopNum; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                Pos shopCor = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                shop.add(shopCor);
            }
            Collections.sort(shop, new Comparator<Pos>() {
                @Override
                public int compare(Pos o1, Pos o2) {
                    int dist1 = Math.abs(home.x - o1.x) + Math.abs(home.y - o1.y);
                    int dist2 = Math.abs(home.x - o2.x) + Math.abs(home.y - o2.y);
                    if (dist1 > dist2) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            for (Pos pos : shop) {
                System.out.println(pos.x + " " + pos.y);
            }

            st = new StringTokenizer(br.readLine(), " ");
            Pos target = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for (Pos pos : shop) {
                if (calcDist(curPos, pos) > 1000) {
                    answer = "sad";
                    break;
                }else{
                    curPos.x = pos.x;
                    curPos.y = pos.y;
                }
            }
            if (calcDist(curPos, target)<=1000) {
                answer = "happy";
            }

            System.out.println(answer);
            testCase--;
        }
    }

    public static int calcDist(Pos n, Pos m) {
        int dist = Math.abs(n.x - m.x) + Math.abs(n.y - m.y);
        return dist;
    }
}

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}