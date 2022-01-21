package programmers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HolidayTraffic {

    private static ArrayList<Transaction> timeList = new ArrayList<>();
    private static ArrayList<String> startTime = new ArrayList<>();
    private static ArrayList<String> endTime = new ArrayList<>();

    private static ArrayList<Integer> possibleTransactionCounts = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String[] temp = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
//        String[] temp = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(temp));
    }

    public static int solution(String[] lines) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        int[] count = new int[lines.length];
        int max = 0;

        for (int i = 0; i < lines.length; i++) {
            String[] pre = lines[i].split(" ");
            Date preEndDate = format.parse(pre[1]);
            long preEnd = preEndDate.getTime();

            for (int j = i; j < lines.length; j++) {
                String[] next = lines[j].split(" ");
                Date nextEndDate = format.parse(next[1]);
                double sec = Double.parseDouble(next[2].substring(0, next[2].length() - 1));

                long nextStart = (long) (nextEndDate.getTime() - sec * 1000 + 1);

                if (preEnd + 1000 > nextStart) {
                    count[i] += 1;
                    max = Math.max(max, count[i]);
                }
            }
        }
        return max;
    }

//    public static int solution(String[] lines) {
//        int answer =0;
//
//        int cnt=0;
//        for (String line : lines) {
//            savingTransactions(line,cnt);
//            cnt++;
//        }
//
//        for (Transaction times : timeList) {
//            savingStartAndEndTime(times);
//
//            System.out.println("times.time = " + times.time);
//            System.out.println("times.processTime = " + times.processTime);
//            System.out.println("times.cnt = " + times.cnt);
//            System.out.println();
//        }
//
//
//        System.out.println(startTime);
//        System.out.println(endTime);
//        return answer;
//    }

    public static void countTransactions(ArrayList<String> startAndEndTimes) {
        int count =0;
        for (int i = 0; i < startAndEndTimes.size()-1; i++) {
            String[] temp = startAndEndTimes.get(i).split(":");
            double millisec = Double.parseDouble(temp[2]);
            for (int j = i+1; j < startAndEndTimes.size(); j++) {

            }
        }
    }

    public static void savingTransactions(String transactionInfo, int count) {
        String[] infoArray = transactionInfo.split(" ");
        timeList.add(new Transaction(infoArray[1], infoArray[2], count));
    }

    public static void savingStartAndEndTime(Transaction transaction) {
        String temp = transaction.processTime.substring(0,transaction.processTime.length() - 1);
        System.out.println("temp = " + temp);

        double durationMilliSecond = Double.parseDouble(temp) * 1000;

        String calculatedStartTime = calculatingStartTime(transaction.time, durationMilliSecond - 1);
        startTime.add(calculatedStartTime);
        endTime.add(transaction.time);
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
        sb.append(String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + milliSecond / 1000);
        return sb.toString();
    }

    public static class Transaction{
        String time;
        String processTime;
        int cnt;

        public Transaction(String time, String processTime, int cnt) {
            this.time = time;
            this.processTime = processTime;
            this.cnt = cnt;
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
