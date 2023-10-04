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

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            parent = IntStream.rangeClosed(0, m).toArray();
            PriorityQueue<Pair> connection = new PriorityQueue<>(Comparator.comparing(o->o.cost));

            int x, y, cost;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());

                connection.add(new Pair(x, y, cost));
            }

            int maxCost = 0;
            for(Pair p : connection) maxCost += p.cost;

            int minCost = kruskal(connection);

            bw.write(Integer.toString(maxCost-minCost));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}