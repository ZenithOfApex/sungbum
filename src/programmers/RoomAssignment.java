package programmers;

import java.util.HashMap;

public class RoomAssignment {

    static HashMap<Long, Long> hm = new HashMap<>();//hm은 방번호와 가능한 다음 방 번호 쌍으로

    public static void main(String[] args) {
        long[] answer = solution(10, new long[]{1, 3, 4, 1, 3, 1});
        for (long l : answer) {
            System.out.println(l);
        }
    }

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = checkCondition(room_number[i]);
        }

        return answer;
    }

    public static long checkCondition(long room) {
        //방이 있다면
        if (!hm.containsKey(room)) {
            hm.put(room, room + 1);
            return room;
        }
        //방이 없다면

        long check = hm.get(room);
        long targetRoom = checkCondition(check);
        hm.put(targetRoom, targetRoom + 1);
        return targetRoom;
    }

}
