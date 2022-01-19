package SDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Queue_10845 {

    static Deque<Integer> dq = new ArrayDeque<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine()," ");
            String command = st.nextToken();
            if (st.hasMoreTokens()) {
                String commandNum = st.nextToken();
                resultByCommand(command, commandNum);
            } else resultByCommand(command, "");
        }

        br.close();
    }

    private static void resultByCommand(String command, String commandNum) {
        if (command.equals("push") && !commandNum.equals("")) {
            int inputNum = Integer.parseInt(commandNum);
            dq.add(inputNum);
        } else if (command.equals("pop")) {
            if (dq.isEmpty()) {
                System.out.println(-1);
            }else System.out.println(dq.poll());
        } else if (command.equals("size")) {
            System.out.println(dq.size());
        } else if (command.equals("front")) {
            if (!dq.isEmpty()) {
                System.out.println(dq.peekFirst());
            }else System.out.println(-1);
        } else if (command.equals("empty")) {
            if (dq.isEmpty()) {
                System.out.println(1);
            } else System.out.println(0);
        } else if (command.equals("back")) {
            if (!dq.isEmpty()) {
                System.out.println(dq.peekLast());
            } else System.out.println(-1);
        }
    }
}
