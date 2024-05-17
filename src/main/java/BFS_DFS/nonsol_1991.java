package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

//1991-트리순회
//트리 구현, 순회 순서에 따라 구현
//다시
public class nonsol_1991 {
    static int N;
    static Node[] tree;
    static Node head = new Node('A', null, null); //처음 루트는 A이므로
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new Node[N+1];

        for (int i =0;i<N;i++) {//본인-왼-오
            st = new StringTokenizer(br.readLine());
            char parentValue = st.nextToken().charAt(0);// nextToken() 메서드로 토큰을 하나씩 꺼내오면 StringTokenizer객체에는 해당 토큰이 사라진다!!
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);
            insertNode(head, parentValue, leftValue, rightValue);
        }
        preorder(head);
        System.out.println();
        inorder(head);
        System.out.println();
        postorder(head);
        System.out.println();
    }
    //전위순회,루트부터
    public static void preorder(Node node) {
        if (node==null) return; //더이상 값이 없을떄까지
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }
    public static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }
    public static void postorder(Node node) {
        if(node==null) return;;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }
    public static void insertNode(Node tmp, char root, char left, char right) {
        if (tmp.value == root) { //루트 노드이면
            tmp.left = (left == '.' ? null : new Node(left,null,null));
            tmp.right = (right == '.' ? null : new Node(right,null,null));
        }
        else { //그게 아니면
            if(tmp.left != null) insertNode(tmp.left, root, left, right);
            if(tmp.right != null) insertNode(tmp.right, root, left, right);
        }
    }
    static class Node {
        char value;
        Node left;
        Node right;
        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
