package BruteForce;

//14501 : 퇴사... N+1 되는날 퇴사를 하기 위해서 남은 N일 동안 최대한 많은 상담을 하려고 함.각각의 상담을 완료하는데 걸리는 기간 t와 받을 수 있는 금액 p로 구성되어있음.
//최대수익을 구할 수 있도록 출력
//브루트포스 => 모든 경우의 수
//그냥 처음부터 끝까지 다 한번씩 돌아서 하면 될 것 같음
//기본 case는 그냥 1부터 끝까지 돌면서 체크, but 어느 케이스들은 종종 시작하는 것은 똑같이, 뒤에 선택하는 것이 달라짐
//백트래킹?,조건이 있는 조합느낌?
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class nonsol_14501 {
    static int N;
    static int[][] arr;
    static boolean[] visited;

    static int max;
    static int tmp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr=new int[2][N+1];
        visited = new boolean[N+1];
        max=0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
        }
        sol(1,1);
        System.out.println(max);
    }
    public static void sol(int start, int day) {
        if (start==1) {
            tmp=0;
        }
        if (day > N+1) {
            return ;
        }
        else if(day <= N+1 ) {
            tmp+=arr[1][start];
            if (tmp > max) {
                max = tmp;
            }
        }

        for (int i=start; i<N+1; i++) {
            if (!visited[i]) {
                visited[i]=true;
                System.out.println("오늘은:"+day);
                sol(day+arr[0][i],day+arr[0][i]);
                System.out.println("돌아옴:"+day);
                visited[i]=false;
            }
        }
    }
}
