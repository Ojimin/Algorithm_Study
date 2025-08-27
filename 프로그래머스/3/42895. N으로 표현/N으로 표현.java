// N을 여러번 사칙연산과 괄호를 활용해 number 만들기
// 출력 : N과 사칙연산만 사용해서 표현할 수 있는 방법 중 N 사용횟수의 최솟값
// 주의 : 나누기 연산의 나머지는 무시
// 주의 : 최솟값이 8보다 크면 -1 return
// 다시
// 1. 1 ~ 8까지 돌면서 각 배열?에 S[i] = N을 i개 써서 만들 수 있는 조합
// 2. 조합 : NN 이런식으로 붙여쓴 경우, j개 조합 * i-j개 조합해서 다 구함
// 2-1. 이때, 사칙연산 주의 : -는 앞뒤 다르게, /도 앞뒤 다르게 + 하나 붙이기
// 3. 이떄 결과값들은 set으로 해서 중복되지 않게 함
// 4. 만약 계산해서 나온 값이 number이면 반복문 즉시 종료
// 조합 + dp?
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        boolean isAnswered = false;
        HashSet<Integer>[] dp = new HashSet[9];
        for (int i=0; i<9; i++) {
            dp[i] = new HashSet<>();
        }
        dp[1].add(N);
        if (dp[1].contains(number)) return 1;
        for (int i=2; i<=8; i++) {
            // 조합
            for (int j=1; j<=i-1; j++) {
                int k = i-j;
                for (int num1 : dp[j]) {
                    for (int num2: dp[k]) {
                        dp[i].add(num1 + num2);
                        dp[i].add(num1 - num2);
                        dp[i].add(num1 * num2);
                        if (num2 != 0) dp[i].add(num1 / num2);
                    } 
                    if( j == i-1) {
                        String str = Integer.toString(num1) + N;
                        dp[i].add(Integer.parseInt(str));
                    }   
                }
            }
            
            if(dp[i].contains(number)) {
                isAnswered = true;
                answer = i;
                break;
            }   
        }
        if (isAnswered) return answer;
        else return -1;
    }
}