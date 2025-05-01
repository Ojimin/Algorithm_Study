package 입출력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//지금 코드 비효율적 => 매번 print하면 성능저하 너무 큼 => StringBuilder로 담아서 한꺼번에 출력하자
public class soL_2738 {
    static int[][] a,b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        a= new int[N][M];
//        b= new int[N][M];

        //행렬 입력받고
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++){
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for (int i=0;i<N;i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j=0;j<M;j++){
//                b[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }

        //행렬 더한 것 출력
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                sb.append(a[i][j]+Integer.parseInt(st.nextToken()) +" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
