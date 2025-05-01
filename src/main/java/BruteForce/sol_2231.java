package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2731-분해합
//N=자연수 M의 분해합일때, M을 N의 생성자라 함
//데이터개수가 백만개이하이므로 브루트포스 가능
public class sol_2231 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int result=0;
        for(int i=0; i<N; i++) {
            String str = Integer.toString(i);
            int temp=0;
//            for (int j=0;j<str.length();j++) {
//                temp+=Character.getNumericValue(str.charAt(j));
//            }
            int number = i;
            //자릿수 구하기!!
            while(number>0){
                temp+=number%10; //자리수를 10씩 분해해서 해당 자리수의 모든 값을 더함
                number/=10;
            }
            if(N==(temp+i)){
                result=i;
                break;
            }
        }
        System.out.println(result);
    }
}
