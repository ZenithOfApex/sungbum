package toss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Solution02 {

    public static void main(String[] args) {
//        int[] levels = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] levels = {1, 2, 3, 4};
        int[] levels = {1, 2, 3};
        System.out.println(solution(levels));

    }

    public static int solution(int[] levels) {
        int answer = 0;
        if (levels.length < 4) {
            answer = -1;
        } else {
            int levelSize = levels.length;
            int quarterIndex = levelSize / 4;
            Arrays.sort(levels);
            answer = levels[levelSize - quarterIndex];
        }

        return answer;
    }
}
