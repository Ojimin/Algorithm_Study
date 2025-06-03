package Simulation;

// 8911 - 거북이, f: 한 눈금 앞으로, b:한 눈금 뒤로, l : 왼쪽으로 90도 회전, r: 오른쪽으로 90도 회전
// x, y축 평행방향만 이동
// 출력 : 거북이가 이동한 영역을 모두 포함하는 가장 작은 직사각형 넓이
// 넓이 = 가로(왼~오) * 세로(위~아래)
// 방향전환 & 앞으로/뒤로
// 거북이가 다 이동한 후에 가장 왼-오 차가 가로, 가장 위-아래 차가 세로길이 => 두개를 곱함

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class answer_8911 {
    static int[][] map;
    static int top, bottom, left, right, d;
    static int hight, width; // 가로 세로

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String[] commands = new String[N];

        for (int i = 0; i < N; i++) {
            top = 0;
            bottom = 0;
            left = 0;
            right = 0;
            d = 0; // 0 : 위, 1: 왼쪽, 2 : 밑, 3 : 오른쪽
            hight = width = 0;
            commands[i] = br.readLine();
            tuttle(commands[i]);
            sb.append(countArea()).append("\n");
        }
        System.out.println(sb); // 결과 출력
    }

    // 거북이가 움직인다.
    public static void tuttle(String c) {
        char[] command = c.toCharArray();
        for (int i = 0; i < command.length; i++) {
            char temp = command[i];
            if (temp == 'F' || temp == 'B') {
                // 명령어가 F와 B 일때만 움직인다
                move(command[i]);
            } else if (temp == 'L' || temp == 'R') {
                // 명령어가 L 과 R 일때는 방향만 바꿔준다.
                changeD(command[i]);
            }
        }
    }

    // 직사각형 크기 구하기
    public static int countArea() {
        return (Math.abs(top - bottom)) * (Math.abs(left - right));
    }

    // 방향 바꿔주기
    public static void changeD(char c) {
        if (c == 'L') {
            d++;
        } else {
            d--;
        }

        // 0 : 위, 1: 왼쪽, 2 : 밑, 3 : 오른쪽 범위 넘어가면 수정 해주기
        if (d == 4) {
            d = 0;
        } else if (d == -1) {
            d = 3;
        }
    }

    public static void move(char c) {
        switch (d) {
            case 0: // 위 보고 있음
                if (c == 'F') {
                    hight++;
                    top = Math.max(top, hight);
                } else {
                    hight--;
                    bottom = Math.min(bottom, hight);
                }
                break;
            case 1: // 왼쪽 보고 있음
                if (c == 'F') {
                    width++;
                    left = Math.max(left, width);
                } else {
                    width--;
                    right = Math.min(right, width);
                }
                break;
            case 2: // 밑에 보고 있음
                if (c == 'F') {
                    hight--;
                    bottom = Math.min(bottom, hight);
                } else {
                    hight++;
                    top = Math.max(top, hight);
                }
                break;
            case 3: // 오른쪽 보고 있음
                if (c == 'F') {
                    width--;
                    right = Math.min(right, width);
                } else {
                    width++;
                    left = Math.max(left, width);
                }
                break;
        }
    }
}
