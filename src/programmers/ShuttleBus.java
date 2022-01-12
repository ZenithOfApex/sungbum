package programmers;

import java.util.*;

public class ShuttleBus {

    public static ArrayList<Time> waitingList, busList;
    public static Queue<Time> onBoardList;

    public static void main(String[] args) {
        System.out.println(solution(2,10,2, new String[]{"08:01", "08:00", "08:03", "08:02"}));
    }

    public static String solution(int n, int t, int m, String[] timetable){
        String answer = "answer";

        waitingList = new ArrayList<>();
        busList = new ArrayList<>();
        onBoardList = new LinkedList<>();

        for (String time : timetable) {
            String[] trimmedTime = time.split(":");
            waitingList.add(new Time(Integer.parseInt(trimmedTime[0]), Integer.parseInt(trimmedTime[1])));
        }
        System.out.println();
        for (Time wait : waitingList) {//기존 기다리는 사원 정보 입력
            System.out.println("wait.hour = " + wait.hour);
            System.out.println("wait.min = " + wait.min);
            System.out.println();
        }

        for (int i = 0; i < n; i++) {//버스 수만큼
            busList.add(new Time(9, i*t));//버스 배차 시간표 입력
        }
        Collections.sort(waitingList, new Comparator<Time>() {//기다리는 사원들 정렬
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.hour == o2.hour) {
                    return o1.min - o2.min;
                } else return -1;
            }
        });

        for (Time wait : waitingList) {
            onBoardList.offer(wait);
        }


        for (Time bus : busList) {
            int people =0;
            int curHour = 0;
            int curMin =0;

            while(!onBoardList.isEmpty()){
                Time person = onBoardList.poll();
                System.out.println("person.hour = " + person.hour);
                System.out.println("person.min = " + person.min);
                if (person.hour <= bus.hour) {
                    if (person.min <= bus.min) {
                        if (people < m - 1) {//버스가 만석이 아니라면
                            people++;
                            curHour = person.hour;
                            curMin = person.min;
                        } else if (people == m - 1) {//콘이 이때 타야 함
                            StringBuilder sb = new StringBuilder();
                            sb.append(curHour + ":" + curMin);
                            answer = sb.toString();
                        } else {// 만석인 경우
                            people = 0;

                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }




        for (Time wait : waitingList) {
            System.out.println("wait.hour = " + wait.hour);
            System.out.println("wait.min = " + wait.min);
            System.out.println();
        }
        System.out.println();
        for (Time bus : busList) {
            System.out.println("bus.hour = " + bus.hour);
            System.out.println("bus.min = " + bus.min);
            System.out.println();
        }

        return answer;
    }

    public static class Time {
        int hour, min;

        public Time(int hour, int min) {
            this.hour = hour;
            if (min >= 60) {
                this.hour++;
                min -= 60;
                this.min = min;
            } else {
                this.min = min;
            }
        }
    }

}
