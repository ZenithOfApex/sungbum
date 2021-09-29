package KaKaoCodingTest;

import java.util.ArrayList;
import java.util.Collections;

public class Q03_final {
    public static void main(String[] args) throws Exception {
        int[] fees = {1, 461, 1, 10};
        String[] records = {"00:00 1234 IN"};
        int[] ans = solution(fees, records);
        for (int k : ans) {
            System.out.println(k);
        }

    }
    public static int[] solution(int[] fees, String[] records){
        ArrayList<carInfo> info = new ArrayList<>();
        //fees 입력
        parkingFee pf = new parkingFee(fees[0], fees[1], fees[2], fees[3]);
        ArrayList<Integer> checkCar = new ArrayList<>();//등록된 차량 번호들 들어가 있음
        ArrayList<Integer> finalAnswer = new ArrayList<>();
        //records 입력
        for (int i = 0; i < records.length; i++) {
            String[] temp = records[i].split(" ");
            String time = temp[0];
            int carNumber = Integer.parseInt(temp[1]);
            String parkingStatus = temp[2];

            if(checkCar.contains(carNumber)){//차량 번호 확인용 리스트 안에 이미 등록되어있다면
                continue;
            }else{
                checkCar.add(carNumber);
                info.add(new carInfo(carNumber,time,parkingStatus));
            }
        }
        Collections.sort(checkCar);
        for (int i = 0; i < checkCar.size(); i++) {//등록된 차량 번호들에서 번호당 pay 계산
            int cnt=0;
            int totalFee=0;
//            System.out.println("checkCar.get(i) = " + checkCar.get(i));
            ArrayList<String> timeToCalc = new ArrayList<>();
            for (int j = 0; j < info.size(); j++) {//입력된 info에서 이제 in and out 구분해서 pay 계산
                if (checkCar.get(i).equals(info.get(j).carNum)) {
                    cnt++;
                    timeToCalc.add(info.get(j).time);
                }
            }
//            System.out.println("checking");
//            for (int l = 0; l < timeToCalc.size(); l++) {
//                System.out.println("timeToCalc = " + timeToCalc.get(l));
//                System.out.println("timeToCalc.size() = " + timeToCalc.size());
//            }
            if (timeToCalc.size()% 2 == 0) {//in and out이 맞아떨어지면
//                System.out.println("entered");
                while (!timeToCalc.isEmpty()) {
//                    System.out.println("even number timeToCalc while entered");
                    String time1 = timeToCalc.get(0);
                    String time2 = timeToCalc.get(1);
                    totalFee += timeToFee(time1, time2, pf);
//                    System.out.println("totalFee = " + totalFee);
                    timeToCalc.remove(0);
                    timeToCalc.remove(1);
                }
            }
            else if(timeToCalc.size() %2 != 0){//in and out이 맞아 떨어지지 않으면
                timeToCalc.add("23:59");
//                System.out.println("entered");
                while (!timeToCalc.isEmpty()) {
//                    System.out.println("odd number timeToCalc while entered");
                    String time1 = timeToCalc.get(0);
                    String time2 = timeToCalc.get(1);
                    totalFee += timeToFee(time1, time2, pf);
//                    System.out.println("totalFee = " + totalFee);
//                    System.out.println("timeToCalc = " + timeToCalc.get(0));
                    timeToCalc.remove(0);
                    timeToCalc.remove(0);
//                    System.out.println("eng");
                }
            }
            finalAnswer.add(totalFee);
        }
        int[] answer = new int[finalAnswer.size()];
        int size=0;
        for (int temp : finalAnswer) {
            answer[size++] = temp;
        }

        return answer;
    }

    public static int timeToFee(String smallTime, String bigTime,parkingFee pay) {//차량 적은 시간이랑 큰 시간 대입하면 가격 반환
        String[] time1 = smallTime.split(":");
        String[] time2 = bigTime.split(":");

        int smallHour = Integer.parseInt(time1[0]);
        int smallMinute = Integer.parseInt(time1[1]);
        int bigHour = Integer.parseInt(time2[0]);
        int bigMinute = Integer.parseInt(time2[1]);
        if (bigMinute - smallMinute < 0) {
            bigHour-=1;
            bigMinute+=60;
        }

        int totalHour = bigHour-smallHour;
        int totalMinute = bigMinute-smallMinute;
        int charge=0;
        int totalTime = (totalHour*60) + totalMinute;//분으로 환산
        if(totalTime<pay.freeTime)
            return pay.freePay;
        else{
            charge += pay.freePay;
            totalTime -= pay.freeTime;
            if (totalTime % pay.addTime == 0) {
                charge +=(totalTime/ pay.addTime)* pay.addFee;
            }else{
                charge+=((totalTime/ pay.addTime)* pay.addFee) + pay.addFee;
            }
        }
        return charge;
    }
}
class parkingFee{
    int freeTime;
    int freePay;
    int addTime;
    int addFee;

    public parkingFee(int freeTime, int freePay, int addTime, int addFee) {
        this.freeTime = freeTime;
        this.freePay = freePay;
        this.addTime = addTime;
        this.addFee = addFee;
    }
}
class carInfo{
    int carNum;
    String time;
    String status;

    public carInfo(int carNum, String time, String status) {
        this.carNum = carNum;
        this.time = time;
        this.status = status;
    }
}
