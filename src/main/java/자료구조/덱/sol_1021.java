package 자료구조.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 1021 - 회전하는 큐
// N개의 원소를 포함하고 있는 양방향 순환 큐, 몇개의 원소를 뽑아내려고 함
// 1. 첫번째 원소 뽑아냄 => poll연산
// 2. 왼쪽으로 한칸 이동 => a1이 맨 뒤로가게 됨, 즉 앞에서 하나 빼서 뒹로 하나 넣음 => Poll 이후 Push
// 3. 오른족으로 한칸 이동 => ak, a1, .. ak-1이 됨, 즉 뒤에서 하나 빼서 앞으로 넣음 => polllast 해서 push
// 출력 : 뽑아내려고 하는 원소의 위치가 주어질때, 2번, 3번 연산의 최솟값
// 해당 원소 위치를 순서대로 뽑아야 합
// 어떻게 연산의 최솟값으로??
// 그냥 완전탐색으로 O(N*M)
// 최적화 - 큐 크기 기준으로 왼쩍으로 회전하는게 더 빠를지/오른쪽이 빠를지 판단 => 상대위치를 idx로 갱신해서 다시 비교
public class sol_1021 {
    static Deque<Integer> q = new ArrayDeque<>();
    static int minCnt = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i=1; i<=N; i++) {
            q.offer(i);
        }
        st = new StringTokenizer(br.readLine());
        int result = 0;
        for (int i=0;i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            int cnt = 0;
            // 하나씩 왼쪽, 오른쪽 peek 값 꺼내서 더 위치 차이 적게나는 걸로
            while(q.peek() != num) {
                int pollNum = q.pollLast(); // 뒤에 있는 거 빼서 앞에 넣기
                q.offerFirst(pollNum);
                cnt++;
            }
            if (cnt > (N - cnt)) {
                for (int j=0; j<cnt; j++) {
                    int addNum = q.poll(); // 앞에있는거 빼서 뒤에 넣기
                    q.offer(addNum);
                }
                cnt = N - cnt;
                for (int j=0; j<cnt; j++) {
                    int pollNum = q.poll(); // 앞에있는거뺴서 뒤에 넣고
                    q.offer(pollNum);
                }
            }
            q.poll();
            N--;
            result += cnt;
        }
        System.out.println(result);
    }
}
