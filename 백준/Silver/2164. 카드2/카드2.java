import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 2164 - 카드2
// 1번카드가 제일 위에, N번 카드가 제일 아래
// 제일 위에 있는 카드를 바닥에 버림 -> 제일 위에 있는 카드를 제일 아래 있는 카드 밑으로 옮김
// 하나 남을때까지 반복
// 출력 : 제일 마지막에 남게되는 카드
// 큐 => 제일 먼저 들어간 것이 제일 먼저 나옴
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q= new LinkedList<>();
        for (int i=1; i<=N; i++) {
            q.offer(i);
        }

        int top = 0;
        while(!q.isEmpty()) {
            top = q.poll();
            if (q.size() == 1) {
                top = q.peek();
                break;
            }
            else if (!q.isEmpty()) {
                top = q.poll();
                q.offer(top);
            } else {
                break;
            }
        }
        System.out.println(top);
    }
}
