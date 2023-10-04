import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0]; int K = input[1];
        Deque<Integer> deque = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(LinkedList::new));
        Deque<Integer> result = new LinkedList<>();

        while (!deque.isEmpty()){
            for(int i = 1; i < K; i++) deque.add(deque.pollFirst());
            result.add(deque.pollFirst());
        }

        bw.write(result.toString().replace("[", "<").replace("]",">"));

        bw.flush();
        bw.close();
    }
}