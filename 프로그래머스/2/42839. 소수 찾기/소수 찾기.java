// 출력 : 흩어진 종이 조각을 붙여 소수를 몇개 만들 수 있는지
// 중복 X 순열 => 11 == 011과 동일 + 소수 판별
// numbers길이<=7
// string으로 조합해서 integer로 변경하자
import java.util.*;

class Solution {
    static int depth;
    static HashSet<Integer> set = new HashSet<>();
    static boolean[] visited;
    public int solution(String numbers) {
        int answer = 0;
        depth = numbers.length();
        for (int i=1; i<=depth; i++) {
            visited = new boolean[depth];
            dfs(0, 0, "", i, numbers);
        }
        for (int i: set) {
            System.out.println(i);
        }
        answer = set.size();
        return answer;
    }
    public void dfs(int level, int start, String tmp, int max, String numbers) {
        if (level == max) {
            if (checkPrime(tmp)) set.add(Integer.parseInt(tmp));
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
    public boolean checkPrime(String str) {
        int num = Integer.parseInt(str);
        if (num == 0 || num == 1) return false;
        for (int i=2; i<num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    
}