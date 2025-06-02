import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14888 - 연산자 끼워넣기
// N개로 이루어진 수열 + N-1개의 연산자
// 연산자 우선순위 X & 앞에서부터 & 정수 나눗셈으로 몫만
// 출력 : 식의 결과가 최대 & 최소 출력
public class Main {
    static int N;
    static int[] num;
    static int[] operator;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        operator = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int depth, int cnt) {
        if (depth == N-1) {
            if ( cnt > max) max = cnt;
            if (cnt < min) min = cnt;
            return;
        }

        for (int i=0; i<4; i++) {
            if (operator[i] == 0) continue;
            int tmp = 0;
            if (i==0) {
                if (depth == 0) {
                    tmp = num[depth] + num[depth+1];
                } else tmp = cnt + num[depth+1];
            }
            else if (i==1){
                if (depth == 0) {
                    tmp = num[depth] - num[depth+1];
                } else tmp = cnt - num[depth+1];
            }
            else if (i==2) {
                if (depth==0) {
                    tmp = num[depth] * num[depth+1];
                } else tmp = cnt * num[depth+1];
            }
            else if (i==3) {
                if(depth==0) {
                    tmp = num[depth] / num[depth+1];
                } else tmp = cnt / num[depth+1];
            }
            operator[i]--;
            dfs(depth+1,tmp);
            operator[i]++;
        }
    }
}
