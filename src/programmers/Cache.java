package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Cache {
    static int answer = 0;
    static int timeCnt;

    public static void main(String[] args) {

//        answer = solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
//        answer = solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
//        answer = solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
//        answer = solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
//        answer = solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
//        answer = solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        answer = solution(3, new String[]{"A", "B", "A"});
        System.out.println(answer);

    }

    private static int solution(int cacheSize, String[] cities) {
        if(cacheSize==0) return 5 * cities.length;

        LinkedList<String> cache = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();

            //캐시 적중
            //이미 캐시에 있던 페이지를 가장 처음으로 가져온다
            //기존에 있던 페이지를 지우고 큐의 가장 처음에 삽입하면 된다
            if (cache.remove(city)) {//remove : 해당 element 존재시 삭제하고 true 반환
                cache.addFirst(city);
                answer += 1;
            } else {
                //캐시 미스
                //캐시가 가득 찬 경우 : 가장 뒤의 페이지를 삭제하고 가장 앞에 새 페이지를 삽입한다.
                //캐시에 자리가 있는 경우 : 가장 앞에 새 페이지를 삽입한다.
                int currentSize = cache.size();

                if (currentSize == cacheSize) {
                    cache.pollLast();
                }

                cache.addFirst(city);
                answer += 5;
            }
        }
        return answer;
    }

//    private static int solution(int cacheSize, String[] cities) {
//        String[] newCities = conversion(cities);
//
//        if (cacheSize == 0) {
//            return 5 * cities.length;
//        }
//
//        ArrayList<City> cache = new ArrayList<>();
//        timeCnt = 1;
//
//        int index = 0;
//        while (true) {
//            ArrayList<City> temp = new ArrayList<>(cache);
//            for (City city : temp) {
//                if (city.cityName.equals(newCities[index])) {
////                    cache.add(new City(newCities[index], timeCnt));
//                    timeCnt++;
//                    answer += 1;
//                } else {
//                    cache.add(new City(newCities[index], timeCnt));
//                    timeCnt++;
//                    answer += 5;
//                }
//            }
//            index++;
//            if (cache.size() == cacheSize || index>= newCities.length) {
//                break;
//            }
//            System.out.println(answer);
//        }
//
//
//        for (int i = index; i < newCities.length; i++) {
//            timeCnt++;
//            Collections.sort(cache);
//
//
//            int visitedCnt = 0;
//            for (City city : cache) {
//                if (city.cityName.equals(newCities[i])) {
//                    city.time = 1;
//                    answer += 1;
//                    break;
//                }else visitedCnt ++;
//            }
//
//            if (visitedCnt == cacheSize) {
//                cache.remove(cacheSize-1);
//                cache.add(new City(newCities[i], timeCnt));
//                answer+=5;
//            }
//        }
//        return answer;
//    }

    private static String[] conversion(String[] cities) {
        String[] temp = new String[cities.length];

        for (int i = 0; i < cities.length; i++) {
            temp[i] = cities[i].toLowerCase();
        }

        return temp;
    }

    private static class City implements Comparable<City>{
        String cityName;
        int time;

        public City(String cityName, int time){
            this.cityName = cityName;
            this.time = time;
        }

        @Override
        public int compareTo(City o) {
            return o.time - this.time;
        }

    }
}
