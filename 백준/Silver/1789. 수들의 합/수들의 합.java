import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long S = Long.parseLong(st.nextToken());
        long sum = 0;
        long count = 1;

        for (long i = 1; i <= S; i++) {
            sum += i;
            if (sum > S) break;
            count++;
        }

        bw.write(Long.toString(count-1));

        bw.flush();
        bw.close();
    }
}