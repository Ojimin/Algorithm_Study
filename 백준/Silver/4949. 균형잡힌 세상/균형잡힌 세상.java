
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 4949 - 균형잡힌 세상
// 출력 : 각 줄마다 균형을 이루는 문자열이면 yes, 아니면 no
// 규칙- 소괄호, 대괄호는 하나씩 짝을 이뤄야 함
// 종료 조건으로 맨 마지막 온점 하나 들어옴
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        StringBuilder sb = new StringBuilder();
        while(!(str = br.readLine()).equals(".")) {
            Stack<Character> stack = new Stack();
            boolean isBalanced = true;
            for (int i=0; i<str.length(); i++) {
                if (str.charAt(i) == '(' || str.charAt(i) == '[') {
                    stack.push(str.charAt(i));
                } else if (str.charAt(i) == ')' || str.charAt(i) == ']') {
                    if (stack.isEmpty()) {
                        isBalanced = false;
                        sb.append("no").append("\n");
                        break;
                    }
                    else if (stack.peek() == '(' && str.charAt(i) !=')') {
                        isBalanced = false;
                        sb.append("no").append("\n");
                        break;
                    } else if(stack.peek()=='[' && str.charAt(i) != ']') {
                        isBalanced = false;
                        sb.append("no").append("\n");
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            if(isBalanced && !stack.isEmpty()) {
                sb.append("no").append("\n");
            } else if (isBalanced && stack.isEmpty()) {
                sb.append("yes").append("\n");
            }
        }
        System.out.println(sb);
    }
}
