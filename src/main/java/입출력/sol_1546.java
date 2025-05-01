package 입출력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1546 - 배열, 세준이는 자기 점수중 최댓값을 골라 이 값을 M이라 하고 모든 점수를 점수/M*100이라 고침
//새로운 평균을 구하는 프로그램
//형변환 : (casting하는 자료형).valueOf(casting할 value)
public class sol_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;//음이 아닌 정수 값이 들어오므로 max 0으로 설정

        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (max < arr[i]) max=arr[i];
        }

        //새로운 평균값 생성
        double avg = 0.0;
        for (int i=0; i<N; i++){
            avg += (double)arr[i]/(double)max * 100.0;
        }
        avg = avg/N;
        System.out.println(avg);
    }
}