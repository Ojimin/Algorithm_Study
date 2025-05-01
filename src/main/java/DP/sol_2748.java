package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

//2748-피보나치수2
//bottom up 방식
public class sol_2748 {
    static int n;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n+1];
        arr[0]=0;
        arr[1]=1;
        fib(n);
        System.out.println(arr[n]);
    }
    //bottom up 방식
    public static long fib(int num) {
        for (int i=2;i<num+1; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[num];
    }
}
