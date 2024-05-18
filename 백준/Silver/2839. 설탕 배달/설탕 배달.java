import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int sum = 0;

		for (int i = N/5; i >= 0; i--) {
			if((N - 5*i) % 3 == 0) {
				sum += i + ((N - 5*i) / 3);
				break;
			}
		}
		
		if(sum == 0) sum = -1;

		bw.write(Integer.toString(sum));

		bw.flush();
		bw.close();
	}
}