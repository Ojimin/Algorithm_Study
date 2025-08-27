// 출력 : 흩어진 종이 조각을 붙여 소수를 몇개 만들 수 있는지
// 중복 X 순열 => 11 == 011과 동일 + 소수 판별
// numbers길이<=7
// string으로 조합해서 integer로 변경하자
import java.util.*;

class Solution {
    static int depth;
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] visited, isPrime;
    public int solution(String numbers) {
        int answer = 0;
        depth = numbers.length();
        isPrime = new boolean[10000000];
        Arrays.fill(isPrime, true);
        //에라토스테네스 체 활용
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i<Math.sqrt(9999999); i++) {
            if(isPrime[i]) {
                for (int j=i*i; j<=9999999; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i=1; i<=depth; i++) {
            visited = new boolean[depth];
            dfs(0, 0, "", i, numbers);
        }
        for(int i: set) {
            System.out.println(i);
        }
        
        answer = set.size();
        return answer;
    }
    public void dfs(int level, int start, String tmp, int max, String numbers) {
        if (level == max) {
            if (isPrime[Integer.parseInt(tmp)]) set.add(Integer.parseInt(tmp));
            return;
        }
        for (int i=0; i<depth; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(level+1, i+1, tmp + Character.toString(numbers.charAt(i)), max, numbers);
                visited[i] = false;
            }
        }
    }
    // 그냥 완탐
    public boolean checkPrime(String str) {
        int num = Integer.parseInt(str);
        if (num == 0 || num == 1) return false;
        for (int i=2; i<num; i++) { // 굳이 num까지 계산 안해도됨. sqrt로 고고
            if(num % i == 0) return false;
        }
        return true;
    }
}