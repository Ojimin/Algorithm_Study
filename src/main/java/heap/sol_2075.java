package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 2075 - N번째 큰수
// N*N 표에 수가 채워져있는데 모두 자신의 한칸 위에 있는 수보다 큼
// 출력 : N번째 큰 수 찾기
// 주의 : -10억 <= >= 10억
public class sol_2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++) {
                long tmp =  Long.parseLong(st.nextToken());
                pq.offer(tmp);
            }
        }
        for (int i=1; i<=N; i++) {
            if (i==N) System.out.println(pq.poll());
            else pq.poll();
        }
    }
}
