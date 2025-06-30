package 자료구조.큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 10845 - 큐
// 정수를 저장하는 큐의 명령을 구현하는 것
// 출력 : 명령이 주어질 때마다 한줄에 하나씩 출력
// 큐 문제지만 덱 사용
// 큐사용해서 할려면 last값은 최근 푸시하는 값을 따로 저장해서 구현
public class sol_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        // pop - 큐에 가장 앞에있는 정수 뺴고 출력, -1
        // size - 정수 개수
        // empty - 비어있으면 1, 아니면 0
        // front - 큐 가장 앞에 있는 정수 출력, 없는 경우 -1
        // back - 큐 가장 뒤에 있는 정수 출력, 없는 경우 -1
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            switch (str) {
                case "push" :
                    int num = Integer.parseInt(st.nextToken());
                    q.offer(num);
                    break;
                case "pop" :
                    if (q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int pollNum = q.poll();
                        sb.append(pollNum+"\n");
                    }
                    break;
                case "front" :
                    if(q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int front = q.peek();
                        sb.append(front+"\n");
                    }
                    break;
                case "back":
                    if(q.isEmpty()) sb.append(-1+"\n");
                    else {
                        int back = q.peekLast();
                        sb.append(back+"\n");
                    }
                    break;
                case "size":
                    sb.append(q.size()+"\n");
                    break;
                case "empty":
                    if(q.isEmpty()) sb.append(1+"\n");
                    else sb.append(0+"\n");
                    break;
            }

        }
        System.out.println(sb);
    }
}
