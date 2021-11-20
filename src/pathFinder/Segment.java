package pathFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Segment {
    public static void main(String[] args) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(2);
        temp.add(5);
        temp.add(4);
        temp.add(6);
        temp.add(8);
//        temp.add(8);
//        temp.add(2);
//        temp.add(4);
//        temp.add(6);
//        temp.add(1);
//        temp.add(2);
        int answer = segment(3,temp);
        System.out.println(answer);
    }

    public static int segment(int x, List<Integer> space){
        int answer =0;

        if(x==1){
            Collections.sort(space);
            answer = space.get(space.size() - 1);
        }else{
            ArrayList<Integer> minValue = new ArrayList<>();
            for (int i = 0; i + x <= space.size(); i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = i; j < i+x; j++) {
                    temp.add(space.get(j));
                }
                Collections.sort(temp);
                minValue.add(temp.get(0));
            }
            Collections.sort(minValue);
            answer = minValue.get(minValue.size() - 1);

        }

        return answer;
    }
}
