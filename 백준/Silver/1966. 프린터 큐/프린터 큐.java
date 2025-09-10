import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1966 - 프린터 큐
// 1. 큐의 가장 앞에 있는 문서의 중요도 확인
// 2. 나머지 문서들 중, 현재 문서보다 중요도가 높은 문서가 하나라도 있으면 앞 문서를 가장 뒤에 재배치, 아니면 인쇄
// 우선순위 큐 x => 우선순위가 낮은 것들은 다시 뒤에 넣어서 다시 돌리기가 애매함
// 출력 : 어떤 한 문서가 몇번째로 인쇄되는지
// 다시 & 실수
public class Main {
    static class Node {
        int idx;
        int important;
        public Node(int idx, int important) {
            this.idx = idx;
            this.important = important;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Queue<Node> q = new LinkedList<>();
            ArrayList<Integer> importantList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<N; i++) {
                int important = Integer.parseInt(st.nextToken());
                q.offer(new Node(i, important));
                importantList.add(important);
            }
            Integer[] importantArr = new Integer[importantList.size()];
            for (int i=0; i< importantList.size(); i++) {
                importantArr[i] = importantList.get(i);
            }
            Arrays.sort(importantArr, Collections.reverseOrder());
            int order = 1;
            while(true) {
                Node node = q.poll();
                if(node.idx == M && importantArr[order-1] == node.important) break;
                else if (importantArr[order-1] == node.important) order++; // 빼낼때만 순서 증가
                else q.offer(node);
            }
            sb.append(order).append("\n");
        }
        System.out.print(sb);
    }
}
