package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

//3052-나머지,
public class sol_3052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> set = new HashSet<>();

        for (int i=0; i<10; i++) {
            int tmp = Integer.parseInt(br.readLine());
            set.add(tmp%42);
        }
        System.out.println(set.size());
    }
}
