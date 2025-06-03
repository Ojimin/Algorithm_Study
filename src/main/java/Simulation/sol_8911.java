package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 8911 - 거북이
// F - 한눈금 앞, B-한눈금 뒤, L-왼쪽 90도, R-오른쪽90도 + L&R은 방향만 바꿈
// 출력: 거북이가 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형의 넓이 - 선분이라면 0
// 주의 : 선분(축) 위에만 움직이면 직사각형 X => 모든 좌표가 x=0 || y=0인 상태
// 직사각형 넓이 구하기 :
// L, R 방향회전 : dx, dy 설정
public class sol_8911 {
    static int T;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            String str = br.readLine();
            int result = move(str);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    // 거북이 이동
    public static int move(String str) {
        Node now = new Node(0, 0);
        int index = 0; // 현재 방향
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(now);
        for (char c : str.toCharArray()) {
            int x, y;
            switch (c) {
                case 'F':
                    x = now.x + dx[index];
                    y = now.y + dy[index];
                    now = new Node(x, y);
                    nodes.add(now);
                    break;
                case 'B':
                    x = now.x - dx[index];
                    y = now.y - dy[index];
                    now = new Node(x, y);
                    nodes.add(now);
                    break;
                case 'R':
                    index = (index + 1) % 4;
                    break;
                case 'L':
                    if (index == 0) index = 3;
                    else index--;
                    break;
            }
        }
        return getOblong(nodes);
    }
    // 직사각형 넓이 구하기 - 최대 x, 최대 y, 최소 x, 최소 y 구하기
    public static int getOblong(ArrayList<Node> nodes) {
        int minX = nodes.get(0).x;
        int minY= nodes.get(0).y;
        int maxX = minX;
        int maxY = minY;
        int w = 0;
        int h = 0;
        for (Node node : nodes) {
            int x = node.x;
            int y = node.y;
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        w = maxX - minX;
        h = maxY - minY;
        return w * h;
    }


}
