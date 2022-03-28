package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheepAndWolf {

    static int answer = 0;
    private static Map<Integer, List<Integer>> nodes;

    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        System.out.println(solution(info, edges));
    }

    private static int solution(int[] info, int[][] edges) {
        nodes = new HashMap<>();

        //node 연결하는 과정
        for (int[] e : edges) {
            //(start, end)에서 start 노드가 없으면 새로 추가
            if (!nodes.containsKey(e[0])) {
                nodes.put(e[0], new ArrayList<>());
            }
            nodes.get(e[0]).add(e[1]);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list, info);
        return answer;
    }

    private static void dfs(int index, int s, int w, List<Integer> list, int[] info) {
        //방문한 info 인덱스가 양이면 양 s+1
        if (info[index] == 0) {
            s += 1;
        }//늑대이면 w+=1
        w+=1;

        if(s<=w) return;

        answer = Math.max(answer, s);

        List<Integer> next = new ArrayList<>();
        next.addAll(list);


        if (nodes.containsKey(index)) {
            next.addAll(nodes.get(index));
        }
        next.remove(Integer.valueOf(index));

        for (Integer i : next) {
            dfs(i, s, w, next, info);
        }

        return;
    }
}
