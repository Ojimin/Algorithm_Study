package swea;

//package algorithm;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
////시작전 java 버전 싹 다 바꾸기
////swea1859-백만장자 프로젝트, 연속된 n일 동안 물건의 매매가를 예측하여 알고있음, 하루엥 최대 1만큼 구입가능, 판매는 얼마든지 가능
////최대 이익 출력
////구현?완탐?
////1.구매한 갯수, 구매한 금액 다 저장할 테이블 필요
////2.기존 구매한 금액보다 오늘날자의 금액이 더 높으면 일단 팔자.
////3.만약 다른 날짜의 금액이 더 높은거면, 그 떄 금액으로 다시 다 팔아버리기
////4.그게 아니면, 그냥 그전까지 샀던 거 팔아버리기
//public class swea_1859 {
//	static int N;
//	static long[] arr;
//	static long buyCnt, profit;
//	static boolean[] visited;
//
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		int T = Integer.parseInt(br.readLine());
//		StringBuilder sb = new StringBuilder();
//		profit=0;
//
//		for (int i=0;i<T; i++) {
//			N = Integer.parseInt(br.readLine());
//			arr=new int[N+1];
//			st = new StringTokenizer(br.readLine());
//			for (int j=1; j<=N; j++) {
//				arr[j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		profit=0;
//
//	}
//	public static void sol(int start, int maxIndex) {
//		visited = new boolean[N+1];
//		buyCnt=0;
//		for (int i=start;i<maxIndex;i++) {
//			visited[i] = true;
//			buyCnt++;
//		}
//		//이익 구하기
//		if(buyCnt!=0) {
//			int tmp_sum=0;
//			for (int i=start; i<maxIndex; i++) {
//				if(visited[i]) {
//					tmp_sum+=arr[i];
//				}
//			}
//			int temp = arr[maxIndex]*buyCnt - profit;
//			if (temp > profit)
//		}
//
//	}
//	public static int getMaxValIndex(int start) {
//		int temp = 0;
//		int result = 0;
//		for (int i=start; i<=N; i++) {
//			if (temp<arr[i]) {
//				temp = arr[i];
//				result = i;
//			}
//		}
//		return result;
//	}
//
//
//}
