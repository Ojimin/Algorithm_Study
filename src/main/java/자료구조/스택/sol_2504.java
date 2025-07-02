package 자료구조.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//2504 - 괄호의 값
// 출력 : 괄호값을 정의한대로 계산하여 출력  & 올바르지 못한 괄호열이면 반드시 0
// () => 2, []=>3, (x) => 2*x, [x] =>3*x,
// 올바른 괄호열 x와 y가 결합된 xy의 괄호값은 x+y 로 계산
// 로직 1.괄호가 올바르게 끝나고 뒤에 또 괄호가 있으면 더하기 할것
// 로직 2.언제일때 더해서 곱할지를 고민
// 길이<=30
// depth, 닫닫이 연속으로 들어오면 pop x
// 다시 - 아이디어가 생각이 안남, 열린 괄호는 닫힌다고 가정
// 닫히기 시작하면 result에 더하고, 곱한만큼 다시 나누기
// 분배법칙 => 곱셈은 깊이, 덥셈은 병렬로 그리고 가장 안쪽의 계산일 수록 먼저 해야하므로 열린것이 바로 닫히면 먼저 게산해야함
public class sol_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int tmp = 1;
        boolean isCorrect = true;
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '(' ||  str.charAt(i) =='[') {
                stack.push(str.charAt(i));
                if(str.charAt(i) == '(') tmp*=2;
                else tmp*=3;
            } else if (str.charAt(i) == ')' || str.charAt(i) == ']') {
                isCorrect = true;
                switch (str.charAt(i)) {
                    case ')' :
                        if (stack.isEmpty()) isCorrect = false;
                        else if(stack.peek() == '(') {
                            if (str.charAt(i-1) == '(') {
                                result += tmp;
                            }
                            tmp /= 2;
                            stack.pop();
                        } else isCorrect = false;
                        break;
                    case ']' :
                        if (stack.isEmpty()) isCorrect = false;
                        else if(stack.peek() == '[') {
                            if (str.charAt(i-1) == '[') {
                                result += tmp;
                            }
                            tmp /= 3;
                            stack.pop();
                        } else isCorrect = false;
                        break;
                }
                if (!isCorrect) break;
            }
        }
        if (!isCorrect) System.out.println(0);
        else {
            if (stack.isEmpty()) {
                System.out.println(result);
            } else {
                System.out.println(0);
            }
        }
    }
}
