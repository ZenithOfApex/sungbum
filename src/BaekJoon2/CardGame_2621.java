package BaekJoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CardGame_2621 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Card> cards = new ArrayList<>();//입력받은 카드의 종류-숫자 리스트
        ArrayList<String> type = new ArrayList<>();//입력받은 카드의 종류 리스트
        ArrayList<Integer> number = new ArrayList<>();//입력받은 카드의 숫자 리스트

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cards.add(new Card(st.nextToken(), Integer.parseInt(st.nextToken())));
        }
        br.close();

        Collections.sort(cards);
        for (Card card : cards) {
            if (!type.contains(card.cardName)) {
                type.add(card.cardName);
            }
            number.add(card.cardNum);
        }
        Collections.sort(number);//숫자들 정렬
        int maxNum = number.get(number.size() - 1);
        int diff = Math.abs(maxNum - number.get(0));
        if (type.size() == 1 && diff==4) {//straightFlush
            System.out.println(maxNum+900);
            System.exit(0);
        } else if (type.size() == 1) {//Flush
            System.out.println(maxNum+ 600);
        } else if (type.size() != 5 && diff == 4) {//straight
            System.out.println(maxNum + 500);
        } else if (type.size() == 2) {//fullHouse
            System.out.println();
        }
    }

//    private static int returnScore(ArrayList<Card> cards) {
//        int red=0, blue=0, green=0, yellow=0;
//        for (Card card : cards) {
//            if (card.cardName.equals("R")) {
//                red++;
//            } else if (card.cardName.equals("G")) {
//                green++;
//            } else if (card.cardName.equals("B")) {
//                blue++;
//            } else {
//                yellow++;
//            }
//        }//문양만 보고 판단/ 넷 중 하나라도 5이면 스트레이트플러쉬 또는 플러쉬
//        if (red == 5 || blue == 5 || green == 5 || yellow == 5) {
//
//        }
//
//
//    }
//
//    public static void checkSequence(ArrayList<Card> cards) {
//
//    }
    static class Card implements Comparable<Card>{
        String cardName;
        int cardNum;

        public Card(String cardName, int cardNum) {
            this.cardName = cardName;
            this.cardNum = cardNum;
        }

        @Override
        public int compareTo(Card o) {
            return this.cardNum - o.cardNum;
        }
    }

}
