package SDS;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Stack_10828 {

    static Stack<Integer> stack = new Stack<>();
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
            stack.add(inputNum);
        } else if (command.equals("pop")) {
            if (stack.isEmpty()) {
                System.out.println(-1);
            }else System.out.println(stack.pop());
        } else if (command.equals("size")) {
            System.out.println(stack.size());
        } else if (command.equals("empty")) {
            if (stack.isEmpty()) {
                System.out.println(1);
            }else System.out.println(0);
        } else if (command.equals("top")) {
            if (!stack.isEmpty()) {
                System.out.println(stack.peek());
            }else System.out.println(-1);
        }
    }
}
