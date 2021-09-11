package KaKaoCodingTest;

public class Q02 {
    public static void main(String[] args) {
        int n=437674;
        int k=3;
        int result = solution(n, k);
        System.out.println(result);
    }
    public static int solution(int n, int k) {
        int answer = -1;

        String[] result = {};
        result = String.valueOf(conversion(n, k)).split("0");
        int count=0;
        for(int i=0;i<result.length;i++){
            for(int j=2;j<n;j++){
                if(Integer.parseInt(result[i])%j ==0)
                    continue;
                else if(j==n-1)
                    count++;
            }
        }
        answer = count;
        return answer;
    }

    public static StringBuilder conversion(int number, int n){
        StringBuilder sb = new StringBuilder();
        int now = number;
        while(now>0){
            if(now%n<10){
                sb.append(now % n);
            }
            else{
                sb.append((char) (now % n - 10 + 'A'));
            }
            now /=n;
        }
        return sb.reverse();
    }
}
