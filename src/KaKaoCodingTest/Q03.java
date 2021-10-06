package KaKaoCodingTest;

import java.util.ArrayList;

public class Q03 {
    public int[] solution(int[] fees, String[] records){
        int[] answer = new int[records.length];
        int basicTime = fees[0];//기본시간
        int basicFee = fees[1];//기본요금
        int addTime = fees[2];//추가시간
        int addFee = fees[3];//추가요금
        ArrayList<carInformation> cars = new ArrayList<>();
        ArrayList<carIn> carIn = new ArrayList<>();
        ArrayList<carOut> carOut = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for(int i=0;i<records.length;i++){//차량 입출고에 대한 정보 records에서 읽어오기
            String[] carRecord = records[i].split(" ");
            String[] time = carRecord[0].split(":");
            cars.add(new carInformation(Integer.parseInt(time[0]),Integer.parseInt(time[1]), Integer.parseInt(carRecord[1]), carRecord[2]));
        }

        for(int i=0;i<cars.size();i++){//In and out으로 차 구분하기
            if(cars.get(i).status.equals("In")){
                carIn.add(new carIn(cars.get(i).hour, cars.get(i).minute, cars.get(i).carNum,false));
            } else if (cars.get(i).status.equals("Out")) {
                carOut.add(new carOut(cars.get(i).hour, cars.get(i).minute, cars.get(i).carNum,false));
            }
        }
        for(int i=0;i<carIn.size();i++){
            for(int j=0;j<carOut.size();j++){
                int timeSpent = timeSpent(carIn.get(i),carOut.get(j));
                int fee =calculateFee(timeSpent, basicTime, basicFee, addTime, addFee);
                for(int k=0;k<cars.size();k++){
                    if(carIn.get(i).carNum == cars.get(k).carNum){
                        cars.get(k).totalFee+=fee;
                    }
                }
            }
        }
        for(int i=0;i<cars.size();i++){
            answer[i] = cars.get(i).totalFee;
        }

        return answer;
    }
    public int calculateFee(int minute,int basicTime, int basicFee, int addTime, int addFee){//여기서의 minute은 입고부터 출고까지의 시간 차이
        int totalFee = 0;
        int unit=0;
        if(minute<basicTime){
            totalFee+=basicFee;
        }else{
            totalFee +=basicFee;
            minute = minute - basicTime;
            unit = (minute%addTime==0) ?  minute/addTime : minute/addTime +addTime;
            totalFee +=unit*addFee;
        }
        return totalFee;
    }

    public int timeSpent(carIn carIn, carOut carOut){
        int diffTime=0;
        int diffMinute = 0;
        int totalTime = 0;

        if(carIn.carNum == carOut.carNum &&(carIn.checked==false && carOut.checked==false)){
            if(carOut.minute < carIn.minute){
                carOut.hour --;
                carOut.minute+=60;
            }
            diffTime = carOut.hour - carIn.hour;
            diffMinute = carOut.minute - carIn.minute;
            totalTime += diffTime *60;
            totalTime +=diffMinute;
            carIn.checked = true;
            carOut.checked = true;
        }
        else if(carIn.carNum == carOut.carNum && carIn.checked ==false){
            if(carOut.checked==true){

            }
            int tempHour = 23;
            int tempMinute = 59;
            diffTime = tempHour - carIn.hour;
            diffMinute = tempMinute - carIn.minute;
            totalTime += diffTime *60;
            totalTime +=diffMinute;
            carIn.checked = true;
            carOut.checked = true;
        }
        return totalTime;
    }
}

class carInformation{
    int hour;
    int minute;
    int carNum;
    String status;
    int totalFee;

    public carInformation(int hour, int minute, int carNum, String status) {
        this.hour = hour;
        this.minute = minute;
        this.carNum = carNum;
        this.status = status;
    }
}

class carIn{
    int hour;
    int minute;
    int carNum;
    boolean checked;

    public carIn(int hour, int minute, int carNum, boolean checked) {
        this.hour = hour;
        this.minute = minute;
        this.carNum = carNum;
        this.checked = checked;
    }
}

class carOut{
    int hour;
    int minute;
    int carNum;
    boolean checked;

    public carOut(int hour, int minute, int carNum, boolean checked) {
        this.hour = hour;
        this.minute = minute;
        this.carNum = carNum;
        this.checked = checked;
    }
}