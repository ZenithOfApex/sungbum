package KaKaoCodingTest;

import java.util.*;

public class CalculatingParkingFee {

    static HashMap<Integer,CarInfo> carHashMap;
    static HashMap<Integer, Integer> totalFee;

    public static void main(String[] args) throws Exception {
        int[] fees = {1, 461, 1, 10};
        String[] records = {"00:00 1234 IN"};
        int[] ans = solution(fees, records);
        for (int k : ans) {
            System.out.println(k);
        }

    }

    private static int[] solution(int[] fee, String[] records) {
        carHashMap = new HashMap<>();
        totalFee = new HashMap<>();

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
            } else {//차가 이미 hashMap에 있는 경우에는 계산해야지
                if (carHashMap.get(carNum).status) {//입차되어 있는 경우
                    int inputTime = carHashMap.get(carNum).time;
                    int calFee = calculateFee(inputTime, time, fee);
                    totalFee.put(carNum, totalFee.get(carNum) + calFee);
                } else {//출차된 경우 새로운 입차 기록
                    carHashMap.put(carNum, new CarInfo(time, true));
                }
            }
        }

        //모든 기록이 끝났는데 입차상태로 남아있는 애들은 23:59로 계산해서 반환해야된다
        for (Integer carNum : carHashMap.keySet()) {
            if (carHashMap.get(carNum).status) {//입차상태로 남아있는경우 계산
                int calFee = calculateFee(carHashMap.get(carNum).time, intoMinute("23:59"), fee);
                if (totalFee.get(carNum) == null) {
                    totalFee.put(carNum, calFee);
                }else totalFee.put(carNum, totalFee.get(carNum) + calFee);
            } else continue;
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

    //error catch하자
    private static int calculateFee(int inTime, int outTime, int[] fee) {
        int basicTime = fee[0];
        int basicFee = fee[1];
        int unitTime = fee[2];
        int unitFee = fee[3];

        int durationTime = outTime - inTime;
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
