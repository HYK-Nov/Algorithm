import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < S.length(); i++) {
            for (int j = i; j < S.length(); j++) {
                hashSet.add(S.substring(i,j+1));
            }
        }

        bw.write(Integer.toString(hashSet.size()));

        bw.flush();
        bw.close();
    }
}