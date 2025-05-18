package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 22867 - 가장 긴 짝수 연속한 부분 수열
// 길이가 N인 수열 S - 1 이상 정수
// 원하는 위치에 있는 수 골라 최대 K번 삭제 가능
// 출력 : k번 삭제한 수열에서 짝수로 이루어졌있는 연속한 부분의 가장 긴 길이
// 투포인터 :조건에 맞는 연속된 길이 구하기에 적합
// start, end 잘 조정 & 어떤 숫자들을 지울 것인지 -> 이거에 집중할 필요가 없는게 투포인터로 구간을 조정해가면서 그 안에 들어가있는 홀자들만 지우면 될듯
// 다시 - 투포인터.. & 기준 : right와 left 사이의 홀수 갯수가 k개 초과면 left 증가, k개 이하면 right증가?
// 다시 - dp로..
public class sol_22857 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int right = 0;
        int left = 0;
        int even = 0;
        int odd = 0;
        if (arr[0] % 2 == 0) even++;
        else odd++;
        int answer = even;
        while(right>=left) {
            if (odd > K) { //홀수가 k개보다 초과면
                if (arr[left]%2 == 0) even--; //현재 left가 even이면 개수 -1
                else odd--;
                left++; // left 증가
            } else { // odd <=K  홀수가 k개 이하면
                right++;
                if (right>=N) break; // (범위체크) right가 끝에 도달
                if(arr[right]%2==0) even++;
                else odd++;
                answer = Math.max(answer,even); //전에 계산한 even과 현재 even 비교해서 더 큰 값 저장
            }
        }
        System.out.println(answer);
    }
}
