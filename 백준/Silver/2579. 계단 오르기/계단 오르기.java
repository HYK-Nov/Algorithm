import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N+1];
        int[] DP = new int[N+1];

        for (int i = 1; i <= N; i++) stair[i] = Integer.parseInt(br.readLine());
        DP[1] = stair[1];

        if (N < 2){
            bw.write(Integer.toString(DP[N]));
        }else {
            DP[2] = stair[2] + stair[1];
            for (int i = 3; i <= N; i++) {
                DP[i] = Math.max(DP[i-2] + stair[i] , DP[i-3]+stair[i]+stair[i-1]);
            }
            bw.write(Integer.toString(DP[N]));
        }

        bw.flush();
        bw.close();
    }
}