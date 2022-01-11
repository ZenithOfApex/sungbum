package programmers;

import java.util.PriorityQueue;

public class ShuttleBus2 {
    public static void main(String[] args) {
        System.out.println(solution(2,10,2, new String[]{"08:01", "08:00", "08:03", "08:02"}));
    }

    static PriorityQueue<Time> pq = new PriorityQueue<>();
    static String answer = "";

    public static String solution(int n, int t, int m, String[] Timetable) {
        for (int i = 0; i < Timetable.length; i++) {
            String s[] = Timetable[i].split(":");
            pq.add(new Time(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }

        bus(n, t, m);
        return answer;
    }

    private static void bus(int n, int t, int m) {
        Time bus = new Time(9, 0);
        Time corn = new Time(9, 0);

        for (int i = 0; i < n; i++) {
            int people =0;

            for (int j = 0; j < m; j++) {
                if (!pq.isEmpty()) {
                    Time temp = pq.peek();

                    if (bus.compareTo(temp) >= 0) {
                        corn = pq.poll();
                        people++;
                    }
                }
                if (i == n - 1 && people == m) {
                    corn = new Time(corn.h, corn.m - 1);
                } else if (i == n - 1 && people < m) {
                    corn = new Time(bus.h, bus.m);
                }
            }
            bus = new Time(bus.h, bus.m+t);
        }
        answer += (corn.h < 10 ? "0" + corn.h : corn.h) + ":" + (corn.m < 10 ? "0" + corn.m : corn.m);
    }

    static class Time implements Comparable<Time> {
        int h,m;

        Time(int h, int m) {
            if (m < 0) {
                m += 60;
                h--;
            }

            if (m >= 60) {
                m-=60;
                h++;
            }
            this.h = h;
            this.m = m;
        }

        public int compareTo(Time o) {
            if (h == o.h) {
                return m - o.m;
            }
            return h - o.h;
        }
    }

}
