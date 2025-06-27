import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 10866 - 덱
// 명령어 주어진 거에 따라 맞게 출력
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        // push_front: 앞에
        // Push_back: 뒤에 넣기
        // pop_front : 덱의 가장 앞에 있는 수 빼고 그 수 출력, 없으면 -1
        // pop_back : 덱의 가장 뒤에 있는 수 빼고 그 수 출력, 없는 경우 -1 출력
        // size : 정수 갯수 출력
        // empty : 덱이 비어있으면 1, 아니면 0을 출력
        // front : 덱 가장 앞에 있는 정수 출력,아니면 -1 출력
        // back : 덱 가장 뒤에 있는 정수 출력, 아니면 -1 출력
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            switch (str) {
                case "push_front":
                    int num = Integer.parseInt(st.nextToken());
                    q.offerFirst(num);
                    break;
                case "push_back":
                    int numBack = Integer.parseInt(st.nextToken());
                    q.offer(numBack);
                    break;
                case "pop_front":
                    if(q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int pollNum = q.poll();
                        sb.append(pollNum+"\n");
                    }
                    break;
                case "pop_back":
                    if (q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int pollNum2 = q.pollLast();
                        sb.append(pollNum2+"\n");
                    }
                    break;
                case "front":
                    if(q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int peekNum = q.peek();
                        sb.append(peekNum + "\n");
                    }
                    break;
                case "back":
                    if(q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int peekNum = q.peekLast();
                        sb.append(peekNum + "\n");
                    }
                    break;
                case "size":
                    sb.append(q.size()+"\n");
                    break;
                case "empty":
                    if (q.isEmpty()) sb.append(1+"\n");
                    else sb.append(0+"\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
