package BFS_DFS;

public class 프로그래머스_타겟넘버 {
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
