package BruteForce;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

//문제 설명 :카드놓기 - n장의 카드에 적힌 숫자가 주여졌을 때 k개를 선택해서 만들 수 있는 정수의 개수 출력
//핵심 개념 : 중복없는 순열
public class permutation_wo_duplicates_5568 {
    static int n, k;
    static int[] nums;
    static List<String> arr; //만들수있는 정수 리스트
    static String[] temp;//임시로 정수합치는 배열
    static boolean[] visited;//중복되지 않기 위해 방문처리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        nums = new int[n];

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums[i] = num;
        }

        arr = new ArrayList<String>();
        temp = new String[k];
        visited = new boolean[n];
        permutation(0);
        arr = arr.stream().distinct().collect(Collectors.toList());//중복제거
        System.out.println(arr.size());
    }

    public static void permutation(int cnt) {
        if (cnt == k) { //k개만큼 뽑았으면 정수 리스트에 담기
            String str = "";
            for (int i = 0; i < k; i++) {
                str += temp[i];
            }
            arr.add(str);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {//방문한 적이 없으면(중복되는 수를 뽑지 않기위해)
                visited[i] = true; //방문체크
                temp[cnt] = Integer.toString(nums[i]); //임시문자배열에 넣기
                permutation(cnt + 1);
                visited[i] = false; //다음순열을 위해 false로 처리 ex)012(여기서 2는 방문처리 fasle로 변경),013..021(여기서 다시 2를 뽑을 수 있음)...
            }
        }
    }
}
