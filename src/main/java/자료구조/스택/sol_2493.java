package 자료구조.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 2493 - 탑
// N개의 높이가 서로 다른 탑을 수평직선의 왼->오 방향으로 세움
// 레이저 신호 : 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사 => 가장 먼저 만나는 단 하나의 탑에서만 수신 가능
// 출력 : 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지 알아내기
// 주의 : 즉 나보다 전에 들어온 탑 중 나보다 최초로 크기가 큰 거에 도달, 만약 이전에 나보다 큰 수신탑이 없었다면 도달X 0
// 이게 왜 스택일까? 스택의 추가, 제거, 최상단 확인의 시간복잡도는 O(1)
// N<=500000,
// 다시 - 무지성 clone은 메모리 초과와 시간 초과... 그냥 단순 구현+stack은 시간초과.. 최적화와 아이디어 싸움.
// 기존코드의 문제 : 각 탑마다 최대 N번 비교함, N^2됨
// 핵심아이디어 : 입력 받을 때, 현재 탑보다 왼쪽에 있는 것들중 자기보다 낮은 탑은 제거  => why? 현재 탑이 그보다 높고 더 오른쪽에 있으므로 그탑들은 아무 신호도 받을 수 x.
// 자기보다 높은 탑이 남아있다면 현재 탑의 신호를 수신하는 탑
// 핵심 : 나보다 작은 높이의 탑은 지우는 것이 핵심 -> 논리적으로 나보다 다음에 들어오는 탑이 도달할 수 없기 때문
// 이 문제가 스택을 사용해야하는 이유는 스택은 가장 최근것을 확인하기 위한 좋은 구조이기 때문
public class sol_2493 {
    static class Top {
        int idx;
        int height;
        public Top(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Top> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) {
                stack.push(new Top(i+1, num));
                sb.append(0+" ");
            }
            else {
                Top top = stack.peek();
                if (top.height > num) {
                    sb.append(top.idx+" ");
                } else if (top.height <= num){
                    boolean isExisted = false;
                    stack.pop();
                    while(!stack.isEmpty()) {
                        if (stack.peek().height <=num) {
                            stack.pop();
                        }
                        else {
                            sb.append(stack.peek().idx+" ");
                            isExisted = true;
                            break;
                        }
                    }
                    if (!isExisted) sb.append(0+" ");
                }
                stack.push(new Top(i+1, num));
            }
        }
        System.out.println(sb);

    }
}
