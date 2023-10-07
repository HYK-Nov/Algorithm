import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(time);
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                sum += time[j];
            }
        }

        bw.write(Integer.toString(sum));

        bw.flush();
        bw.close();
    }
}