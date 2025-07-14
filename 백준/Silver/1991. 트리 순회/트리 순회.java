
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1991 - 트리 순회
// 출력 : 전위 순회, 중위 순회, 후위 순회한 결과 출력
// 항상 A가 루트노드가 되며, 자식 노드가 없는 경우 .으로 표현됨
// 다시
public class Main {
    public static class Node {
        char ch;
        Node prev;
        Node next;

        public Node(char ch, Node prev, Node next) {
            this.ch = ch;
            this.prev = prev;
            this.next = next;
        }
    }
    static int N;
    static Node head = new Node('A', null, null);
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            char prev = st.nextToken().charAt(0);
            char next = st.nextToken().charAt(0);
            insertNode(head, c, prev, next);
        }
        preOrder(head);
        sb.append("\n");
        inOrder(head);
        sb.append("\n");
        postOrder(head);
        sb.append("\n");
        System.out.println(sb);
    }

    public static void insertNode(Node tmp, char ch, char prev, char next) {
        if (tmp.ch == ch) {
            tmp.prev = (prev == '.' ? null : new Node(prev, null, null));
            tmp.next = (next == '.' ? null : new Node(next, null, null));
        } else {
            if (tmp.prev != null) insertNode(tmp.prev, ch, prev, next);
            if (tmp.next != null) insertNode(tmp.next, ch, prev, next);
        }
    }

    public static void preOrder(Node node) {
        if (node == null) return;
        sb.append(node.ch);
        preOrder(node.prev);
        preOrder(node.next);
    }

    public static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.prev);
        sb.append(node.ch);
        inOrder(node.next);
    }

    public static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.prev);
        postOrder(node.next);
        sb.append(node.ch);
    }
}
