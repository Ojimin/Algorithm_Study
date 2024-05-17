import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2668-숫자고르기, 첫째줄 숫자를 적절히 뽑으면 그 뽑힌 정수를 이루는 집합과 뽑힌 정수들의 바로 밑의 둘째줄에 들어있는 정수들이 이루는 집합 일치함
//뽑힌 개수가 최대가 되도록 개수와 뽑힌 정수들 오름차순을 출력
//그래프 탐색,,,
public class nonsol_2668 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int max = 0;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[2][N];
        result = new int[N];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            arr[0][i] = i + 1;
            arr[1][i] = Integer.parseInt(br.readLine());
        }
        //1~N까지 뽑아서 돌려보깅
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N];
            dfs(0, 0, i);
        }
        sb.append(max + "\n");
        for (int i = 0; i < N; i++) {
            if (result[i]==1) {
                sb.append(arr[0][i] + "\n");
            }
        }
        System.out.print(sb);
    }

    public static void dfs(int num, int num_cnt, int max_num) {
        if (num_cnt == max_num) return;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                num_cnt++;
                dfs(i, num_cnt, max_num);
                count(num_cnt);
                num_cnt--;
                visited[i] = false;
            }
        }

    }
    // 방문한 인덱스들 계산
    public static void count(int num_cnt) {
        int correct_cnt = 0;
        int[] temp_result = new int[N];
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                int tmp = 0;
                while (tmp < N) {
                    if (visited[tmp]) {
                        if (arr[1][tmp] == arr[0][i]) {
                            correct_cnt++;
                            temp_result[i] = 1;
                            break;
                        }
                    }
                    tmp++;
                }
            }
        }
        if (num_cnt == correct_cnt) {
            if (max < correct_cnt) {
                max = correct_cnt;
                for(int i=0; i<N; i++) {
                    if(temp_result[i]==1) {
                        result[i]=1;
                    }
                    else result[i]=0;
                }
            }
        }
    }
}
