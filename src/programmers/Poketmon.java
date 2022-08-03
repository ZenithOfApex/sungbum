package programmers;

import java.util.HashMap;
import java.util.HashSet;

public class Poketmon {

    static HashMap<Integer, Integer> poketDic = new HashMap<>();

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 3};
        int answer = solution(nums);
        System.out.println(answer);

    }

    public static int solution(int[] nums) {
        int size = nums.length;
        int answer = 0;
        int targetSize = size / 2;
        HashSet<Integer> checked = new HashSet<>();

        for (int poketmon : nums) {
            if (poketDic.get(poketmon) == null) {
                poketDic.put(poketmon, 1);
            } else {
                int curPoketmonCnt = poketDic.get(poketmon);
                poketDic.put(poketmon, curPoketmonCnt + 1);
            }
        }

        for (Integer poket : poketDic.keySet()) {
            if (targetSize == 0) break;
            if (poketDic.get(poket) > 0) {
                answer++;
                targetSize--;
                int temp = poketDic.get(poket);
                checked.add(poket);
                poketDic.put(poket, temp - 1);
            }else continue;
        }

        if (targetSize != 0) {
            for (Integer poket : poketDic.keySet()) {
                if (targetSize == 0) break;
                if (poketDic.get(poket) > 0) {
                    targetSize--;
                    int temp = poketDic.get(poket);
                    checked.add(poket);
                    poketDic.put(poket, temp - 1);
                }else continue;
            }
        }

        return answer;
    }
}
