package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 1927 - 최소힙
// 배열에서 가장 작은 값 출력, 이후 그 배열에서 제거
// 0 입력이면 이전까지 들어온 숫자중 가장 작은 값 출력하고 그 값을 제거
public class sol_1927 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                Integer temp = pq.poll();
                if (temp == null) sb.append("0").append("\n");
                else sb.append(temp).append("\n");
            } else {
                pq.offer(num);
            }
        }

        System.out.println(sb);

    }
}
