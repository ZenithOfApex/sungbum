package toss;

import java.util.ArrayList;
import java.util.Collections;

public class Solution03 {
    public static void main(String[] args) {
        int[][] dungeon = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(80, dungeon));
    }

    public static int solution(int k, int[][] dungeons) {
        int answer = 0;
        ArrayList<Integer> availableDun = new ArrayList<>();

        for (int[] dungeon : dungeons) {
            if (dungeon[0] > k) {
                continue;
            } else {
                availableDun.add(dungeon[1]);
            }
        }

        Collections.sort(availableDun);
        int temp = k;

        for (Integer can : availableDun) {
            int check = temp - can;
            if (check >= 0) {
                answer++;
                temp = temp - can;
            } else {
                break;
            }
        }
        return answer;
    }
}
