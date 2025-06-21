import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int next;
        int dist;
        public Node(int next, int dist) {
            this.next = next;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[v+1];
        int[] dist = new int[v+1];
        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i<=v; i++){
            list.add(new ArrayList<>());
        }
        Arrays.fill(dist,Integer.MAX_VALUE);
        for(int i = 1; i<=e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b,c));
        }
        dist[k] = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(k,0));
        while(!priorityQueue.isEmpty()){
            Node cur = priorityQueue.poll();
            int node = cur.next;
            int di = cur.dist;
            if(visited[node]) continue;
            visited[node] = true;
            for(Node n : list.get(node)){
                if(dist[n.next] > di + n.dist){
                    dist[n.next] = di + n.dist;
                    priorityQueue.offer(new Node(n.next,dist[n.next]));
                }
            }
        }
        for(int i = 1; i<=v; i++){
            if(dist[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(dist[i]);
            }
        }
    }
}