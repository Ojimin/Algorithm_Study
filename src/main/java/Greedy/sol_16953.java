package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//16953-a->b, 정수 a를 b로 바꾸려고 함. 가능한 연산은 2를 곱합거나 1을 수의 가장 오른쪽에 추가하기
//필요한 연산의 최솟값을 구함
//출력 : 필요한 연산의 최솟값에 +1을 더한 값 출력, 만들 수 없는 경우 -1 출력
//거꾸로 b에서 a가 되도록 만들어보기, 2로 나누어지는지 확인, 안되면 10으로 나누고 나머지가 1이 되는지 확인, 이것도 아니면 -1 출력. b가 a보다 작아져도 -1 출력
//그리디,거꾸로 생각하기
//bfs로도 풀어보기!!
//다시
public class sol_16953 {
    static int a, b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int cnt =0;

        while(true) {
            if(b==a) {
                cnt++;
                break;
            }
            if(b%2==0) {
                b=b/2;
                cnt++;
            }else if ((b-1)%10==0){
                b=(b-1)/10;
                if(b<a){
                    cnt=-1;
                    break;
                }
                cnt++;
            } else {
                cnt=-1;
                break;
            }
        }
        System.out.println(cnt);
    }
    //bfs버전
}
