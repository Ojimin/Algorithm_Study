package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 15721 - 번데기
// 브루트포스 => 다 for문 돌면됨
//뻔, 데기 각각 세면서 01010011 0101000111
// n회차인지, 참여인원배열, 뻔/데기 각각 세기
// 회차가 끝난 것을 어떻게 구분? 힌트1. 숫자로 힌트2. 나눠서
// 풀이 정리: while문 돌면서 1. 먼저 번데기번데기 => S와 동일하면 번째 수 세면서 동일한지 체크, 몇번째 사람인지도 계쏙 체크 2. 회차에 따라 달라지는 번번 데기데기 체크
// 다시
// order 대신 번cnt + 데cnt -1도 할 수 있을듯
public class sol_15721 {
    static int A; // 참여하는 사람
    static int T; // 구하고자 하는 번째
    static int S; // 0-뻔인지, 1-데기인지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A= Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        S = Integer.parseInt(br.readLine());

        System.out.println(solve());
    }

    public static int solve(){
        int order = 0; // 몇번쨰사람, A
        int n = 1; // 몇회차인지
        int cnt = 0; //몇번쨰 번/데기 인지 - T와 비교

        while (true) {
            // 0101
            for (int i=0; i<4; i++) {
                if (i % 2 == 0) {
                    if (S == 0) {
                        cnt++;
                    }
                } else {
                    if (S == 1) {
                        cnt++;
                    }
                }
                if (cnt == T) return order % A;
                order++;
            }

            // 00 뻔뻔
            for (int i=0; i<n+1; i++) {
                if (S == 0) {
                    cnt++;
                }
                if (cnt == T) return order % A;
                order++;
            }

            // 11 데기데기
            for (int i=0; i<n+1; i++) {
                if (S == 1) {
                    cnt++;
                }
                if (cnt == T) return order % A;
                order++;
            }
            n++;
        }
    }
}
