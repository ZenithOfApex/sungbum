package programmers;

import java.util.ArrayList;
import java.util.HashMap;

public class Compression {
    static HashMap<String, Integer> dictionary = new HashMap<>();
    static ArrayList<Integer> compress = new ArrayList<>();
    static int curIndex = 27;

    public static void main(String[] args) {
        int[] temp = solution("KAKAO");

        for (int i : temp) {
            System.out.println(i);
        }
    }

    private static int[] solution(String msg) {
        //msg 크기 불변
        //사전 길이 가변

        initData();

        boolean isEnd = false;

        for (int idx = 0; idx < msg.length(); idx++) {
            String word = msg.charAt(idx) + "";
            while (dictionary.containsKey(word)) {
                idx++;
                if (idx == msg.length()) {
                    isEnd = true;
                    break;
                }
                word += msg.charAt(idx);
            }

            if (isEnd) {
                compress.add(dictionary.get(word));
                break;
            }

            compress.add(dictionary.get(word.substring(0, word.length() - 1)));
            dictionary.put(word, curIndex++);

            idx--;
        }

        int[] answer = new int[compress.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = compress.get(i);
        }
        return answer;
    }


    private static void initData() {
        int index = 1;

        for (int i = 65; i < 91; i++) {
            dictionary.put(String.valueOf((char) i), index++);
        }
    }


}
