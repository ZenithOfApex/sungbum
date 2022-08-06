package programmers;

public class NotEnoughMoney {
    public static void main(String[] args) {
        System.out.println(solution(3, 20, 4));
    }

    private static long solution(int price, int money, int count) {
        long totalFee = 0;

        for (int i = 1; i <= count; i++) {
            totalFee += price * i;
        }

        if (totalFee <= money) {
            return 0;
        } else {
            return totalFee - money;
        }
    }
}
