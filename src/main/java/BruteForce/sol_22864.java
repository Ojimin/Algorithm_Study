package BruteForce;

import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
public class sol_22864 {
    //하루 최대 피로도 M이 넘지 않도록 하루에 얼마나 일을 할 수 있는지 구하기
    //하루는 24시간
    //1.완탐 - 모든 경우의 수 찾음
    //2.그냥 타임이 24시간 될때까지 m이 넘지 않도록 더하고 빼고 게속 반복
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());//피로도
        int b = Integer.parseInt(st.nextToken());//일
        int c = Integer.parseInt(st.nextToken());//쉬면 이만큼 피로도 회복
        int m = Integer.parseInt(st.nextToken());//최대 피로도
        int t = 0;
        int max_work = 0;
        int p = 0;
        while (t < 24) {
            p += a;
            if (a > m) break;
            if (p > m) {
                p -= a;
                p -= c;
                if (p < 0) {
                    p = 0;
                }
                t++;
                continue;
            }
            max_work += b;
            t++;
        }
        System.out.println(max_work);
    }
}