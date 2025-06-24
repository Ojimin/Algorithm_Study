import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 10773 - 제로
// 잘못된 수를 부를 때마다 0을 외쳐서 가장 최근에 재민이가 쓴 수를 지우게 시킴 => FILO
// 출력 : 모든 수를 받아 적은 후 그 수의 합
// 주의 : 합은 최대 2^31 -1 & 0을 외치는 것은 지울 수 있는 수가 보장됨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        long result = 0;

        for (int i=0; i<K; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                int popNum = stack.pop();
                result -= popNum;
            } else {
                stack.push(num);
                result += num;
            }
        }
        System.out.println(result);
    }
}
