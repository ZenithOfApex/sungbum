package programmers;

public class JumpJump {
    public static void main(String[] args) {
        int n = 5000;
        int result = solution(n);//5
        System.out.println(result);
    }

    public static int solution(int n){
        int answer = 0;

        while(n>0){
            if(n%2!=0){
                answer++;
                n--;
            }else{
                n/=2;
            }

        }

        return answer;
    }
}
