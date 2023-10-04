import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class Pair{
    int x, y, cost;
    Pair(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

public class Main {
    static int[] parent;

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

    public static int kruskal(PriorityQueue<Pair> pq){
        int cost = 0;

        Pair pair;
        while (!pq.isEmpty()){
            pair = pq.poll();
            if (union(pair.x, pair.y)) cost += pair.cost;
        }

        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int A, B, C;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(o -> o.cost));

        parent = IntStream.rangeClosed(0, V).toArray();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            pq.add(new Pair(A, B, C));
        }

        bw.write(Integer.toString(kruskal(pq)));

        bw.flush();
        bw.close();
    }
}