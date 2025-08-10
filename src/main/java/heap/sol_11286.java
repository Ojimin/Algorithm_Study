package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 11286 - 절댓값 힙
// 배열에 정수 x 넣고 절댓값이 가장 작은 값 출력 & 그 값을 배열에서 제거
// 절댓값이 같다면 가장 작은 수 출력
public class sol_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                }
                return Math.abs(o1) - Math.abs(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (pq.isEmpty()) sb.append(0).append("\n");
                else sb.append(pq.poll()).append("\n");
            } else {
                pq.offer(num);
            }
        }
        System.out.println(sb);
    }
}
