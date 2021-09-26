package BaekJoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class antenna_18310 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> house = new ArrayList<>();
        ArrayList<where> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            house.add(num);
        }
        sc.close();

        Collections.sort(house);

        for (int i = 0; i < house.size(); i++) {
            int start = house.get(i);
            int sum=0;
            for (int j = 0; j < house.size(); j++) {
                sum+=Math.abs(house.get(j)-start);
            }
            result.add(new where(start, sum));

        }
        int answer= result.get(0).distance;
        int answerPosition=0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).distance < answer) {
                answer = result.get(i).distance;
                answerPosition = result.get(i).house;
            }
        }
        System.out.println(answerPosition);
    }
}
class where{
    int house;
    int distance;

    public where(int house, int distance) {
        this.house = house;
        this.distance = distance;
    }
}
