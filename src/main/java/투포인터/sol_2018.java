package 투포인터;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//2018 - 수들의 합5, 어떤 자연수 N은 몇개의 연속된 자연수의 합으로 나타낼 수 있음
//이 N이 몇개의 연속된 자연수의 합으로 나타내는 가지수를 알고싶어함. 사용하는 자연수는 N 이하여야 함
//출력: 가지수를 출력함
//투포인터의 대표적 문제, 투 포인터의 이동원칙 - 값이 커지면 start 포인터를 다음칸으로 조정, 값이 작아지면 end 포인터를 한칸더 이동
//(sum> N) : sum-=start, start++ (sum<N) : sum+=end; end++; (sum==N): end++; sum+=end; result++;
//연속하는 자연수 k의 개수를 늘려서 각 합이 N과 일치하는지 체크
//굳이 배열 필요 없음!
//다시 - 처음에는 for문 돌아서 했는데 그럴 필요가 없음
public class sol_2018 {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int start =1, end=1; //투포인터 설정
        int sum=1, result=1;
        while (end!=N) {
            if(sum==N) { //맞으면
                result++;
                end++; //endindex 증가
                sum+=end; //누적합에 end 더하기
            } else if (sum>N) { // N보다 크면
                sum-=start;
                start++;
            } else { //N보다 작으면
                end++;
                sum+=end;
            }
        }
        System.out.println(result);
    }
}
