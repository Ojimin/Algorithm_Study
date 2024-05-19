package swea;

import java.util.Scanner;

//최빈수 출력 문제
//완탐, 구현
public class swea_1204 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T;
        T=sc.nextInt();
        int[] arr = new int[1000];
        int[] visited;
        int result=0;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            visited=new int[1000];
            for (int i=0; i<1000; i++) {
                arr[i] = sc.nextInt();
            }
            result=sol(arr,visited);
            sb.append("#"+n+" "+arr[result]+"\n");
        }
        System.out.print(sb);
        sc.close();
    }
    public static int sol(int[] arr, int[] visited) {
        for (int i=0; i<999; i++) {
            for (int j=i+1; j<1000; j++) {
                if (arr[i]==arr[j]) {
                    visited[i]++;
                    visited[j]++;
                }
            }
        }
        int max = 0;
        int index=0;
        for (int i=0; i<1000; i++) {
            if(max <= visited[i]) {
                max = visited[i];
                index=i;
            }
        }
        return index;
    }
}
