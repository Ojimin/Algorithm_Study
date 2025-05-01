package math;

import java.math.BigInteger;

//최대공약수(GCD) & 최소공배수(LCM)
public class GCD_LCM {
    public static void main(String[] args) {
        // GCD 방법 1 - BigInteger GCD 함수 사용
        BigInteger a = new BigInteger("15");
        BigInteger b = new BigInteger("5");

        BigInteger c= a.gcd(b);
        System.out.println(c);

        int gcd = getGCD(15, 5);
        System.out.println(gcd);

        //LCM 방법 1 - GCD 활용
        int n1 = 3;
        int n2 = 15;
        int lcm = (n1 * n2) / getGCD(n1, n2);
        System.out.println(lcm);
    }
    //GCD 방법 2
    public static int getGCD(int a, int b) {
        if (b== 0) {
            return a;
        } else {
            return getGCD(b, a%b);
        }
    }
}
