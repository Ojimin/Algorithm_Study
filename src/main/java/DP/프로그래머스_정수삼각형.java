package DP;

public class 프로그래머스_정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = Integer.MIN_VALUE;
        for (int i=0; i<triangle.length; i++) {
            for (int j=0; j<triangle[i].length; j++) {
                if (i-1 >=0 && j-1 >=0) {
                    if (j<triangle[i-1].length) triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                    else triangle[i][j] += triangle[i-1][j-1];
                } else if(i-1>=0) {
                    triangle[i][j] += triangle[i-1][j];
                }
            }
        }
        for (int i=0; i<triangle[triangle.length-1].length; i++) {
            if (answer < triangle[triangle.length-1][i]) {
                answer = triangle[triangle.length-1][i];
            }
        }
        return answer;
    }
}
