package programmers;

import java.util.ArrayList;

public class EndToEnd {
    public static void main(String[] args) {
//        int n = 3;
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int n =2;
        String[] words = {"hello", "one", "even", "never", "now", "word", "draw"};
        int[] answer = solution(n,words);
        for (int i : answer) {
            System.out.print(i+" ");
        }
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];//[who, turn]
        ArrayList<String> spoken = new ArrayList<>();

        int turn =1;//전체 몇번째 사이클인지를 반환
        int wordsIndex=0;
        String prevWord = "";
        boolean keepGo = true;
        while(true){

            for (int i = 1; i <= n; i++) {//i는 말하는 사람
                String word = words[wordsIndex];
                if (spoken.contains(word)) {//이미 말한 단어를 말한 경우
                    answer[0]= i;
                    answer[1] = turn;
                    keepGo = false;
                    break;
                }else{//이미 말한 단어가 아닌 경우
                    if(wordsIndex==0){//첫번째 단어인 경우에는 무조건 인정
                        spoken.add(word);
                        prevWord = word;
                        wordsIndex++;
                    }else{//두번째
                        if (word.charAt(0) != prevWord.charAt(prevWord.length() - 1)) {//끝말 이어지지 않은 경우
                            answer[0]=i;
                            answer[1]=turn;
                            keepGo = false;
                            break;
                        }else{//끝말이 이어지는 경우
                            spoken.add(word);
                            prevWord = word;
                            wordsIndex++;
                        }
                    }
                }
            }
            System.out.println(spoken);
            if (wordsIndex == words.length && keepGo == true) {
                answer[0]=0;
                answer[1]=0;
                break;
            } else if (keepGo == false) {
                break;
            }
            turn++;
        }

        return answer;
    }
}
