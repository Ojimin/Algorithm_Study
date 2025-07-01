import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 10799 - 쇠막대기
// 규칙1. 쇠막대기는 자신보다 긴 쇠막대기 위에만 놓일 수 있음
// 규칙2. 긴 쇠막대기를 자르는 레이저는 적어도 하나 존재
// 규칙3. 레이저는 어떤 쇠막대기의 양끝점과도 겹치지 않음
// 레이저는 괄호 쌍을 표현됨 => 쇠막대기의 왼쪽끝은 여는 괄호, 오른쪽 끝은 닫는 괄호로
// 출력 : 잘려진 쇠막대기의 조각 총 갯수
// 괄호문자개수<=10^5
// 처음 자르는 거면 2배됨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int initial = 0;
        int cnt = 0;
        boolean isLazer = false;
        for (int i=0; i<str.length()-1; i++) {
            if(isLazer) {
                isLazer = false;
                continue;
            }
            if (str.charAt(i) == '(' && str.charAt(i+1) == ')') {
                isLazer= true;
                if(stack.isEmpty()) continue;
                else {
                    cnt += stack.size() + initial;
                    initial = 0;
                }

            } else {
                if (stack.isEmpty()) {
                    stack.push(str.charAt(i));
                    initial++;
                }
                else {
                    if (str.charAt(i) == '(') {
                        stack.push(str.charAt(i));
                        initial++;
                    }
                    else {
                        stack.pop();
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
