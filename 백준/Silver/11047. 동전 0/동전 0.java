import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] coin = new int[input[0]];
		int count = 0;
		
		for (int i = 0; i < input[0]; i++) coin[i] = Integer.parseInt(br.readLine());
		for (int i = input[0]-1; i >= 0; i--) {
			if(coin[i] > input[1]) continue;
			else if(input[1] == 1) {
				count++;
				break;
			}
			else if(input[1] / coin[i] > 0) {
				count += input[1] / coin[i];
				input[1] %= coin[i];
			}
		}
		
		System.out.println(count);
		
		bw.flush();
		bw.close();
	}
}