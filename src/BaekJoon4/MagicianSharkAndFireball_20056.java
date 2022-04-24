package BaekJoon4;

import java.io.*;
import java.util.*;

public class MagicianSharkAndFireball_20056 {
    static int N, M, K;
    static int answer = 0;
    static ArrayList<Fireball>[][] map;
    static ArrayList<Fireball> list = new ArrayList<>();
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Fireball(r, c, m, s, d));
        }

        while (K-- > 0) {
            move();
            checkFireball();
        }

        for (Fireball fireball : list) {
            answer += fireball.m;
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void checkFireball() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() == 1) {
                    map[i][j].clear();
                }

                if (map[i][j].size() < 2) {
                    continue;
                }

                int massSum = 0, sSum = 0;

                boolean even = map[i][j].get(0).d %2 ==0 ? true : false;
                boolean odd = map[i][j].get(0).d %2 ==1 ? true : false;

                for (Fireball cur : map[i][j]) {
                    massSum += cur.m;
                    sSum += cur.s;
                    even = even & cur.d % 2 == 0 ? true : false;
                    odd = odd & cur.d % 2 == 1 ? true : false;
                    list.remove(cur);
                }
                int newMass = massSum / 5;
                int size = map[i][j].size();
                map[i][j].clear();

                if (newMass == 0) {
                    continue;
                }
                int newS = sSum / size;
                if (even | odd) {
                    for (int k = 0; k < 8; k += 2) {
                        list.add(new Fireball(i, j, newMass, newS, k));
                    }
                } else {
                    for (int k = 1; k < 8; k += 2) {
                        list.add(new Fireball(i, j, newMass, newS, k));
                    }
                }
            }
        }
    }

    private static void move() {
        for (Fireball fireball : list) {
            int nx = (fireball.r + N + dx[fireball.d] * (fireball.s % N)) % N;
            int ny = (fireball.c + N + dy[fireball.d] * (fireball.s % N)) % N;

            fireball.r = nx;
            fireball.c = ny;
            map[nx][ny].add(fireball);
        }
    }

    static class Fireball{
        int r,c,m,s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
