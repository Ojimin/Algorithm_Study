import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 3986 - 좋은 단어
// 출력: 좋은 단어의 수
// 선끼리 교차하지 않고 각 글자를 정확히 한개의 다른 위치에 있는 같은 글자와 짝 지을 수 있으면 좋은단어
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        for (int i=0; i<N; i++) {
            String str = br.readLine();
            if(str.length() % 2 != 0) continue;
            Stack<Character> stack = new Stack<>();
            boolean isGood = true;
            for (char c : str.toCharArray()) {
                if (stack.isEmpty()) stack.push(c);
                else if (stack.peek() == c) stack.pop();
                else stack.push(c);
            }
            if (stack.isEmpty()) result++;
        }
        System.out.println(result);
    }
}
