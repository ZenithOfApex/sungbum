package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheepAndWolf_Answer {

    private static int MaxCnt;
    private static Map<Integer, List<Integer>> nodes;


    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        System.out.println(solution(info, edges));
    }

    private static int solution(int[] info, int[][] edges) {
        MaxCnt = 0;
        nodes = new HashMap<>();

        for (int[] edge : edges) {
            if(!nodes.containsKey(edge[0]))
                nodes.put(edge[0], new ArrayList<>());
            nodes.get(edge[0]).add(edge[1]);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list, info);
        return MaxCnt;
    }

    private static void dfs(int index, int s, int w, List<Integer> list, int[] info) {
        if(info[index]==0) s+=1;
        else w+=1;
        if(s<=w) return;

        MaxCnt = Math.max(MaxCnt, s);

        List<Integer> next = new ArrayList<>();
        next.addAll(list);
        if(nodes.containsKey(index))
            next.addAll(nodes.get(index));
        next.remove(Integer.valueOf(index));

        for (Integer n : next) {
            dfs(n, s, w, next, info);
        }

        return;
    }
}
