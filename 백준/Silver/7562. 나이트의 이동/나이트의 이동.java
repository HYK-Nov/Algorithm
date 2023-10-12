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
    static int I;
    static int[] dirX = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dirY = {-2, -1, 1, 2, 2, 1, -1, -2};
    static Queue<Pair> queue = new LinkedList<>();
    static StringBuffer sb = new StringBuffer();

    public static void BFS(int[] knight, int[] moveKnight){
        boolean[][] check = new boolean[I][I];
        queue.clear();
        queue.add(new Pair(knight[0], knight[1]));
        check[knight[1]][knight[0]] = true;

        int curX = knight[0]; int curY = knight[1]; int count = 0; int size = 0;
        Pair coords;
        while (!queue.isEmpty()){
            size = queue.size();

            for (int i = 0; i < size; i++) {
                coords = queue.poll();

                for (int j = 0; j < 8; j++) {
                    if(curX == moveKnight[0] && curY == moveKnight[1]) {
                        sb.append(count).append("\n");
                        return;
                    }else if((curX = coords.x + dirX[j]) == moveKnight[0] && (curY = coords.y + dirY[j]) == moveKnight[1]){
                        sb.append(count+1).append("\n");
                        return;
                    }

                    curX = coords.x + dirX[j];
                    curY = coords.y + dirY[j];

                    if(curX >= 0 && curX < I && curY >= 0 && curY < I){
                        if(!check[curY][curX]){
                            queue.add(new Pair(curX, curY));
                            check[curY][curX] = true;
                        }
                    }
                }
            }
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[] knight; int[] moveKnight;

        for (int i = 0; i < T; i++) {
            I = Integer.parseInt(br.readLine());

            knight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            moveKnight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            BFS(knight, moveKnight);
        }
        System.out.println(sb);

        bw.flush();
        bw.close();
    }
}