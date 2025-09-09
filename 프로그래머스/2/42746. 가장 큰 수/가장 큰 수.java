// 0/양의 정수가 주어졌을 때, 정수를 이어붙여 만들 수 있는 가장 큰 수 알아내기
// 출력: 가장 큰수를 문자열로 바꾸어 return
// numbers <= 10^5 이하 => 백트래킹은 터짐
// 무슨아이디어? 첫번째 문자열이 가장 큰 값-그다음 큰값... => 수 자체가 큰게 아니라
// 다시 => 문자열 2개 붙였을 때, 둘중에 더 큰 것으로 리턴
import java.util.*;

class Solution {
    static int max = Integer.MIN_VALUE;
    public String solution(int[] numbers) {
        String answer = "";
        String[] nToStr = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            nToStr[i] = numbers[i] + "";
        }
        // 내림차순 정렬
        Arrays.sort(nToStr, new Comparator<>() {
            @Override
            public int compare(String str1, String str2) {
                return (str2+str1).compareTo(str1+str2);
            }
        });
        for (int i=0; i<nToStr.length; i++) {
            answer+=nToStr[i];
        } 
        if (answer.charAt(0) == '0') answer="0";
        return answer;
    }

}