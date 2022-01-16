package programmers;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class HolidayTraffic {

    private static ArrayList<Transaction> timeList = new ArrayList<>();
    private static ArrayList<String> startAndEndTimes = new ArrayList<>();
    private static ArrayList<String> startTime = new ArrayList<>();
    private static ArrayList<String> endTime = new ArrayList<>();

    private static ArrayList<Integer> possibleTransactionCounts = new ArrayList<>();

    public static void main(String[] args) {
//        String[] temp = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] temp = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(temp));
    }

    public static int solution(String[] lines) {
        int answer =0;

        for (String line : lines) {
            savingTransactions(line);
        }

        for (Transaction times : timeList) {
            savingStartAndEndTime(times);

            System.out.println("times.time = " + times.time);
            System.out.println("times.processTime = " + times.processTime);
            System.out.println();
        }

        for (int i = 0; i < startAndEndTimes.size()-1; i += 2) {//startAndEndTime의 짝수 index에 시작시간 담겨있다.
            startTime.add(startAndEndTimes.get(i));
            endTime.add(startAndEndTimes.get(i + 1));
        }


        return answer;
    }

    public static void savingTransactions(String transactionInfo) {
        String[] infoArray = transactionInfo.split(" ");
        timeList.add(new Transaction(infoArray[1], infoArray[2]));
    }

    public static void savingStartAndEndTime(Transaction transaction) {
        String temp = transaction.processTime.substring(0,transaction.processTime.length() - 1);
        System.out.println("temp = " + temp);

        double durationMilliSecond = Double.parseDouble(temp) * 1000;

        String startTime = calculatingStartTime(transaction.time, durationMilliSecond - 1);
        startAndEndTimes.add(startTime);
        startAndEndTimes.add(transaction.time);

    }

    public static String calculatingStartTime(String strTime, double minusTime) {
        String[] trimmedTime = strTime.split(":");
        int hour = Integer.parseInt(trimmedTime[0]);
        int minute = Integer.parseInt(trimmedTime[1]);
        double milliSecond = Double.parseDouble(trimmedTime[2])*1000;

        if (milliSecond - minusTime < 0) {
            minute--;
            milliSecond+=1000;
            if (minute < 0) {
                hour--;
                minute += 60;
            }
        } else milliSecond -= minusTime;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d",hour));
        sb.append(":");
        sb.append(String.format("%02d",minute));
        sb.append(":");
        sb.append(milliSecond / 1000);

        return sb.toString();
    }

    public static class Transaction{
        String time;
        String processTime;

        public Transaction(String time, String processTime) {
            this.time = time;
            this.processTime = processTime;
        }
    }

    public static class Time{
        int hour;
        int min;
        int second;
        double milliSecond;

        public Time(int hour, int min, int second, double milliSecond) {
            this.hour = hour;
            this.min = min;
            this.second = second;
            this.milliSecond = milliSecond;
        }
    }
}
