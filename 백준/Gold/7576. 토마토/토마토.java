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
    static int M;
    static int N;
    static int[][] tomato;
    static boolean[][] check;
    static Queue<Pair> queue = new LinkedList<>();
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    static int dayCount = 0;

    public static void BFS(){
        int curX; int curY;
        int size;
        boolean hasNearTomato;
        Pair coords;

        while (!queue.isEmpty()){
            hasNearTomato = false;
            size = queue.size();

            for (int i = 0; i < size; i++) {
                coords = queue.remove();
                check[coords.y][coords.x] = true;

                for (int j = 0; j < 4; j++) {
                    curX = dirX[j] + coords.x;
                    curY = dirY[j] + coords.y;

                    if(curX >= 0 && curX < M && curY >= 0 && curY < N){
                        if(tomato[curY][curX] == 0 && !check[curY][curX]){
                            hasNearTomato = true;
                            queue.add(new Pair(curX, curY));
                            check[curY][curX] = true;
                            tomato[curY][curX] = 1;
                        }
                    }
                }
            }
            if(hasNearTomato) dayCount++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tomato = new int[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tomato[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tomato[i][j] == 1 && !check[i][j]){
                    queue.add(new Pair(j, i));
                }
            }
        }

        BFS();

        outer: for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tomato[i][j] == 0) {
                    dayCount = -1;
                    break outer;
                }
            }
        }

        if (dayCount == 0) bw.write("0");
        else bw.write(Integer.toString(dayCount));

        bw.flush();
        bw.close();
    }
}