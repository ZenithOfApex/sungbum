package BaekJoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Emoticon_14226 {

    static int S;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        visited = new boolean[2001][2001];

        bfs();
        br.close();
    }

    private static void bfs() {
        Queue<Emoticons> queue = new LinkedList();

        queue.offer(new Emoticons(1, 0,0));
        visited[0][1] = true;

        while (!queue.isEmpty()) {
            Emoticons polled = queue.poll();

            int emoticon_num = polled.emoticon_num;
            int clipboard_num = polled.clipboard_num;
            int time = polled.time;

            if (emoticon_num == S) {
                System.out.println(time);
                return;
            }


            if (emoticon_num > 0 && emoticon_num < 2000) {
                //클립보드에 복사하는 operation
                if (!visited[emoticon_num][emoticon_num]) {
                    visited[emoticon_num][emoticon_num] = true;
                    queue.offer(new Emoticons(emoticon_num, emoticon_num, time + 1));
                }
                //출력된 이모티콘 중에 하나 제거하는 operation
                if (!visited[emoticon_num - 1][clipboard_num]) {
                    visited[emoticon_num - 1][clipboard_num] = true;
                    queue.offer(new Emoticons(emoticon_num - 1, clipboard_num, time + 1));
                }
            }
            if (clipboard_num > 0 && emoticon_num + clipboard_num < 2000) {
                //복붙하는 operation
                if (!visited[emoticon_num + clipboard_num][clipboard_num]) {
                    visited[emoticon_num + clipboard_num][clipboard_num] = true;
                    queue.offer(new Emoticons(emoticon_num + clipboard_num, clipboard_num, time + 1));
                }
            }
        }

    }

    static class Emoticons{
        int emoticon_num;
        int clipboard_num;
        int time;

        public Emoticons(int emoticon_num, int clipboard_num, int time) {
            this.emoticon_num = emoticon_num;
            this.clipboard_num = clipboard_num;
            this.time = time;
        }
    }
}
