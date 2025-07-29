// n개의 음이 아닌 정수들 중, 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟넘버 만들기
// 출력 : 타겟넘버 만드는 방법 수
// dfs?
class Solution {
    static int length;
    static int answer = 0; 
    public int solution(int[] numbers, int target) {
        length = numbers.length;
        dfs(0,0,target,numbers);
        return answer;
    }
    public static void dfs(int depth, int cnt, int target, int[] numbers) {
        if (depth == length) {
            if (target == cnt) {
                answer++;
            }
            return;
        }
        // 0이면 -, 1이면 +
        for (int i=0; i<2; i++) {
            if (i==0) {
                dfs(depth+1, cnt-numbers[depth], target, numbers);
            } else {
                dfs(depth+1, cnt+numbers[depth], target,numbers);
            }
            
        }
        
    }
}