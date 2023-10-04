import java.io.*;
import java.util.*;

class Pair {
    int x, y, d;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Pair(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int N, M, D;
    static int[][] map;
    static int enemy = 0;
    static int maxCount = 0;

    public static void archerPosition(int dept) {
        if (dept == 3) {
            maxCount = Math.max(castleDefense(), maxCount);
            return;
        }
        for (int i = 0; i < M; i++) {
            if (map[N][i] == 0) {
                map[N][i] = 2;
                archerPosition(dept + 1);
                map[N][i] = 0;
            }
        }
    }

    public static int castleDefense() {
        int[][] copyMap = mapCopy();
        List<Pair> archer = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            if (copyMap[N][i] == 2) archer.add(new Pair(i, N));
        }
        PriorityQueue<Pair> target = new PriorityQueue<>((o1, o2) -> {
            if (o1.d < o2.d) return -1;
            else if (o1.d > o2.d) return 1;
            else {
                if (o1.x < o2.x) return -1;
                else if (o1.x > o2.x) return 1;
            }
            return 0;
        });
        Queue<Pair> attack = new LinkedList<>();
        int copyEnemy = enemy;
        int count = 0;

        Pair cur;
        while (copyEnemy > 0){
            for (int i = 0; i < 3; i++) {
                for (int j = N-1; j >= 0; j--) {
                    for (int k = 0; k < M; k++) {
                        if (copyMap[j][k] == 1){
                            int distance = distance(j, k, archer.get(i).y, archer.get(i).x);
                            if (distance <= D) {
                                target.add(new Pair(k, j, distance));
                            }
                        }
                    }
                }
                if (!target.isEmpty()) attack.add(target.poll());
                target.clear();
            }

            while (!attack.isEmpty()) {
                cur = attack.poll();
                if (copyMap[cur.y][cur.x] == 1){
                    copyMap[cur.y][cur.x] = 0;
                    count++;
                    copyEnemy--;
                }
            }

            for (int i = N-1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (copyMap[i][j] == 1 ){
                        if (i+1 < N){
                            copyMap[i][j] = 0;
                            copyMap[i+1][j] = 1;
                        }else{
                            copyMap[i][j] = 0;
                            copyEnemy--;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static int distance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public static int[][] mapCopy(){
        int[][] copy = new int[N+1][M];

        for (int i = 0; i <= N; i++) {
            copy[i] = map[i].clone();
        }

        return copy;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) enemy++;
            }
        }

        archerPosition(0);

        bw.write(Integer.toString(maxCount));

        bw.flush();
        bw.close();
    }
}