package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 8911 - 거북이
// F - 한눈금 앞, B-한눈금 뒤, L-왼쪽 90도, R-오른쪽90도 + L&R은 방향만 바꿈
// 출력: 거북이가 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형의 넓이 - 선분이라면 0
// 주의 : 선분(축) 위에만 움직이면 직사각형 X => 모든 좌표가 x=0 || y=0인 상태
// 직사각형 넓이 구하기 :
public class nonsol_8911 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {

        }

    }

}
