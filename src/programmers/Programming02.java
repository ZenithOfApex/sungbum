package programmers;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Programming02 {
    static Stack<String> stack = new Stack<>();
    static Deque<String> deque = new LinkedList<>();
    public static void main(String[] args) {
        String[] records = {"john share", "mary comment", "jay share", "check notification", "check notification", "sally comment", "james share", "check notification", "lee share", "laura share", "will share", "check notification", "alice comment", "check notification"};
        String[] answer = solution(records);

        System.out.println("answer");
        for (String s : answer) {
            System.out.println(s);
        }
        System.out.println();
    }

    private static String[] solution(String[] records) {
        for (String record : records) {
            if (record.equals("check notification")) {
                //stack에서 꺼내서 보관함으로 이동하는 로직

                String poppedStr = stack.pop();
                String[] poppedArr = poppedStr.split(" ");

                if (poppedArr[1].equals("shared") || poppedArr[3].equals("shared") || poppedArr[4].equals("shared")) {//뽑아온게 shared라면
                    if (deque.isEmpty()) {
                        deque.offerLast(poppedStr);
                    } else {
                        String dequeStr = deque.peekLast();
                        String[] dequeArr = dequeStr.split(" ");
                        if (poppedArr[1].equals("shared") && dequeArr[1].equals("shared")) {//뽑아온 1명, 기존 1명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and " + poppedArr[0] + " shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[1].equals("shared") && dequeArr[3].equals("shared")) {//뽑아온 1명, 기존 2명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and 3 others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[1].equals("shared") && dequeArr[4].equals("shared")) {//뽑아온 1명, 기존 여러명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(dequeArr[2])+1)+ " others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[3].equals("shared") && dequeArr[1].equals("shared")) {//뽑아온 2명, 기존 1명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and 3 others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[3].equals("shared") && dequeArr[3].equals("shared")) {//뽑아온 2명, 기존 2명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and 4 others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[3].equals("shared") && dequeArr[4].equals("shared")) {//뽑아온 2명, 기존 여러명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(dequeArr[2])+2) +" others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[4].equals("shared") && dequeArr[1].equals("shared")) {//뽑아온 여러명, 기존 1명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(poppedArr[2]+1))+" others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[4].equals("shared") && dequeArr[3].equals("shared")) {//뽑아온 여러명, 기존 2명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(poppedArr[2]+2))+" others shared your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[4].equals("shared") && dequeArr[4].equals("shared")) {//뽑아온 여러명, 기존 여러명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(poppedArr[2]+1+Integer.parseInt(dequeArr[2])))+" others shared your post");
                            deque.offerLast(sb.toString());
                        }
                    }
                } else if (poppedArr[1].equals("commented") || poppedArr[3].equals("commented") || poppedArr[4].equals("commented")) {
                    if (deque.isEmpty()) {
                        deque.offerLast(poppedStr);
                    } else {
                        String dequeStr = deque.peekLast();
                        String[] dequeArr = dequeStr.split(" ");
                        if (poppedArr[1].equals("commented") && dequeArr[1].equals("commented")) {//뽑아온 1명, 기존 1명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and " + poppedArr[0] + " commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[1].equals("commented") && dequeArr[3].equals("commented")) {//뽑아온 1명, 기존 2명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and 3 others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[1].equals("commented") && dequeArr[4].equals("commented")) {//뽑아온 1명, 기존 여러명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(dequeArr[2])+1)+ " others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[3].equals("commented") && dequeArr[1].equals("commented")) {//뽑아온 2명, 기존 1명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and 3 others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[3].equals("commented") && dequeArr[3].equals("commented")) {//뽑아온 2명, 기존 2명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and 4 others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[3].equals("commented") && dequeArr[4].equals("commented")) {//뽑아온 2명, 기존 여러명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(dequeArr[2])+2) +" others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[4].equals("commented") && dequeArr[1].equals("commented")) {//뽑아온 여러명, 기존 1명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(poppedArr[2]+1))+" others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[4].equals("commented") && dequeArr[3].equals("commented")) {//뽑아온 여러명, 기존 2명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(poppedArr[2]+2))+" others commented your post");
                            deque.offerLast(sb.toString());
                        }else if (poppedArr[4].equals("commented") && dequeArr[4].equals("commented")) {//뽑아온 여러명, 기존 여러명
                            StringBuilder sb = new StringBuilder();
                            sb.append(dequeArr[0] + " and "+String.valueOf(Integer.parseInt(poppedArr[2]+1+Integer.parseInt(dequeArr[2])))+" others commented your post");
                            deque.offerLast(sb.toString());
                        }
                    }
                } else {//뽑아온게 commented라면
                    deque.offerLast(poppedStr);
                }
            } else {
                StringTokenizer st = new StringTokenizer(record, " ");
                String name = st.nextToken();
                String operation = st.nextToken();

                if (stack.isEmpty()) {//스택이 비어있는 경우에는 이름+멘트 추가
                    if (operation.equals("share")) {
                        stack.add(name + " shared your post");
                    } else if (operation.equals("comment")) {
                        stack.add(name + " commented on your post");
                    }
                } else {
                    String temp = stack.peek();
                    String[] tempArr = temp.split(" ");
                    if (temp.contains(operation)) {//이전에 들어있는 경우가 shared인 경우
                        stack.pop();
                        if (tempArr[1].equals("shared")) {//ex john shared your post
                            StringBuilder sb = new StringBuilder();
                            sb.append(tempArr[0] + " and " + name + " shared your post");
                            stack.add(sb.toString());
                        } else if (tempArr[3].equals("shared")) {//ex lee and jolie shared your post
                            StringBuilder sb = new StringBuilder();
                            sb.append(tempArr[0] + " and 2 others shared your post");
                            stack.add(sb.toString());
                        } else if (tempArr[4].equals("shared")) {//ex lee and 3 others shared your post
                            int count = Integer.parseInt(tempArr[2]);
                            count++;
                            StringBuilder sb = new StringBuilder();
                            sb.append(tempArr[0] + " " + tempArr[1] + " " + String.valueOf(count) + " others shared your post");
                            stack.add(sb.toString());
                        }
                    } else if (temp.contains(operation)) {//이전에 들어있는 경우가 commented인 경우
                        stack.pop();
                        if (tempArr[1].equals("commented")) {//ex john shared your post
                            StringBuilder sb = new StringBuilder();
                            sb.append(tempArr[0] + " and " + name + " commented your post");
                            stack.add(sb.toString());
                        } else if (tempArr[3].equals("commented")) {//ex lee and jolie shared your post
                            StringBuilder sb = new StringBuilder();
                            sb.append(tempArr[0] + " 2 others commented your post");
                            stack.add(sb.toString());
                        } else if (tempArr[4].equals("commented")) {//ex lee and 3 others shared your post
                            int count = Integer.parseInt(tempArr[2]);
                            count++;
                            StringBuilder sb = new StringBuilder();
                            sb.append(tempArr[0] + " " + tempArr[1] + " " + String.valueOf(count) + " others commented your post");
                            stack.add(sb.toString());
                        }
                    }else{
                        if (operation.equals("share")) {
                            stack.add(name + " shared your post");
                        } else if (operation.equals("comment")) {
                            stack.add(name + " commented on your post");
                        }
                    }
                }
            }
            System.out.println("stack");
            for (String s : stack) {
                System.out.println(s);
            }
            System.out.println();

            System.out.println("deque");
            for (String s : deque) {
                System.out.println(s);
            }
            System.out.println();
        }

        String[] answer = new String[deque.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = deque.pollFirst();
        }

        return answer;

    }

}
