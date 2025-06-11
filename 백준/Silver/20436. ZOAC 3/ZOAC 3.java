import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static char A;
	static char B;
	static int dis;
	
	static char[][] leftarr = {{'q','w','e','r','t'},{'a','s','d','f','g'},{'z','x','c','v'}};
	static char[][] rightarr = {{'.','y','u','i','o','p'},{'.','h','j','k','l'},{'b','n','m'}};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stf = new StringTokenizer(br.readLine());
		A = (stf.nextToken().charAt(0));
		B = (stf.nextToken().charAt(0));
		
		int leftstart_y = -1;
		int leftstart_x = -1;
		int rightstart_y = -1;
		int rightstart_x = -1;
		
		String inputstr = br.readLine();
		
		//왼쪽 시작 인덱스 기억
		for(int i = 0; i < leftarr.length; i++) {
			for(int j = 0; j < leftarr[i].length; j++) {
				char c = leftarr[i][j];
				if(A==c) {
					leftstart_y = i;
					leftstart_x = j;
				}
			}
		}
		//오른쪽 시작 인덱스 기억
		for(int i = 0; i < rightarr.length; i++) {
			for(int j = 0; j < rightarr[i].length; j++) {
				char c = rightarr[i][j];
				if(B==c) {
					rightstart_y = i;
					rightstart_x = j;
				}
			}
		}
		
		//실제 입력
		for(int k = 0; k < inputstr.length(); k++) {
			char cur = inputstr.charAt(k);
			
			int leftmove_y = -1;
			int leftmove_x = -1;
			int rightmove_y = -1;
			int rightmove_x = -1;
			
			//현재 입력받은 문자가 왼쪽 손에 있다면
			for(int i = 0; i < leftarr.length; i++) {
				for(int j = 0; j < leftarr[i].length; j++) {
					char c = leftarr[i][j];
					if(cur==c) {
						leftmove_y = i;
						leftmove_x = j;
					}
				}
			}
			
			//왼쪽 손으로 누른다면
			if(leftmove_y!=-1 && leftmove_x!=-1) {
				dis += Math.abs(leftstart_y-leftmove_y) + Math.abs(leftstart_x-leftmove_x);
				dis++;
				
				//시작인덱스 갱신
				leftstart_y = leftmove_y;
				leftstart_x = leftmove_x;
				continue;
			}		
			
			//현재 입력받은 문자가 오른쪽 손에 있다면
			for(int i = 0; i < rightarr.length; i++) {
				for(int j = 0; j < rightarr[i].length; j++) {
					char c = rightarr[i][j];
					if(cur==c) {
						rightmove_y = i;
						rightmove_x = j;
					}
				}
			}
			
			
			//오른쪽 손으로 누른다면
			if(rightmove_y!=-1 && rightmove_x!=-1) {
				dis += Math.abs(rightstart_y-rightmove_y) + Math.abs(rightstart_x-rightmove_x);
				dis++;
				
				//시작인덱스 갱신
				rightstart_y = rightmove_y;
				rightstart_x = rightmove_x;
				continue;
			}	
			
			
		}
		
		
        
        System.out.println(dis);
               
        

	}

}