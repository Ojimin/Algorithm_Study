package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//4779-칸토어 집합,
// 칸토어 집합: 0과 1 사이의 실수로 이루어진 집합으로 구간 0,1에서 시작해서 각 구간을 3등분하여 가운데 구간을 반복적으로 제외하는 방식으로 만듦
// -가 3^n개 있는 문자열에서 시작 -> 문자열 3등분 한뒤, 가운데 문자열을 공백으로 바꿈 -> 각 선을 다시 3등분하고 가운데 공백, 모든 선의 길이가 1일 될때가지 반복
// 출력 : 칸토어 집합의 근사를 출력함, 마지막 과정이 끝난 후 결과를 출력
// 다시 분할 -> 또 분할, 분할정복
//1.3^n개만큼 - 만들고 3등분,
//2.가운데 부분 인덱스 빼고 나머지 0~x, y~z까지 다시 재귀 호출
//3. 각 선의 길이가 1이면 멈추고(기준: 옆에 더 선이 있는지 없는지 체킹) 리턴
public class sol_4779 {
    static char[] set;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder sb = new StringBuilder();
        while((input=br.readLine())!=null) {  // eof 처리
            int n=Integer.parseInt(input);
            set=new char[(int)Math.pow(3,n)];
            for (int i=0; i<Math.pow(3,n); i++) {
                set[i] = '-';
            }
            makeSet(0,set.length-1);
            for (int i=0; i<set.length; i++) {
                sb.append(set[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void makeSet(int start, int end) {
        if((end-start)<=1) return;
        //3등분 쪼개고 가운데부분 공백으로 바꾸기
        int border = (end-start+1)/3;
        for(int i=border+start; i<2*border+start; i++) {
            set[i]=' ';
        }
        if (border==1) return;
        makeSet(start, border+start-1);
        makeSet(2*border+start, end);
    }
}
