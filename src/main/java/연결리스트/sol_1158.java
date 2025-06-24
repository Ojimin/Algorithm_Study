package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

// 1158 - 요세푸스 문제
// 1~N번까지 사람 중 K번째 사람 제거 => N명의 사람이 모두 제거될때까지 반복
// 출력 : 원에서 사람이 제거되는 순서 : (N,K)-요세푸스 순열
// <3, 5, 1, 2> 이러한 형태로 출력해야함
public class sol_1158 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i<N; i++) list.add(i+1);
        ListIterator<Integer> listIterator =  list.listIterator();
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int idx = 0;
        while(!list.isEmpty()) {
            if (!listIterator.hasNext()) listIterator = list.listIterator(0);
            int num = listIterator.next();
            idx++;
            if (idx == K) {
                listIterator.remove();
                sb.append(num +", ");
                idx = 0;
            }
        }
        sb.replace(sb.length()-2,sb.length(), ">");
        System.out.println(sb);
    }
}
