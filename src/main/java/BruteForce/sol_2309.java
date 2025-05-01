package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2309-일곱 난쟁이
//출력 : 키 합 100이 되는 일곱 난쟁이 출력
//완탐
//오름차순으로 먼저 정렬하고 작은 것부터 먼저 더해버리기
//9개중 2개를 제외한것, 조합 문제?
public class sol_2309 {
    static int[] arr = new int[9];
    static int[] check = new int[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        //정렬
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        //키합 100 구하기
        for (int i=0; i<8;i++) {
            for (int j=i+1; j<9; j++) {
                boolean istrue = sum(i,j);
                if (istrue) {
                    print();
                    return ;
                }
            }

        }
    }
    public static boolean sum(int a, int b) {
        int sum=0;
        check = new int[9];
        for (int i=0;i<9;i++) {
            if(i==a || i==b) {
                continue;
            }
            check[i]=1;
            sum+=arr[i];
        }
        if (sum==100) {
            return true;
        }else {
            return false;
        }
    }

    public static void print() {
        StringBuilder sb= new StringBuilder();
        for (int i=0; i<9; i++) {
            if (check[i]==1) {
                sb.append(arr[i] + "\n");
            }
        }
        System.out.print(sb);
    }
}
