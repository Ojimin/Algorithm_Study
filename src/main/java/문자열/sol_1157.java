package 문자열;

import java.io.IOException;
import java.util.Scanner;

//1157-단어 공부, 대소문자 구별 x, 가장 많이 사용된 알파벳 출력
//다시해보기
//알파벳 'a~z' 총 26개로 배열 선언
//입력 문자열 길이만큼 반복문 실행
//알파벳 개수만큼 반복문 실행
public class sol_1157 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase();
        int[] numCnt=new int[26];
        for(int i=0;i<str.length();i++) {
            int num = str.charAt(i)-'A';//0,1..
            numCnt[num]++;
        }
        int max=0;
        char answer='?';
        for (int i=0;i<numCnt.length;i++) {
            if(max<numCnt[i]){
                max=numCnt[i];
                answer=(char)(i+'A'); //아스키코드 int to char
            }else if(max==numCnt[i]) { //여러개 이상 나오면 ? 처리
                answer='?';
            }
        }
        System.out.println(answer);
    }
}
