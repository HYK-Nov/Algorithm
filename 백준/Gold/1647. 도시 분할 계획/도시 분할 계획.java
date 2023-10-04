import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int parent[];

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x < y) parent[y] = x;
        else parent[x] = y;

        return true;
    }

    public static int find(int x){
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] connection = new int[M][3];
        parent = IntStream.rangeClosed(0, N).toArray();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                connection[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(connection, Comparator.comparing(o->o[2]));

        int cost = 0;
        int lastValue = 0;
        for (int i = 0; i < M; i++) {
            if (find(connection[i][0]) != find(connection[i][1])){
                union(connection[i][0], connection[i][1]);
                cost += connection[i][2];
                lastValue = connection[i][2];
            }
        }
        cost -= lastValue;

        bw.write(Integer.toString(cost));

        bw.flush();
        bw.close();
    }
}