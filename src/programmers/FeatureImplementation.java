package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FeatureImplementation {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] rtnValue = solution(progresses, speeds);
        for (int i : rtnValue) {
            System.out.println(i);
        }
    }

    private static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> forConversionList = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            queue.add((int) (Math.ceil((100.0 - progresses[i]) / speeds[i])));
        }

        while (!queue.isEmpty()) {
            int day = queue.poll();
            int cnt =1;

            while (!queue.isEmpty() && day >= queue.peek()) {
                cnt++;
                queue.poll();
            }
            forConversionList.add(cnt);
        }

        return forConversionList.stream().mapToInt(Integer::intValue).toArray();
    }
}
