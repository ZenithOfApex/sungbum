package KaKaoCodingTest;

public class test {
    public static void main(String[] args) {
        int num =14;

        System.out.println(conversion(14, 8));

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
