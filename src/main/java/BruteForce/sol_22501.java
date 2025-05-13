package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 22501 - 재귀의 귀재,
// ispalindrom 함수의 호출 횟수를 구하자
// isPalindrom 함수의 반환값 & recursion 함수 호출 횟수를 한줄에 출력
// palindrom이면 1, 아니면 0
public class sol_22501 {
    static int cnt;
    public static int recursion(String s, int l, int r){
        cnt++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<T;i++){
            String s = br.readLine();
            cnt = 0;
            sb.append(isPalindrome(s) + " " + cnt).append("\n");
        }
        System.out.println(sb);
    }
}
