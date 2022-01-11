package programmers;

import java.util.HashMap;
import java.util.Map;

public class RoomAssignment2 {
    static Map<Long, Long> map = new HashMap<>();

    public static void main(String[] args) {
        long[] answer = solution(10, new long[]{1, 3, 4, 1, 3, 1});
        for (long l : answer) {
            System.out.println(l);
        }
    }

    public static long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }
        return answer;
    }

    private static long findEmptyRoom(long room) {
        if (!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }

        long nextRoom = map.get(room);
        long emptyRoom = findEmptyRoom(nextRoom);
        map.put(room, emptyRoom);
        return emptyRoom;
    }
}
