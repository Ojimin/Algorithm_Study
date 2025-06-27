package 자료구조.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 10828 - 스택
// push, pop, size, empty, top 명령어 수행
// 출력 : 각 명령이 주어질때마다 한줄에 하나씩 출력
public class sol_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            switch (order) {
                case "push" :
                    int num = Integer.parseInt(st.nextToken());
                    stack.push(num);
                    break;
                case "pop" :
                    if (stack.isEmpty()) sb.append(-1+"\n");
                    else {
                        Integer popNum = stack.pop();
                        sb.append(popNum+"\n");
                    }
                    break;
                case "size" :
                    sb.append(stack.size()+"\n");
                    break;
                case "empty":
                    if (stack.isEmpty()) sb.append(1+"\n");
                    else sb.append(0+"\n");
                    break;
                case "top":
                    if(stack.isEmpty()) sb.append(-1+"\n");
                    else sb.append(stack.peek()+"\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
