package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

// 1874 - 스택 수열
// 1~n까지의 수를 스택에 넣었다가 뽑아 늘어놓아 하나의 수열 만듦
// 주의 : 스택에 push하는 순서는 오름차순으로 지킴
// 출력 : 스택을 이용해 그 수열을 만들 수 있는지 & 있다면 어떤 순서로 Push와 pop 연산을 수행해야 하는지를
// 출력 : 입력된 수열을 만들기 위해 필요한 연산 출력 - push 연산은 +, pop연산은 -로 & 불가능한 경우 NO
// 해당 수열을 만들 수 있는 방법은 1가지뿐
// 자바에서는 스택 자료구조를 사용하지 않으므로 접근 1. arraylist?
// 접근 2. 덱?

public class sol_1874 {
    static int N;
    static int[] arr;
    static ArrayList<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        int start = 0; // 넣어야하는 값
        while((N--) > 0) {
            int value = Integer.parseInt(br.readLine());
            if (value > start) { // 꺼내야하는 값이 start보다 크면 그전까지 계속 넣어야함
                for (int i=start+1; i<=value; i++) {
                    stack.push(i);
                    sb.append('+').append('\n');
                }
                start = value;
            } else if (stack.peek() != value) { //top에 있는 값이 꺼낼 값과 다른 경우 no
                System.out.println("NO");
                return;
            }
            stack.pop();
            sb.append('-').append('\n');
        }
        System.out.println(sb);

//        for (int i=1; i<=N; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//        boolean isPossible = solve();
//        if (isPossible) System.out.print(sb);
//        else System.out.println("NO");
    }

    public static boolean solve() {
        int tmp = 1; // 넣어야할 값
        int top = 0; // 현재 top에 위치한 값
        boolean isPossible = true;
        for (int i=1; i<=N; i++) {
            if (top == arr[i]) {
                list.remove(list.size()-1);
                if (!list.isEmpty())  top = list.get(list.size()-1);
                sb.append("-").append("\n");
            }
            else if (top > arr[i]) {
                isPossible = false;
                break;
            }
            else {
                while(tmp != arr[i]) {
                    sb.append("+").append("\n");
                    list.add(tmp);
                    tmp++;
                }
                sb.append("+").append("\n");
                sb.append("-").append("\n");
                if(!list.isEmpty())  top = list.get(list.size()-1);
                tmp++;
            }
        }
        return isPossible;
    }
}
