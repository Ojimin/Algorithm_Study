package 자료구조.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 5430 - AC
// AC에는 두가지 함수 - R(뒤집기), D(버리기)
// R은 배열에 있는 수의 순서를 뒤집는 함수, D는 첫번째 수를 버리는 함수 => 비어있는 데 사용할 경우 에러 발생
// 출력 : 배열의 초기값과 수행할 함수가 주어졌을때, 결과 출력, 에러 발생시 error
// T<=100, p<=10^5,함수 개수 n<=10^5, p+n<=7*10^5
// 왜 덱인가? 스택의 FILO와 큐의 맨 첫번째 수 뽑기 둘 다 필요하기 때문
// 그러나 시간초과 발생 => 그래서 RR = 그냥 원래로 취급하는 것을 핵심으로 해서 성공
public class sol_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            String function = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new ArrayDeque<>();
            String input = br.readLine();
            String[] arr = input.substring(1,input.length()-1).split(",");
            for (int i=0; i<n; i++) {
                deque.offer(Integer.parseInt(arr[i]));
            }
            boolean isError = false;
            boolean isReversed = false;
            for (int i=0; i<function.length(); i++) {
                if (deque.isEmpty() && function.charAt(i) == 'D') {
                    sb.append("error").append("\n");
                    isError = true;
                    break;
                } else {
                    switch (function.charAt(i)) {
                        case 'R':
                            if (!isReversed) isReversed = true;
                            else isReversed = false;
                            break;
                        case 'D':
                            if (isReversed) deque.pollLast();
                            else deque.poll();
                            break;
                    }
                }
            }
            if (deque.isEmpty() && !isError) sb.append("[]").append("\n");
            else if (!isError) {
                sb.append("[");
                if (isReversed) {
                    while(!deque.isEmpty()) {
                        int num = deque.pollLast();
                        sb.append(num+",");
                    }
                } else {
                    while(!deque.isEmpty()) {
                        int num = deque.poll();
                        sb.append(num+",");
                    }
                }
                sb.replace(sb.length()-1, sb.length(), "]\n");
            }
        }
        System.out.println(sb);
    }
}
