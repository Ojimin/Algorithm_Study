package BruteForce;

import java.io.*;
import java.util.StringTokenizer;

//문제 설명 : 00시 00분 00초부터 N시 59분 59초까지의 모든 시각 중에서 K가 하나라도 포함되는 시각들의 수를 출력
//핵심 원리 : 브루트포스(완전탐색) - 일반적으로 100만개 이하일 경우 완전탐색 알고리즘을 사용하면 가장 적절함
public class sol_18312 {
    //내가 푼 방식 - 너무 if문이 많다
    public static void sol(int N, int K) {
        int cnt = 0;
        String str = "";
        for (int i = 0; i <= N; i++) {
            str = Integer.toString(i);
            if (str.length() == 1) {
                str = "0" + str;
            }
            if (str.contains(Integer.toString(K))) {
                cnt += 3600;
                continue;
            }
            for (int j = 0; j <= 59; j++) {
                str = Integer.toString(j);
                if (str.length() == 1) {
                    str = "0" + str;
                }
                if (str.contains(Integer.toString(K))) {
                    cnt += 60;
                    continue;
                }
                for (int k = 0; k <= 59; k++) {
                    str = Integer.toString(k);
                    if (str.length() == 1) {
                        str = "0" + str;
                    }
                    if (str.contains(Integer.toString(K))) {
                        cnt += 1;
                    }
                }
            }
        }
        System.out.println(cnt);
    }

    //다른 분 코드 참고
    public static void simulate(int N, int K) {
        int cnt = 0;
        for (int hour = 0; hour <= N; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                for (int second = 0; second < 60; second++) {
                    if (hour % 10 == K || hour / 10 == K || minute % 10 == K || minute / 10 == K || second % 10 == K || second / 10 == K) {
                        //10으로 나눈 나머지나 몫을 계산해서 k와 같은지 비교, 하나라도 있으면 cnt값 증가
                        cnt += 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        sol(N, K);
    }
}
