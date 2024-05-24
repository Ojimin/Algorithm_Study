package 문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//1541-잃어버린 괄호, 괄호를 적절히 쳐서 식의 값이 최소가 되도록하기
//최소값 출력
//+,-, 숫자로 이루어짐. 5자리보다 많이 연속되는 숫자는 없음
//문자열
//다시-그리디, 구현 위주로 많이 풀어볼 것
//접근방식 : -를 기준으로 문자열을 나눔, 나눠진 문자열들을 + 기준으로 나눈다음 각각 더한다, 첫번째 값은 더해주고 나머지는 뺴면서 최솟값으로 만듦
//키포인트 : 최대한 큰수를 빼는것. 덧셈부분을 먼저 계산한다!!
//주의 : 첫번째 수는 양수!!, split으로 분리할시에 split의 경우 정규식을 받기 때문에 +를 하면 regex.pattern 어쩌구 exception을 뱉음.escape 처리를 위해 \\+를 해줘야함,
//StringTokenizer로 분리해서 푸는 방법도 있다
public class nonsol_1541 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = Integer.MAX_VALUE;
        String[] subtraction = br.readLine().split("-");

        for (int i = 0; i < subtraction.length; i++) {
            int temp = 0;
            String[] addition = subtraction[i].split("\\+");
            for (int j = 0; j < addition.length; j++) {
                temp += Integer.parseInt(addition[j]);
            }
            if(sum==Integer.MAX_VALUE) {
                sum=temp; //처음 수 더해주고
            } else {
                sum-=temp; //나머지는 -로 분리했으므로 빼주기
            }
        }
        System.out.println(sum);
    }
}
