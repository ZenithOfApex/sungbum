package pathFinder;

import java.util.*;

public class PathFind {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] map;
    static boolean[][] visit;
    static int[][] result;

    public static void main(String[] args) {
        List<String> grid = new ArrayList<>();
        grid.add("..");
        grid.add("..");

        String answer = reachTheEnd(grid, 2);
        System.out.println(answer);

    }

    public static String reachTheEnd(List<String> grid, int maxTime) {
        // Write your code here
        String answer ="";
        int n = grid.size();
        int m = grid.get(0).length();

        map = new int[n][m];
        visit = new boolean[n][m];
        result = new int[n][m];
        Queue<Pos> q = new LinkedList<>();

        for(int i=0;i<n;i++){
            String temp = grid.get(i);
            for(int j=0;j<m;j++){
                if (String.valueOf(temp.charAt(j)).equals(".")) {
                    map[i][j] = 1;
                }else{
                    map[i][j] = 0;
                }
            }
        }


        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = -1;
            }
        }
        result[0][0] = maxTime;

        q.offer(new Pos(0, 0, maxTime));

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            int x = pos.curX;
            int y = pos.curY;
            int time = pos.time;


            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && time > 0) {
                    if (map[nx][ny] == 1 && !visit[nx][ny]) {
                        q.offer(new Pos(nx, ny, time - 1));
                        result[nx][ny] = time-1;
                        visit[nx][ny] = true;
                    }
                }
            }
        }
        int ret = result[n-1][m-1];
        if(ret>=0){
            answer = "Yes";
        }else{
            answer = "No";
        }
        return answer;
    }
}

class Pos{
    int curX;
    int curY;
    int time;

    public Pos(int curX, int curY, int time) {
        this.curX = curX;
        this.curY = curY;
        this.time = time;
    }
}
