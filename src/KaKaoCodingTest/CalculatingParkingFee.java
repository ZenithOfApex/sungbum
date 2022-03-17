package KaKaoCodingTest;

import java.util.*;

public class CalculatingParkingFee {

    static HashMap<Integer,CarInfo> carHashMap;
    static HashMap<Integer, Integer> totalFee;
    static HashMap<Integer, Integer> totalTime;

    public static void main(String[] args) throws Exception {
//        int[] fees = {1, 461, 1, 10};
//        String[] records = {"00:00 1234 IN"};

        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        int[] ans = solution(fees, records);
        for (int k : ans) {
            System.out.println(k);
        }

    }

    private static int[] solution(int[] fee, String[] records) {
        carHashMap = new HashMap<>();
        totalFee = new HashMap<>();
        totalTime = new HashMap<>();

        for (String record : records) {//레코드마다 처리
            String[] recordArr = record.split(" ");
            int time = intoMinute(recordArr[0]);
            int carNum = Integer.parseInt(recordArr[1]);

            boolean recordStatus;
            if (recordArr[2].equals("IN")) {
                recordStatus = true;
            }else recordStatus = false;

            //등록되어 있지 않은 경우 새로 등록
            if (!carHashMap.keySet().contains(carNum)) {
                carHashMap.put(carNum, new CarInfo(time, recordStatus));
                totalTime.put(carNum, time);
                totalFee.put(carNum, 0);
                System.out.println(record);
            } else {//차가 이미 hashMap에 있는 경우에는 시간만 계산하면 된다
                if (carHashMap.get(carNum).status && !recordStatus) {//입차되어 있고 받은 기록이 out인 경우
                    int enterTime = carHashMap.get(carNum).time;
                    int durationTime = time - enterTime;
                    totalTime.put(carNum, totalTime.get(carNum) + durationTime);
                    carHashMap.get(carNum).status = false;
//                    totalFee.put(carNum, totalFee.get(carNum) + calFee);
                    System.out.println(record);
                } else {//출차된 경우 새로운 입차 기록
                    carHashMap.put(carNum, new CarInfo(time, true));
                    System.out.println(record);
                }
            }
        }

        //모든 기록이 끝났는데 입차상태로 남아있는 애들은 23:59로 계산해서 반환해야된다
        for (Integer carNum : carHashMap.keySet()) {
            if (carHashMap.get(carNum).status) {//입차상태로 남아있는경우 계산
                int durationTime = intoMinute("23:59") - carHashMap.get(carNum).time;
                totalTime.put(carNum, totalTime.get(carNum) + durationTime);
                int calFee = calculateFee(carHashMap.get(carNum).time,fee);
                if (totalFee.get(carNum) == null) {
                    totalFee.put(carNum, calFee);
                }else totalFee.put(carNum, totalFee.get(carNum) + calFee);
            } else continue;
        }

        //최종계산
        for (Integer value : totalTime.values()) {
            System.out.println("value = " + value);
        }
        //hashMap을 int형 배열로 반환
        int[] rtnNum = new int[carHashMap.size()];
        int[] rtnFee = new int[carHashMap.size()];

        int index = 0;
        for (Integer carNum : carHashMap.keySet()) {
            rtnNum[index++] = carNum;
        }

        Arrays.sort(rtnNum);

        for (int i = 0; i < rtnFee.length; i++) {
            rtnFee[i] = totalFee.get(rtnNum[i]);
        }

        return rtnFee;
    }

    //모든 시간은 분으로 계산
    private static int intoMinute(String time) {
        String[] trimmedTime = time.split(":");
        return (60 * (Integer.parseInt(trimmedTime[0]))) + Integer.parseInt(trimmedTime[1]);
    }

    private static int calculateFee(int durationTime, int[] fee) {
        int basicTime = fee[0];
        int basicFee = fee[1];
        int unitTime = fee[2];
        int unitFee = fee[3];

        //주차 시간이 기본 시간 범위 내에 있을 경우
        if (durationTime <= basicTime) {
            return basicFee;
        } else {
            return basicFee + ((int)Math.ceil((durationTime-basicTime) / unitTime) * unitFee);
        }
    }

    private static class CarInfo{
        int time;
        boolean status;

        public CarInfo(int time, boolean status) {
            this.time = time;
            this.status = status;
        }
    }
}
