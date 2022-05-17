package programmers;

import java.util.StringTokenizer;

public class EditingTable {
    public static void main(String[] args) {

        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        System.out.println(solution(8, 2, cmd));

//        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
//        System.out.println(solution(8, 2, cmd));
    }


    private static String solution(int n, int k, String[] cmd) {
        String answer = "";

        executeCmd(cmd);

        return answer;
    }

    private static void executeCmd(String[] cmd) {

        for (String s : cmd) {
            StringTokenizer st = new StringTokenizer(s, " ");
            String command = st.nextToken();
            int moveSize = 0;
            if (st.hasMoreTokens()) {
                moveSize = Integer.parseInt(st.nextToken());
            }

            switch (command) {
                case "D":
                    moveDown();
                    break;
                case "C":
                    delete();
                    break;
                case "U":
                    moveUp();
                    break;
                case "Z":
                    rollback();
                    break;
            }
        }
    }

    private static void rollback() {

    }

    private static void moveUp() {

    }

    private static void delete() {

    }

    private static void moveDown() {

    }


}
