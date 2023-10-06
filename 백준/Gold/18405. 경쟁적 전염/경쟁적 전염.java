import java.io.*;
import java.util.*;

class Pair{
    int x; int y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static Queue<Pair> queue = new LinkedList<>();
    static int[][] virus;
    static boolean[][] check;

    public static void BFS(int N, int K, int S){
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int size; int curX; int curY;
        Pair pair;

        while (S > 0){
            size = queue.size();

            for (int i = 0; i < size; i++) {
                pair = queue.remove();
                for (int j = 0; j < 4; j++) {
                    curX = pair.x + dirX[j];
                    curY = pair.y + dirY[j];

                    if(curX >= 0 && curX < N && curY >= 0 && curY < N){
                        if (virus[curY][curX] == 0 && !check[curY][curX]){
                            queue.add(new Pair(curX, curY));
                            check[curY][curX] = true;
                            virus[curY][curX] = virus[pair.y][pair.x];
                        }
                    }
                }
            }
            S--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        check = new boolean[N][N];
        virus = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                virus[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (virus[j][k] == i) {
                        queue.add(new Pair(k, j));
                        check[j][k] = true;
                    }
                }
            }
        }


        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        if(queue.size() > 0) BFS(N, K, S);

        bw.write(Integer.toString(virus[X-1][Y-1]));

        bw.flush();
        bw.close();
    }
}