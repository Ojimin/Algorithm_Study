package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 11729 - 최대힙, 배열에 자연수 x를 넣고 배열에서 가장 큰 값을 출력, 그 값을 배열에서 제거
// 출력 : 0이 주어질 때마다 배열에서 가장 큰 값 출력하고 제거
public class sol_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Integer outX=0;
        for (int i = 1; i <= N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                outX = pq.poll();
                sb.append(outX==null ? 0 : outX).append("\n");
            }
            else pq.offer(x);
        }
        System.out.println(sb);
    }
}
