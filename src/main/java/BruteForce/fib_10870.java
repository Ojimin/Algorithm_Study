package BruteForce;

import java.util.Scanner;

//문제설명 : 피보나치수열 f(n) = f(n-1)+f(n-2)
//핵심 개념 : 재귀를 활용한 브루트포스
public class fib_10870 {
    public static int solution(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return solution(num - 1) + solution(num - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int result = solution(num);
        System.out.println(result);
    }
}
