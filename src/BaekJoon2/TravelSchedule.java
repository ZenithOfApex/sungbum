package BaekJoon2;

import java.util.ArrayList;

public class TravelSchedule {
    public static void main(String[] args) {
        String answer = "";
        String[][] city = {{"홍콩", "11PM", "9AM"}, {"엘에이", "3PM", "2PM"}};
        answer = solution(3.5, city);
        System.out.println(answer);
    }

    public static String solution(double time, String[][] plans) {
        String lastCity="호치민";
        ArrayList<schedule> sched = new ArrayList<>();//도시,출발,도착 스케쥴이 들어간 리스트
        for (String[] plan : plans) {
            sched.add(new schedule(plan[0], plan[1], plan[2]));//일정별 도시, 출발, 도착 시간 추가
        }

        //스케쥴 리스트에서 하나씩 받아와서 가능여부 판단-가능하다면 시간 차감
        for (schedule schedule : sched) {
            double checkDepart = convertTime(schedule.departure);
            double checkArrival = convertTime(schedule.arrival);
            time = checkingFridayTime(time, checkDepart);
            time = checkingMondayTime(time, checkArrival);
            if (time >= 0) {
                lastCity = schedule.city;
            } else {
                break;
            }
        }
        return lastCity;
    }

    public static double convertTime(String inputTime) {
        int realTime=0;
        String time = inputTime.substring(0, inputTime.length() - 2);
        int inputRealTime = Integer.parseInt(time);
        String dayNight = inputTime.substring(inputTime.length() - 2, inputTime.length());
        if (dayNight.equals("PM")) {
            realTime = inputRealTime+12;
            return (double)realTime;
        }else{
            return (double)inputRealTime;
        }
    }

    public static double checkingFridayTime(double time, double departureTime) {
        if ( departureTime> 18) {//금요일 출발 시간이 퇴근 후 라면
            return time;
        } else if (departureTime >= 9.5 || departureTime <= 18) {
            time = time - (18 - departureTime);
            return time;
        }else{
            time = time-8.5;
            return time;
        }

    }

    public static double checkingMondayTime(double time, double arrivalTime) {
        if (arrivalTime < 13) {
            return time;
        }
        else if(arrivalTime >= 13 || arrivalTime <= 18){
            time = time-arrivalTime+13;
            return time;
        }else{
            time = time -5;
            return time;
        }
    }

    static class schedule{
        String city;
        String departure;
        String arrival;

        public schedule(String city, String departure, String arrival) {
            this.city = city;
            this.departure = departure;
            this.arrival = arrival;
        }
    }
}
