package programmers;

import java.util.Stack;
import java.util.StringTokenizer;

public class EditingTable {
    static int targetIndex = 0;
    static int[][] graph;
    static int lastIndex = 0;
    static Stack<Integer> trashBin = new Stack<>();

    public static void main(String[] args) {

//        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
//        System.out.println(solution(8, 2, cmd));

        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(solution(8, 2, cmd));
    }


    private static String solution(int n, int k, String[] cmd) {
        graph = new int[n][2];
        targetIndex = k;
        lastIndex = n;

        executeCmd(cmd);
        String answer = buildStringAnswer();

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
                    moveDown(moveSize);
                    break;
                case "C":
                    delete();
                    break;
                case "U":
                    moveUp(moveSize);
                    break;
                case "Z":
                    rollback();
                    break;
            }
        }
    }

    private static void rollback() {
        if (trashBin.pop() <= targetIndex) {
            targetIndex++;
        }
        lastIndex++;
    }

    private static void moveUp(int move) {
        targetIndex -= move;
    }

    private static void delete() {
        //삭제 대상 인덱스 스택 저장
        trashBin.add(targetIndex);
        //일반적인 경우: 전체 한칸씩 줄어든다
        lastIndex--;
        if (targetIndex == lastIndex) {//마지막 행을 지우는 경우
            targetIndex--;
        }
    }

    private static void moveDown(int move) {
        targetIndex += move;
    }

    private static String buildStringAnswer() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lastIndex; i++) {
            sb.append("O");
        }
        //지워진 index만 x로 변경
        while (!trashBin.isEmpty()) {
            sb.insert(trashBin.pop().intValue(), "X");
        }

        return sb.toString();
    }
}
