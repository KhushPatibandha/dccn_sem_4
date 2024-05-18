import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v = sc.nextInt();
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        ArrayList<Integer> pathArray = new ArrayList<>();

        for(int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < v; i++) {
            System.out.print("Enter the number of adjacent vertices for vertex " + i + ": ");
            int adjNum = sc.nextInt();

            for(int j = 0; j < adjNum; j++) {
                System.out.print("Enter the adjacent node for vertex " + i + ": ");
                int adjNode = sc.nextInt();
                System.out.print("Enter the weight between " + i + " and " + adjNode + ": ");
                int adjWeight = sc.nextInt();

                if(adj.get(i).contains(List.of(adjNode, adjWeight))) {
                    continue;
                }
                adj.get(i).add(new ArrayList<>(List.of(adjNode, adjWeight)));
                adj.get(adjNode).add(new ArrayList<>(List.of(i, adjWeight)));
            }

            // For Logging purposes only
            System.out.println("At index " + i + ": " + adj.get(i));
        }

        System.out.print("Enter the source vertex: ");
        int source = sc.nextInt();

        System.out.print("Enter the destination vertex: ");
        int destination = sc.nextInt();

        int[] result = dijkstra(v, adj, pathArray, source, destination);

        System.out.println("Shortest distance from source vertex " + source + " to all other vertices: " + Arrays.toString(result));

        System.out.println("Shortest path from source vertex " + source + " to destination vertex " + destination + ": " + pathArray);

        sc.close();
    }

    public static int[] dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, ArrayList<Integer> pathArray, int s, int d) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);

        int[] distance = new int[v];
        int[] parent = new int[v];
        Arrays.fill(parent, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[s] = 0;
        pq.add(new Pair(0, s));

        while(!pq.isEmpty()) {
            int currDistance = pq.peek().distance;
            int currNode = pq.peek().node;
            pq.remove();

            for(int i = 0; i < adj.get(currNode).size(); i++) {
                int adjNode = adj.get(currNode).get(i).get(0);
                int edgeWeight = adj.get(currNode).get(i).get(1);

                if(currDistance + edgeWeight < distance[adjNode]) {
                    parent[adjNode] = currNode;
                    distance[adjNode] = currDistance + edgeWeight;
                    pq.add(new Pair(distance[adjNode], adjNode));
                }
            }
        }

        getPath(s, d, parent, pathArray);

        return distance;
    }

    public static void getPath(int source, int destination, int[] parent, ArrayList<Integer> pathArray) {
        for(int v = destination; v != -1; v = parent[v]) {
            pathArray.add(0, v);
        }
    }
}

class Pair {
    int distance;
    int node;
    public Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}
